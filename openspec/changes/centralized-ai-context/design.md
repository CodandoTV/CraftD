## Context

Hoje o CraftD tem contexto de IA fragmentado:
- `CLAUDE.md` + `.claude/instructions/` → só Claude Code lê
- `.claude/skills/` → só Claude Code conhece
- Nenhum arquivo nativo para Copilot, Cursor, Gemini ou Codex

O resultado: devs que usam Copilot ou Cursor no mesmo repo trabalham sem as regras arquiteturais do projeto. O padrão que já funciona no Claude Code (arquivo principal referencia instruções detalhadas em subpastas) é o modelo a ser generalizado.

## Goals / Non-Goals

**Goals:**
- Criar `mcp/context/` como fonte única de verdade para qualquer ferramenta de IA
- Cada ferramenta tem arquivo nativo leve com essencial inline + referência a `mcp/context/`
- Skills autodescritivas com `name` e `description` que qualquer modelo descobre ao ler
- Comunidade entende o padrão ao abrir qualquer pasta nativa (explicativo em inglês + diagrama)
- Zero infraestrutura — apenas arquivos markdown

**Non-Goals:**
- MCP Server com processo rodando (futuro, se necessário)
- Garantir comportamento idêntico entre todos os modelos (cada um interpreta à sua maneira)
- Migrar o sistema de skills estruturadas do `.claude/skills/` (formato YAML interno) — apenas criar versões em `mcp/context/skills/` legíveis por qualquer modelo

## Decisions

### Estrutura de pastas

```
mcp/
  README.md                    ← explicativo para a comunidade (Mermaid)
  context/
    rules.md                   ← regras arquiteturais (do CLAUDE.md)
    module-graph.md            ← dependências entre módulos (novo)
    android.md                 ← do .claude/instructions/android-patterns.md
    ios.md                     ← do .claude/instructions/ios-patterns.md
    flutter.md                 ← do .claude/instructions/flutter-patterns.md
    skills/
      new-component.md         ← como criar novo componente
      review-pr.md             ← checklist de revisão de PR
      run-build.md             ← como rodar o build
      android-testing.md       ← padrões de teste Android
      compose-ui.md            ← boas práticas Compose
```

### Arquivos nativos por ferramenta

| Ferramenta | Arquivo nativo | Estratégia |
|---|---|---|
| Claude Code | `CLAUDE.md` | Atualizar referências para `mcp/context/` |
| GitHub Copilot | `.github/copilot-instructions.md` | Novo — essencial inline + referência |
| Cursor | `.cursorrules` | Novo — essencial inline + referência |
| Gemini (Google IDX) | `.gemini/context.md` | Novo — essencial inline + referência |
| Codex / OpenAI | `AGENTS.md` | Novo — essencial inline + referência |

**Decisão:** cada arquivo nativo carrega as 5-8 regras mais críticas inline (garante que qualquer modelo as leia, mesmo sem seguir a referência) e adiciona `For complete rules and skills, read mcp/context/` ao final.

**Alternativa descartada:** arquivo nativo apenas como apontador. Risco: modelos com contexto limitado ou implementações inconsistentes ignoram a instrução de ir ler outra pasta.

### Conteúdo das skills em mcp/context/skills/

Cada skill é um markdown autodescritivo:

```markdown
---
name: new-component
description: Create a new Server Driven UI component in CraftD following
             the CraftDBuilder pattern across all platforms
trigger: when user asks to create a new component or builder
---

## Steps
...
```

O frontmatter `name` + `description` permite que o modelo liste o que sabe fazer antes de ser perguntado. É o padrão que o próprio `.claude/skills/` usa, adaptado para ser legível por qualquer ferramenta.

### Explicativos para a comunidade

Cada pasta nativa ganha um arquivo `AI_CONTEXT.md` (ou `README.md` onde faz sentido) em inglês explicando que o conteúdo não é duplicado — há uma fonte central. Inclui diagrama Mermaid mostrando o fluxo.

**Decisão de nome:** `AI_CONTEXT.md` em vez de `README.md` para não conflitar com READMEs existentes e deixar claro o propósito.

## Risks / Trade-offs

- **[Risco] Modelos ignoram a referência a `mcp/context/`** → Mitigado mantendo regras críticas inline em cada arquivo nativo
- **[Risco] `mcp/context/` fica desatualizado** → O nome da pasta (`mcp/`) pode confundir — não é um MCP Server, é contexto. Mitigado com `README.md` explicativo claro
- **[Trade-off] Alguma duplicação intencional** → Regras críticas aparecem tanto no arquivo nativo quanto em `mcp/context/rules.md`. Aceito: o arquivo nativo é o "resumo garantido", o `mcp/context/` é o "detalhe completo"
- **[Risco] `.claude/skills/` e `mcp/context/skills/` coexistem** → As skills em `.claude/skills/` têm formato estruturado para o Claude Code; as de `mcp/context/skills/` são markdown livre para qualquer modelo. Diferentes propósitos, podem coexistir sem conflito
