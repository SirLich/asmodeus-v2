package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RpgSummon extends AsmodeusCommand
{
    public RpgSummon()
    {
        super("RpgSummon");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        if(args != null){
            if(sender instanceof  Player){
                Player player = (Player) sender;
                if(args[0].equalsIgnoreCase("cow")){
                    net.minecraft.server.v1_12_R1.Entity entity = MobCreator.makeCow(20,3,"Bobby!",14);
                    MobCreator.spawn(entity, player.getLocation());
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
/*
World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
                    Location loc = player.getLocation();
                    RpgPolarBear polarBear = new RpgPolarBear(((CraftWorld) world).getHandle());
                    RpgEntityList.addEntity(polarBear.getUniqueID());
                    RpgEntity rpgEntity = RpgEntityList.getRpgEntity(polarBear.getUniqueID());
                    rpgEntity.setName("Polar Bear");
                    rpgEntity.setAggressive(false);
                    rpgEntity.setMaxHealth(50);
                    rpgEntity.setToFullHealth();
                    polarBear.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                    ((CraftWorld) world).addEntity(polarBear, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    System.out.println("polar_bear successfully added.");
                    ChatUtils.chatInfo(player, "Spawned in a polar_bear at your location!");
 */
