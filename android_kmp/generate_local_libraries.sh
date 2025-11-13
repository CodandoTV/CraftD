#!/usr/bin/env bash
set -euo pipefail

# ============================================================
# CONFIGURAÇÕES DO SCRIPT
# ============================================================
VERSION="${VERSION:-1.0.0-local}"
SAMPLE_PATH="${SAMPLE_PATH:-./KMP-Sample/gradle}"
MODULES="${MODULES:-craftd-core craftd-compose}"
TOML_KEY="${TOML_KEY:-craftdCompose}"
TOML_PATH="$SAMPLE_PATH/libs.versions.toml"

# ============================================================
# sed compatibilidade (GNU vs BSD/macOS)
# ============================================================
if command -v gsed >/dev/null 2>&1; then
  SED=gsed
  SED_INPLACE=(-i)
elif sed --version >/dev/null 2>&1; then
  SED=sed
  SED_INPLACE=(-i)
else
  SED=sed
  SED_INPLACE=(-i '')
fi

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT_DIR"

echo ">> Using VERSION:   $VERSION"
echo ">> SAMPLE_PATH:     $SAMPLE_PATH"
echo ">> TOML_PATH:       $TOML_PATH"
echo ">> TOML_KEY:        $TOML_KEY"

# ============================================================
# Atualiza VERSION / VERSION_NAME nos gradle.properties
# ============================================================
for m in $MODULES; do
  props="$m/gradle.properties"
  if [ -f "$props" ]; then
    echo ">> Updating $props → VERSION=$VERSION"
    "$SED" "${SED_INPLACE[@]}" "s/^VERSION=.*/VERSION=${VERSION}/" "$props" || true
    "$SED" "${SED_INPLACE[@]}" "s/^VERSION_NAME=.*/VERSION_NAME=${VERSION}/" "$props" || true
  else
    echo "!! Missing $props (skipping)"
  fi
done

# ============================================================
# Build release dos módulos
# ============================================================
for m in $MODULES; do
  echo ">> Assembling release for :$m"
  ./gradlew :"$m":assembleRelease
done

# ============================================================
# Publish no mavenLocal
# ============================================================
for m in $MODULES; do
  echo ">> Publishing :$m to mavenLocal"
  ./gradlew :"$m":publishToMavenLocal
done

# ============================================================
# Atualiza a versão no libs.versions.toml
# ============================================================
if [ -f "$TOML_PATH" ]; then
  echo ">> Updating TOML key $TOML_KEY → \"$VERSION\""
  "$SED" "${SED_INPLACE[@]}" \
    "s/^[[:space:]]*${TOML_KEY}[[:space:]]*=[[:space:]]*\"[^\"]*\"/${TOML_KEY} = \"${VERSION}\"/" \
    "$TOML_PATH" || true
else
  echo "!! Error: TOML not found at $TOML_PATH"
fi

echo ">> DONE: Published version \"$VERSION\" to mavenLocal and updated TOML."
