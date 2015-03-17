package me.madness.toggles.movements;



import me.madness.base.Module;
import me.madness.toggles.TypeCategory;
import me.madness.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C07PacketPlayerDigging;

import org.lwjgl.input.Keyboard;

public class NoSlow extends Module{
	  private int bowRelease;

public NoSlow()
	{
	super("NoSlow", TypeCategory.MOVEMENT); 
	this.setColor(0xFFD4BB32); 
	this.setKey(Keyboard.KEY_L);
	this.setVisible(true);
	this.setDescription("Nessuno ti puo fermare.");
	}
public void onUpdate(){
	if(!this.getState()){
		return;
	}
		Wrapper.getPlayer().moveForward *= 0.0F;
	      Wrapper.getPlayer().movementInput.moveStrafe *= 0.0F;
	      if (Minecraft.getMinecraft().thePlayer.isBlocking()) {
	    	  Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, 0, 0, 0, 000));
	      }
	    
	if (this.getState() && (Minecraft.getMinecraft().thePlayer.isUsingItem())){
		return;
	}
		      Wrapper.getPlayer().moveForward *= 0.0F;
		      if (Minecraft.getMinecraft().thePlayer.isBlocking()) {
		        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, 0, 0, 0, 000));
		      }
		      Wrapper.getPlayer().movementInput.moveStrafe *= 0.0F;
		    }
		  }