package de.kev.OnlinePlayerList.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.kev.OnlinePlayerList.main.Main;

public class Chat implements Listener{

    static boolean colored_chat_name_toggle = Main.colored_chat_name_toggle;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if(colored_chat_name_toggle){
            String player_name = event.getPlayer().getDisplayName();
            String message = event.getMessage();
            event.setFormat("§6" + player_name + "§f " + message);
        }
    }
}
