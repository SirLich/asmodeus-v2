package me.sirlich.AsmodeusRpg.mobs.monsters;

import me.sirlich.AsmodeusRpg.mobs.pathfinders.PFGHop;
import me.sirlich.AsmodeusRpg.mobs.pathfinders.PFGMeleeAttack;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftSilverfish;

public class RpgCritter extends EntitySilverfish
{
    public RpgCritter(World world){
        super(world);
        this.bukkitEntity = new RpgCritter.CraftRpgCritter(this.world.getServer(),this);
    }

    @Override
    public void r(){
        this.goalSelector.a(1,new PFGHop(this));
        this.goalSelector.a(2, new PFGMeleeAttack(this));
    }

    private static class CraftRpgCritter extends CraftSilverfish
    {
        private CraftRpgCritter(CraftServer server, EntitySilverfish parent)
        {
            super(server, parent);
        }

    }
}
