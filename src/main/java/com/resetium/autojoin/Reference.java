package com.resetium.autojoin;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class Reference {
	public static final @Nonnull String MODID = "autojoin";
	public static final @Nonnull String NAME = "Autojoin";
	public static final @Nonnull String VERSION = "${version}";
	public static final @Nonnull String FORGE = "${forgeversion}";
	public static void playSound(final ResourceLocation sound, final Double pitch) {
		Minecraft.getInstance().getSoundManager().play((ISound) new SimpleSound(sound, SoundCategory.MASTER, 0.25F, pitch.floatValue(), false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F, false));
	}
	public static boolean emptyBlank(String s) {
		return StringUtils.isBlank(s);
	}
}
