package dev.map.anticheat.autoclicker;

import dev.map.anticheat.methods.isDiggingMethod;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;

import java.util.HashMap;

public class CPSDetection implements Listener {


   public static int EntityClicks;


    @EventHandler
    public void onClick(PlayerAnimationEvent event) {
        Player player = event.getPlayer();


        if (event.getAnimationType() == PlayerAnimationType.ARM_SWING) {
            if (isDiggingMethod.isDigging(isDiggingMethod.Digging)) {
                EntityClicks++;



            }
        }
    }
}





