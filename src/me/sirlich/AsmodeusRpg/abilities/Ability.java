package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.entity.Player;

public class Ability {
    private Player player;
    private int rechargeRate;
    private String name;
    private String rechargeMessage;
    private String useMessage;

    public Ability(Player player, int rechargeRate, String name, String rechargeMessage, String useMessage){
        this.player = player;
        this.rechargeRate = rechargeRate;
        this.rechargeMessage = rechargeMessage;
        this.name = name;
        this.useMessage = useMessage;

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

    public int getRechargeRate()
    {
        return rechargeRate;
    }

    public String getUseMessage(){
        return useMessage;
    }

    public void run(){
        System.out.println(name + "was run by player " + player);
    }

}
