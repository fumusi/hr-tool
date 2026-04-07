@echo off
echo.
echo ╔═══════════════════════════════════════════════════════════════════╗
echo ║                  HR TOOL - POKRETANJE FRONTEND-A                 ║
echo ║                                                                   ║
echo ║  Ova skripta će instalirati zavisnosti i pokrenuti frontend     ║
echo ╚═══════════════════════════════════════════════════════════════════╝
echo.

cd /d C:\Users\dimi3\OneDrive\Desktop\praksaproj\hr-tool-frontend

echo [1/2] Instalacija zavisnosti (npm install)...
echo (Ovo može potrajati nekoliko minuta)
echo.

npm install

echo.
echo [2/2] Pokretanje aplikacije (npm start)...
echo.
echo ✓ Frontend će biti dostupan na: http://localhost:3000
echo ✓ Automatski će se otvoriti browser
echo.

npm start

pause

