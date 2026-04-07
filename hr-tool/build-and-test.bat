@echo off
echo.
echo ╔═══════════════════════════════════════════════════════════════════╗
echo ║              HR TOOL - KOMPAJLIRANJE I TESTIRANJE                ║
echo ╚═══════════════════════════════════════════════════════════════════╝
echo.

cd /d C:\Users\dimi3\OneDrive\Desktop\praksaproj\hr-tool

echo [1/4] Čišćenje (mvn clean)...
mvn clean

echo.
echo [2/4] Preuzimanje zavisnosti (mvn install)...
mvn install -DskipTests

echo.
echo [3/4] Kompajliranje (mvn compile)...
mvn compile

echo.
echo [4/4] Pokretanje testova (mvn test)...
mvn test

echo.
echo ═══════════════════════════════════════════════════════════════════
echo GOTOVO!
echo ═══════════════════════════════════════════════════════════════════
echo.

pause

