## ADDED Requirements

### Requirement: Cada pasta nativa tem documento explicativo em inglês para a comunidade
O projeto SHALL incluir um arquivo `AI_CONTEXT.md` em cada pasta que contém configuração de ferramenta de IA (`.claude/`, `.github/`), e um `README.md` em `mcp/`, explicando em inglês o padrão adotado e por que o conteúdo não é duplicado.

#### Scenario: Desenvolvedor que clona o repo entende a estrutura sem ler código
- **WHEN** um desenvolvedor abre `.claude/` ou `.github/` no GitHub
- **THEN** encontra `AI_CONTEXT.md` explicando o padrão centralizado com diagrama Mermaid

#### Scenario: mcp-local/README.md explica o propósito da pasta
- **WHEN** um desenvolvedor acessa `mcp/` no repositório
- **THEN** encontra `README.md` explicando que é a fonte central de contexto AI, não um MCP Server em execução

### Requirement: Diagrama Mermaid ilustra o fluxo de contexto entre ferramentas e fonte central
O documento explicativo SHALL incluir um diagrama Mermaid mostrando como cada ferramenta lê seu arquivo nativo e como esse arquivo referencia `mcp-local/context/`.

#### Scenario: Diagrama é renderizado corretamente no GitHub
- **WHEN** o arquivo `AI_CONTEXT.md` ou `mcp-local/README.md` é visualizado no GitHub
- **THEN** o diagrama Mermaid renderiza mostrando o fluxo: ferramentas → arquivos nativos → mcp-local/context/

#### Scenario: Diagrama comunica a ausência de duplicação
- **WHEN** um desenvolvedor lê o diagrama
- **THEN** fica claro que cada ferramenta aponta para a mesma fonte, sem cópias do conteúdo

### Requirement: Explicativo menciona como replicar o padrão em outros repositórios
O documento `mcp-local/README.md` SHALL incluir uma nota indicando que a estrutura pode ser copiada para outros repositórios públicos como ponto de partida.

#### Scenario: Comunidade consegue adotar o padrão no próprio projeto
- **WHEN** um desenvolvedor externo lê `mcp-local/README.md`
- **THEN** encontra instrução clara de como replicar a estrutura em outro repositório
