package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.items.ItemHandler;
import me.sirlich.AsmodeusRpg.items.RPGItem;
import me.sirlich.AsmodeusRpg.items.RPGWeapon;
import me.sirlich.AsmodeusRpg.utilities.Vector3D;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class RPGDamage implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPrimaryAttack(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (!attackSuccess) {
                e.setCancelled(true);

                Player observer = e.getPlayer();

                int damage = 1;
                double range = 3;

                //Calculate damage from weapon here.

                //RPGWeapon wep /*= RPGItem.getWeapon(observer.getItemInHand())*/;
            /*if (wep != null) {
                damage = wep.getPrimaryDamage().getRandomInt();
                range = wep.getPrimaryRange();
            } else {*/
                ItemStack i = observer.getItemInHand();
                RPGWeapon wep = ItemHandler.getWeaponFromItem(i);
                if (wep != null) {
                    damage = wep.getPrimaryDamage().getRandomInt();
                    System.out.println(damage);
                    range = wep.getPrimaryRange();
                }
                //}

                Location observerPos = observer.getEyeLocation();
                Vector3D observerDir = new Vector3D(observerPos.getDirection());

                Vector3D observerStart = new Vector3D(observerPos);
                Vector3D observerEnd = observerStart.add(observerDir.multiply(range));

                Player hit = null;

                // Get nearby entities
                for (Player target : observer.getWorld().getPlayers()) {
                    // Bounding box of the given player
                    Vector3D targetPos = new Vector3D(target.getLocation());
                    Vector3D minimum = targetPos.add(-0.5, 0, -0.5);
                    Vector3D maximum = targetPos.add(0.5, 1.67, 0.5);

                    if (target != observer && hasIntersection(observerStart, observerEnd, minimum, maximum)) {
                        if (hit == null ||
                                hit.getLocation().distanceSquared(observerPos) >
                                        target.getLocation().distanceSquared(observerPos)) {

                            hit = target;
                            System.out.println(observerPos.distance(targetPos.toVector().toLocation(observer.getWorld())));
                        }
                    }
                }

                // Hit the closest player
                if (hit != null) {
                    PlayerList.getRpgPlayer(hit).editHealth(-1 * damage);
                    hit.setVelocity(observer.getLocation().getDirection().setY(0).normalize().multiply(0));
                }
            } else {
                attackSuccess = false;
            }
        }
    }

    private boolean attackSuccess = false;

    @EventHandler (priority = EventPriority.LOWEST)
    public void onAttack(EntityDamageByEntityEvent e) {
        attackSuccess = true;
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
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        RpgPlayer player = PlayerList.getRpgPlayer(e.getPlayer());
        player.setHealth(player.getMaxHealth());
    }

    private boolean hasIntersection(Vector3D p1, Vector3D p2, Vector3D min, Vector3D max) {
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
