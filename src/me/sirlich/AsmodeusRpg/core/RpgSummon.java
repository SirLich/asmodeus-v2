package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.mobs.damageResponses.RpgCowDamageResponse;
import me.sirlich.AsmodeusRpg.mobs.damageResponses.RpgPolarBearDamageResponse;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgCow;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgPolarBear;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgPolarBearRider;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
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
                    rpgEntity.setMaxHealth(50);
                    rpgEntity.setToFullHealth();
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
                    rpgEntity.setMaxHealth(50);
                    rpgEntity.setToFullHealth();
                    rpgEntity.setDamageResponse(new RpgPolarBearDamageResponse(polarBear.getUniqueID()));
                    polarBear.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                    ((CraftWorld) world).addEntity(polarBear, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    System.out.println("polar_bear successfully added.");
                    ChatUtils.chatInfo(player, "Spawned in a polar_bear at your location!");
                } else if(args[0].equalsIgnoreCase("polar_bear_rider")){
                    World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
                    Location loc = player.getLocation();
                    RpgPolarBearRider polarBearRider = new RpgPolarBearRider(((CraftWorld) world).getHandle());
                    RpgEntityList.addEntity(polarBearRider.getUniqueID());
                    RpgEntity rpgEntity = RpgEntityList.getRpgEntity(polarBearRider.getUniqueID());
                    rpgEntity.setName("Polar Bear Rider");
                    rpgEntity.setAggression(false);
                    rpgEntity.setMaxHealth(50);
                    rpgEntity.setToFullHealth();
                    rpgEntity.setDamageResponse(new RpgPolarBearDamageResponse(polarBearRider.getUniqueID()));
                    polarBearRider.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                    ((CraftWorld) world).addEntity(polarBearRider, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    System.out.println("polar_bear_rider successfully added.");
                    ChatUtils.chatInfo(player, "Spawned in a polar_bear_rider at your location!");
                } else if(args[0].equalsIgnoreCase("polar_bear_and_rider")){

                    World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
                    Location loc = player.getLocation();
                    //Bear
                    RpgPolarBearRider polarBearRider = new RpgPolarBearRider(((CraftWorld) world).getHandle());
                    RpgEntityList.addEntity(polarBearRider.getUniqueID());
                    RpgEntity rpgEntity = RpgEntityList.getRpgEntity(polarBearRider.getUniqueID());
                    rpgEntity.setName("Polar Bear Rider");
                    rpgEntity.setAggression(false);
                    rpgEntity.setMaxHealth(50);
                    rpgEntity.setToFullHealth();
                    rpgEntity.setDamageResponse(new RpgPolarBearDamageResponse(polarBearRider.getUniqueID()));
                    polarBearRider.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                    //Rider
                    RpgPolarBear polarBear = new RpgPolarBear(((CraftWorld) world).getHandle());
                    RpgEntityList.addEntity(polarBear.getUniqueID());
                    RpgEntity rpgBearEntity = RpgEntityList.getRpgEntity(polarBear.getUniqueID());
                    rpgBearEntity.setName("Polar Bear");
                    rpgBearEntity.setAggression(false);
                    rpgBearEntity.setMaxHealth(50);
                    rpgBearEntity.setToFullHealth();
                    rpgBearEntity.setDamageResponse(new RpgPolarBearDamageResponse(polarBear.getUniqueID()));
                    polarBear.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());

                    ((CraftWorld) world).addEntity(polarBear, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    ((CraftWorld) world).addEntity(polarBearRider, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    polarBearRider.startRiding(polarBear);
                    System.out.println("polar_bear_and_rider successfully added.");
                    ChatUtils.chatInfo(player, "Spawned in a polar_bear_and_rider at your location!");
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
