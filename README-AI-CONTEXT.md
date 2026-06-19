# AI Context Structure

CraftD uses multiple AI coding tools (Claude Code, GitHub Copilot, Gemini, Cursor, etc.). Instead of maintaining separate context files for each tool — which would quickly diverge — all tools point to the same source.

```
📄 AGENTS.md  ← single source of truth (master initializer)
📁 ai/        ← detailed context: instructions, skills, module graph
```

```mermaid
graph LR
    CLAUDE["Claude Code<br/><code>CLAUDE.md</code>"]
    COPILOT["GitHub Copilot<br/><code>.github/copilot-instructions.md</code>"]
    CURSOR["Cursor<br/><code>.cursorrules</code>"]
    GEMINI["Gemini<br/><code>.gemini/context.md</code>"]
    CODEX["Codex / OpenAI<br/>(reads natively)"]

    AG["📄 AGENTS.md<br/>Master Initializer"]
    IA["📁 ai/<br/>Detailed Context"]

    CLAUDE -->|reads| AG
    COPILOT -->|reads| AG
    CURSOR -->|reads| AG
    GEMINI -->|reads| AG
    CODEX -->|reads| AG

    AG -->|references| IA
```

## ai/ structure

```
ai/
  module-graph.md         ← module dependency graph
  instructions/
    android.md            ← Android/KMP patterns
    ios.md                ← iOS/SwiftUI patterns
    flutter.md            ← Flutter patterns
  skills/
    architecture.md       ← architectural rules and code conventions
    compose-ui.md         ← Compose component checklist and patterns
    android-testing.md    ← Android/KMP testing strategies
    android-gradle-logic.md ← build-logic and version catalog
    new-component.md      ← step-by-step for new components
    review-pr.md          ← PR review checklist
    run-build.md          ← how to run builds per platform
```

## Tool-specific config

| Location | Tool | Purpose |
|---|---|---|
| `AGENTS.md` | All tools | Master initializer — single source of truth |
| `CLAUDE.md` | Claude Code | Entry point → reads `AGENTS.md` |
| `.gemini/context.md` | Gemini | Entry point → reads `AGENTS.md` |
| `.github/copilot-instructions.md` | GitHub Copilot | Entry point → reads `AGENTS.md` |
| `.cursorrules` | Cursor | Entry point → reads `AGENTS.md` |
