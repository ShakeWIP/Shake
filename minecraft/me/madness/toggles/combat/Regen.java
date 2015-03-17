package me.madness.toggles.combat;

import me.madness.base.Module;
import me.madness.toggles.TypeCategory;
import me.madness.utils.Wrapper;
import net.minecraft.network.play.client.C03PacketPlayer;

import org.lwjgl.input.Keyboard;

public class Regen
  extends Module
{
  public Regen()
  {
		super("Regen", TypeCategory.COMBAT); 
		this.setColor(0xFF4DB5A3); 
		this.setKey(Keyboard.KEY_UP);
		this.setVisible(true);
		this.setDescription("Regeneri la vita.");
  }
  
  public void onUpdate()
  {
    if (getState())
    {
        if ((Wrapper.getPlayer().onGround) && (!Wrapper.getPlayer().capabilities.isCreativeMode) && (Wrapper.getMinecraft().thePlayer.getFoodStats().getFoodLevel() > 16) && (Wrapper.getMinecraft().thePlayer.getHealth() < 20.0F) && (!Wrapper.getMinecraft().thePlayer.isEating())) {
            for (int i = 0; i < 2100; i++) {
              Wrapper.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer(false));
        }
      }
    }
    else {}
  }
  
}
