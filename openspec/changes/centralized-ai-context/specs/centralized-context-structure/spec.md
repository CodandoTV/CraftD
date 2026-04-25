## ADDED Requirements

### Requirement: mcp-local/context/ é a fonte única de verdade para contexto AI
O projeto SHALL manter uma pasta `mcp-local/context/` na raiz contendo todo o contexto arquitetural, regras e skills do projeto em formato markdown legível por qualquer ferramenta de IA.

#### Scenario: Desenvolvedor com qualquer ferramenta acessa contexto completo
- **WHEN** um desenvolvedor aponta qualquer ferramenta de IA para `mcp-local/context/`
- **THEN** a ferramenta tem acesso a regras arquiteturais, padrões por plataforma e skills disponíveis

#### Scenario: Estrutura de arquivos está presente e organizada
- **WHEN** a pasta `mcp-local/context/` é inspecionada
- **THEN** contém `rules.md`, `module-graph.md`, `android.md`, `ios.md`, `flutter.md` e pasta `skills/`

### Requirement: Skills são autodescritivas com frontmatter padronizado
Cada skill em `mcp-local/context/skills/` SHALL ter frontmatter com `name`, `description` e `trigger` para que qualquer modelo descubra o que pode fazer.

#### Scenario: Modelo lista skills disponíveis ao ler a pasta
- **WHEN** um modelo lê os arquivos em `mcp-local/context/skills/`
- **THEN** consegue identificar nome, descrição e quando usar cada skill pelo frontmatter

#### Scenario: Skill de novo componente está disponível
- **WHEN** desenvolvedor pede para criar um novo componente CraftD em qualquer ferramenta
- **THEN** a ferramenta encontra `mcp-local/context/skills/new-component.md` com o guia completo

### Requirement: module-graph.md documenta dependências entre módulos explicitamente
O arquivo `mcp-local/context/module-graph.md` SHALL documentar as dependências entre módulos de forma explícita, sem exigir leitura do `settings.gradle.kts`.

#### Scenario: Modelo entende que módulos de plataforma não se dependem
- **WHEN** um modelo lê `mcp-local/context/module-graph.md`
- **THEN** entende claramente que `craftd-compose`, `craftd-xml`, `ios/` e `flutter/` dependem apenas do `craftd-core` e nunca entre si
