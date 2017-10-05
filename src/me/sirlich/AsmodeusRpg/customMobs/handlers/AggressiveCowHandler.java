package me.sirlich.AsmodeusRpg.customMobs.handlers;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.customMobs.events.AggressiveCowMeleeAttackPlayerEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AggressiveCowHandler implements Listener
{
    @EventHandler
    public void cowAttack(AggressiveCowMeleeAttackPlayerEvent event){
        PlayerList.getRpgPlayer(event.getPlayer()).meleeDamage(4);
    }
}
