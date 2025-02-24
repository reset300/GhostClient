package com.BreadRes.ghostclient.ui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber
public class KeyHandler {
    private static final Minecraft mc = Minecraft.getInstance();
    public static final KeyMapping OPEN_MENU = new KeyMapping("key.ghostclient.menu", GLFW.GLFW_KEY_BACKSLASH, "key.categories.ghostclient");

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (OPEN_MENU.consumeClick()) {
            mc.setScreen(new CheatMenu());
        }
    }
}
