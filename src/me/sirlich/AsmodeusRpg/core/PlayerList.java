package me.sirlich.AsmodeusRpg.core;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import me.sirlich.AsmodeusRpg.abilities.Ability;
import me.sirlich.AsmodeusRpg.abilities.DoubleJumpAbility;
import org.bukkit.entity.Player;
import sun.misc.UUDecoder;

public class PlayerList {

    private static HashSet<UUID> uuidHashSet = new HashSet<>();
    public static HashMap <Player,RpgPlayer> rpgPlayerHashMap = new HashMap<>();
    public static HashMap <RpgPlayer,Player> playerHashMap = new HashMap<>();

    public static void addPlayer(Player p){
        RpgPlayer p2 = new RpgPlayer();
        Ability toggleFlyAbility = new Ability(p);
        Ability switchHandAbility = new DoubleJumpAbility(p);
        Ability dropWeaponAbility = new Ability(p);

        p2.setFlyAbility(toggleFlyAbility);
        p2.setSwapAbility(switchHandAbility);
        p2.setDropAbility(dropWeaponAbility);

        rpgPlayerHashMap.put(p,p2);
        playerHashMap.put(p2,p);
        uuidHashSet.add(p.getUniqueId());
    }

    public static RpgPlayer getRpgPlayer(Player player){
        return rpgPlayerHashMap.get(player);
    }

    public static Player getPlayer(RpgPlayer rpgPlayer){
        System.out.println("Inside get player! inside playerLiast");
        Player player = playerHashMap.get(rpgPlayer);
        System.out.println(player.getName());
        return player;
    }

    public static boolean isPlayerOnline(UUID uuid){
        System.out.println("Querrying for online player");
        return !uuidHashSet.contains(uuid);
    }
}
