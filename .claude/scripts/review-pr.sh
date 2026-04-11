#!/bin/bash
# Hook: auto-review PR after gh pr create
# Reads tool output from stdin (JSON), extracts PR URL and triggers Claude review

INPUT=$(cat)

COMMAND=$(echo "$INPUT" | python3 -c "import json,sys; d=json.load(sys.stdin); print(d.get('tool_input',{}).get('command',''))" 2>/dev/null)

# Only trigger on gh pr create
if ! echo "$COMMAND" | grep -q "gh pr create"; then
  exit 0
fi

OUTPUT=$(echo "$INPUT" | python3 -c "import json,sys; d=json.load(sys.stdin); print(d.get('tool_response',''))" 2>/dev/null)

PR_URL=$(echo "$OUTPUT" | grep -oE 'https://github\.com/[^ ]+/pull/[0-9]+')
PR_NUMBER=$(echo "$PR_URL" | grep -oE '[0-9]+$')

if [ -z "$PR_NUMBER" ]; then
  exit 0
fi

echo "Iniciando review do PR #$PR_NUMBER..."

claude -p "Review PR #$PR_NUMBER do repositório CraftD seguindo as regras de review do CLAUDE.md. Use 'gh pr view $PR_NUMBER --json files,body,title' para ver o PR, leia os arquivos modificados, e poste um review com 'gh pr review $PR_NUMBER --comment -b \"<seu comentário>\"' cobrindo: regras arquiteturais, completude (builder registrado, onAction coberto), testes e docs." \
  --allowedTools "Bash(gh pr view:*),Bash(gh pr review:*),Bash(gh api:*),Read,Glob,Grep" \
  2>/dev/null &

echo "Review do PR #$PR_NUMBER iniciado em background."
