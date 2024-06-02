package org.punkcraft.oneworld;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelRegistrar;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "oneworldsponge", name = "OneWorldSponge", version = "1.0", description = "OneWorldSponge")
public class OneWorldSponge {

    @Inject
    private Logger logger;

    private ChannelBinding.RawDataChannel channel;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running WorldSwapper2!!!");
        Sponge.getEventManager().registerListeners(this, new WSListener());

        // Регистрация канала плагина
        ChannelRegistrar channelRegistrar = Sponge.getChannelRegistrar();
        this.channel = channelRegistrar.createRawChannel(this, "velocity:command");
    }

    public ChannelBinding.RawDataChannel getChannel() {
        return channel;
    }
}