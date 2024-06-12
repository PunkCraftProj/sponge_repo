package org.punkcraft.oneworld;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelRegistrar;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Plugin(id = "oneworldsponge", name = "OneWorldSponge", version = "1.0", description = "OneWorldSponge")
public class OneWorldSponge {

    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path configDir;

    private ChannelBinding.IndexedMessageChannel channel;
    private List<Zone> zones = new ArrayList<>();

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running OneWorldSponge!!!");

        ChannelRegistrar channelRegistrar = Sponge.getChannelRegistrar();
        this.channel = channelRegistrar.createChannel(this, "custom:main");

        // Регистрация сообщения
        this.channel.registerMessage(TestMessage.class, 0);

        // Загрузка конфигурации зон
        createZones();
        loadZones();

        Sponge.getEventManager().registerListeners(this, new WSListener(this, this.channel, zones));
    }

    private void createZones() {
        File configFile = new File("zones.cfg");
        if (!configFile.exists()) {
            try {
                if (configFile.createNewFile()) {
                    System.out.println("Файл zones.cfg успешно создан.");
                } else {
                    System.out.println("Не удалось создать файл zones.cfg.");
                }
            } catch (IOException e) {
                System.out.println("Произошла ошибка при создании файла zones.cfg.");
                e.printStackTrace();
            }
        }
    }

    private void loadZones() {
        try {
            Path configFile = configDir.resolve("zones.yml");
            if (Files.notExists(configFile)) {
                Files.createDirectories(configDir); // на случай если каталог не существует
                Files.createFile(configFile);
            }

            YAMLConfigurationLoader loader = YAMLConfigurationLoader.builder().setPath(configFile).build();
            ConfigurationNode rootNode = loader.load();

            ConfigurationNode zonesNode = rootNode.getNode("zones");
            if (!zonesNode.isVirtual()) {
                for (ConfigurationNode node : zonesNode.getChildrenMap().values()) {
                    String name = node.getKey().toString();
                    int x1 = node.getNode("x1").getInt();
                    int y1 = node.getNode("y1").getInt();
                    int z1 = node.getNode("z1").getInt();
                    int x2 = node.getNode("x2").getInt();
                    int y2 = node.getNode("y2").getInt();
                    int z2 = node.getNode("z2").getInt();

                    zones.add(new Zone(name, x1, y1, z1, x2, y2, z2));
                }
            } else {
                logger.warn("No zones found in the configuration file.");
            }
        } catch (IOException e) {
            logger.error("Failed to load zones from zones.yml", e);
        }
    }

    public ChannelBinding.IndexedMessageChannel getChannel() {
        return channel;
    }

    public Logger getLogger() {
        return logger;
    }
}