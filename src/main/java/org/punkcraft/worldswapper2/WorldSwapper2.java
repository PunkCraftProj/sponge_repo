package org.punkcraft.worldswapper2;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

// Imports for logger
import com.google.inject.Inject;
import org.slf4j.Logger;

@Plugin(id = "worldswapper2", name = "WorldSwapper2", version = "1.0", description = "WorldSwapper2")
public class WorldSwapper2 {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running ExamplePlugin!!!");
        Sponge.getEventManager().registerListeners(this, new WSListener());
    }

}