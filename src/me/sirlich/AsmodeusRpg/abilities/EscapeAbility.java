package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class EscapeAbility extends Ability
{
    private Player player;
    public EscapeAbility(Player p) {
        super(p);
        this.player = super.getPlayer();
    }
    @Override
    public void run(){
        System.out.println("Event run!");
        player.setVelocity(player.getLocation().getDirection().multiply(new Vector(-3,0,-3)).add(new Vector(0,0.5,0)));
        player.sendMessage("hello!");
    }

    @Override
    public int getRechargeRate()
    {
        return 50;
    }
}
