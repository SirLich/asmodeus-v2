package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import me.sirlich.AsmodeusRpg.mobs.events.RpgPolarBearMeleeAttackPlayerEvent;
import net.minecraft.server.v1_12_R1.EntityCreature;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PathFinderGoalRpgPolarBearMeleeAttack extends PathFinderGoalRpgMeleeAttack
{

    public PathFinderGoalRpgPolarBearMeleeAttack(EntityCreature var1, double var2, boolean var4)
    {
        super(var1, var2, var4);
    }

    @Override
    public void handleHit(Player player){
        RpgPolarBearMeleeAttackPlayerEvent attackEvent = new RpgPolarBearMeleeAttackPlayerEvent(player, this.b.getBukkitEntity());
        Bukkit.getPluginManager().callEvent(attackEvent);
    }
}
