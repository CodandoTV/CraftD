#!/usr/bin/env python3
"""
Generates JUnit4 + MockK unit tests for Kotlin files using the Claude API.

Usage:
  python generate_tests.py <file1.kt> [file2.kt ...]

Or via environment variable:
  CHANGED_FILES="file1.kt\nfile2.kt" python generate_tests.py
"""

import os
import sys
import re
import anthropic


def extract_package(content: str) -> str:
    match = re.search(r"^package\s+([\w.]+)", content, re.MULTILINE)
    return match.group(1) if match else ""


def source_to_test_path(source_path: str) -> str:
    """
    Maps a production source path to its expected test path.

    Examples:
      src/commonMain/kotlin/com/...Foo.kt  ->  src/test/java/com/...FooTest.kt
      src/androidMain/kotlin/com/...Bar.kt ->  src/test/java/com/...BarTest.kt
    """
    path = re.sub(
        r"src/(common|android)Main/kotlin/",
        "src/test/java/",
        source_path,
    )
    return path[:-3] + "Test.kt"


def file_name_without_ext(path: str) -> str:
    return os.path.basename(path).replace(".kt", "")


def build_prompt(file_path: str, content: str, package_name: str) -> str:
    class_name = file_name_without_ext(file_path)
    return f"""You are an expert Kotlin developer specializing in unit testing for Kotlin Multiplatform (KMP) projects.

## Project context
- **CraftD** is a Server Driven UI library for Android/KMP.
- The server returns a list of `SimpleProperties` (key + value as `JsonElement`).
- The lib renders native components dynamically via `CraftDBuilder`.
- Stack: Kotlin, JUnit4, MockK, kotlinx.serialization, Jetpack Compose.

## Your task
Generate comprehensive unit tests for the Kotlin source file below.

## Requirements
- Framework: **JUnit4** (`@RunWith(JUnit4::class)`) + **MockK** (`io.mockk.*`)
- Package: `{package_name}`
- Test class name: `{class_name}Test`
- Test method style: backtick notation — e.g. `` `given null JsonElement when convertToElement then returns null`() ``
- **Data classes**: test construction with all params, default values, `copy()`, `equals`/`hashCode`.
- **Extension functions with JsonElement**: test null input, valid JSON deserialization, malformed/wrong-type JSON (expect `null` return).
- **DiffUtil callbacks**: test `areItemsTheSame` (same key, different key) and `areContentsTheSame` (equal value, different value, `AbstractMap` comparison edge case).
- **Enums**: verify all enum constant names exist using `enumValueOf`.
- **Mapper functions** (`toSimpleProperties`, `toListSimpleProperties`): test with sample data, empty list, and null-value fields.
- Do **not** include explanations or markdown fences.
- Output **only** the complete Kotlin test file, starting with `package {package_name}`.

## Source file
Path: `{file_path}`

```kotlin
{content}
```
"""


def call_claude(client: anthropic.Anthropic, prompt: str) -> str:
    message = client.messages.create(
        model="claude-opus-4-6",
        max_tokens=4096,
        messages=[{"role": "user", "content": prompt}],
    )
    return message.content[0].text


def write_github_output(key: str, value: str) -> None:
    output_file = os.environ.get("GITHUB_OUTPUT", "")
    if not output_file:
        return
    with open(output_file, "a") as f:
        if "\n" in value:
            f.write(f"{key}<<EOF\n{value}\nEOF\n")
        else:
            f.write(f"{key}={value}\n")


def write_step_summary(lines: list) -> None:
    summary_file = os.environ.get("GITHUB_STEP_SUMMARY", "")
    if not summary_file:
        return
    with open(summary_file, "a") as f:
        f.write("\n".join(lines) + "\n")


def main() -> None:
    api_key = os.environ.get("ANTHROPIC_API_KEY")
    if not api_key:
        print("ERROR: ANTHROPIC_API_KEY is not set.", file=sys.stderr)
        sys.exit(1)

    # Collect files from CLI args or CHANGED_FILES env var
    if len(sys.argv) > 1:
        raw = " ".join(sys.argv[1:])
        files = [f.strip() for f in re.split(r"[\s]+", raw) if f.strip()]
    else:
        files = [
            f.strip()
            for f in os.environ.get("CHANGED_FILES", "").splitlines()
            if f.strip()
        ]

    if not files:
        print("No Kotlin files to process.")
        write_github_output("generated_count", "0")
        sys.exit(0)

    client = anthropic.Anthropic(api_key=api_key)
    generated = []

    for file_path in files:
        if not os.path.isfile(file_path):
            print(f"[SKIP] File not found: {file_path}")
            continue

        test_path = source_to_test_path(file_path)

        if os.path.isfile(test_path):
            print(f"[SKIP] Test already exists: {test_path}")
            continue

        print(f"[GEN]  {file_path}")
        print(f"   ->  {test_path}")

        with open(file_path, "r", encoding="utf-8") as f:
            content = f.read()

        package_name = extract_package(content)
        if not package_name:
            print(f"[WARN] Could not extract package from {file_path}, skipping.")
            continue

        try:
            prompt = build_prompt(file_path, content, package_name)
            test_content = call_claude(client, prompt)

            os.makedirs(os.path.dirname(test_path), exist_ok=True)
            with open(test_path, "w", encoding="utf-8") as f:
                f.write(test_content)

            generated.append(
                {
                    "source": file_path,
                    "test": test_path,
                    "test_name": file_name_without_ext(file_path) + "Test.kt",
                }
            )
            print(f"[OK]   Written: {test_path}\n")

        except Exception as exc:
            print(f"[ERROR] Failed to generate test for {file_path}: {exc}", file=sys.stderr)

    count = len(generated)
    print(f"\nDone. Generated {count} test file(s).")

    write_github_output("generated_count", str(count))

    if generated:
        test_files = "\n".join(item["test"] for item in generated)
        covered_names = ", ".join(item["test_name"] for item in generated)
        write_github_output("test_files", test_files)
        write_github_output("covered_names", covered_names)

        summary_lines = [
            "## Auto-generated Unit Tests",
            "",
            "| Source file | Generated test |",
            "| --- | --- |",
        ]
        for item in generated:
            summary_lines.append(f"| `{item['source']}` | `{item['test']}` |")
        write_step_summary(summary_lines)


if __name__ == "__main__":
    main()
