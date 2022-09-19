package com.artemogg.connectly;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("connectly")
public class ConnectlyMod {

    public ConnectlyMod() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent //Срабатывает когда игрок подключился к серверу
    public void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer(); //Игрок
        GetServerFruit.isConnect = true;
        GetServerFruit.startConnection(player);
        player.sendMessage(new StringTextComponent("You get 1 strike every 5 seconds.."), player.getUniqueID());
    }

    @SubscribeEvent //Срабатывает когда игрок отключился от сервера.
    public void onPlayerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        GetServerFruit.isConnect = false;
    }
}
