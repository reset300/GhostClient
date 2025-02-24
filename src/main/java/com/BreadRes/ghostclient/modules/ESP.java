package com.BreadRes.ghostclient.modules;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.joml.Matrix4f;
import java.util.Objects;

public class ESP {
    public static boolean enabled = false;
    private static final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onRenderWorld(RenderLevelStageEvent event) {
        if (!enabled || mc.player == null || mc.level == null) return;

        PoseStack poseStack = event.getPoseStack();
        for (Player player : mc.level.players()) {
            if (player == mc.player) continue;
            drawESPBox(poseStack, player);
            drawTracer(poseStack, player);
        }
    }

    private static void drawESPBox(PoseStack poseStack, Player player) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder builder = tesselator.getBuilder();

        double x = player.getX() - mc.getEntityRenderDispatcher().camera.getPosition().x;
        double y = player.getY() - mc.getEntityRenderDispatcher().camera.getPosition().y;
        double z = player.getZ() - mc.getEntityRenderDispatcher().camera.getPosition().z;

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        builder.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR);
        float size = 0.6f;

        builder.vertex((float) (x - size), (float) y, (float) (z - size)).color(0, 255, 0, 255).endVertex();
        builder.vertex((float) (x + size), (float) y, (float) (z - size)).color(0, 255, 0, 255).endVertex();

        builder.vertex((float) (x + size), (float) y, (float) (z - size)).color(0, 255, 0, 255).endVertex();
        builder.vertex((float) (x + size), (float) y, (float) (z + size)).color(0, 255, 0, 255).endVertex();

        builder.vertex((float) (x + size), (float) y, (float) (z + size)).color(0, 255, 0, 255).endVertex();
        builder.vertex((float) (x - size), (float) y, (float) (z + size)).color(0, 255, 0, 255).endVertex();

        builder.vertex((float) (x - size), (float) y, (float) (z + size)).color(0, 255, 0, 255).endVertex();
        builder.vertex((float) (x - size), (float) y, (float) (z - size)).color(0, 255, 0, 255).endVertex();

        tesselator.end();
    }

    private static void drawTracer(PoseStack poseStack, Player player) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder builder = tesselator.getBuilder();

        double x = player.getX() - mc.getEntityRenderDispatcher().camera.getPosition().x;
        double y = player.getY() - mc.getEntityRenderDispatcher().camera.getPosition().y;
        double z = player.getZ() - mc.getEntityRenderDispatcher().camera.getPosition().z;

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        builder.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR);
        builder.vertex(0f, 1.6f, 0f).color(255, 0, 0, 255).endVertex();
        builder.vertex((float) x, (float) y, (float) z).color(255, 0, 0, 255).endVertex();
        tesselator.end();

        drawPlayerName(poseStack, player, (float) x, (float) y, (float) z);
    }

    private static void drawPlayerName(PoseStack poseStack, Player player, float x, float y, float z) {
        Font font = mc.font;
        MultiBufferSource buffer = mc.renderBuffers().bufferSource(); // ОНОВЛЕННЯ!
        Matrix4f matrix = poseStack.last().pose();

        String text = Objects.requireNonNull(player.getName()).getString() + " (" + (int) mc.player.distanceTo(player) + "m)";

        font.drawInBatch(text, x, y + 2, 0xFFFFFF, false, matrix, buffer, Font.DisplayMode.NORMAL, 0, 15728880);

        ((MultiBufferSource.BufferSource) buffer).endBatch();
    }




}
