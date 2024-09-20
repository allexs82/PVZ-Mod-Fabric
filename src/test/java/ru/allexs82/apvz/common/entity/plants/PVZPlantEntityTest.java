package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.Bootstrap;
import net.minecraft.SharedConstants;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class PVZPlantEntityTest {
    private AutoCloseable mocks;

    @Mock
    private PVZPlantEntity mockDefensivePlant;
    @Mock
    private PVZPlantEntity mockNormalPlant;
    @Mock
    private ServerWorld mockWorld;
    @Mock
    private DamageSource mockDamageSource;
    @Mock
    private DamageSources mockDamageSources;

    @BeforeEach
    void setUp() {
        SharedConstants.createGameVersion();
        Bootstrap.initialize();
        mocks = MockitoAnnotations.openMocks(this);

        when(mockDamageSources.cramming()).thenReturn(mockDamageSource);
        when(mockWorld.getDamageSources()).thenReturn(mockDamageSources);

        when(mockNormalPlant.isDefensive()).thenReturn(false);
        when(mockDefensivePlant.isDefensive()).thenReturn(true);

        when(mockDefensivePlant.getWorld()).thenReturn(mockWorld);
        when(mockNormalPlant.getWorld()).thenReturn(mockWorld);

        doCallRealMethod().when(mockDefensivePlant).tickCramming();
        doCallRealMethod().when(mockNormalPlant).tickCramming();
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void tickCramming_DefensiveAndDefensive() {
        when(mockWorld.getOtherEntities(any(), any())).thenReturn(List.of(mockDefensivePlant));
        mockDefensivePlant.tickCramming();
        verify(mockDefensivePlant, atLeastOnce()).damage(any(), anyFloat());
    }

    @Test
    void tickCramming_DefensiveAndNormal() {
        when(mockWorld.getOtherEntities(any(), any())).thenReturn(List.of(mockNormalPlant));
        mockDefensivePlant.tickCramming();
        verify(mockDefensivePlant, never()).damage(any(), anyFloat());
    }

    @Test
    void tickCramming_NormalAndNormal() {
        when(mockWorld.getOtherEntities(any(), any())).thenReturn(List.of(mockNormalPlant));
        mockNormalPlant.tickCramming();
        verify(mockNormalPlant, atLeastOnce()).damage(any(), anyFloat());
    }

    @Test
    void tickCramming_NormalAndAir() {
        when(mockWorld.getOtherEntities(any(), any())).thenReturn(List.of());
        mockNormalPlant.tickCramming();
        verify(mockNormalPlant, never()).damage(any(), anyFloat());
    }

    @Test
    void tickCramming_DefensiveAndAir() {
        when(mockWorld.getOtherEntities(any(), any())).thenReturn(List.of());
        mockDefensivePlant.tickCramming();
        verify(mockDefensivePlant, never()).damage(any(), anyFloat());
    }

    @Test
    void tickCramming_WithNotPlantEntity() {
        when(mockWorld.getOtherEntities(any(), any())).thenReturn(List.of(mock(SheepEntity.class)));
        mockNormalPlant.tickCramming();
        verify(mockNormalPlant, never()).damage(any(), anyFloat());
    }

}
