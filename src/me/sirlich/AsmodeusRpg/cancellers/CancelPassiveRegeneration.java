package me.sirlich.AsmodeusRpg.cancellers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class CancelPassiveRegeneration implements Listener
{
    @EventHandler
    public void entityRegainHealthEvent(EntityRegainHealthEvent event)
    {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

}
