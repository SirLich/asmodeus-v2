package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntity;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DebugStick implements Listener
{
    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand() != null){
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (event.getRightClicked() instanceof LivingEntity) {
                event.setCancelled(true);
                Entity entity = event.getRightClicked();
                if(RpgEntityList.containEntity(entity.getUniqueId())){
                    if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("health")){
                        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueId());
                        event.getPlayer().sendMessage(" " + rpgEntity.getHealth());
                    } else if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("maxhealth")){
                        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueId());
                        event.getPlayer().sendMessage(" " + rpgEntity.getHealth());
                    } else if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("level")){
                        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueId());
                        event.getPlayer().sendMessage(" " + rpgEntity.getLevel());
                    } else if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("level")){
                        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueId());
                        event.getPlayer().sendMessage(" " + rpgEntity.getLevel());
                    } else if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("meleedamage")){
                        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueId());
                        event.getPlayer().sendMessage(" " + rpgEntity.getMeleeDamage());
                    } else if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("HealthRegeneration")){
                        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueId());
                        event.getPlayer().sendMessage(" " + rpgEntity.getHealthRegeneration());
                    }
                }
            }
        }
    }
}
