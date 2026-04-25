---
name: android-gradle-logic
description: Gradle build logic e Convention Plugins. Use when mexendo na configuração de build do CraftD (build-logic/, libs.versions.toml).
---

# Android Gradle Build Logic

## Estrutura do CraftD

O CraftD já usa o padrão recomendado:
- `build-logic/` — convention plugins
- `gradle/libs.versions.toml` — version catalog centralizado

## Regras

- Dependências novas sempre via `libs.versions.toml` — nunca versão hardcoded no `build.gradle.kts`
- Configuração compartilhada entre módulos vai em convention plugin no `build-logic/`, não copiada
- Módulos referenciam plugins via `alias(libs.plugins.xxx)`

## Adicionando dependência nova

1. Adiciona versão em `[versions]` no `libs.versions.toml`
2. Adiciona a lib em `[libraries]`
3. Referencia no `build.gradle.kts` do módulo via `libs.xxx`

## Adicionando plugin novo

1. Adiciona em `[plugins]` no `libs.versions.toml`
2. Aplica no módulo via `alias(libs.plugins.xxx)`
