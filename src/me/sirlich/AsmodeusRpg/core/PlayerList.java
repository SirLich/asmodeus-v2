package me.sirlich.AsmodeusRpg.core;
import java.util.HashMap;

import me.sirlich.AsmodeusRpg.abilities.Ability;
import me.sirlich.AsmodeusRpg.abilities.DoubleJumpAbility;
import org.bukkit.entity.Player;

public class PlayerList {
    public static HashMap <Player,RpgPlayer> playerMap = new HashMap<Player,RpgPlayer>();
    public static void addPlayer(Player p){
        RpgPlayer p2 = new RpgPlayer();

        Ability toggleFlyAbility = new Ability(p);
        Ability switchHandAbility = new DoubleJumpAbility(p);
        Ability dropWeaponAbility = new Ability(p);

        p2.setToggleFlyAbility(toggleFlyAbility);
        p2.setSwitchHandAbility(switchHandAbility);
        p2.setDropWeaponAbility(dropWeaponAbility);


        playerMap.put(p,p2);
    }
    public static RpgPlayer getPlayer(Player p){
        return playerMap.get(p);
    }
}
