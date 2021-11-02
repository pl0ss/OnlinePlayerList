# OnlinePlayerList
<p>Online Player List Minecraft Plugin (mein erstes Plugin)</p>

<h2>Funktionen</h2>
<ul>
<li>Aktuelle Playerlist, Server-Status [Online/Offline] und Uptime/Downtime seit letzten start/stop auf einer Website</li>
<li>Verkürzte join und quit Chat-Messages: +Name -Name</li>
<li>Spielername des Nachrichtensenders wird im Chat golden angezeigt, um Name und eigentliche Nachricht schneller zu unterscheiden </li>
<li>Fehlgeschlagene Login-Versuche werden im Chat angezeigt z.B. nicht auf der Whitelist</li>
</ul>

<h2>Config</h2
<p>.../plugins/OnlinePlayerList/config.yml</p>
<p>api_url muss angegeben werden <br>
alle Funktionen können aktiviert/deaktiviert werden</p>

<h2>API</h2>
<p>Folgende Daten werden an die in der config.yml hinterlegte adresse gesendet:</p>
<ul>
  <li>Server Start: ?event=start&version=[]&maxplayer=[]&whitelist=[true/false]</li>
  <li>Server Stop: ?event=stop</li>
  <li>Player Join: ?event=join&name=[]</li>
  <li>Player Quit: ?event=quit&name=[]</li>
  <li>Fehlgeschlagener Login-Versuch: ?event=join_failed&name=[]</li>
</ul>
