# OnlinePlayerList
Online Player List Minecraft Plugin (mein erstes Plugin)

## Funktionen
* Aktuelle Playerlist, Server-Status [Online/Offline] und Uptime/Downtime seit letzten start/stop auf einer Website
* Verkürzte join und quit Chat-Messages: +Name -Name
* Spielername des Nachrichtensenders wird im Chat golden angezeigt, um Name und eigentliche Nachricht schneller zu unterscheiden 
* Fehlgeschlagene Login-Versuche werden im Chat angezeigt z.B. nicht auf der Whitelist

## Config
.../plugins/OnlinePlayerList/config.yml
* api_url muss angegeben werden
* alle Funktionen können aktiviert/deaktiviert werden

## API
Folgende Daten werden an die in der config.yml hinterlegte Adresse gesendet:
* Server Start: ?event=start&version=[]&maxplayer=[]&whitelist=[true/false]
* Server Stop: ?event=stop
* Player Join: ?event=join&name=[]
* Player Quit: ?event=quit&name=[]
* Fehlgeschlagener Login-Versuch: ?event=join_failed&name=[]


## Bilder
![](chat.png  "New im Vergleich zu Vanilla")