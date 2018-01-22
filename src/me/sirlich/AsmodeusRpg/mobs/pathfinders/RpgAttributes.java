package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import net.minecraft.server.v1_12_R1.AttributeRanged;
import net.minecraft.server.v1_12_R1.IAttribute;

public class RpgAttributes
{
    public static final IAttribute level;

    static {
        level = (new AttributeRanged((IAttribute)null, "rpg.level", 1.0D, 0.0D, 10.0D)).a(true);
    }
}
