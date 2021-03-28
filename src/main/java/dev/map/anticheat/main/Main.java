package dev.map.anticheat.main;

import dev.map.anticheat.autoclicker.CPSDetection;
import dev.map.anticheat.events.CompassCPS;
import dev.map.anticheat.methods.isDiggingMethod;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new isDiggingMethod(), this);
        Bukkit.getPluginManager().registerEvents(new CPSDetection(), this);
        Bukkit.getPluginManager().registerEvents(new CompassCPS(), this);
    }
}
