package me.sirlich.AsmodeusRpg.customMobs.npcs;

import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class CivilianHandler implements Listener
{
    /*
    Handles the Villlager interaction
     */
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getScoreboardTags().contains("civilian")) {
            event.setCancelled(true);
            ChatUtils.civilianChat(event.getPlayer(),"For now, all villagers say the same thing.");
        }
    }
}
