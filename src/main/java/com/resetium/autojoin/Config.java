package com.resetium.autojoin;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ForgeConfigSpec;

/*
@Deprecated
public class Config {
	private static @Nullable Config instance;

	private ConfigBase config;
	private ConfigBase dynamicconfig;

	public final ConfigProperty<Integer> durationMainMenu;
	public final ConfigProperty<Integer> durationPing;
	public final ConfigProperty<Integer> durationAutoLogin;
	public final ConfigProperty<Integer> durationDisconnected;
	private final ConfigProperty<String> targetServerNameDefault;
	private final ConfigProperty<String> targetServerIPDefault;
	private final ConfigProperty<Boolean> targetAutoLoginDefault;
	public final ConfigProperty<String> notificationSound;
	public final ConfigProperty<Double> notificationPitch;
	public final ConfigProperty<Boolean> startWithMultiplayerMenu;
	public final ConfigProperty<Boolean> startAndConnect;
	public final ConfigProperty<Boolean> ignorePopulation;
	public final ConfigProperty<String> countdownPosition;

	public final ConfigProperty<String> targetServerName;
	public final ConfigProperty<String> targetServerIP;
	public final ConfigProperty<Boolean> targetAutoLogin;
	public final ConfigProperty<Boolean> miscInitServer;
	
	
	private Config(final @Nonnull File staticFile, final @Nonnull File dynamicFile, final @Nonnull String version, @Nonnull final ICompat compat) {
		// init static config
		this.config = new ConfigBase(staticFile, version);

		this.config.getCategory("Duration").setLanguageKey("serverobserver.config.duration").setComment("Set the time such as ping interval");
		this.durationMainMenu = this.config.propertyInteger(this.config.get("Duration", "MainMenu", 15, "Time until the multiplay screen is displayed after starting Minecraft (invalid with 0)").setMinValue(0).setLanguageKey("serverobserver.config.duration.mainmenu"));
		this.durationPing = this.config.propertyInteger(this.config.get("Duration", "Ping", 20, "Ping interval (minimum: 10 seconds)").setMinValue(10).setLanguageKey("serverobserver.config.duration.ping"));
		this.durationAutoLogin = this.config.propertyInteger(this.config.get("Duration", "AutoLogin", 10, "Time from ping completion to automatic login (minimum: 10 seconds)").setMinValue(10).setLanguageKey("serverobserver.config.duration.autologin"));
		this.durationDisconnected = this.config.propertyInteger(this.config.get("Duration", "Disconnected", 30, "The time from the disconnection screen to the display of the multiplay screen (Minimum: 10 seconds, less than 10 will not return to multiplay screen)").setMinValue(10).setLanguageKey("serverobserver.config.duration.disconnected"));

		this.config.getCategory("ObserveTarget").setLanguageKey("serverobserver.config.observetarget").setComment("Remember the server being monitored");
		this.targetServerNameDefault = this.config.propertyString(this.config.get("ObserveTarget", "DefaultServerName", "", "Name of the server being monitored").setLanguageKey("serverobserver.config.observetarget.servername"));
		this.targetServerIPDefault = this.config.propertyString(this.config.get("ObserveTarget", "DefaultServerIP", "", "IP address of the server being monitored").setLanguageKey("serverobserver.config.observetarget.serverip"));
		this.targetAutoLoginDefault = this.config.propertyBoolean(this.config.get("ObserveTarget", "DefaultAutoLogin", false, "Auto Login Mode").setLanguageKey("serverobserver.config.observetarget.autologin"));

		this.config.getCategory("Notification").setLanguageKey("serverobserver.config.notification").setComment("Notify by sound");
		this.notificationSound = this.config.propertyString(this.config.get("Notification", "Sound", compat.getDefaultSound(), "Sound resource location").setLanguageKey("serverobserver.config.notification.sound"));
		this.notificationPitch = this.config.propertyDouble(this.config.get("Notification", "Pitch", 1.0, "Sound pitch (minimum: 0.0, maximum: 2.0)").setMinValue(0.0).setMaxValue(2.0).setLanguageKey("serverobserver.config.notification.pitch"));

		this.config.getCategory("Miscellaneous").setLanguageKey("serverobserver.config.miscellaneous").setComment("Miscellaneous");
		this.startWithMultiplayerMenu = this.config.propertyBoolean(this.config.get("Miscellaneous", "StartWithMultiplayerMenu", false, "After game initialized, show multiplayer menu").setLanguageKey("serverobserver.config.miscellaneous.startwithmultiplayermenu").setRequiresMcRestart(true));
		this.startAndConnect = this.config.propertyBoolean(this.config.get("Miscellaneous", "StartAndConnect", false, "After game initialized, connect to the server").setLanguageKey("serverobserver.config.miscellaneous.startandconnect").setRequiresMcRestart(true));
		this.ignorePopulation = this.config.propertyBoolean(this.config.get("Miscellaneous", "IgnorePopulation", false, "Try connection if server is full").setLanguageKey("serverobserver.config.miscellaneous.ignorepopulation"));
		this.countdownPosition = this.config.propertyString(this.config.get("Miscellaneous", "CountdownPosition", "TopRight", "Coutdown text position in custom main menu", new String[] { "None", "TopLeft", "Top", "TopRight", "Left", "Center", "Right", "BottomLeft", "Bottom", "BottomRight" }).setLanguageKey("serverobserver.config.miscellaneous.countdownposition"));

		// init dynamic config
		this.dynamicconfig = new ConfigBase(dynamicFile, version);

		this.dynamicconfig.getCategory("Status").setLanguageKey("serverobserver.config.status").setComment("Current Status");
		this.targetServerName = this.dynamicconfig.propertyString(this.dynamicconfig.get("Status", "SelectedServerName", this.targetServerNameDefault.get(), "Name of the server being monitored").setLanguageKey("serverobserver.config.status.servername"));
		this.targetServerIP = this.dynamicconfig.propertyString(this.dynamicconfig.get("Status", "SelectedServerIP", this.targetServerIPDefault.get(), "IP address of the server being monitored").setLanguageKey("serverobserver.config.status.serverip"));
		this.targetAutoLogin = this.dynamicconfig.propertyBoolean(this.dynamicconfig.get("Status", "SelectedAutoLogin", this.targetAutoLoginDefault.get(), "Auto Login Mode").setLanguageKey("serverobserver.config.status.autologin"));
		this.miscInitServer = this.dynamicconfig.propertyBoolean(this.dynamicconfig.get("Status", "InitServers", true, "Initialize server list").setLanguageKey("serverobserver.config.status.initservers").setRequiresMcRestart(true));
	}

	public ConfigBase getBase() {
		return this.config;
	}

	public ConfigBase getBaseDynamic() {
		return this.dynamicconfig;
	}

	public void save() {
		this.config.save();
	}

	public static @Nonnull Config getConfig() {
		if (instance!=null)
			return instance;
		throw new IllegalStateException("config not initialized");
	}

	public static void init(final @Nonnull File staticFile, final @Nonnull File dynamicFile, final @Nonnull String version, @Nonnull final ICompat icompat) {
		instance = new Config(staticFile, dynamicFile, version, icompat);
	}
}
*/

