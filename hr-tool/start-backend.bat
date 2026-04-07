@echo off
echo.
echo ╔═══════════════════════════════════════════════════════════════════╗
echo ║                  HR TOOL - POKRETANJE BACKEND-A                  ║
echo ║                                                                   ║
echo ║  Ova skripta će kompajlirati, testirati i pokrenuti backend     ║
echo ╚═══════════════════════════════════════════════════════════════════╝
echo.

cd /d C:\Users\dimi3\OneDrive\Desktop\praksaproj\hr-tool

echo [1/3] Čišćenje starih fajlova (mvn clean)...
mvn clean

echo.
echo [2/3] Pokretanje testova (mvn test)...
mvn test

echo.
echo [3/3] Pokretanje aplikacije (mvn spring-boot:run)...
echo.
echo ✓ Backend će biti dostupan na: http://localhost:8080
echo ✓ Swagger UI: http://localhost:8080/swagger-ui.html
echo.

mvn spring-boot:run

pause

