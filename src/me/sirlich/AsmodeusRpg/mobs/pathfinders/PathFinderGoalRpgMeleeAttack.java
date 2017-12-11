package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.core.RpgPlayerList;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntity;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PathFinderGoalRpgMeleeAttack extends PathfinderGoal
{
    World world;
    protected EntityCreature entityCreature;
    protected int c;
    double walkingSpeed;
    boolean noIdeaButMakeItTrue;
    PathEntity pathEntity;
    private int h;
    private double i;
    private double j;
    private double k;
    protected final int g = 20;

    public PathFinderGoalRpgMeleeAttack(EntityCreature entityCreature, double walkingSpeed) {
        this.entityCreature = entityCreature;
        this.world = entityCreature.world;
        this.walkingSpeed = walkingSpeed;
        this.noIdeaButMakeItTrue = true;
        this.a(3);
    }

    public boolean a() {
        System.out.println("Inside");
        EntityLiving goalTarget = this.entityCreature.getGoalTarget();
        if (goalTarget == null) {
            System.out.println("goalTarget is null");
            return false;
        } else if (!goalTarget.isAlive()) {
            return false;
        } else if(!RpgEntityList.getRpgEntity(entityCreature.getUniqueID()).isAggressive()){
            return false;
        } else {
            System.out.println("Success!");
            this.pathEntity = this.entityCreature.getNavigation().a(goalTarget);
            if (this.pathEntity != null) {
                return true;
            } else {
                return this.a(goalTarget) >= this.entityCreature.d(goalTarget.locX, goalTarget.getBoundingBox().b, goalTarget.locZ);
            }
        }
    }

    public boolean b() {
        EntityLiving goalTarget = this.entityCreature.getGoalTarget();
        if (goalTarget == null) {
            return false;
        } else if (!goalTarget.isAlive()) {
            return false;
        } else if (!this.noIdeaButMakeItTrue) {
            return !this.entityCreature.getNavigation().o();
        } else if (!this.entityCreature.f(new BlockPosition(goalTarget))) {
            return false;
        } else {
            return !(goalTarget instanceof EntityHuman) || !((EntityHuman)goalTarget).isSpectator() && !((EntityHuman)goalTarget).z();
        }
    }

    public void c() {
        this.entityCreature.getNavigation().a(this.pathEntity, this.walkingSpeed);
        this.h = 0;
    }

    public void d() {
        EntityLiving var1 = this.entityCreature.getGoalTarget();
        if (var1 instanceof EntityHuman && (((EntityHuman)var1).isSpectator() || ((EntityHuman)var1).z())) {
            this.entityCreature.setGoalTarget((EntityLiving)null);
        }

        this.entityCreature.getNavigation().p();
    }

    public void e() {
        EntityLiving var1 = this.entityCreature.getGoalTarget();
        this.entityCreature.getControllerLook().a(var1, 30.0F, 30.0F);
        double var2 = this.entityCreature.d(var1.locX, var1.getBoundingBox().b, var1.locZ);
        --this.h;
        if ((this.noIdeaButMakeItTrue || this.entityCreature.getEntitySenses().a(var1)) && this.h <= 0 && (this.i == 0.0D && this.j == 0.0D && this.k == 0.0D || var1.d(this.i, this.j, this.k) >= 1.0D || this.entityCreature.getRandom().nextFloat() < 0.05F)) {
            this.i = var1.locX;
            this.j = var1.getBoundingBox().b;
            this.k = var1.locZ;
            this.h = 4 + this.entityCreature.getRandom().nextInt(7);
            if (var2 > 1024.0D) {
                this.h += 10;
            } else if (var2 > 256.0D) {
                this.h += 5;
            }

            if (!this.entityCreature.getNavigation().a(var1, this.walkingSpeed)) {
                this.h += 15;
            }
        }

        this.c = Math.max(this.c - 1, 0);
        this.a(var1, var2);
    }

    protected void a(EntityLiving var1, double var2) {
        double var4 = this.a(var1);
        if (var2 <= var4 && this.c <= 0) {
            if(var1 != null){
                Player player = Bukkit.getPlayer(var1.getUniqueID());
                Entity entity = (Entity) this.entityCreature;
                onHit(entity,player);
            }
        }

    }

    public void onHit(Entity entity, Player player){
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());
        RpgPlayer rpgPlayer = RpgPlayerList.getRpgPlayer(player);
        rpgPlayer.meleeDamage(4);
        rpgPlayer.knockbackByEntity(0.7,rpgEntity.getLocation());
        rpgEntity.knockbackByEntity(0.3, 0.3,player.getLocation());
    }


    protected double a(EntityLiving var1) {
        return (double)(this.entityCreature.width * 2.0F * this.entityCreature.width * 2.0F + var1.width);
    }
}

