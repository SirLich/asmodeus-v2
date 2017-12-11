package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntity;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


/*
This class handles all instances of players dealing damage to mobs in classic ways (like hitting them)
 */

public class PlayerAttackEntityHandler implements Listener
{
    @EventHandler
    public void playerAttack(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            event.setCancelled(true);
            Player player = (Player) event.getDamager();
            RpgEntity rpgEntity = RpgEntityList.getRpgEntity(event.getEntity().getUniqueId());
            LivingEntity livingEntity = (LivingEntity) event.getEntity();

            System.out.println("DamageReaction a mob!");
            double damage = 3;
            rpgEntity.knockbackByEntity(0.4,0.3,player.getLocation());
            rpgEntity.meleeDamageEntity(damage, player);
            rpgEntity.setAggressive(true);
        }
    }
}
