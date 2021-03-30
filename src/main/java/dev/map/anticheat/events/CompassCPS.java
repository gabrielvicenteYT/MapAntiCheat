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
    private HashMap<UUID, Boolean> playerIDisRunningMap = new HashMap<UUID, Boolean>();

    @EventHandler
    public void onClick(PlayerInteractEntityEvent event) {
        Entity target = event.getRightClicked();
        String targetname = target.getName();
        Player player = event.getPlayer();


        if (target instanceof Player) {
            if (player.getItemInHand().getType().equals(Material.COMPASS)) {
                if (!playerIDisRunningMap.containsKey(player.getUniqueId())) {
                    playerIDisRunningMap.put(player.getUniqueId(), false);
                } else {
                    if (playerIDisRunningMap.get(player.getUniqueId()) == true) {
                        player.sendMessage(ChatColor.RED + "Thread is already running.");
                    } else {
                        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

                            int index = 11;
                            double averageclicks = 0;

                            public void run() {

                                if (index == 11) {
                                    playerIDisRunningMap.put(player.getUniqueId(), false);
                                    CPSDetection.clicks.put(target.getUniqueId(), 0);
                                    index--;
                                    playerIDisRunningMap.put(player.getUniqueId(), true);
                                } else if (index > 0 && index < 11) {

                                    clicks = CPSDetection.clicks.get(target.getUniqueId());
                                    player.sendMessage(ChatColor.GRAY + "Clicks: " + ChatColor.DARK_AQUA + clicks);
                                    averageclicks = averageclicks + clicks;
                                    CPSDetection.clicks.put(target.getUniqueId(), 0);
                                    index--;
                                }
                                if (index == 0) {

                                    player.sendMessage(target.getName() + ChatColor.GRAY + ": Had an average of " + ChatColor.DARK_AQUA + averageclicks / 10 + " CPS");
                                    Bukkit.getScheduler().cancelTask(taskID);
                                    playerIDisRunningMap.put(player.getUniqueId(), false);
                                }

                            }
                        }, 0, 20);
                    }
                }
            }
        }
    }
}













