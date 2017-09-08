package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Ability {
    protected Player player;
    protected double energyCost;
    protected String name;

    public Player getPlayer(){
        return player;
    }
    public String getName(){
        return name;
    }
    public Ability(Player p){
        player = p;
    }
    public double getEnergyCost() {
        return energyCost;
    }
    public void setEnergyCost(double energyCost) {
        this.energyCost = energyCost;
    }
    public void run(){
        player.sendMessage(ChatColor.AQUA + "The ability " + name + " was run.");//Test just to see if event fires.
    }
}
