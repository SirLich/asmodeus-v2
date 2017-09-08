package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.entity.Player;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;

public class DoubleJumpAbility extends Ability
{
    public DoubleJumpAbility(Player p) {
        super(p);
        player = super.getPlayer();
        name = "Drop something";
    }
    @Override
    public void run(){
        ChatUtils.chatInfo(player, "You tried to drop an item I see.");
    }
}
