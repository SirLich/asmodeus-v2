package me.sirlich.AsmodeusRpg.core;
import java.util.HashMap;

import me.sirlich.AsmodeusRpg.abilities.Ability;
import me.sirlich.AsmodeusRpg.abilities.DoubleJumpAbility;
import org.bukkit.entity.Player;

public class PlayerList {
    public static HashMap <Player,RpgPlayer> playerMap = new HashMap<>();
    public static HashMap <RpgPlayer,Player> rpgPlayerMap = new HashMap<>();

    public static void addPlayer(Player p){
        RpgPlayer p2 = new RpgPlayer();

        Ability toggleFlyAbility = new Ability(p);
        Ability switchHandAbility = new DoubleJumpAbility(p);
        Ability dropWeaponAbility = new Ability(p);

        p2.setFlyAbility(toggleFlyAbility);
        p2.setSwapAbility(switchHandAbility);
        p2.setDropAbility(dropWeaponAbility);
        playerMap.put(p,p2);
        rpgPlayerMap.put(p2,p);
    }
    public static RpgPlayer getRpgPlayer(Player p){
        return playerMap.get(p);
    }
    public static Player getPlayer(RpgPlayer p){
        return rpgPlayerMap.get(p);
    }
}
