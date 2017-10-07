package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.customMobs.monsters.AggressiveCow;
import me.sirlich.AsmodeusRpg.customMobs.npcs.Blacksmith;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class RpgSummon extends AsmodeusCommand
{
    public RpgSummon()
    {
        super("RpgSummon");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        System.out.println("First");
        if(args[0] != null){
            if(sender instanceof  Player){
                Player player = (Player) sender;
                if(args[0].equalsIgnoreCase("cow")){
                    World world = Bukkit.getServer().getWorld("world");
                    Location loc = player.getLocation();
                    AggressiveCow cow = new AggressiveCow(((CraftWorld) world).getHandle());
                    RpgEntityList.addEntity(cow.getUniqueID());
                    RpgEntity rpgEntity = RpgEntityList.getRpgEntity(cow.getUniqueID());
                    rpgEntity.setName("Aggresive Cow");
                    cow.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                    ((CraftWorld) world).addEntity(cow, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    System.out.println("Cow successfully added.");
                    ChatUtils.chatInfo(player, "Spawned in a cow at your location!");
                }

            } else{
                System.out.println("Please only call this command in-game");
            }

        } else {
            String msg = "Spawn in a mob: cow";
            if(sender instanceof Player){
                sender.sendMessage("msg");
            }
            System.out.println("msg");
        }
        return true;
    }
}
