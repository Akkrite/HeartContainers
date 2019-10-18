package com.mctourney.heart;

import com.mctourney.heart.cmd.Commands;
import com.mctourney.heart.events.HeartPickup;
import com.mctourney.heart.events.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HeartContainer extends JavaPlugin {
    private static HeartContainer plugin;

    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        this.saveConfig();
        PluginManager pm = Bukkit.getPluginManager();


        pm.registerEvents(new PlayerListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new HeartPickup(), 0, 9);
        getCommand("maxhealth").setExecutor(new Commands());
        getCommand("spawnheart").setExecutor(new Commands());

    }

    public static HeartContainer getInstance() {
        return plugin;
    }

}