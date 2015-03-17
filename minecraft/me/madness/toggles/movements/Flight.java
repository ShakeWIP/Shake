package me.madness.toggles.movements;

import me.madness.base.Module;
import me.madness.toggles.TypeCategory;
import me.madness.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

import org.lwjgl.input.Keyboard;

public class Flight extends Module
{
	  private float speed;
	  private float height;
	  private double pitch;
	  private double yaw;
	  private boolean setLoc;
	  
  public Flight()
  {
			super("Flight", TypeCategory.MOVEMENT); 
			this.setColor(0xFF7FB9D8); 
			this.setKey(Keyboard.KEY_F);
			this.setVisible(true);
			this.setDescription("Voli.");
  }
  
  public static boolean mode = true;
  
  public void onUpdate()
  {
	  /*if(this.getState()){
		  
	         setName("Flight (Normal)");
	         Wrapper.getPlayer().onGround = true;
	         Wrapper.getPlayer().motionX = 0.0D;
	         Wrapper.getPlayer().motionY = 0.0D;
	         Wrapper.getPlayer().motionZ = 0.0D;
	         Wrapper.getPlayer().jumpMovementFactor = 2.0F;
	         if (Wrapper.getMinecraft().inGameHasFocus)
	         {
	           if (Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindJump.keyCode))
	           {
	             Wrapper.getPlayer().motionY += 1.0D;
	           }
	           else if (Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindSneak.keyCode))
	           {
	             Wrapper.getPlayer().motionY -= 1.0D;
	           }
	         }
	         Wrapper.getPlayer().onGround = false;
	  }*/
	  if(this.getState()){
	  if(mode){
		  setName("Flight (NC)");
    if ((!Minecraft.getMinecraft().thePlayer.isInWater()) && (!(Minecraft.getMinecraft().thePlayer.onGround)))
    {
    	Minecraft.getMinecraft().thePlayer.isAirBorne = false;
    	Minecraft.getMinecraft().thePlayer.motionY =- 0.03345F;
    	Minecraft.getMinecraft().thePlayer.capabilities.allowFlying = true;
    if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode()) && Minecraft.getMinecraft().currentScreen == null)
    {
    	Minecraft.getMinecraft().thePlayer.isAirBorne = false;
    	Minecraft.getMinecraft().thePlayer.motionY =- 0.5;
    	Minecraft.getMinecraft().thePlayer.capabilities.allowFlying = true;
    }
    else if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()) && Minecraft.getMinecraft().currentScreen == null)
    {
    	Minecraft.getMinecraft().thePlayer.isAirBorne = false;
    	Minecraft.getMinecraft().thePlayer.motionY =- 0.03345F;
    	//Minecraft.getMinecraft().thePlayer.addVelocity(Wrapper.getPlayer()().motionX * 3, 0, Wrapper.getPlayer()().motionX * 3);
       //Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY - 1.722D, Wrapper.getPlayer()().boundingBox.minY, Minecraft.getMinecraft().thePlayer.posZ, true));
    	Minecraft.getMinecraft().thePlayer.setPosition(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY + 0.03000F, Minecraft.getMinecraft().thePlayer.posZ);
    	Minecraft.getMinecraft().thePlayer.capabilities.allowFlying = true;
    }
    }
  }
	  }
  }
public void onDisable ()
  {
	  Minecraft.getMinecraft().thePlayer.capabilities.isFlying = false;
  }
  
  public void onEnable()
  {
	  if(mode){
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.boundingBox.minY + 0.1, Minecraft.getMinecraft().thePlayer.posY + 0.1, Minecraft.getMinecraft().thePlayer.posZ, false));
		  Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.boundingBox.minY - 5,Minecraft.getMinecraft().thePlayer.posY - 5, Minecraft.getMinecraft().thePlayer.posZ, false));
		  Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.boundingBox.minY + 0.1, Minecraft.getMinecraft().thePlayer.posY + 0.1, Minecraft.getMinecraft().thePlayer.posZ, false));
		  Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.boundingBox.minY - 5, Minecraft.getMinecraft().thePlayer.posY - 5, Minecraft.getMinecraft().thePlayer.posZ, false));
		  Minecraft.getMinecraft().thePlayer.setPosition(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY - 5, Minecraft.getMinecraft().thePlayer.posZ);
		  Minecraft.getMinecraft().thePlayer.onGround = true;
	  }
	  
  }
}