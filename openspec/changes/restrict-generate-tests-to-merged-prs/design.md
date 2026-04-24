## Context

O workflow `generate-tests.yml` usa `workflow_run` como gatilho, o que faz com que ele execute toda vez que o workflow "Build and Test for PRs" conclui com sucesso — independentemente de haver um merge. Isso significa que a geração de testes (e o custo associado à Claude API) ocorre a cada push em branches com PR aberto.

O estado atual do gatilho:
```yaml
workflow_run:
  workflows: ["Build and Test for PRs"]
  types: [completed]
```

A mudança substitui esse gatilho por `pull_request` com tipo `closed`, filtrando apenas merges reais.

## Goals / Non-Goals

**Goals:**
- Garantir que `generate-tests.yml` rode apenas quando um PR é mergeado no `main`
- Manter o `workflow_dispatch` para acionamento manual com `override_files`
- Simplificar a lógica de contexto eliminando a ramificação `workflow_run`

**Non-Goals:**
- Não alterar a lógica de geração de testes em si (Python script, Claude API, PR criado)
- Não modificar outros workflows (`pr.yml`)
- Não mudar o comportamento do `workflow_dispatch`

## Decisions

### Gatilho: `pull_request` com tipo `closed` + condição `merged`

**Decisão:** Usar `on: pull_request: types: [closed]` com `if: github.event.pull_request.merged == true` no job.

**Alternativas consideradas:**
- `push` no branch `main` — funcionaria, mas perderia o número do PR de origem, que é usado no corpo do PR de testes gerados.
- Manter `workflow_run` com filtro extra — não é possível filtrar por merge via `workflow_run`; exigiria lógica de shell para verificar via `gh api`, mais frágil.

**Rationale:** O evento `pull_request.closed` com `merged == true` é a forma idiomática do GitHub Actions para "PR mergeado". Fornece `github.event.pull_request.number` diretamente, mantendo a rastreabilidade no PR gerado.

### Base do diff: `github.event.pull_request.base.sha`

**Decisão:** No step "Check for Kotlin file changes", substituir `origin/main...HEAD` por `${{ github.event.pull_request.base.sha }}...HEAD` quando o gatilho for `pull_request`.

**Rationale:** Com `pull_request.closed`, o checkout ocorre no merge commit. Usar o SHA base do PR garante que o diff cubra exatamente os arquivos modificados naquele PR — o mesmo conjunto analisado pelo CI.

### Remoção do step "Resolve trigger context"

**Decisão:** Remover o step `ctx` que normalizava `head_sha` e `pr_number` entre os dois gatilhos (`workflow_dispatch` vs `workflow_run`).

**Rationale:** Com apenas `pull_request` e `workflow_dispatch`, os valores são obtidos diretamente:
- `pr_number`: `github.event.pull_request.number` (ou `"manual"` no dispatch)
- `head_sha`: `github.event.pull_request.merge_commit_sha` (ou `github.sha` no dispatch)

Isso elimina a indireção e simplifica os steps subsequentes.

## Risks / Trade-offs

- **[Risco] PRs mergeados em branches que não `main`** → O job pode ser restrito adicionando `if: github.event.pull_request.base.ref == 'main'`. Incluir essa condição como precaução.
- **[Trade-off] Geração só ocorre pós-merge** → Antes, testes podiam ser gerados ainda com o PR aberto (como efeito colateral). Agora é intencional — só pós-merge. Aceito como comportamento desejado.

## Migration Plan

1. Editar `.github/workflows/generate-tests.yml`:
   - Substituir bloco `workflow_run` por `pull_request: types: [closed]`
   - Remover step `Resolve trigger context`
   - Atualizar condição `if` do job para verificar `merged == true` e base `main`
   - Atualizar referências de `steps.ctx.outputs.*` nos steps seguintes
2. Fazer commit e push — sem necessidade de rollback especial; reverter o arquivo restaura o comportamento anterior.
