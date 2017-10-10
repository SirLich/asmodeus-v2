package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.customMobs.damageResponses.RpgCowDamageResponse;
import me.sirlich.AsmodeusRpg.customMobs.damageResponses.RpgPolarBearDamageResponse;
import me.sirlich.AsmodeusRpg.customMobs.monsters.RpgCow;
import me.sirlich.AsmodeusRpg.customMobs.monsters.RpgPolarBear;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
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
        if(args[0] != null){
            if(sender instanceof  Player){
                Player player = (Player) sender;
                if(args[0].equalsIgnoreCase("cow")){
                    World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
                    Location loc = player.getLocation();
                    RpgCow cow = new RpgCow(((CraftWorld) world).getHandle());
                    RpgEntityList.addEntity(cow.getUniqueID());
                    RpgEntity rpgEntity = RpgEntityList.getRpgEntity(cow.getUniqueID());
                    rpgEntity.setName("Aggresive Cow");
                    rpgEntity.setAggression(false);
                    rpgEntity.setDamageResponse(new RpgCowDamageResponse(cow.getUniqueID()));
                    cow.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                    ((CraftWorld) world).addEntity(cow, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    System.out.println("Cow successfully added.");
                    ChatUtils.chatInfo(player, "Spawned in a cow at your location!");
                } else if(args[0].equalsIgnoreCase("polar_bear")){
                    World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
                    Location loc = player.getLocation();
                    RpgPolarBear polarBear = new RpgPolarBear(((CraftWorld) world).getHandle());
                    RpgEntityList.addEntity(polarBear.getUniqueID());
                    RpgEntity rpgEntity = RpgEntityList.getRpgEntity(polarBear.getUniqueID());
                    rpgEntity.setName("Polar Bear");
                    rpgEntity.setAggression(false);
                    rpgEntity.setDamageResponse(new RpgPolarBearDamageResponse(polarBear.getUniqueID()));
                    polarBear.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                    ((CraftWorld) world).addEntity(polarBear, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    System.out.println("polar_bear successfully added.");
                    ChatUtils.chatInfo(player, "Spawned in a polar_bear at your location!");
                }

            } else{
                System.out.println("Please only call this command in-game");
            }

        } else {
            String msg = "Spawn in a mob: cow, polar_bear";
            if(sender instanceof Player){
                sender.sendMessage("msg");
            }
            System.out.println("msg");
        }
        return true;
    }
}