/**
 * Various configuration values. Different from Reference in that this is what appears in the .TOML file.
 * @author Resetium
 */
public final class Config {
	
	/**
	 * Settings common to client and server. Even though these should probably be client only.
	 * @author Resetium
	 */
	public static class Common {
		public final ForgeConfigSpec.ConfigValue<Integer> durationMainMenu;
		public final ForgeConfigSpec.ConfigValue<Integer> durationPing;
		public final ForgeConfigSpec.ConfigValue<Integer> durationAutoLogin;
		public final ForgeConfigSpec.ConfigValue<Integer> durationDisconnected;
		public final ForgeConfigSpec.ConfigValue<String> notificationSound;
		public final ForgeConfigSpec.ConfigValue<Double> notificationPitch;
/*		public final ForgeConfigSpec.ConfigValue<Boolean> startWithMultiplayerMenu;
		public final ForgeConfigSpec.ConfigValue<Boolean> startAndConnect;
		public final ForgeConfigSpec.ConfigValue<Boolean> ignorePopulation;
*/
		public final ForgeConfigSpec.ConfigValue<String> targetServerName;
		public final ForgeConfigSpec.ConfigValue<String> targetServerIP;
		public final ForgeConfigSpec.ConfigValue<Boolean> targetAutoLogin;
		
