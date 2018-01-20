package me.sirlich.AsmodeusRpg.cancellers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

public class CancelFallDamage implements Listener
{
    @EventHandler
    public void playerBlockDamage(EntityDamageByBlockEvent event){
        event.setCancelled(true);
    }
}
