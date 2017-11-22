package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.items.ItemHandler;
import me.sirlich.AsmodeusRpg.items.RPGWeapon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerAttackEntityHandler implements Listener
{
    @EventHandler
    public void playerAttack(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            event.setCancelled(true);
            Player player = (Player) event.getDamager();
            ItemStack i = player.getInventory().getItemInMainHand();
            RPGWeapon wep = ItemHandler.getWeaponFromItem(i);
            me.sirlich.AsmodeusRpg.core.RpgEntity rpgEntity = me.sirlich.AsmodeusRpg.core.RpgEntityList.getRpgEntity(event.getEntity().getUniqueId());
            LivingEntity livingEntity = (LivingEntity) event.getEntity();
            livingEntity.damage(0);
            if (wep != null) {
                System.out.println("Hit a mob!");
                double damage = wep.getPrimaryDamage().getRandomInt();
                rpgEntity.knockbackByEntity(0.4,0.3,player.getLocation());
                rpgEntity.meleeDamageEntity(damage);
                rpgEntity.setAggressive(true);
            } else {
                rpgEntity.knockbackByEntity(0.2,0.2,player.getLocation());
                rpgEntity.meleeDamageEntity(10);
                rpgEntity.setAggressive(true);
            }
        }
    }
}
