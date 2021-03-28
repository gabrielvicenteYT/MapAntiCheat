package dev.map.anticheat.methods;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class isDiggingMethod implements Listener {

    public static int Digging = 0;

    @EventHandler
    public int onDig(PlayerInteractEvent event){
        if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            Digging = 1;
        } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR){
            Digging = 0;
        }
        return Digging;
    }
    public static Boolean isDigging(int Digging){
        if (Digging == 1){
            return false;
        }
        return true;
    }
}
