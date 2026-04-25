## ADDED Requirements

### Requirement: Workflow executa apenas em PRs mergeados no main
O workflow `generate-tests.yml` SHALL ser acionado automaticamente somente quando um pull request for mergeado na branch `main`.

#### Scenario: PR mergeado no main dispara o workflow
- **WHEN** um pull request é fechado com `merged == true` e `base.ref == 'main'`
- **THEN** o job `generate-tests` é executado

#### Scenario: PR fechado sem merge não dispara o workflow
- **WHEN** um pull request é fechado com `merged == false`
- **THEN** o job `generate-tests` NÃO é executado

#### Scenario: Push em branch sem merge não dispara o workflow
- **WHEN** um commit é feito em qualquer branch sem que haja um merge no main
- **THEN** o job `generate-tests` NÃO é executado

#### Scenario: PR mergeado em branch que não main não dispara o workflow
- **WHEN** um pull request é mergeado em uma branch diferente de `main` (ex: `develop`)
- **THEN** o job `generate-tests` NÃO é executado

### Requirement: Workflow_dispatch mantido para acionamento manual
O workflow SHALL continuar suportando acionamento manual via `workflow_dispatch` com o parâmetro opcional `override_files`.

#### Scenario: Disparo manual sem override roda scan completo
- **WHEN** o workflow é acionado via `workflow_dispatch` sem `override_files`
- **THEN** o job escaneia todos os arquivos `.kt` sem cobertura em `craftd-core`

#### Scenario: Disparo manual com override_files usa arquivos informados
- **WHEN** o workflow é acionado via `workflow_dispatch` com `override_files` preenchido
- **THEN** o job processa apenas os arquivos listados em `override_files`

### Requirement: Número do PR de origem preservado no PR gerado
O workflow SHALL incluir o número do PR que disparou a geração no corpo do PR de testes criado automaticamente.

#### Scenario: PR de testes referencia o PR de origem
- **WHEN** o workflow é disparado por um merge de PR número `N`
- **THEN** o corpo do PR gerado contém `Triggered by PR #N`

#### Scenario: Disparo manual usa label "manual"
- **WHEN** o workflow é acionado via `workflow_dispatch`
- **THEN** o corpo do PR gerado contém `Triggered by PR #manual`
