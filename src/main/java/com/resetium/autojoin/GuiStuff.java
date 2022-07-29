package com.resetium.autojoin;

import javax.annotation.Nullable;

import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.gui.screen.Screen;

public class GuiStuff {
	static boolean isMainMenu(final @Nullable Screen screen) {
		return screen instanceof MainMenuScreen;
	}
	static boolean isMultiplayerMenu(final @Nullable Screen screen) {
		return screen instanceof MultiplayerScreen;
	}
	static boolean isDisconnected(final @Nullable Screen screen) {
		return screen instanceof DisconnectedScreen;
	}
}
