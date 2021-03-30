package dev.map.anticheat.autoclicker;

import dev.map.anticheat.methods.DiggingListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;

import java.util.HashMap;
import java.util.UUID;

public class CPSDetection implements Listener {



   public static HashMap<UUID, Integer> clicks = new HashMap<UUID, Integer>();


    @EventHandler
    public void onClick(PlayerAnimationEvent event) {
        Player player = event.getPlayer();


        if (event.getAnimationType() == PlayerAnimationType.ARM_SWING) {
            if (DiggingListener.isDigging(DiggingListener.digging)) {
                if (clicks.containsKey(player.getUniqueId())){
                    clicks.put(player.getUniqueId(), clicks.get(player.getUniqueId()) + 1);

                } else {
                    clicks.put(player.getUniqueId(), 0);
                }




            }
        }
    }
}





