package me.sirlich.AsmodeusRpg.core;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class CancelHunger implements Listener
{
    @EventHandler
    public void playerItemConsumeEvent(PlayerItemConsumeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void stopHungerEvent(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }
}
