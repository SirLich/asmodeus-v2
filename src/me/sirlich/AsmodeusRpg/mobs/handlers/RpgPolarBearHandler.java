package me.sirlich.AsmodeusRpg.mobs.handlers;

import me.sirlich.AsmodeusRpg.core.RpgPlayerList;
import me.sirlich.AsmodeusRpg.core.RpgEntityList;
import me.sirlich.AsmodeusRpg.mobs.events.RpgPolarBearDamagedEvent;
import me.sirlich.AsmodeusRpg.mobs.events.RpgPolarBearMeleeAttackPlayerEvent;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RpgPolarBearHandler implements Listener
{
    @EventHandler
    public void polarBearAttack(RpgPolarBearMeleeAttackPlayerEvent event){
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_POLAR_BEAR_HURT, 1,1);
        RpgPlayerList.getRpgPlayer(event.getPlayer()).knockbackByEntity(0.2,event.getEntity().getLocation());
        RpgEntityList.getRpgEntity(event.getEntity().getUniqueId()).knockbackByEntity(0.3, 0.3,event.getPlayer().getLocation());
        RpgPlayerList.getRpgPlayer(event.getPlayer()).meleeDamage(12);
    }
    @EventHandler
    public void polarBearAttacked(RpgPolarBearDamagedEvent event){
        RpgEntityList.getRpgEntity(event.getEntity().getUniqueId()).setAggressive(true);
    }
}
