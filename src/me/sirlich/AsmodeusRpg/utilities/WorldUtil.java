package me.sirlich.AsmodeusRpg.utilities;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class WorldUtil
{
    public static Entity getEntityByUniqueId(UUID uuid) {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getUniqueId().equals(uuid))
                    return entity;
            }
        }
        return null;
    }
}
