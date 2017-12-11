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
            if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("health")){
                if (event.getRightClicked() instanceof LivingEntity) {
                    event.setCancelled(true);
                    Entity entity = event.getRightClicked();
                    if(RpgEntityList.doesEntityExist(entity.getUniqueId())){
                        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueId());
                        event.getPlayer().sendMessage(" " + rpgEntity.getHealth());
                    }
                }
            }
        }
    }
}
