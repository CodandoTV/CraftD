## Why

CraftD é um repositório público usado como referência pela comunidade, mas hoje cada ferramenta de IA (Claude Code, Copilot, Cursor, Gemini, Codex) tem seu próprio arquivo de contexto isolado — ou nenhum. Isso significa que regras arquiteturais, padrões de plataforma e skills ficam duplicados, desatualizados ou simplesmente ausentes dependendo da ferramenta usada. O momento é agora porque o ecossistema de AI coding tools convergiu o suficiente para que um padrão de "fonte única de contexto" seja viável e legível por todas as ferramentas.

## What Changes

- Criar pasta `mcp-local/context/` como fonte única de verdade para regras, padrões e skills
- Migrar conteúdo de `.claude/instructions/` (android-patterns, ios-patterns, flutter-patterns) para `mcp-local/context/`
- Migrar skills de `.claude/skills/` para `mcp-local/context/skills/`
- Criar arquivos nativos leves para cada ferramenta: `.github/copilot-instructions.md`, `.cursorrules`, `.gemini/context.md`, `AGENTS.md` (Codex) — cada um com regras essenciais inline + referência a `mcp-local/context/`
- Atualizar `CLAUDE.md` para referenciar `mcp-local/context/` em vez de `.claude/instructions/`
- Adicionar `.md` explicativo em inglês em cada pasta nativa (`.claude/`, `.github/`) para a comunidade entender a estrutura sem duplicação, com diagrama Mermaid

## Capabilities

### New Capabilities

- `centralized-context-structure`: Estrutura `mcp-local/context/` como fonte única de contexto AI com regras, padrões por plataforma e skills autodescritivas
- `per-tool-native-files`: Arquivos nativos leves por ferramenta que carregam essencial inline e referenciam `mcp-local/context/` para detalhes
- `community-explainer-docs`: Documentação explicativa em inglês em cada pasta nativa para a comunidade entender o padrão adotado

### Modified Capabilities

<!-- Nenhuma spec existente a alterar -->

## Impact

- **Arquivos novos:** `mcp-local/context/rules.md`, `mcp-local/context/android.md`, `mcp-local/context/ios.md`, `mcp-local/context/flutter.md`, `mcp-local/context/module-graph.md`, `mcp-local/context/skills/` (migração), `.github/copilot-instructions.md`, `.cursorrules`, `.gemini/context.md`, `AGENTS.md`, `mcp-local/README.md`, `.claude/README.md`, `.github/AI_CONTEXT.md`
- **Arquivos modificados:** `CLAUDE.md` (atualizar referências de `.claude/instructions/` para `mcp-local/context/`)
- **Sem breaking change:** `.claude/skills/` e `.claude/instructions/` podem coexistir durante migração
- **Sem dependências externas:** apenas arquivos markdown, zero infraestrutura
