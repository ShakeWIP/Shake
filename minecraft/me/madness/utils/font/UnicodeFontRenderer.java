package me.madness.utils.font;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glIsEnabled;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScaled;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class UnicodeFontRenderer extends FontRenderer
{
	private final TrueTypeFont font;
	
	public UnicodeFontRenderer(Font awtFont)
	{
		super(Minecraft.getMinecraft().gameSettings, new ResourceLocation(
			"textures/font/ascii.png"), Minecraft.getMinecraft()
			.getTextureManager(), false);
		
		font = new TrueTypeFont(awtFont, false);
		String alphabet =
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		FONT_HEIGHT = font.getHeight(alphabet) / 2;
	}
	
	@Override
	public int drawString(String string, int x, int y, int color)
	{
		if(string == null)
			return 0;
		glPushMatrix();
		glScaled(0.5, 0.5, 0.5);
		
		boolean blend = glIsEnabled(GL_BLEND);
		boolean lighting = glIsEnabled(GL_LIGHTING);
		boolean texture = glIsEnabled(GL_TEXTURE_2D);
		if(!blend)
			glEnable(GL_BLEND);
		if(lighting)
			glDisable(GL_LIGHTING);
		if(!texture)
			glEnable(GL_TEXTURE_2D);
		TextureImpl.bindNone();
		x *= 2;
		y *= 2;
		
		font.drawString(x, y, string, new org.newdawn.slick.Color(color));
		
		if(!texture)
			glDisable(GL_TEXTURE_2D);
		if(lighting)
			glEnable(GL_LIGHTING);
		if(!blend)
			glDisable(GL_BLEND);
		glPopMatrix();
		return x;
	}
	
	@Override
	public int drawStringWithShadow(String string, float x, float y, int color)
	{
		return drawString(string, (int)x, (int)y, color);
	}
	
	@Override
	public int getCharWidth(char c)
	{
		return getStringWidth(Character.toString(c));
	}
	
	@Override
	public int getStringWidth(String string)
	{
		return font.getWidth(string) / 2;
	}
	
	public int getStringHeight(String string)
	{
		return font.getHeight(string) / 2;
	}
}
