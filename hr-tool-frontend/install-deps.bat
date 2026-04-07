@echo off
echo.
echo ╔═══════════════════════════════════════════════════════════════════╗
echo ║         HR TOOL FRONTEND - INSTALACIJA ZAVISNOSTI               ║
echo ╚═══════════════════════════════════════════════════════════════════╝
echo.

cd /d C:\Users\dimi3\OneDrive\Desktop\praksaproj\hr-tool-frontend

echo Brisanje stare instalacije...
if exist node_modules rmdir /s /q node_modules
if exist package-lock.json del package-lock.json

echo.
echo Instalacija svih zavisnosti (npm install)...
echo Ovo može potrajati 2-5 minuta...
echo.

npm install

echo.
echo ═══════════════════════════════════════════════════════════════════
echo GOTOVO!
echo Sada možeš pokrenuti: npm start
echo ═══════════════════════════════════════════════════════════════════
echo.

pause

