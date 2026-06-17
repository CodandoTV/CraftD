## 1. Criar estrutura mcp-local/context/

- [x] 1.1 Criar pasta `mcp-local/context/` e `mcp-local/context/skills/` na raiz do projeto
- [x] 1.2 Criar `mcp-local/context/rules.md` com as regras arquiteturais do `CLAUDE.md` (seção "Regras arquiteturais — nunca violar" e "Convenções de código")
- [x] 1.3 Criar `mcp-local/context/module-graph.md` documentando explicitamente as dependências entre módulos (craftd-core ← compose/xml/ios/flutter, sem dependências cruzadas entre plataformas)
- [x] 1.4 Criar `mcp-local/context/android.md` com conteúdo de `.claude/instructions/android-patterns.md`
- [x] 1.5 Criar `mcp-local/context/ios.md` com conteúdo de `.claude/instructions/ios-patterns.md`
- [x] 1.6 Criar `mcp-local/context/flutter.md` com conteúdo de `.claude/instructions/flutter-patterns.md`

## 2. Migrar skills para mcp-local/context/skills/

- [x] 2.1 Criar `mcp-local/context/skills/new-component.md` com frontmatter (name, description, trigger) e guia de criação de componente CraftD
- [x] 2.2 Criar `mcp-local/context/skills/review-pr.md` com frontmatter e checklist de revisão de PR (baseado na seção "Review de PRs" do CLAUDE.md)
- [x] 2.3 Criar `mcp-local/context/skills/android-testing.md` com frontmatter e conteúdo de `.claude/skills/android-testing/`
- [x] 2.4 Criar `mcp-local/context/skills/compose-ui.md` com frontmatter e conteúdo de `.claude/skills/compose-ui/`
- [x] 2.5 Criar `mcp-local/context/skills/run-build.md` com frontmatter e instruções para rodar o build (`./gradlew build` no módulo afetado)

## 3. Criar arquivos nativos por ferramenta

- [x] 3.1 Criar `.github/copilot-instructions.md` com regras críticas inline (arquitetura, nomenclatura, proibições) + referência a `mcp-local/context/`
- [x] 3.2 Criar `.cursorrules` com regras críticas inline + referência a `mcp-local/context/`
- [x] 3.3 Criar `.gemini/context.md` com regras críticas inline + referência a `mcp-local/context/`
- [x] 3.4 Criar `AGENTS.md` (Codex/OpenAI) com regras críticas inline + referência a `mcp-local/context/`
- [x] 3.5 Atualizar `CLAUDE.md` para referenciar `mcp-local/context/` em vez de `.claude/instructions/` nos pontos de plataforma

## 4. Criar documentação explicativa para a comunidade

- [x] 4.1 Criar `mcp-local/README.md` em inglês explicando o propósito da pasta, com diagrama Mermaid mostrando o fluxo ferramenta → arquivo nativo → mcp-local/context/ e nota sobre como replicar em outros repositórios
- [x] 4.2 Criar `.claude/AI_CONTEXT.md` em inglês explicando que `.claude/` é específico do Claude Code e que o contexto completo vive em `mcp-local/context/`, com diagrama Mermaid
- [x] 4.3 Criar `.github/AI_CONTEXT.md` em inglês explicando que `copilot-instructions.md` é o arquivo nativo do Copilot e que o contexto completo vive em `mcp-local/context/`, com diagrama Mermaid
