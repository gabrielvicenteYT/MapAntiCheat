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

import java.util.HashMap;
import java.util.UUID;


public class CompassCPS implements Listener {

    private int taskID;
    private int clicks = 0;
    private int playerclicks;
    private HashMap<UUID, Integer> isRunning = new HashMap<UUID, Integer>();

    @EventHandler
    public void onClick(PlayerInteractEntityEvent event) {
        Entity target = event.getRightClicked();
        String targetname = target.getName();
        Player player = event.getPlayer();


        if (target instanceof Player) {
            if (player.getItemInHand().getType().equals(Material.COMPASS)) {
                if (!isRunning.containsKey(player.getUniqueId())) {
                    isRunning.put(player.getUniqueId(), 0);
                } else {
                    if (isRunning.get(player.getUniqueId()) == 1) {
                        player.sendMessage(ChatColor.RED + "Thread is already running.");
                    } else {
                        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

                            int index = 11;
                            double averageclicks = 0;

                            public void run() {

                                if (index == 11) {
                                    isRunning.put(player.getUniqueId(), 0);
                                    CPSDetection.Clicks.put(target.getUniqueId(), 0);
                                    index--;
                                    isRunning.put(player.getUniqueId(), 1);
                                } else if (index > 0 && index < 11) {

                                    clicks = CPSDetection.Clicks.get(target.getUniqueId());
                                    player.sendMessage(ChatColor.GRAY + "Clicks: " + ChatColor.DARK_AQUA + clicks);
                                    averageclicks = averageclicks + clicks;
                                    CPSDetection.Clicks.put(target.getUniqueId(), 0);
                                    index--;
                                }
                                if (index == 0) {

                                    player.sendMessage(target.getName() + ChatColor.GRAY + ": Had an average of " + ChatColor.DARK_AQUA + averageclicks / 10 + " CPS");
                                    Bukkit.getScheduler().cancelTask(taskID);
                                    isRunning.put(player.getUniqueId(), 0);
                                }

                            }
                        }, 0, 20);
                    }
                }
            }
        }
    }
}













