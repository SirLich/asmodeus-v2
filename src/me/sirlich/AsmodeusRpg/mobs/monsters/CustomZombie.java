package me.sirlich.AsmodeusRpg.mobs.monsters;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityShootBowEvent;

// Requires NMSUtils to be available! https://pastebin.com/V6G1B4cg
// Make sure you implement IRangedEntity if you want to make the mob shoot arrows!
public class CustomZombie extends EntityZombie implements IRangedEntity
{

    public CustomZombie(World world)
    {
        super(world);
    }


    /*
    @Override
    protected void initAttributes() {
        // Calling the super method for the rest of the attributes.
        super.initAttributes();
        // Next, overriding armor and max health!
        // Setting the max health to 40:
        this.getAttributeInstance(NMSUtils.Attributes.MAX_HEALTH.getValue()).setValue(40.0);
        // Setting the 'defense' (armor) to 5:
        this.getAttributeInstance(NMSUtils.Attributes.ARMOR.getValue()).setValue(5.0);
    }
    */

    @Override

    protected void r()
    {
        // Adding our custom pathfinder selectors.
        // Grants our zombie the ability to swim.
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        // This causes our zombie to shoot arrows.
        // The parameters are: The ranged entity, movement speed, cooldown,
        // maxDistance
        // Or, with the second constructor: The ranged entity, movement speed,
        // mincooldown, maxcooldown, maxDistance
        this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, 1.0, 12, 20));
        // Gets our zombie to attack creepers and skeletons!
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityCreeper.class, true));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntitySkeleton.class, true));
        // Causes our zombie to walk towards it restriction.
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0));
        // Causes the zombie to walk around randomly.
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0));
        // Causes the zombie to look at players. Optional in our case. Last
        // argument is range.
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0f));
        // Causes the zombie to randomly look around.
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public void a(final EntityLiving target, final float f)
    {
        // Preparing the projectile
        final EntityArrow entityarrow = this.prepareProjectile(f);
        // Calculating the motion for the arrow to hit
        final double motX = target.locX - this.locX;
        final double motY = target.getBoundingBox().b + target.length / 3.0f - entityarrow.locY;
        final double motZ = target.locZ - this.locZ;
        final double horizontalMot = MathHelper.sqrt(motX * motX + motZ * motZ);
        // 'Shooting' the projectile (aka preparing it for being added to the
        // world.)
        entityarrow.shoot(motX, motY + horizontalMot * 0.2, motZ, 1.6f, 14 - world.getDifficulty().a() * 4);

        // OPTIONAL! Calls the event for shooting, that can be cancelled. I'd
        // keep it for other plugins that could cancel it.
        final EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent(this, this.getItemInMainHand(),
                entityarrow, 0.8f);
        if (event.isCancelled()) {
            event.getProjectile().remove();
            return;
        }
        // Checking if the projectile has been changed thru the event..
        if (event.getProjectile() == entityarrow.getBukkitEntity()) {
            this.world.addEntity(entityarrow);
        }
        // And last, playing the shooting sound.
        this.a(SoundEffects.fV, 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
    }

    protected EntityArrow prepareProjectile(final float unknown)
    {
        // Creating the arrow instance. Now, you see, it's a Tipped Arrow. No
        // idea why, but EntityArrow is abstract and we can't instantiate it
        // without creating a custom class.
        // This is why the arrows nowadays have the odd particle effect!
        final EntityArrow arrow = new EntityTippedArrow(this.world, this);
        // No idea what this does, copied from the sourcecode
        arrow.a(this, unknown);
        return arrow;
    }

    @Override
    public GroupDataEntity prepare(DifficultyDamageScaler dds, GroupDataEntity gde)
    {
        // Calling the super method FIRST, so in case it changes the equipment,
        // our equipment overrides it.
        gde = super.prepare(dds, gde);
        // We'll set the main hand to a bow and head to a pumpkin now!
        this.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.BOW));
        this.setSlot(EnumItemSlot.HEAD, new ItemStack(Blocks.PUMPKIN));
        // Last, returning the GroupDataEntity called gde.
        return gde;
    }
}
