package me.sirlich.AsmodeusRpg.customMobs.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AggressiveCowMeleeAttackPlayerEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();

    public Player getPlayer()
    {
        return player;
    }

    private final Player player;

    public AggressiveCowMeleeAttackPlayerEvent(Player player){
        this.player = player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
