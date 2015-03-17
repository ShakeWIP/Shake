package me.madness.overrides;

import me.madness.base.Module;
import me.madness.core.Madness;
import me.madness.utils.Wrapper;
import me.madness.utils.font.Fonts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiIngameOverride extends GuiIngame{

	/**
	 * @author Fox
	 * @param par1Minecraft
	 */
	   
	private int posY = 5;
	 
	private boolean keyStates[];
	
	public GuiIngameOverride(Minecraft par1Minecraft) {
		super(par1Minecraft);
		keyStates = new boolean [256];	
	}

	public boolean checkKey(int i){
		if(Wrapper.getMinecraft().currentScreen != null){return false;}
	    	if(Keyboard.isKeyDown(i) != keyStates[i]){
	    		return keyStates[i] = !keyStates[i];
	    }else{
	    		return false;
	    }
	}
	
	@Override
	public void renderGameOverlay(float par1, boolean par2, int par3, int par4){
		super.renderGameOverlay(par1, par2, par3, par4);
		if(!Wrapper.getGameSettings().showDebugInfo){
			this.showName(Madness.NOME_CLIENT);;
		}
		ScaledResolution sr = new ScaledResolution(Wrapper.getMinecraft().gameSettings, Wrapper.getMinecraft().displayWidth, Wrapper.getMinecraft().displayHeight);
		int width = sr.getScaledWidth();
		int height  = sr.getScaledHeight();
		int number = 0;
		for(Module var1: Madness.getInstance().getTogglesManager().activeToggles){		
			if(var1.getState() && var1.isVisible()){
				int x = 12;
				int y = 2 + (number * 10);
				Wrapper.getFontRenderer().drawStringWithShadow(var1.getName(), width - Wrapper.getFontRenderer().getStringWidth(var1.getName()) - 2
				,y, var1.getColor());
				number++;
			}
		}
	}
	


	/**
	 * INT X - INT Y 
	 * @param s
	 */
	private final void showName(String s){	
		GL11.glScalef(2f, 2f, 2f);
		Wrapper.getFontRenderer().drawStringWithShadow("M", 1, 1, 0xFFBA00);
		GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
    	Wrapper.getFontRenderer().drawStringWithShadow("FPS: " + Wrapper.getMinecraft().debugFPS, 31, 8, 0xcc666666);
        GL11.glScalef(2, 2, 2);
		Wrapper.getFontRenderer().drawStringWithShadow("adness", 15, 9, 0xFFBA00);
		
		for(Module var1:  Madness.getInstance().getTogglesManager().activeToggles){	
			if(checkKey(var1.getKey())){		
				var1.onToggle();
			
		}
	}
}
}