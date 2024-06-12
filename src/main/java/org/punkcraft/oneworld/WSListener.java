package org.punkcraft.oneworld;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.text.Text;

import java.util.List;

public class WSListener {
    private final OneWorldSponge plugin;
    private final ChannelBinding.IndexedMessageChannel channel;
    private final List<Zone> zones;

    public WSListener(OneWorldSponge plugin, ChannelBinding.IndexedMessageChannel channel, List<Zone> zones) {
        this.plugin = plugin;
        this.channel = channel;
        this.zones = zones;
    }

    @Listener
    public void onEntityMove(MoveEntityEvent event) {
        Entity entity = event.getTargetEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();

            for (Zone zone : zones) {
                player.sendMessage(Text.of("Перебор зоны" + zone.getName()));
                if (zone.isWithinZone(x, y, z)) {
                    player.sendMessage(Text.of("Вы находитесь в зоне: " + zone.getName()));

                    // Создаем и отправляем сообщение
                    TestMessage testMessage = new TestMessage(player.getName() + " " + zone.getName());
                    channel.sendTo(player, testMessage);

                    // Выход из цикла после нахождения первой зоны
                    break;
                }
            }
        }
    }
}