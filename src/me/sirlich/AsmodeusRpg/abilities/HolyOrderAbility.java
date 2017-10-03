package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class HolyOrderAbility extends Ability
{
    private final int MAX_RANGE = 5;
    private final int POWER = 1;

    public HolyOrderAbility(Player player)
    {
        super(player);
        setName("Holy Order");
        setRechargeRate(100);
    }

    @Override
    public void run()
    {
        List<Entity> nearby = getPlayer().getNearbyEntities(MAX_RANGE, MAX_RANGE, MAX_RANGE);
        for (Entity entity : nearby) {
            if (entity instanceof Damageable && entity.getType() != EntityType.ARMOR_STAND && entity.getType() != EntityType.VILLAGER) {
                Location playerLoc = getPlayer().getLocation();
                Location entityLoc = entity.getLocation();
                Double slope = ((entityLoc.getZ() - playerLoc.getZ()) / (entityLoc.getX() - playerLoc.getX()));
                Vector vec = new Vector(-Math.abs(Math.cos(slope) * POWER), 0.5, -(Math.abs(Math.sin(slope) * POWER)));
                entity.setVelocity(vec);
            }
        }
    }
}
