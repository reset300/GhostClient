package com.BreadRes.ghostclient.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FastFly {
    public static boolean enabled = false;
    private static final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (!enabled || mc.player == null) return;

        Entity vehicle = mc.player.getVehicle();
        if (vehicle != null) {
            vehicle.setDeltaMovement(vehicle.getDeltaMovement().scale(2.0));
        }
    }
}
