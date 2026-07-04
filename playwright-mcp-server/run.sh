#!/usr/bin/env bash
# Launch the Playwright MCP server (STDIO mode)
# Usage: ./run.sh [--headless=false] [--browser=firefox]
set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
JAR="$SCRIPT_DIR/target/playwright-mcp-server-1.0.0.jar"

if [ ! -f "$JAR" ]; then
  echo "JAR not found. Building first..." >&2
  cd "$SCRIPT_DIR" && mvn package -q -DskipTests
fi

exec java \
  -Dspring.main.banner-mode=off \
  -Dlogging.pattern.console= \
  "$@" \
  -jar "$JAR"
