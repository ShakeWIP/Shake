package me.madness.toggles.movements;

import me.madness.base.Module;
import me.madness.toggles.TypeCategory;
import me.madness.utils.Wrapper;
import net.minecraft.network.play.client.C03PacketPlayer;

import org.lwjgl.input.Keyboard;

public class Speed extends Module{

	public static float speed = 0.0F;
	
	public Speed() {
		super("Speed", TypeCategory.MOVEMENT); 
		this.setColor(0xFF96CC39); 
		this.setKey(Keyboard.KEY_G);
		this.setVisible(true);
		this.setDescription("Sprinta automaticamente.");
	}
	
	public void onUpdate(){
		if(this.getState()){
			     speed += 2.0F;
			 
			     if ((speed >= 10.0F) && (!Wrapper.getPlayer().movementInput.jump) && (Wrapper.getPlayer().onGround))
			     {
				   Wrapper.getPlayer().setSprinting(true);
			       Wrapper.getMinecraft().timer.timerSpeed = 1.1999F;
			       Wrapper.getPlayer().motionX *= 2.299D;
			       Wrapper.getPlayer().motionZ *= 2.299D;
			       speed = 0.0F;
			     }
			     
		}
	} 
		    public void onDisable()
			{
				Wrapper.getPlayer().setSprinting(false);
				Wrapper.getMinecraft().timer.timerSpeed = 1.0F;
			}
}