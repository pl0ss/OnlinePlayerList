package de.kev.OnlinePlayerList.main;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.kev.OnlinePlayerList.listeners.JoinAndQuitListener;
import de.kev.OnlinePlayerList.listeners.Chat;

public class Main extends JavaPlugin{
	

        //config file
        private File configFile;
        private FileConfiguration config;

        private void createconfig() {
            configFile = new File(getDataFolder(), "config.yml");
            if(!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                saveResource("config.yml", false);
            }
    
            config = new YamlConfiguration();
            try {
                config.load(configFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
        //

        public static String api_url;
        public static boolean online_player_list_toggle;
        public static boolean short_player_join_and_leave_toggle;
        public static boolean announce_failed_logins_toggle;
        public static boolean colored_chat_name_toggle;

        public void onEnable(){
            System.out.println("[OnlinePlayerList] by Kev");
            
            createconfig();
            api_url = this.getConfig().getString("api_url");
            //online_player_list_toggle = this.getConfig().getString("online_player_list") != null;
            if(this.getConfig().getString("online_player_list") == "true"){online_player_list_toggle = true;} else {online_player_list_toggle = false;}
            //short_player_join_and_leave_toggle = this.getConfig().getString("short_player_join_and_leave") != null;
            if(this.getConfig().getString("short_player_join_and_leave") == "true"){short_player_join_and_leave_toggle = true;} else {short_player_join_and_leave_toggle = false;}
            //announce_failed_logins_toggle = this.getConfig().getString("announce_failed_logins") != null;
            if(this.getConfig().getString("announce_failed_logins") == "true"){announce_failed_logins_toggle = true;} else {announce_failed_logins_toggle = false;}
            //colored_chat_name_toggle = this.getConfig().getString("colored_chat_name") != null;
            if(this.getConfig().getString("colored_chat_name") == "true"){colored_chat_name_toggle = true;} else {colored_chat_name_toggle = false;}

            PluginManager pluginManager = Bukkit.getPluginManager();
            pluginManager.registerEvents(new JoinAndQuitListener(), this); 
            pluginManager.registerEvents(new Chat(), this); 

            try {
                if(online_player_list_toggle){
                    FirstLoadEvent();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public void onDisable(){
            try {
                if(online_player_list_toggle){
                    LastLaodEvent();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public static void FirstLoadEvent() throws IOException{
        URL url = new URL(api_url +"?event=start" + "&version=" + Bukkit.getServer().getVersion().replace(" ", "%20") + "&maxplayer=" + Bukkit.getServer().getMaxPlayers() + "&whitelist=" + Bukkit.getServer().hasWhitelist());
        URLConnection con = url.openConnection();
        con.getInputStream();
        con.connect();
        //Bukkit.broadcastMessage("FirstLoad: EventON");
        //System.out.println("[OnlinePlayerList] FirstLoad: EventON");
	}
     
	public static void LastLaodEvent() throws IOException{
        URL url = new URL(api_url + "?event=stop");
        URLConnection con = url.openConnection();
        con.getInputStream();
        con.connect();
        //Bukkit.broadcastMessage("LastLaod: EventOFF");
        //System.out.println("[OnlinePlayerList] LastLaod: EventOFF");
	}
}


