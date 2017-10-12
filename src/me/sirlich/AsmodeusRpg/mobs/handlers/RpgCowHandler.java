package me.sirlich.AsmodeusRpg.mobs.handlers;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgEntityList;
import me.sirlich.AsmodeusRpg.mobs.events.RpgCowDamagedEvent;
import me.sirlich.AsmodeusRpg.mobs.events.RpgCowMeleeAttackPlayerEvent;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RpgCowHandler implements Listener
{
    @EventHandler
    public void cowAttack(RpgCowMeleeAttackPlayerEvent event){
        System.out.println("Cow attack");
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_COW_HURT, 1,1);
        PlayerList.getRpgPlayer(event.getPlayer()).meleeDamage(4);
        PlayerList.getRpgPlayer(event.getPlayer()).knockbackByEntity(0.7,event.getEntity().getLocation());
        RpgEntityList.getRpgEntity(event.getEntity().getUniqueId()).knockbackByEntity(0.3, 0.3,event.getPlayer().getLocation());
    }
    @EventHandler
    public void cowAttacked(RpgCowDamagedEvent event){
        System.out.println("USED!");
        RpgEntityList.getRpgEntity(event.getEntity().getUniqueId()).setAggression(true);
    }
}
