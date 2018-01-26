package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.core.RpgPlayerList;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntity;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PFGMeleeAttack extends PathfinderGoal {
    World a;
    protected EntityCreature entityCreature;
    private  RpgEntity rpgEntity;
    protected int c;

    private double walkSpeed = 1;
    boolean e;
    PathEntity f;
    private int h;
    private double i;
    private double j;
    private double k;
    protected final int g = 20;

    public PFGMeleeAttack(EntityCreature entityCreature) {
        this.entityCreature = entityCreature;
        this.a = entityCreature.world;
        this.e = true;
        this.a(3);
    }

    public boolean a() {
        EntityLiving var1 = this.entityCreature.getGoalTarget();
        if (var1 == null) {
            return false;
        } else if (!var1.isAlive()) {
            return false;
        } else {
            this.f = this.entityCreature.getNavigation().a(var1);
            if (this.f != null) {
                return true;
            } else {
                return this.a(var1) >= this.entityCreature.d(var1.locX, var1.getBoundingBox().b, var1.locZ);
            }
        }
    }

    public boolean b() {
        EntityLiving var1 = this.entityCreature.getGoalTarget();
        if (var1 == null) {
            return false;
        } else if (!var1.isAlive()) {
            return false;
        } else if (!this.e) {
            return !this.entityCreature.getNavigation().o();
        } else if (!this.entityCreature.f(new BlockPosition(var1))) {
            return false;
        } else {
            return !(var1 instanceof EntityHuman) || !((EntityHuman)var1).isSpectator() && !((EntityHuman)var1).z();
        }
    }

    public void c() {
        this.entityCreature.getNavigation().a(this.f, this.walkSpeed);
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
        if ((this.e || this.entityCreature.getEntitySenses().a(var1)) && this.h <= 0 && (this.i == 0.0D && this.j == 0.0D && this.k == 0.0D || var1.d(this.i, this.j, this.k) >= 1.0D || this.entityCreature.getRandom().nextFloat() < 0.05F)) {
            this.i = var1.locX;
            this.j = var1.getBoundingBox().b;
            this.k = var1.locZ;
            this.h = 4 + this.entityCreature.getRandom().nextInt(7);
            if (var2 > 1024.0D) {
                this.h += 10;
            } else if (var2 > 256.0D) {
                this.h += 5;
            }

            if (!this.entityCreature.getNavigation().a(var1, this.walkSpeed)) {
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
        rpgPlayer.meleeDamage(rpgEntity.getMeleeDamage());
        rpgPlayer.knockbackByEntity(rpgEntity.getMeleeKnockbackGiven(),rpgEntity.getLocation());
        rpgEntity.knockbackByEntity(rpgEntity.getMeleeKnockbackTaken(), 0.3,player.getLocation());
    }

    protected double a(EntityLiving var1) {
        return (double)(this.entityCreature.width * 2.0F * this.entityCreature.width * 2.0F + var1.width);
    }
}



