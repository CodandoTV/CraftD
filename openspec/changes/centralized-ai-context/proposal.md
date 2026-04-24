## Why

CraftD é um repositório público usado como referência pela comunidade, mas hoje cada ferramenta de IA (Claude Code, Copilot, Cursor, Gemini, Codex) tem seu próprio arquivo de contexto isolado — ou nenhum. Isso significa que regras arquiteturais, padrões de plataforma e skills ficam duplicados, desatualizados ou simplesmente ausentes dependendo da ferramenta usada. O momento é agora porque o ecossistema de AI coding tools convergiu o suficiente para que um padrão de "fonte única de contexto" seja viável e legível por todas as ferramentas.

## What Changes

- Criar pasta `mcp/context/` como fonte única de verdade para regras, padrões e skills
- Migrar conteúdo de `.claude/instructions/` (android-patterns, ios-patterns, flutter-patterns) para `mcp/context/`
- Migrar skills de `.claude/skills/` para `mcp/context/skills/`
- Criar arquivos nativos leves para cada ferramenta: `.github/copilot-instructions.md`, `.cursorrules`, `.gemini/context.md`, `AGENTS.md` (Codex) — cada um com regras essenciais inline + referência a `mcp/context/`
- Atualizar `CLAUDE.md` para referenciar `mcp/context/` em vez de `.claude/instructions/`
- Adicionar `.md` explicativo em inglês em cada pasta nativa (`.claude/`, `.github/`) para a comunidade entender a estrutura sem duplicação, com diagrama Mermaid

## Capabilities

### New Capabilities

- `centralized-context-structure`: Estrutura `mcp/context/` como fonte única de contexto AI com regras, padrões por plataforma e skills autodescritivas
- `per-tool-native-files`: Arquivos nativos leves por ferramenta que carregam essencial inline e referenciam `mcp/context/` para detalhes
- `community-explainer-docs`: Documentação explicativa em inglês em cada pasta nativa para a comunidade entender o padrão adotado

### Modified Capabilities

<!-- Nenhuma spec existente a alterar -->

## Impact

- **Arquivos novos:** `mcp/context/rules.md`, `mcp/context/android.md`, `mcp/context/ios.md`, `mcp/context/flutter.md`, `mcp/context/module-graph.md`, `mcp/context/skills/` (migração), `.github/copilot-instructions.md`, `.cursorrules`, `.gemini/context.md`, `AGENTS.md`, `mcp/README.md`, `.claude/README.md`, `.github/AI_CONTEXT.md`
- **Arquivos modificados:** `CLAUDE.md` (atualizar referências de `.claude/instructions/` para `mcp/context/`)
- **Sem breaking change:** `.claude/skills/` e `.claude/instructions/` podem coexistir durante migração
- **Sem dependências externas:** apenas arquivos markdown, zero infraestrutura
