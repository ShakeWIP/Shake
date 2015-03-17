package me.madness.toggles.combat;

import java.util.Iterator;

import me.madness.base.Module;
import me.madness.toggles.TypeCategory;
import me.madness.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemSword;

import org.lwjgl.input.Keyboard;


public class Aura extends Module
{

    public Aura()
    {
		super("Dolpen Rapting", TypeCategory.COMBAT); 
		this.setColor(0xCC3300); 
		this.setKey(Keyboard.KEY_R);
		this.setVisible(true);
		this.setDescription("Atacckaka.");
		ka_range = 4.2F;
        ka_speed = 7.6;
        x = 0L;
        y = -1L;

    }
        
        
    public void onUpdate()
    {
        if(getState()){
            try
            {
                x = System.nanoTime() / 1000000L;
                for(Iterator iterator = Minecraft.getMinecraft().theWorld.loadedEntityList.iterator(); iterator.hasNext();)
                {
                    Object i = iterator.next();
                    Entity e = (Entity)i;
                    boolean setting = e != Minecraft.getMinecraft().thePlayer && Minecraft.getMinecraft().thePlayer.getDistanceToEntity(e) <= ka_range && (e instanceof EntityOtherPlayerMP) && !e.isDead && !e.isInvisible() && TempoPassato(1000D / ka_speed);
                    if(setting)
                    {
                        Wrapper.getMinecraft().gameSettings.keyBindUseItem.pressed = true;
                        boolean wasSprinting = Wrapper.getMinecraft().thePlayer.isSprinting();
                        Minecraft.getMinecraft().thePlayer.setSprinting(false);
                        Wrapper.getMinecraft().thePlayer.swingItem();
                        Wrapper.getMinecraft().playerController.attackEntity(Wrapper.getMinecraft().thePlayer, e);
                        Wrapper.getMinecraft().thePlayer.setSprinting(wasSprinting);
                        if(Autoblock.block)
                        {
                            if(Minecraft.getMinecraft().currentScreen == null && Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem() != null && (Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword))
                                Minecraft.getMinecraft().playerController.sendUseItem(Minecraft.getMinecraft().thePlayer, Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem());
                        }
                        break;
                    }
                }

            }
            catch(Exception exception) { }
    }
   }

    public boolean TempoPassato(double d)
    {
        return (double)(x - y) >= d;
    }

    public float ka_range;
    public double ka_speed;
    public long x;
    public long y;

}