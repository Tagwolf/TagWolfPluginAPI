package com.tagwolf;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import java.io.File;
import java.util.*;

public abstract class TagWolfAPI extends JavaPlugin {
    
    private Config config;
    
    @Override
    public void onEnable() {
        config = new Config(this);
        onStart();
    }
    
    @Override
    public void onDisable() {
        onStop();
    }
    
    public abstract void onStart();
    public abstract void onStop();
    
    public void cmd(String name, CmdExecutor exec) {
        Objects.requireNonNull(getCommand(name)).setExecutor((sender, cmd, label, args) -> {
            return exec.run(sender, args);
        });
    }
    
    public interface CmdExecutor {
        boolean run(CommandSender sender, String[] args);
    }
    
    public void event(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
    
    public class Config {
        private YamlConfiguration yaml;
        private File file;
        
        public Config(JavaPlugin plugin) {
            file = new File(plugin.getDataFolder(), "config.yml");
            if (!file.exists()) {
                plugin.saveResource("config.yml", false);
            }
            yaml = YamlConfiguration.loadConfiguration(file);
        }
        
        public String getString(String path) { return yaml.getString(path); }
        public int getInt(String path) { return yaml.getInt(path); }
        public boolean getBoolean(String path) { return yaml.getBoolean(path); }
        public void set(String path, Object value) { yaml.set(path, value); }
        public void save() { try { yaml.save(file); } catch (Exception e) {} }
        public void reload() { yaml = YamlConfiguration.loadConfiguration(file); }
    }
    
    public Config cfg() { return config; }
    
    public void msg(CommandSender sender, String msg) { sender.sendMessage("§7[§6Plugin§7] §f" + msg); }
    public void msg(Player p, String msg) { p.sendMessage("§7[§6Plugin§7] §f" + msg); }
    public void log(String msg) { getLogger().info(msg); }
    public void error(String msg) { getLogger().severe(msg); }
}
