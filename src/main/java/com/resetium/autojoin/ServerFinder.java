package com.resetium.autojoin;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;

public class ServerFinder {
	private final static Logger LOGGER = LogManager.getLogger();

	public static void Main() {
		final ServerList list = new ServerList(Minecraft.getInstance());
		final String serverIP = Config.COMMON.targetServerIP.get();
		final String serverName = Config.COMMON.targetServerName.get();
		final Set<String> ipexists = Sets.newHashSet();
		final int countserver = list.size();
		for (int i = 0; i < countserver; i++) {
			final ServerData data = list.get(i);
			if (data != null)
				ipexists.add(data.ip);
		}
		boolean dirty = false;
		if (!ipexists.contains(serverIP)) {
			ServerData newdata = new ServerData(serverName, serverIP, false);
			list.add(newdata);
			LOGGER.info("Adding new server: {}", newdata);
			dirty = true;
		}

		if (dirty)
			list.save();

	}
}
