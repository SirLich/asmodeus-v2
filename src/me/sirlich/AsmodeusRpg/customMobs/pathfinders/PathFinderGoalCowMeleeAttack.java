package me.sirlich.AsmodeusRpg.customMobs.pathfinders;


import me.sirlich.AsmodeusRpg.customMobs.events.AggressiveCowMeleeAttackPlayerEvent;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PathFinderGoalCowMeleeAttack extends PathfinderGoal
{
    World a;
    protected EntityCreature b;
    protected int c;
    double d;
    boolean e;
    PathEntity f;
    private int h;
    private double i;
    private double j;
    private double k;
    protected final int g = 20;

    public PathFinderGoalCowMeleeAttack(EntityCreature var1, double var2, boolean var4) {
        this.b = var1;
        this.a = var1.world;
        this.d = var2;
        this.e = var4;
        this.a(3);
    }

    public boolean a() {
        EntityLiving var1 = this.b.getGoalTarget();
        if (var1 == null) {
            return false;
        } else if (!var1.isAlive()) {
            return false;
        } else {
            this.f = this.b.getNavigation().a(var1);
            if (this.f != null) {
                return true;
            } else {
                return this.a(var1) >= this.b.d(var1.locX, var1.getBoundingBox().b, var1.locZ);
            }
        }
    }

    public boolean b() {
        EntityLiving var1 = this.b.getGoalTarget();
        if (var1 == null) {
            return false;
        } else if (!var1.isAlive()) {
            return false;
        } else if (!this.e) {
            return !this.b.getNavigation().o();
        } else if (!this.b.f(new BlockPosition(var1))) {
            return false;
        } else {
            return !(var1 instanceof EntityHuman) || !((EntityHuman)var1).isSpectator() && !((EntityHuman)var1).z();
        }
    }

    public void c() {
        this.b.getNavigation().a(this.f, this.d);
        this.h = 0;
    }

    public void d() {
        EntityLiving var1 = this.b.getGoalTarget();
        if (var1 instanceof EntityHuman && (((EntityHuman)var1).isSpectator() || ((EntityHuman)var1).z())) {
            this.b.setGoalTarget((EntityLiving)null);
        }

        this.b.getNavigation().p();
    }

    public void e() {
        EntityLiving var1 = this.b.getGoalTarget();
        this.b.getControllerLook().a(var1, 30.0F, 30.0F);
        double var2 = this.b.d(var1.locX, var1.getBoundingBox().b, var1.locZ);
        --this.h;
        if ((this.e || this.b.getEntitySenses().a(var1)) && this.h <= 0 && (this.i == 0.0D && this.j == 0.0D && this.k == 0.0D || var1.d(this.i, this.j, this.k) >= 1.0D || this.b.getRandom().nextFloat() < 0.05F)) {
            this.i = var1.locX;
            this.j = var1.getBoundingBox().b;
            this.k = var1.locZ;
            this.h = 4 + this.b.getRandom().nextInt(7);
            if (var2 > 1024.0D) {
                this.h += 10;
            } else if (var2 > 256.0D) {
                this.h += 5;
            }

            if (!this.b.getNavigation().a(var1, this.d)) {
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
                AggressiveCowMeleeAttackPlayerEvent cowEvent = new AggressiveCowMeleeAttackPlayerEvent(player, this.b.getBukkitEntity());
                Bukkit.getPluginManager().callEvent(cowEvent);
            }

            this.c = 20;
            this.b.a(EnumHand.MAIN_HAND);
            this.b.B(var1);
        }

    }
    protected double a(EntityLiving var1) {
        return (double)(this.b.width * 2.0F * this.b.width * 2.0F + var1.width);
    }
}
