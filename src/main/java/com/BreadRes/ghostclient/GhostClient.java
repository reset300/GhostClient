package com.BreadRes.ghostclient;

import com.BreadRes.ghostclient.modules.ESP;
import com.BreadRes.ghostclient.modules.SpeedHack;
import com.BreadRes.ghostclient.modules.FastFly;
import com.BreadRes.ghostclient.ui.KeyHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("ghostclient")
public class GhostClient {
    public GhostClient() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ESP.class);
        MinecraftForge.EVENT_BUS.register(SpeedHack.class);
        MinecraftForge.EVENT_BUS.register(FastFly.class);
        MinecraftForge.EVENT_BUS.register(KeyHandler.class);
    }
}
