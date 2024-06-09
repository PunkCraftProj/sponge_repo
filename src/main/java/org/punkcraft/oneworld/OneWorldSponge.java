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

    private ChannelBinding.IndexedMessageChannel channel;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running OneWorldSponge!!!");

        ChannelRegistrar channelRegistrar = Sponge.getChannelRegistrar();
        this.channel = channelRegistrar.createChannel(this, "custom:main");

        // Регистрация сообщения
        this.channel.registerMessage(TestMessage.class, 0);

        Sponge.getEventManager().registerListeners(this, new WSListener(this, this.channel));
    }

    private void 

    public ChannelBinding.IndexedMessageChannel getChannel() {
        return channel;
    }

    public Logger getLogger() {
        return logger;
    }
}