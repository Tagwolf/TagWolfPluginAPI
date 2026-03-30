@echo off
echo TagWolf Plugin Builder
echo ====================

if "%1"=="" (
    echo Benutzung: build.bat [plugin-name]
    echo Beispiel: build.bat meinplugin
    exit /b
)

set PLUGIN=%1
set SRC=plugins\%PLUGIN%\src
set OUT=out

if not exist "%SRC%" (
    echo Fehler: Plugin %PLUGIN% nicht gefunden!
    exit /b
)

mkdir %OUT% 2>nul

echo Kompiliere %PLUGIN%...
javac -cp "lib/spigot.jar;api" -d %OUT% %SRC%\*.java

if errorlevel 1 (
    echo Fehler beim Kompilieren!
    exit /b
)

echo Kopiere plugin.yml...
copy plugins\%PLUGIN%\plugin.yml %OUT%\ >nul
copy plugins\%PLUGIN%\config.yml %OUT%\ >nul 2>nul

echo Erstelle JAR...
cd %OUT%
jar cf ..\%PLUGIN%.jar *
cd ..

echo.
echo Fertig! Plugin ist: %PLUGIN%.jar
echo Kopiere es in deinen Server (plugins/ Ordner)
pause
