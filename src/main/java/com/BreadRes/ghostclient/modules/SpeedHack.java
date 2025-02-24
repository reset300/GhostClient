package com.BreadRes.ghostclient.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpeedHack {
    public static boolean enabled = false;
    private static final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (!enabled || mc.player == null) return;

        Player player = mc.player;
        if (player.isSprinting()) {
            player.setDeltaMovement(player.getDeltaMovement().multiply(1.5, 1.0, 1.5));
        }
    }
}
