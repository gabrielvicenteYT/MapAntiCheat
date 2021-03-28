package dev.map.anticheat.events;

import com.sun.tools.javac.util.Convert;
import dev.map.anticheat.autoclicker.CPSDetection;
import dev.map.anticheat.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class CompassCPS implements Listener {

    private int taskID;
    private int clicks = 0;
    private int isRunning = 1;
    private int playerclicks;


    @EventHandler
    public void onClick(PlayerInteractEntityEvent event) {
        Entity target = event.getRightClicked();
        String targetname = target.getName();
        Player player = event.getPlayer();


        if (player.getItemInHand().getType().equals(Material.COMPASS)) {

            if (isRunning == 0) {
                player.sendMessage(ChatColor.RED + "Thread is already running.");
            } else {
                taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

                    int index = 11;
                    double averageclicks = 0;

                    public void run() {



                        if (index == 11) {
                            isRunning = 0;
                            CPSDetection.EntityClicks = 0;
                            index--;
                        } else if (index > 0 && index < 11) {

                            clicks = CPSDetection.EntityClicks;
                            player.sendMessage(ChatColor.GRAY + "Clicks: " + ChatColor.DARK_AQUA + clicks);
                            averageclicks = averageclicks + clicks;

                            CPSDetection.EntityClicks = 0;
                            index--;
                        }
                        if (index == 0) {

                            player.sendMessage(target.getName() + ChatColor.GRAY + ": Had an average of " + ChatColor.DARK_AQUA + averageclicks / 10 + " CPS");
                            Bukkit.getScheduler().cancelTask(taskID);
                            isRunning = 1;
                        }

                    }
                }, 0, 20);
            }
        }
    }
}









