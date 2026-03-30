#!/bin/bash
echo "TagWolf Plugin Builder"
echo "===================="

if [ -z "$1" ]; then
    echo "Benutzung: ./build.sh [plugin-name]"
    echo "Beispiel: ./build.sh meinplugin"
    exit 1
fi

PLUGIN=$1
SRC="plugins/$PLUGIN/src"
OUT="out"

if [ ! -d "$SRC" ]; then
    echo "Fehler: Plugin $PLUGIN nicht gefunden!"
    exit 1
fi

mkdir -p $OUT

echo "Kompiliere $PLUGIN..."
javac -cp "lib/spigot.jar:api" -d $OUT $SRC/*.java

if [ $? -ne 0 ]; then
    echo "Fehler beim Kompilieren!"
    exit 1
fi

echo "Kopiere plugin.yml..."
cp plugins/$PLUGIN/plugin.yml $OUT/
cp plugins/$PLUGIN/config.yml $OUT/ 2>/dev/null

echo "Erstelle JAR..."
cd $OUT
jar cf ../$PLUGIN.jar *
cd ..

echo ""
echo "Fertig! Plugin ist: $PLUGIN.jar"
echo "Kopiere es in deinen Server (plugins/ Ordner)"
