package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.entity.Player;

public class Ability {
    private Player player;
    private int rechargeRate;
    private String name;
    private String rechargeMessage;

    public Ability(Player player, int rechargeRate, String name, String rechargeMessage){
        this.player = player;
        this.rechargeRate = rechargeRate;
        this.rechargeMessage = rechargeMessage;
        this.name = name;
    }
    public Ability(Player p){
        player = p;
    }

    public Player getPlayer(){
        return player;
    }

    public String getName(){
        return name;
    }

    public String getRechargeMessage(){
        return rechargeMessage;
    }

    public int getRechargeRate(int level)
    {
        return rechargeRate;
    }

    public void run(){
        System.out.println(name + "was run by player " + player);
    }

}
