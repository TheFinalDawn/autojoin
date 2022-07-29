package com.resetium.autojoin;

import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("autojoin")
public class Core
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Loading function that runs when the game starts. Sets up everything else.
     */
    public Core() {
    	// Register our wall of config options
        ModLoadingContext.get().registerConfig(Type.COMMON, Config.COMMON_SPEC); 
        if (!Config.COMMON.enabled.get()) {
        	LOGGER.error("Autojoin disabled, shutting off");
        	return;
        }
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Client Setup phase code. Sets up the Server Finder.
     * @param event Event passed by forge to all mods in the client setup phase.
     */
	private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    	LOGGER.info("Preparing to cause confusion");
    	LOGGER.info("Loading Server Finder");
        ServerFinder.Main();
    }
    
    /**
     * Detects when a new GUI is loaded, specifically listening for the Main Menu, Multiplayer, and Disconnected menus.
     * Fires off delayed triggers to open Multiplayer, ping/join a server, and open Multiplayer respectively.
     * @param event Event listener that signals immediately after a GUI is initialized (opened).
     */
    @SubscribeEvent
    public void onGuiEvent(InitGuiEvent.Post event) {
    	if (Reference.emptyBlank(Config.COMMON.targetServerIP.get())) return;
    	Screen gui = event.getGui();
    	LOGGER.debug("Neat, this is an Init GUI Event, just after it's fired");
    	LOGGER.debug("event is {}", event);
    	LOGGER.debug("the guiscreen object making this is {}", gui.getClass());
    	TickHandler.clearQueue();
		TickHandler.setScreen(gui);
    	if (GuiStuff.isMainMenu(gui) && Config.COMMON.durationMainMenu.get() > 0) {
    		TickHandler.openDelay(1, Config.COMMON.durationMainMenu.get());
    	} else if (GuiStuff.isMultiplayerMenu(gui)) {
    		TickHandler.setMultiplayerScreen((MultiplayerScreen) gui);
    		ServerFinder.Main();
    		TickHandler.openDelay(2, Config.COMMON.durationPing.get());
    	} else if (GuiStuff.isDisconnected(gui) && Config.COMMON.durationDisconnected.get() >= 10) {
    		TickHandler.openDelay(1, Config.COMMON.durationDisconnected.get());
    	} else {
    		TickHandler.clearQueue();
    	}
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
    	if (event.phase.equals(Phase.START)) return; 
    	TickHandler.handleTick(event);
    }
}
