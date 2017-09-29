package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.customMobs.monsters.TestMob;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class TestMobSpawn extends AsmodeusCommand
{
    public TestMobSpawn()
    {
        super("test_mob_spawn");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] argv)
    {
        if(sender instanceof Player){
            Player player = (Player) sender;
            World world = Bukkit.getServer().getWorld("world");
            Location loc = player.getLocation();
            TestMob testMob = new TestMob(((CraftWorld) world).getHandle());
            testMob.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
            ((CraftWorld) world).addEntity(testMob, CreatureSpawnEvent.SpawnReason.CUSTOM);
            System.out.println("Test Mob successfully added.");
        } else {
            System.out.println("Please only use this command in game!");
        }
        return true;
    }
}
