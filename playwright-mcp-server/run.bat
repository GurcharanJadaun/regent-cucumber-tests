@echo off
REM Launch the Playwright MCP server (STDIO mode) on Windows
REM Usage: run.bat [extra JVM args]

set SCRIPT_DIR=%~dp0
set JAR=%SCRIPT_DIR%target\playwright-mcp-server-1.0.0.jar

if not exist "%JAR%" (
    echo JAR not found. Building first... 1>&2
    cd /d "%SCRIPT_DIR%"
    call mvn package -q -DskipTests
)

java -Dspring.main.banner-mode=off -Dlogging.pattern.console= %* -jar "%JAR%"
