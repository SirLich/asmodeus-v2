package me.sirlich.AsmodeusRpg.customMobs.pathfinders;

import me.sirlich.AsmodeusRpg.customMobs.events.RpgCowMeleeAttackPlayerEvent;
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
        RpgCowMeleeAttackPlayerEvent cowEvent = new RpgCowMeleeAttackPlayerEvent(player, this.b.getBukkitEntity());
        Bukkit.getPluginManager().callEvent(cowEvent);
    }
}
