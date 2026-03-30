## Voraussetzungen
- Java JDK 8 oder höher
- Spigot/Paper Server (1.13+)

## Schnellstart
1. `git clone https://github.com/Tagwolf/TagWolfPluginAPI.git`
2. Lade `spigot.jar` in den `lib/` Ordner
3. Erstelle ein Plugin: `./new-plugin.sh meinplugin` (Linux) oder `new-plugin.bat meinplugin` (Windows)
4. Baue es: `./build.sh meinplugin` oder `build.bat meinplugin`

## Struktur
- `api/` – Die API-Klassen
- `plugins/` – Hier landen deine Plugins
- `lib/` – Benötigte JARs (spigot.jar)
- `out/` – Temporäre Build-Dateien
# Beispiel (mit wget)
wget -P lib/ https://hub.spigotmc.org/nexus/content/repositories/snapshots/org/spigotmc/spigot-api/1.20.4-R0.1-SNAPSHOT/spigot-api-1.20.4-R0.1-SNAPSHOT.jar
# WIR SIND IN DER BETA!
