package me.sirlich.AsmodeusRpg.abilities;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

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
        getPlayer().playSound(getPlayer().getLocation(), Sound.ENTITY_PARROT_IMITATE_CREEPER, 1, 1);
        List<Entity> nearby = getPlayer().getNearbyEntities(MAX_RANGE, MAX_RANGE, MAX_RANGE);
        for (Entity entity : nearby) {
            if (entity instanceof Damageable && entity.getType() != EntityType.ARMOR_STAND && entity.getType() != EntityType.VILLAGER) {
                RpgEntityList.getRpgEntity(entity.getUniqueId()).knockbackByEntity(2, 0.6,getPlayer().getLocation());
            }
        }
    }
}
