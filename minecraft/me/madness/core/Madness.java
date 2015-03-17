package me.madness.core;

import me.madness.managers.ToggleManager;
import me.madness.overrides.GuiIngameOverride;
import me.madness.overrides.GuiNewChatOverride;
import me.madness.utils.Wrapper;
import net.minecraft.client.Minecraft;

public class Madness {
	
	public static String NOME_CLIENT = "Madness";	
	
	public static String VERSIONE_CLIENT = "0.1a";
	
	public static Madness instance = new Madness();
	
	private static ToggleManager TogglesManager;

	public final void startClient(Minecraft mc){
		TogglesManager = new ToggleManager();
		Wrapper.getMinecraft().ingameGUI = new GuiIngameOverride(mc);
		Wrapper.getMinecraft().ingameGUI.persistantChatGUI = new GuiNewChatOverride(mc);
	}
	
	public static ToggleManager getTogglesManager() {
		if (TogglesManager == null)
			TogglesManager = new ToggleManager();
		return TogglesManager;
	}
	
	/**
	 * 
	 * @return Madness Instance.
	 */
	public static Madness getInstance(){
		return instance;
	}
}
