package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.items.ItemHandler;
import me.sirlich.AsmodeusRpg.items.RPGItem;
import me.sirlich.AsmodeusRpg.items.RPGWeapon;
import me.sirlich.AsmodeusRpg.items.attackevents.Hit;
import me.sirlich.AsmodeusRpg.utilities.Vector3D;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class RPGDamage implements Listener
{

    private boolean attackSuccess = false;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrimaryAttack(PlayerInteractEvent e)
    {
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (!attackSuccess) {

                Player p = e.getPlayer();
                if (ItemHandler.getWeaponFromItem(p.getItemInHand()) == null) {
                    new Hit().setDamage(1, 1).setRange(3).setStamina(0).setKnockback(0.3).setKnockup(0.25).execute(p);
                    System.out.println("fist");
                } else if (ItemHandler.getWeaponFromItem(p.getItemInHand()).getPrimaryEvent() == null) {
                    new Hit().setDamage(1, 1).setRange(3).setStamina(0).setKnockback(0.3).setKnockup(0.25).execute(p);
                    System.out.println("no primary attack");
                } else {
                    RPGWeapon wep = ItemHandler.getWeaponFromItem(p.getItemInHand());
                    wep.getPrimaryEvent().execute(p);
                    System.out.println("you're a failure");
                }

            } else {
                attackSuccess = false;
            }
        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            Player p = e.getPlayer();
            if (ItemHandler.getWeaponFromItem(p.getItemInHand()) != null) {
                if (ItemHandler.getWeaponFromItem(p.getItemInHand()).getSecondaryEvent() != null) {
                    RPGWeapon wep = ItemHandler.getWeaponFromItem(p.getItemInHand());
                    wep.getSecondaryEvent().execute(p);
                    System.out.println("you're a failure");
                }
            }

        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAttack(EntityDamageByEntityEvent e)
    {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            int damage;
            double knockback;
            double knockup;
            if (ItemHandler.getWeaponFromItem(p.getItemInHand()) == null) {
                damage = 1;
                knockback = 0.3;
                knockup = 0.25;
                System.out.println("fist");
            } else if (ItemHandler.getWeaponFromItem(p.getItemInHand()).getPrimaryEvent() == null) {
                damage = 1;
                knockback = 0.3;
                knockup = 0.25;
                System.out.println("no primary attack");
            } else if (ItemHandler.getWeaponFromItem(p.getItemInHand()).getPrimaryEvent() instanceof Hit) {
                RPGWeapon wep = ItemHandler.getWeaponFromItem(p.getItemInHand());
                Hit event = (Hit) wep.getPrimaryEvent();
                damage = event.getDamage().getRandomInt();
                knockback = event.getKnockback();
                knockup = event.getKnockup();
                System.out.println("youve got mail");
            } else {
                damage = 0;
                knockback = 0;
                knockup = 0;
            }
            e.setCancelled(true);
            attackSuccess = true;
            if (e.getEntity() instanceof Player) {
                RpgPlayer rpgPlayer = PlayerList.getRpgPlayer((Player) e.getEntity());
                rpgPlayer.meleeDamage(damage);
                rpgPlayer.knockbackByEntity(knockback, knockup, p.getLocation());
                System.out.println(e.getEntity().getName());
            } else {
                RpgEntity rpgEntity = RpgEntityList.getRpgEntity(e.getEntity());
                //rpgEntity.damageResponse();
                rpgEntity.knockbackByEntity(knockback,knockup,p.getLocation());
                rpgEntity.meleeDamageEntity(damage);
                System.out.println(rpgEntity.getName());
            }
            /*attackSuccess = true;
            e.setCancelled(true);
            ItemStack i = ((Player) e.getDamager()).getItemInHand();
            int damage = 1;
            RPGWeapon wep = ItemHandler.getWeaponFromItem(i);
            if (wep != null) {
                damage = wep.getPrimaryDamage().getRandomInt();
                System.out.println(damage);
            }
            if (e.getEntity() instanceof Player) {
                PlayerList.getRpgPlayer((Player) e.getEntity()).editHealth(-1 * damage);
                if (e.getDamager() instanceof Player) {
                    Player damager = (Player) e.getDamager();

                    Entity p = e.getEntity();

                    p.setVelocity(damager.getLocation().getDirection().setY(0).normalize().multiply(0));
                }
            }*/
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onDeath(PlayerDeathEvent e)
    {
        e.setDeathMessage("");
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e)
    {
        RpgPlayer player = PlayerList.getRpgPlayer(e.getPlayer());
        player.setHealth(player.getMaxHealth());
    }

    private boolean hasIntersection(Vector3D p1, Vector3D p2, Vector3D min, Vector3D max)
    {
        final double epsilon = 0.0001f;

        Vector3D d = p2.subtract(p1).multiply(0.5);
        Vector3D e = max.subtract(min).multiply(0.5);
        Vector3D c = p1.add(d).subtract(min.add(max).multiply(0.5));
        Vector3D ad = d.abs();

        if (Math.abs(c.x) > e.x + ad.x)
            return false;
        if (Math.abs(c.y) > e.y + ad.y)
            return false;
        if (Math.abs(c.z) > e.z + ad.z)
            return false;

        if (Math.abs(d.y * c.z - d.z * c.y) > e.y * ad.z + e.z * ad.y + epsilon)
            return false;
        if (Math.abs(d.z * c.x - d.x * c.z) > e.z * ad.x + e.x * ad.z + epsilon)
            return false;
        if (Math.abs(d.x * c.y - d.y * c.x) > e.x * ad.y + e.y * ad.x + epsilon)
            return false;

        return true;
    }

}
