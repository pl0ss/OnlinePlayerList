package de.kev.OnlinePlayerList.listeners;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import de.kev.OnlinePlayerList.main.Main;


public class JoinAndQuitListener implements Listener {

	static String api_url = Main.api_url;
	static boolean online_player_list_toggle = Main.online_player_list_toggle;
	static boolean short_player_join_and_leave_toggle = Main.short_player_join_and_leave_toggle;
	static boolean announce_failed_logins_toggle = Main.announce_failed_logins_toggle;
	
	@EventHandler
    public void normalLogin(PlayerLoginEvent event) throws IOException {
		if(online_player_list_toggle){
			if(event.getResult() == PlayerLoginEvent.Result.ALLOWED) { // wegen whitelist
				String event_type = "join";
				String name = event.getPlayer().getName();
				//Bukkit.broadcastMessage(event_type + ": " + name);
				Request(event_type, name);
			} else { // z.B. nicht auf der whitelist
				String event_type = "join_failed";
				String name = event.getPlayer().getName();
				if(announce_failed_logins_toggle){
					Bukkit.broadcastMessage("§6? §e" + name + " §6tried to join the game");
				}
				Request(event_type, name);
			}
		}
    }
	
	@EventHandler
    public void normalLogin(PlayerQuitEvent event) throws IOException {
		if(online_player_list_toggle){
			String event_type = "quit";
    	    String name = event.getPlayer().getName();
			//Bukkit.broadcastMessage(event_type + ": " + name);
    	    Request(event_type, name);
		}
    }
	
	private void Request(String event_type, String name) throws IOException {
        URL url = new URL(api_url + "?event=" + event_type + "&name=" + name);
	    //Bukkit.broadcastMessage("Request done! " + event_type + " " + name);
        URLConnection con = url.openConnection();
        con.getInputStream();
        con.connect();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if(short_player_join_and_leave_toggle){
			event.setJoinMessage("§2+ §e" + event.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void onJoin(PlayerQuitEvent event) {
		if(short_player_join_and_leave_toggle){
			event.setQuitMessage("§4- §e" + event.getPlayer().getName());
		}
	}
}
