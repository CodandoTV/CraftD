## Why

O workflow `generate-tests.yml` dispara a cada commit em qualquer branch porque o gatilho `workflow_run` reage à conclusão de "Build and Test for PRs" independente de o PR ter sido mergeado ou não. Isso gera execuções desnecessárias e custo de API durante o desenvolvimento, quando o ideal é gerar testes apenas uma vez — ao merge no `main`.

## What Changes

- Substituir o gatilho `workflow_run` por `pull_request` com o tipo `closed` + filtro `merged == true`
- Remover a lógica de resolução de contexto para o gatilho `workflow_run` (step "Resolve trigger context")
- Ajustar o step "Check for Kotlin file changes" para usar `github.event.pull_request.base.sha` como base do diff em vez de `origin/main`
- Manter o gatilho `workflow_dispatch` inalterado para acionamento manual

## Capabilities

### New Capabilities

- `generate-tests-trigger`: Controle de gatilho do workflow de geração de testes — restringe execução a PRs mergeados no `main`

### Modified Capabilities

<!-- Nenhuma spec existente a alterar -->

## Impact

- **Arquivo afetado:** `.github/workflows/generate-tests.yml`
- **Comportamento anterior:** workflow roda a cada push que passe no CI de qualquer branch com PR aberto
- **Comportamento novo:** workflow roda apenas quando um PR é mergeado no `main`
- **Sem impacto** em outros workflows, código da lib ou testes existentes
