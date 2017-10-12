package me.sirlich.AsmodeusRpg.cancellers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class CancelMobDrops implements Listener
{
    @EventHandler
    public void mobDeath(EntityDeathEvent event){
        event.getDrops().clear();
    }
}
