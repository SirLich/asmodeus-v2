package me.sirlich.AsmodeusRpg.mobs.npcs;

import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CivilianHandler implements Listener
{
    /*
    Handles the Villlager interaction
     */
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event)
    {
        if (event.getRightClicked().getScoreboardTags().contains("civilian")) {
            event.setCancelled(true);
            List<String> quoteList = CivilianList.getMessages(event.getRightClicked());
            int index = ThreadLocalRandom.current().nextInt(0, quoteList.size());
            String quote = quoteList.get(index);
            ChatUtils.civilianChat(event.getPlayer(), quote);
        }
    }
}
