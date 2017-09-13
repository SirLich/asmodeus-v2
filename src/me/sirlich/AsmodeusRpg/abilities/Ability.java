package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.entity.Player;

public class Ability {
    protected Player player;
    protected int rechargeRate;
    protected String name;
    public Ability(Player player, int rechargeRate, String name){
        this.player = player;
        this.rechargeRate = rechargeRate;
        this.name = name;
    }
    public Player getPlayer(){
        return player;
    }
    public String getName(){
        return name;
    }
    public Ability(Player p){
        player = p;
    }
    public int getRechargeRate(int level)
    {
        return rechargeRate;
    }
    public void run(){
        System.out.println(name + "was run by player " + player);
    }
}
