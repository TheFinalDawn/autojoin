package com.resetium.autojoin;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ServerSelectionList;
import net.minecraft.client.gui.screen.ServerSelectionList.Entry;
import net.minecraft.client.gui.screen.ServerSelectionList.NormalEntry;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.TickEvent;

/**
 * Runs a queue of timing-related commands.
 * 
 * @author Resetium
 *
 */
@SuppressWarnings("rawtypes")
public class TickHandler {
	private static final Logger LOGGER = LogManager.getLogger();
	private static int count = 0;
	private static Screen currentScreen;
	private static MultiplayerScreen multiplayer;
	private static Queue commands = new LinkedList();
	private static String targetIP = Config.COMMON.targetServerIP.get();

	/**
	 * Advances the queue every second and fires any function it finds before
	 * throwing it out.
	 * 
	 * @param event The event to listen to.
	 */
	public static void handleTick(TickEvent event) {
		NormalEntry target = null;
		if (count % 20 == 0 && !commands.isEmpty()) {
			int thing = (int) commands.poll();
			switch (thing) {
			case 0:
				break;
			case 1:
				multiplayer.init(Minecraft.getInstance(), currentScreen.width, currentScreen.height);
				Minecraft.getInstance().setScreen(multiplayer);
				target = getServerEntry((MultiplayerScreen) currentScreen, Config.COMMON.targetServerIP.get());
				LOGGER.warn("Automatically opened Multiplayer Menu");
				if (target.getServerData().pinged && target.getServerData().ping>-1) {
					Reference.playSound(new ResourceLocation(Config.COMMON.notificationSound.get()), Config.COMMON.notificationPitch.get());
					openDelay(3, Config.COMMON.durationAutoLogin.get()); // trigger autojoin countdown
				} else {
					try {
						((MultiplayerScreen) currentScreen).getPinger().pingServer(target.getServerData(), new TickHandler().new Running());
					} catch (UnknownHostException e) {
						LOGGER.error("CANNOT FIND HOST EXCEPTION: {}", e);
					} catch (Exception e) {
						LOGGER.error("PING SERVER ERROR: {}", e);
					}
				}
				break;
			case 2:
				target = getServerEntry((MultiplayerScreen) currentScreen, Config.COMMON.targetServerIP.get());
				LOGGER.info("Attempting to ping target server {}", Config.COMMON.targetServerIP.get());
				LOGGER.debug("STUFF GOES HERE: {}", target.getServerData().ip);
				ServerList servers = ((MultiplayerScreen) currentScreen).getServers();
				for (int i = 0; i < servers.size(); i++) {
					ServerData server = servers.get(i);
					LOGGER.debug("SERVER DATA GO! IP: {} Name: {} Ping: {} Pinged: {} MOTD: {}", server.ip, server.name,
							server.ping, server.pinged, server.motd);
				}
				
				if (target.getServerData().pinged && target.getServerData().ping>-1) {
					Reference.playSound(new ResourceLocation(Config.COMMON.notificationSound.get()), Config.COMMON.notificationPitch.get());
					if (Config.COMMON.targetAutoLogin.get()) {
						openDelay(3, Config.COMMON.durationAutoLogin.get()); // trigger autojoin countdown
					}
				} else {
					try {
						((MultiplayerScreen) currentScreen).getPinger().pingServer(target.getServerData(), new TickHandler().new Running());
					} catch (UnknownHostException e) {
						LOGGER.error("CANNOT FIND HOST EXCEPTION: {}", e);
					} catch (Exception e) {
						LOGGER.error("PING SERVER ERROR: {}", e);
					}
					openDelay(2, Config.COMMON.durationPing.get()); // try again
				}
				break;
			case 3:
				target = getServerEntry((MultiplayerScreen) currentScreen, Config.COMMON.targetServerIP.get());
				LOGGER.info("Automatically connecting to: {}", target.getServerData().ip);
				LOGGER.info((Entry) target);
				getServerSelectionList((MultiplayerScreen) currentScreen).setSelected(target);
				((MultiplayerScreen) currentScreen).joinSelectedServer();
				break;
			default:
				LOGGER.error("Out of Range: Attempted to fire an event outside of the allowed events");
			}
		}
		count++;
	}

	/**
	 * Queues a task with a delay, in seconds.
	 * 
	 * @param task  A number correlating with the event that will fire. 1 opens
	 *              multiplayer, 2 pings the target, 3 connects to the target. Other
	 *              values will do nothing.
	 * @param delay The time in seconds until it's called.
	 */
	@SuppressWarnings("unchecked")
	public static void openDelay(int task, int delay) {
		for (int i = delay; i > 0; i--) {
			commands.add(nothing()); // Don't ask
		}
		commands.add(task);
		multiplayer = new MultiplayerScreen(currentScreen);
	}

	/**
	 * Updates the screen used by other functions in this class. Fire this upon a new menu being created.
	 * @param screen The Screen that we should use in other functions.
	 */
	public static void setScreen(Screen screen) {
		currentScreen = screen;
	}

	/**
	 * Same as setScreen, but for when we specifically want a MultiplayerScreen.
	 * @param screen The MultiplayerScreen that we should use in other functions.
	 */
	public static void setMultiplayerScreen(MultiplayerScreen screen) {
		multiplayer = screen;
	}

	/**
	 * Gets the ServerSelectionList from a MultiplayerScreen.
	 * @param screen Pass in the MultiplayerScreen that you want to get the ServerSelectionList of.
	 * @return Returns the ServerSelectionList, but much more easily accessible. Run .children() and iterate through that to get the individual servers.
	 */
	public static ServerSelectionList getServerSelectionList(MultiplayerScreen screen) {
		return (ServerSelectionList) screen.children().get(0);
	}

	/**
	 * Gets the ServerSelectionList.NormalEntry of a targeted IP.
	 * @param screen The MultiplayerScreen to check the ServerSelectionList of. DO NOT PASS IN ANY OTHER SCREEN.
	 * @param ip The IP of the requested NormalEntry.
	 * @return The NormalEntry of the requested server, null if not found
	 */
	public static NormalEntry getServerEntry(MultiplayerScreen screen, String ip) {
		ServerSelectionList serverSelectList = getServerSelectionList(screen);
		for (Entry i : serverSelectList.children()) {
			if (i instanceof ServerSelectionList.NormalEntry) {
				ServerData k = ((ServerSelectionList.NormalEntry) i).getServerData();
				LOGGER.info("normal server found, ip is \"{}\", need \"{}\"", k.ip, targetIP);
				if (StringUtils.equals(k.ip, ip)) {
					return (NormalEntry) i;
				}
			}
		}
		return null;
	}
	
	/**
	 * Clears the command queue. Particularly useful if multiple tasks are queued.
	 */
	public static void clearQueue() {
		commands.clear();
	}

	/**
	 * Does nothing. Useful, anyway. Returns 0. Neat for stuffing into things that
	 * expect a function.
	 */
	private static int nothing() {
		return 0;
	}
	
	private class Running implements Runnable {

		public void run() {
			
		}
		
	}
}
