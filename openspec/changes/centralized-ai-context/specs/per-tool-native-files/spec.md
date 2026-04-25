## ADDED Requirements

### Requirement: Cada ferramenta tem arquivo nativo com regras essenciais inline
O projeto SHALL manter um arquivo nativo para cada ferramenta de IA suportada contendo as regras arquiteturais mais críticas diretamente no arquivo (inline), garantindo funcionamento mesmo sem leitura de `mcp-local/context/`.

#### Scenario: Copilot funciona sem configuração adicional
- **WHEN** um desenvolvedor abre o projeto com GitHub Copilot
- **THEN** `.github/copilot-instructions.md` é lido automaticamente com as regras críticas do projeto

#### Scenario: Cursor funciona sem configuração adicional
- **WHEN** um desenvolvedor abre o projeto com Cursor
- **THEN** `.cursorrules` é lido automaticamente com as regras críticas do projeto

#### Scenario: Claude Code funciona sem configuração adicional
- **WHEN** um desenvolvedor abre o projeto com Claude Code
- **THEN** `CLAUDE.md` é lido automaticamente com as regras críticas e referência a `mcp-local/context/`

### Requirement: Arquivos nativos referenciam mcp-local/context/ para detalhes completos
Todo arquivo nativo SHALL incluir ao final uma instrução explícita para ler `mcp-local/context/` para regras completas e skills disponíveis.

#### Scenario: Modelo com suporte a leitura de contexto externo carrega detalhes
- **WHEN** um modelo capaz de ler arquivos externos processa o arquivo nativo
- **THEN** encontra a instrução `For complete rules and skills, read mcp-local/context/` e carrega o contexto completo

#### Scenario: Modelo sem suporte a leitura externa ainda funciona
- **WHEN** um modelo ignora a referência a `mcp-local/context/`
- **THEN** ainda possui as regras críticas inline e pode trabalhar com o projeto corretamente

### Requirement: Ferramentas suportadas cobrem Claude, Copilot, Cursor, Gemini e Codex
O projeto SHALL ter arquivos nativos para: `CLAUDE.md` (Claude Code), `.github/copilot-instructions.md` (GitHub Copilot), `.cursorrules` (Cursor), `.gemini/context.md` (Gemini/Google IDX), `AGENTS.md` (Codex/OpenAI).

#### Scenario: Todos os arquivos nativos existem na estrutura do projeto
- **WHEN** a raiz do projeto é inspecionada
- **THEN** todos os cinco arquivos nativos estão presentes com conteúdo válido