		public final ForgeConfigSpec.ConfigValue<Boolean> enabled;
		public final ForgeConfigSpec.ConfigValue<Boolean> doRetry;
		
		
		
		/**
		 * Defines all the config values.
		 * @param builder The ForgeConfigSpec Builder to use.
		 */
		public Common(ForgeConfigSpec.Builder builder) {
			builder.push("Timing Settings")
				.comment("Feel lucky it's this easy.");
				durationMainMenu = builder
						.comment("Time until the multiplayer screen is opened automatically, in seconds (disabled with 0)")
						.translation(Reference.MODID+".config.duration.mainmenu")
						.defineInRange("Auto-Open Multiplayer Delay", 15, 0, 60);
				durationPing = builder
						.comment("Time between server pings (min 10s)")
						.translation(Reference.MODID+".config.duration.ping")
						.defineInRange("Server Ping Interval", 20, 10, 60);
				durationAutoLogin = builder
						.comment("Time from successful ping to automatic login (min 10s)")
						.translation(Reference.MODID+".config.duration.autologin")
						.defineInRange("Auto-Login Delay", 20, 10, 60);
				durationDisconnected = builder
						.comment("Time until the multiplayer screen is displayed, in seconds (disabled with <10)")
						.translation(Reference.MODID+".config.duration.disconnected")
						.defineInRange("Disconnected Close Delay", 20, 0, 60);
			
			builder.pop();
			
			builder.push("Notifications")
				.comment("Noises and what they mean");
			
				notificationSound = builder
						.comment("Alert the user that the target is online by playing this sound")
						.translation(Reference.MODID+".config.notification.sound")
						.define("Notification Sound", SoundEvents.EXPERIENCE_ORB_PICKUP.getLocation().toString());
				notificationPitch = builder
						.comment("Pitch of the sound listed above (0.0-2.0, normal is 1.0)")
						.translation(Reference.MODID+".config.notification.pitch")
						.defineInRange("Notification Pitch", 1.0D, 0.0D, 2.0D);
				
			builder.pop();
			
			builder.push("Miscellaneous")
				.comment("We're all control freaks at heart");
			
				doRetry = builder
						.comment("If this is disabled, we will only try to open Multiplayer once from the main menu.")
						.define("Retry Opening Multiplayer From Main Menu", true);
			/*  startWithMultiplayerMenu = builder // These aren't implemented yet
						.comment("Immediately load the multiplayer menu upon start?")
						.translation(Reference.MODID+".config.miscellaneous.startwithmultiplayermenu")
						.define("Open Multiplayer on Startup", true);
				startAndConnect = builder
						.comment("Immediately (try to) connect to the target upon start?")
						.translation(Reference.MODID+".config.miscellaneous.startandconnect")
						.define("Start and Connect", true);
				ignorePopulation = builder
						.comment("Should we ignore if the server's full and try to join anyway?")
						.translation(Reference.MODID+".config.miscellaneous.ignorepopulation")
						.define("Ignore Populated", true); */
		
			builder.pop();
			
			builder.push("Targeting")
				.comment("Please select a target. Please select a target. Please sele- you get the point.");
				targetServerName = builder
						.comment("Name of the server we'll monitor")
						.translation(Reference.MODID+".config.status.servername")
						.define("Monitored Server Name", "");
				targetServerIP = builder
						.comment("Address of the server being monitored\n§4§lREQUIRES RESTART§r")
						.translation(Reference.MODID+".config.status.serverip")
						.define("Monitored Server Address", "");
				targetAutoLogin = builder
						.comment("Automatic login rather than monitoring")
						.translation(Reference.MODID+".config.status.autologin")
						.define("Automatic Login", true);
			builder.pop();
			enabled = builder
					.comment("Toggle off to disable this mod gracefully.\n§4§lREQUIRES RESTART§r")
					.worldRestart()
					.define("Autojoin Enabled", true);
		}
	}
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;
	
	/**
	 * "Constructor" for a nice pile of config values that can easily* be referenced.
	 */
		static {
			Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
			COMMON = commonSpecPair.getLeft();
			COMMON_SPEC = commonSpecPair.getRight();
		}
	}