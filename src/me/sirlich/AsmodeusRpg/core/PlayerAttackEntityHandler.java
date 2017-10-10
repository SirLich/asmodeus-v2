package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.items.ItemHandler;
import me.sirlich.AsmodeusRpg.items.RPGWeapon;
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
            RpgEntity rpgEntity = RpgEntityList.getRpgEntity(event.getEntity().getUniqueId());
            if (wep != null) {
                System.out.println("Hit a mob!");
                double damage = wep.getPrimaryDamage().getRandomInt();
                rpgEntity.damageRespone();
                if(event.getEntity().isOnGround()){
                    rpgEntity.knockbackByEntity(0.4,0.3,player.getLocation());
                } else{
                    rpgEntity.knockbackByEntity(0.4,0,player.getLocation());
                }
            } else {
                rpgEntity.damageRespone();
                rpgEntity.knockbackByEntity(0.2,0.2,player.getLocation());
            }
        }
    }
}
