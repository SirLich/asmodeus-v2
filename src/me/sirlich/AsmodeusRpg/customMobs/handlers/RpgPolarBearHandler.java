package me.sirlich.AsmodeusRpg.customMobs.handlers;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgEntityList;
import me.sirlich.AsmodeusRpg.customMobs.events.RpgCowMeleeAttackPlayerEvent;
import me.sirlich.AsmodeusRpg.customMobs.events.RpgPolarBearDamagedEvent;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RpgPolarBearHandler implements Listener
{
    @EventHandler
    public void polarBearAttack(RpgCowMeleeAttackPlayerEvent event){
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_POLAR_BEAR_HURT, 1,1);
        PlayerList.getRpgPlayer(event.getPlayer()).meleeDamage(12);
        PlayerList.getRpgPlayer(event.getPlayer()).knockbackByEntity(-0.2,event.getEntity().getLocation());
        RpgEntityList.getRpgEntity(event.getEntity().getUniqueId()).knockbackByEntity(0.3, 0.3,event.getPlayer().getLocation());
    }
    @EventHandler
    public void polarBearAttacked(RpgPolarBearDamagedEvent event){
        RpgEntityList.getRpgEntity(event.getEntity().getUniqueId()).setAggression(true);
    }
}
