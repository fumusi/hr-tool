@echo off
setlocal enabledelayedexpansion

echo.
echo ╔═══════════════════════════════════════════════════════════════════╗
echo ║              DIJAGNOSTIKA GREŠAKA PRI KOMPAJLIRANJU              ║
echo ╚═══════════════════════════════════════════════════════════════════╝
echo.

cd /d C:\Users\dimi3\OneDrive\Desktop\praksaproj\hr-tool

echo Pokušaj 1: Direkt compile sa verbose izlazom
echo ════════════════════════════════════════════
mvn -X clean compile 2>&1 | findstr /I "error fail exception problem"

echo.
echo Pokušaj 2: Install sa debug info
echo ════════════════════════════════════════════
mvn clean install -DskipTests -X 2>&1 | tail -50

echo.
echo Pokušaj 3: Test kompajliranje
echo ════════════════════════════════════════════
mvn test-compile 2>&1 | findstr /I "error fail exception"

echo.
echo Ako vidiš ERROR - javi šta piše!
echo.
pause

