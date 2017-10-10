package me.sirlich.AsmodeusRpg.customMobs.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RpgCowDamagedEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    public Entity getEntity(){
        return entity;
    }

    private final Entity entity;

    public RpgCowDamagedEvent(Entity entity){
        this.entity = entity;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
