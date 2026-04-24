---
name: review-pr
description: Review a CraftD pull request checking architectural rules, completeness, tests, and docs
trigger: when the user asks to review a PR, check a pull request, or validate changes
---

# CraftD PR Review Checklist

Use `gh pr review <number> --comment -b "<comment>"` for general comments and `gh api` for inline comments.

## 1. Architectural Rules

- [ ] Component implements `CraftDBuilder` (Android/KMP), protocol equivalent (iOS), or abstract class (Flutter)
- [ ] No dependency between platform modules (`craftd-compose` → `craftd-xml` is forbidden)
- [ ] `commonMain` has no platform-specific imports or dependencies
- [ ] External libraries (Coil, Glide, etc.) are injected via constructor, not coupled directly in the builder

## 2. Completeness

- [ ] Builder registered in `CraftDBuilderManager` of the respective platform
- [ ] `onAction`/fallback covered — even if it's a no-op
- [ ] `XxxProperties` (or equivalent) defined in `craftd-core`
- [ ] `CraftDComponentKey` updated with the new component key

## 3. Tests

- [ ] Unit tests present for the new component
- [ ] Test naming uses backtick notation: `` `given X when Y then Z` ``
- [ ] Test path mirrors source path: `src/test/java/...`
- [ ] Tests cover: construction, defaults, `copy()`, `equals/hashCode`, and builder `craft()`

## 4. Documentation

- [ ] `docs/how-to-use/` updated for the affected platform (`compose.md`, `view-system.md`, `swift-ui.md`, or `flutter.md`)
- [ ] Sample app updated with the new component

## 5. Code Standards

- [ ] `CraftD` prefix on all lib classes and files
- [ ] `modifier: Modifier = Modifier` exposed on composables
- [ ] No hardcoded color, typography, or spacing values
- [ ] Composable is stateless (state hoisting)

## How to Comment

```bash
# General PR comment
gh pr review 123 --comment -b "LGTM — all architectural rules followed."

# Request changes
gh pr review 123 --request-changes -b "Builder not registered in CraftDBuilderManager."

# Inline comment via API
gh api repos/CodandoTV/CraftD/pulls/123/comments \
  -f body="Missing onAction fallback" \
  -f path="android_kmp/craftd-compose/src/commonMain/..." \
  -f line=42
```
