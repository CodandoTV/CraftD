# CraftD — Initializer

Read this file at the start of every session before doing anything else.

---

## What is CraftD

CraftD is a **Server Driven UI** multiplatform library. The server decides which components to render and how; the client (app) only executes. Supports:

- Android Compose (`android_kmp/craftd-compose`)
- Android View System / XML (`android_kmp/craftd-xml`)
- KMP Compose Multiplatform (`android_kmp/craftd-compose` via commonMain)
- iOS SwiftUI (`ios/craftd-swiftui`)
- Flutter (`flutter/craftd_widget`)

## Module Structure

```
android_kmp/
  craftd-core/          # shared models and abstractions (KMP)
    commonMain/         # code shared across platforms
    androidMain/        # Android-specific implementations
  craftd-compose/       # Compose / KMP implementation
  craftd-xml/           # View System (XML) implementation
  app-sample-android/   # Android sample app
  app-sample-cmp/       # KMP Compose sample app
  build-logic/          # shared build configuration

ios/craftd-swiftui/     # iOS library (Swift Package + CocoaPods)
flutter/craftd_widget/  # Flutter library (pub.dev)
docs/                   # site documentation (MkDocs)
```

Full module dependency graph → `mcp-local/module-graph.md`

---

## Loading Platform Context

Before starting any task, identify the platform and read the corresponding file:

- Android/KMP → `mcp-local/instructions/android.md`
- iOS → `mcp-local/instructions/ios.md`
- Flutter → `mcp-local/instructions/flutter.md`

Ignore folders outside the platform of the current task:
- Android/KMP tasks → ignore `ios/` and `flutter/`
- iOS tasks → ignore `android_kmp/` and `flutter/`
- Flutter tasks → ignore `android_kmp/` and `ios/`

When generating a `proposal.md` via `/propose`, detect the platform and add frontmatter:

```
---
platform: android   # android / compose / xml / kmp mentioned
platform: ios       # ios / swiftui / swift mentioned
platform: flutter   # flutter / dart mentioned
platform: all       # multiplatform or unclear
---
```

When starting `/apply`, read the `platform:` field from the change's `proposal.md` and load the corresponding instructions file before any other read.

---

## Implementing Tasks

When completing each task from a `tasks.md`:

1. Implement the task code
2. Run `./gradlew build` in the affected module (`android_kmp/`)
3. Fix any compilation errors
4. Only then mark `[x]` in `tasks.md`

Never mark `[x]` before the build passes.

---

## Agent Orchestration (Android/KMP components)

When applying a change that adds a new Android/KMP component (detectable by the task structure: core → compose/xml → docs/sample), use parallel agents with isolated worktrees:

**Round 1** — sequential (core is a dependency of everything else):
- Agent Core → `craftd-core` tasks (model, enum, key)

**Round 2** — parallel (after Round 1 is merged):
- Agent Compose → `craftd-compose` tasks (composable, builder, registration, tests)
- Agent XML → `craftd-xml` tasks (render, registration)

**Round 3** — sequential (after Round 2):
- Agent Docs/Sample → documentation and sample app tasks

**Round 4** — reviewer (after Round 3):
- Agent Reviewer → reviews all produced code against the rules in `mcp-local/skills/architecture.md`. No build — each agent already validated theirs.

Each agent runs in an isolated worktree (`isolation: "worktree"`) and validates the build before marking `[x]`.

### When NOT to use an agent (do inline):
- Rounds 3 and 4 — simple edits, agent overhead outweighs the benefit
- Any task with fewer than 10 files to edit and no need for isolated build

### When to use an agent with worktree:
- Rounds 1 and 2 — isolated compilation needed, risk of conflict between parallel modules

### How to write an agent prompt:
- Pass the exact file paths of relevant files
- Include the reference code snippet (e.g., the existing builder to replicate)
- Never write "read the project and implement" — specify what and where

### Model by task type:
- Mechanical edits (JSON, doc, simple registration): use `model: "haiku"`
- Logic, compilation, and architectural decisions: Sonnet (default)

### Progress reporting

After each relevant state change (agent started, completed, or errored), show a progress table:

| Agent | Status | Tasks |
|---|---|---|
| Agent Core | ✓ Complete | 1.x |
| Agent Compose | ⟳ Running | 2.x |
| Agent XML | ⏳ Waiting | 3.x |

Icons: `⟳` running, `✓` complete, `⏳` waiting, `✗` error.

During execution, report progress as:

```
[Agent Core]     ✓ 1.1 IMAGE_COMPONENT added
[Agent Core]     ✓ 1.2 CraftDContentScale created
[Agent Compose]  ⟳ 2.2 Creating CraftDImage composable...
[Agent XML]      ⟳ 3.1 Creating CraftDImageComponent...
[Agent Compose]  ✓ 2.2 CraftDImage composable created
```

Report at each task started and completed.

---

## CI / Automation

- **`pr.yml`** — build and tests, triggers on every PR
- **`generate-tests.yml`** — auto-generates unit tests via Claude API for modified `.kt` files, opens a separate PR. Only runs after `pr.yml` passes. Does not run on bot PRs.

---

## PR Review

When a PR is published or when requested, review using `gh pr review` checking:

1. **Architectural rules** — component implements `CraftDBuilder` (or equivalent), no dependency between platform modules, `commonMain` without platform deps
2. **Completeness** — builder registered in `CraftDBuilderManager`, `onAction`/fallback covered, properties model in `craftd-core`
3. **Tests** — unit tests present with correct backtick naming
4. **Docs** — `docs/how-to-use/` updated for the affected platform
5. **Code standard** — `CraftD` prefix, `Modifier` exposed, no hardcoded color/typography values

Use `gh pr review <number> --comment -b "<comment>"` for general comments and `gh api` for inline comments.

---

╔══════════════════════════════════╗
║       SESSION TOKEN: READY      ║
╚══════════════════════════════════╝

You reached the end of init.md.
Reply with the token above before starting any task.
