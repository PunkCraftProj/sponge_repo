package org.punkcraft.worldswapper2;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
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

                // Выполнение команды /server от имени игрока
                Sponge.getCommandManager().process(player, "server");
            } else {
                player.sendMessage(Text.of("Ваши координаты меньше 10000, пропускаем выполнение команды."));
            }
        }
    }
}