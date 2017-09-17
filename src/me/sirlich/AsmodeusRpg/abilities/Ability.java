package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.entity.Player;

public class Ability {
    private Player player;
    private int rechargeRate;
    private String name;

    public Ability(Player p){
        player = p;
    }

    public Player getPlayer()
    {
        return player;
    }
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public int getRechargeRate()
    {
        return rechargeRate;
    }
    public void setRechargeRate(int rechargeRate)
    {
        this.rechargeRate = rechargeRate;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public void run(){
        System.out.println(name + " was run by player " + player);
    }

}
