package com.BreadRes.ghostclient.ui;

import com.BreadRes.ghostclient.modules.ESP;
import com.BreadRes.ghostclient.modules.SpeedHack;
import com.BreadRes.ghostclient.modules.FastFly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class CheatMenu extends Screen {
    private static final Minecraft mc = Minecraft.getInstance();

    protected CheatMenu() {
        super(Component.translatable("gui.ghostclient.title"));
    }

    @Override
    protected void init() {
        int y = 50;
        int x = width / 2 - 100;

        addRenderableWidget(Button.builder(Component.translatable("ESP: " + (ESP.enabled ? "ON" : "OFF")),
                button -> {
                    ESP.enabled = !ESP.enabled;
                    button.setMessage(Component.translatable("ESP: " + (ESP.enabled ? "ON" : "OFF")));
                }).bounds(x, y, 200, 20).build());

        y += 30;
        addRenderableWidget(Button.builder(Component.translatable("SpeedHack: " + (SpeedHack.enabled ? "ON" : "OFF")),
                button -> {
                    SpeedHack.enabled = !SpeedHack.enabled;
                    button.setMessage(Component.translatable("SpeedHack: " + (SpeedHack.enabled ? "ON" : "OFF")));
                }).bounds(x, y, 200, 20).build());

        y += 30;
        addRenderableWidget(Button.builder(Component.translatable("FastFly: " + (FastFly.enabled ? "ON" : "OFF")),
                button -> {
                    FastFly.enabled = !FastFly.enabled;
                    button.setMessage(Component.translatable("FastFly: " + (FastFly.enabled ? "ON" : "OFF")));
                }).bounds(x, y, 200, 20).build());

        y += 50;
        addRenderableWidget(Button.builder(Component.translatable("gui.ghostclient.close"),
                button -> this.onClose()).bounds(x, y, 200, 20).build());
    }

    @Override
    public void onClose() {
        mc.setScreen(null);
    }
}
