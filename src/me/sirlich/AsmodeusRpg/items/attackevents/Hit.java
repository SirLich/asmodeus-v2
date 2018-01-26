package me.sirlich.AsmodeusRpg.items.attackevents;

import me.sirlich.AsmodeusRpg.core.RpgPlayerList;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntity;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.items.AttackEvent;
import me.sirlich.AsmodeusRpg.utilities.Range;
import me.sirlich.AsmodeusRpg.utilities.Vector3D;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Hit extends AttackEvent {

    public Range<Integer> damage;
    public double range;
    public double stamina;
    public double knockback;
    public double knockup;

    @Override
    public Hit finish() {
        this.setDescription(
                "&6&o- Damage: &e&o" + this.damage.getMinimum() + "-" + this.damage.getMaximum(),
                "&6&o- Range: &e&o" + (Double.toString(range).endsWith(".0") ? new Double(range).intValue() : range) + " Blocks",
                "&6&o- Stamina Cost: &e&o" + (Double.toString(stamina).endsWith(".0") ? new Double(stamina).intValue() : stamina));
        return this;
    }

    @Override
    public void execute(Player p) {

        Location observerPos = p.getEyeLocation();
        Vector3D observerDir = new Vector3D(observerPos.getDirection());

        Vector3D observerStart = new Vector3D(observerPos);
        Vector3D observerEnd = observerStart.add(observerDir.multiply(range));

        Entity hit = null;

        // Get nearby entities
        for (Entity e : p.getWorld().getNearbyEntities(observerPos, range + 3, range + 3, range + 3)) {
            // Bounding box of the given player
            Vector3D targetPos = new Vector3D(e.getLocation());
            Vector3D minimum = targetPos.add(-0.5, 0, -0.5);
            Vector3D maximum = targetPos.add(0.5, 1.67, 0.5);

            if (e != p && hasIntersection(observerStart, observerEnd, minimum, maximum)) {
                if ((hit == null ||
                        hit.getLocation().distanceSquared(observerPos) >
                                e.getLocation().distanceSquared(observerPos)) && !e.isDead()) {

                    hit = e;
                }
            }
        }


        // DamageReaction the closest player
        if (hit != null) {
            if (hit instanceof Player) {
                RpgPlayer rpgPlayer = RpgPlayerList.getRpgPlayer((Player) hit);
                rpgPlayer.meleeDamage(damage.getRandomInt());
                rpgPlayer.knockbackByEntity(knockback,knockup,p.getLocation());
                System.out.println(hit.getName());
            } else {
                RpgEntity rpgEntity = RpgEntityList.getRpgEntity(hit);
                //rpgEntity.damageResponse();
                rpgEntity.knockbackByEntity(knockback,knockup,p.getLocation());
                //rpgEntity.meleeDamageEntity(damage.getRandomInt(), (Player) hit);
                System.out.println(rpgEntity.getName());
            }

            //DamageReaction.setVelocity(observer.getLocation().getDirection().setY(0).normalize().multiply(0));
        }

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

    public Range<Integer> getDamage() {
        return this.damage;
    }

    public Hit setDamage(int minDamage, int maxDamage) {
        this.damage = new Range<>(minDamage, maxDamage, null);
        return this;
    }

    public double getRange() {
        return this.range;
    }

    public Hit setRange(double range) {
        this.range = range;
        return this;
    }

    public double getStamina() {
        return this.stamina;
    }

    public Hit setStamina(double stamina) {
        this.stamina = stamina;
        return this;
    }

    public double getKnockback() {
        return this.knockback;
    }

    public Hit setKnockback(double knockback) {
        this.knockback = knockback;
        return this;
    }

    public double getKnockup() {
        return this.knockup;
    }

    public Hit setKnockup(double knockup) {
        this.knockup = knockup;
        return this;
    }

}
