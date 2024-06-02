package org.punkcraft.oneworld;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.text.Text;

public class WSListener {
    @Listener
    public void onEntityMove(MoveEntityEvent event) {
        Entity entity = event.getTargetEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;

            // Проверяем координаты игрока
            if (Math.abs(player.getLocation().getBlockX()) > 10000 || Math.abs(player.getLocation().getBlockZ()) > 10000) {
                player.sendMessage(Text.of("Ваши координаты больше 10000! Выполнение команды /server..."));

                // Создание данных для отправки
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("server");

                // Отправка данных через канал
                ChannelBinding.RawDataChannel channel = ((OneWorldSponge) Sponge.getPluginManager().getPlugin("worldswapper2").get().getInstance().get()).getChannel();
                channel.sendTo(player, (buf) -> buf.writeBytes(out.toByteArray()));
            }
        }
    }
}