package me.sirlich.AsmodeusRpg.customMobs.handlers;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgEntityList;
import me.sirlich.AsmodeusRpg.customMobs.events.AggressiveCowMeleeAttackPlayerEvent;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AggressiveCowHandler implements Listener
{
    @EventHandler
    public void cowAttack(AggressiveCowMeleeAttackPlayerEvent event){
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_COW_HURT, 1,1);
        PlayerList.getRpgPlayer(event.getPlayer()).meleeDamage(4);
        PlayerList.getRpgPlayer(event.getPlayer()).knockbackByEntity(0.7,event.getEntity().getLocation());
        RpgEntityList.getRpgEntity(event.getEntity().getUniqueId()).knockbackByEntity(0.3, 0.3,event.getPlayer().getLocation());
    }
}
