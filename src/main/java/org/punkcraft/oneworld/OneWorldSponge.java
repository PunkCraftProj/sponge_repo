package org.punkcraft.oneworld;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelRegistrar;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

import java.util.concurrent.TimeUnit;

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

        Task.builder()
                .execute(this::updatePlayerListHeader)
                .interval(1, TimeUnit.SECONDS)
                .submit(this);

        // Регистрация сообщения
        this.channel.registerMessage(TestMessage.class, 0);

        Sponge.getEventManager().registerListeners(this, new WSListener(this, this.channel));
    }

    private void updatePlayerListHeader() {
        double TPS = Sponge.getServer().getTicksPerSecond();
        Text header = Text.builder("§aTPS: §f" + String.format("%.2f", TPS)).build();

        for (Player player : Sponge.getServer().getOnlinePlayers()) {
            player.getTabList().setHeaderAndFooter(header, Text.EMPTY);
        }
    }

    public ChannelBinding.IndexedMessageChannel getChannel() {
        return channel;
    }

    public Logger getLogger() {
        return logger;
    }
}