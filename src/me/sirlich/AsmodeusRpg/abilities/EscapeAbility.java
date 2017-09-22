package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class EscapeAbility extends Ability
{
    private Player player;
    public EscapeAbility(Player p) {
        super(p);
        this.player = super.getPlayer();
        setName("Escape");
        setRechargeRate(150);
    }
    @Override
    public void run(){
        player.setVelocity(player.getLocation().getDirection().multiply(new Vector(-3,0,-3)).add(new Vector(0,0.5,0)));
    }
}
