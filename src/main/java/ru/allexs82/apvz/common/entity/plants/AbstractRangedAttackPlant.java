package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.world.World;
import ru.allexs82.apvz.common.entity.projectile.PVZProjectileEntity;
import ru.allexs82.apvz.core.ModCore;
import ru.allexs82.apvz.core.ModSounds;
import ru.allexs82.apvz.utils.TickConvertor;

public abstract class AbstractRangedAttackPlant extends PVZPlantEntity implements RangedAttackMob {
    protected AbstractRangedAttackPlant(EntityType<? extends PVZPlantEntity> entityType, World world) {
        super(entityType, world);
    }

    protected AbstractRangedAttackPlant(EntityType<? extends PVZPlantEntity> entityType, World world, boolean aquatic, boolean defensive) {
        super(entityType, world, aquatic, defensive);
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        PVZProjectileEntity projectile = this.getProjectile();
        if (projectile == null) {
            ModCore.LOGGER.error("AbstractRangedAttackPlant#shootAt: Projectile is null for {}", this.getDefaultName());
            return;
        }

        double targetEyeY = target.getEyeY() - 1.1F;
        double distanceX = target.getX() - this.getX();
        double distanceY = targetEyeY - projectile.getY();
        double distanceZ = target.getZ() - this.getZ();
        double velocityModifier = Math.sqrt(distanceX * distanceX + distanceZ * distanceZ) * 0.2F;
        projectile.setVelocity(distanceX, distanceY + velocityModifier, distanceZ, 1.6F, 2.0F);

        this.playSound(ModSounds.PROJECTILE_THROWN, 0.6F, 1.0F);
        this.getWorld().spawnEntity(projectile);
    }

    @Override
    protected void initCustomGoals() {
        this.goalSelector.add(0, new ProjectileAttackGoal(this, 0.0D, this.getAttackDelayTicks(), this.getAttackRange()));
    }

    protected abstract PVZProjectileEntity getProjectile();

    protected int getAttackDelayTicks() {
        return TickConvertor.seconds(1);
    }

    protected int getAttackRange() {
        return 32;
    }
}
