package org.punkcraft.oneworld;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.text.Text;

public class WSListener {
    private final OneWorldSponge plugin;
    private final ChannelBinding.IndexedMessageChannel channel;

    public WSListener(OneWorldSponge plugin, ChannelBinding.IndexedMessageChannel channel) {
        this.plugin = plugin;
        this.channel = channel;
    }

    @Listener
    public void onEntityMove(MoveEntityEvent event) {
        Entity entity = event.getTargetEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;

            // Проверяем координаты игрока
            if (Math.abs(player.getLocation().getBlockX()) > 10000 || Math.abs(player.getLocation().getBlockZ()) > 10000) {
                player.sendMessage(Text.of("Ваши координаты больше 10000!"));

                // Создаем и отправляем сообщение
                TestMessage testMessage = new TestMessage("KinZeun server2");
                channel.sendTo(player, testMessage);
            }
        }
    }
}
