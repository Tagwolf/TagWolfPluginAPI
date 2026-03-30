package com.tagwolf.meinplugin;

import com.tagwolf.TagWolfAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Main extends TagWolfAPI implements Listener {
    
    @Override
    public void onStart() {
        log("Plugin gestartet!");
        
        event(this);
        
        cmd("hallo", (sender, args) -> {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                msg(p, "Hallo " + p.getName() + "!");
            } else {
                msg(sender, "Hallo Console!");
            }
            return true;
        });
        
        cmd("heal", (sender, args) -> {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.setHealth(20);
                p.setFoodLevel(20);
                msg(p, "§aDu wurdest geheilt!");
            } else {
                msg(sender, "Nur Spieler können das nutzen!");
            }
            return true;
        });
        
        String welcome = cfg().getString("welcome-message");
        log("Willkommensnachricht: " + welcome);
    }
    
    @Override
    public void onStop() {
        log("Plugin gestoppt!");
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        String msg = cfg().getString("welcome-message");
        if (msg != null) {
            p.sendMessage("§a" + msg.replace("{player}", p.getName()));
        }
    }
}
