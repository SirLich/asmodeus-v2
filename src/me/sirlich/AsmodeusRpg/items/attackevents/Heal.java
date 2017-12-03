package me.sirlich.AsmodeusRpg.items.attackevents;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.core.RpgPlayerList;
import me.sirlich.AsmodeusRpg.items.AttackEvent;
import me.sirlich.AsmodeusRpg.utilities.ActionBarUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Heal extends AttackEvent {

    public int recovery;
    public int cooldownValue;
    public double stamina;

    public Heal setRecovery(int recovery) {
        this.recovery = recovery;
        return this;
    }

    public Heal setCooldown(int cooldown) {
        this.cooldownValue = cooldown;
        return this;
    }

    public Heal setStamina(double stamina) {
        this.stamina = stamina;
        return this;
    }

    @Override
    public AttackEvent finish() {
        this.setDescription(
                "&6&o- Spell: &e&oHeal",
                "&6&o- Recovery: &e&o" + recovery + " health",
                "&6&o- Cooldown: &e&o" + cooldownValue + " seconds");
        return this;
    }

    @Override
    public void execute(Player p) {
        if (isUsable) {
            RpgPlayer rpgPlayer = RpgPlayerList.getRpgPlayer(p);
            rpgPlayer.rawHeal(recovery);
            rpgPlayer.getPlayer().getWorld().spawnParticle(Particle.HEART, rpgPlayer.getPlayer().getLocation().add(0, 1, 0), 3, 0.2, 0.2, 0.2);
            startCooldown();
        } else {
            ActionBarUtil.sendActionBar(p, "&2[&a!&2] &fYou can use this again in " + (cooldown + 1) + " seconds.");
        }
    }

    public boolean isUsable = true;
    public int cooldown = 0;

    int id = 0;

    public void startCooldown() {

        cooldown = cooldownValue;
        isUsable = false;

        id = Bukkit.getScheduler().runTaskTimerAsynchronously(AsmodeusRpg.getInstance(), new Runnable() {
            @Override
            public void run() {

                if (cooldown > 0) {
                    cooldown -= 1;
                } else {
                    isUsable = true;
                    Bukkit.getScheduler().cancelTask(id);
                }

            }
        }, 0L, 20L).getTaskId();

    }

}
