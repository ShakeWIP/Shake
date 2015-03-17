package me.madness.utils;

import me.madness.utils.render.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.Packet;

public class Wrapper{

	/**
	 * 
	 * @return Minecraft
	 */
	public static final Minecraft getMinecraft(){
		return Minecraft.getMinecraft();
	}
	
	/**
	 * 
	 * @return Player
	 */
	public static final EntityClientPlayerMP getPlayer(){
		return getMinecraft().thePlayer;
	}
	
	/**
	 * 
	 * @return World
	 */
	public static final WorldClient getWorld(){
		return getMinecraft().theWorld;
	}
	
	/**
	 * 
	 * @return FontRenderer
	 */
	public static final FontRenderer getFontRenderer(){
		return getMinecraft().fontRenderer;
	}
	
	/**
	 * 
	 * @return ScaledResolution
	 */
	public static final ScaledResolution getScaledResolution() {
		return new ScaledResolution(getMinecraft().gameSettings, getMinecraft().displayWidth, getMinecraft().displayHeight);
	}
	
	/**
	 * 
	 * @return ScreenWidth
	 */
	public static final int getScreenWidth() {
		return getScaledResolution().getScaledWidth();
	}

	/**
	 * 
	 * @return ScreenHeight
	 */
	public static final int getScreenHeight() {
		return getScaledResolution().getScaledHeight();
	}

	/**
	 * 
	 * @return GameSettings
	 */
	public static final GameSettings getGameSettings(){
		return getMinecraft().gameSettings;
	}
	/**
	 * 
	 * @param packet
	 */
	public static void sendPacket(Packet packet){
	    getPlayer().sendQueue.addToSendQueue(packet);
	}

	/**
	 * 
	 * @return
	 */
    public static final PlayerControllerMP getPlayerController() {
        return getMinecraft().playerController;
    }
    
    public static void drawChat(int width, int height) {
		GuiUtil.drawBorderedRect(-1, -height + 8, width + 4, 3, 1, 0xff000000, 0x65000000);
	}
}
