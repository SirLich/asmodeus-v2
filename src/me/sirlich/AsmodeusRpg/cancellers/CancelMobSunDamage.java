package me.sirlich.AsmodeusRpg.cancellers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class CancelMobSunDamage implements Listener
{
    @EventHandler
    public void onSunburn(EntityDamageEvent event){
        if(event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onLight(EntityCombustEvent event){
        event.setCancelled(true);
    }
}
