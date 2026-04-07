@echo off
echo.
echo Obrisavanje Maven cache-a...
cd /d C:\Users\dimi3\OneDrive\Desktop\praksaproj\hr-tool

if exist .m2 rmdir /s /q .m2
if exist target rmdir /s /q target

echo.
echo Preuzimanje zavisnosti...
mvn clean install -DskipTests

echo.
echo GOTOVO!
echo.
pause

