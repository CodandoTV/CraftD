## 1. Substituir gatilho do workflow

- [x] 1.1 Remover o bloco `workflow_run` de `.github/workflows/generate-tests.yml`
- [x] 1.2 Adicionar gatilho `pull_request: types: [closed]` no lugar

## 2. Atualizar condição do job

- [x] 2.1 Adicionar condição `if` no job `generate-tests` para verificar `github.event.pull_request.merged == true`
- [x] 2.2 Adicionar filtro `github.event.pull_request.base.ref == 'main'` na condição do job (ou manter para `workflow_dispatch`)

## 3. Remover e simplificar lógica de contexto

- [x] 3.1 Remover o step "Resolve trigger context" (`id: ctx`) inteiramente
- [x] 3.2 Atualizar o step "Checkout" para usar `github.event.pull_request.merge_commit_sha` (ou `github.sha` para dispatch)
- [x] 3.3 Atualizar referências `steps.ctx.outputs.pr_number` e `steps.ctx.outputs.head_sha` nos steps seguintes para usar os valores diretos do evento

## 4. Ajustar diff de Kotlin

- [x] 4.1 No step "Check for Kotlin file changes", substituir a base do diff de `origin/main...HEAD` por `${{ github.event.pull_request.base.sha }}...HEAD` para o gatilho `pull_request`
- [x] 4.2 Garantir que o modo `workflow_dispatch` continue usando o scan completo (sem base SHA de PR)

## 5. Validação

- [x] 5.1 Revisar o YAML final e confirmar que a sintaxe está correta (sem erros de indentação ou referências quebradas)
- [x] 5.2 Confirmar que `pr_number` no body do PR gerado continua referenciando o número correto (ou "manual" para dispatch)
