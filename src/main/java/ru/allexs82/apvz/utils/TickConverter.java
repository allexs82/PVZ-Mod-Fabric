package ru.allexs82.apvz.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public abstract class TickConverter {

    public static int seconds(float value) {
        return convert(value, TimeUnit.SECONDS);
    }

    public static int minutes(float value) {
        return convert(value, TimeUnit.MINUTES);
    }

    public static int hours(float value) {
        return convert(value, TimeUnit.HOURS);
    }

    /**
     * Converts the given time unit to Minecraft ticks.
     *
     * @param value   the value to convert
     * @param timeUnit the time unit to convert from
     * @return the converted value in Minecraft ticks
     * @throws UnsupportedOperationException if the time unit is not {@code HOURS, MINUTES, SECONDS}
     * @throws IllegalArgumentException if the value is negative
     */
    @Contract(pure = true)
    public static int convert(float value, @NotNull TimeUnit timeUnit) {
        if (value < 0) throw new IllegalArgumentException("Value cannot be negative");
        return switch (timeUnit) {
            case HOURS -> (int) (value * 20 * 60 * 60);
            case MINUTES -> (int) (value * 20 * 60);
            case SECONDS -> (int) (value * 20);
            default -> throw new UnsupportedOperationException("Unsupported time unit: " + timeUnit);
        };
    }
}
