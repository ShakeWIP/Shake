package me.madness.overrides;

import me.madness.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;

import org.lwjgl.opengl.GL11;

public class GuiNewChatOverride extends GuiNewChat {

   private int height = 0;
   private int width = 0;
   private int delay = 0;


   public GuiNewChatOverride(Minecraft par1Minecraft) {
      super(par1Minecraft);
   }

   public void func_146230_a(int p_146230_1_) {
      if(this.field_146247_f.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
         int var2 = this.func_146232_i();
         boolean var3 = false;
         int var4 = 0;
         int var5 = this.field_146253_i.size();
         float var6 = this.field_146247_f.gameSettings.chatOpacity * 0.9F + 0.1F;
         if(var5 > 0) {
            if(this.func_146241_e()) {
               var3 = true;
            }

            float var7 = this.func_146244_h();
            MathHelper.ceiling_float_int((float)this.func_146228_f() / var7);
            float chatY;
            if(Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
               chatY = 15.0F;
            } else if(Minecraft.getMinecraft().thePlayer.getTotalArmorValue() == 0) {
               chatY = 3.0F;
            } else if(Minecraft.getMinecraft().thePlayer.isPotionActive(Potion.field_76444_x) && Minecraft.getMinecraft().thePlayer.getTotalArmorValue() > 0) {
               chatY = -15.0F;
            } else {
               chatY = -5.0F;
            }

            GL11.glPushMatrix();
            GL11.glTranslatef(2.0F, chatY, 0.0F);
            GL11.glScalef(var7, var7, 1.0F);
            boolean var14 = false;
            if(this.height > 0 && this.width > 0) {
               Wrapper.drawChat(this.width, this.height + 10);
               this.width = 0;
               this.height = 0;
            }

            for(int var9 = 0; var9 + this.field_146250_j < this.field_146253_i.size() && var9 < var2; ++var9) {
               ChatLine var10 = (ChatLine)this.field_146253_i.get(var9 + this.field_146250_j);
               if(var10 != null) {
                  int var11 = p_146230_1_ - var10.getUpdatedCounter();
                  if(var11 < 200 || var3) {
                     double var12 = (double)var11 / 200.0D;
                     var12 = 1.0D - var12;
                     var12 *= 10.0D;
                     if(var12 < 0.0D) {
                        var12 = 0.0D;
                     }

                     if(var12 > 1.0D) {
                        var12 = 1.0D;
                     }

                     var12 *= var12;
                     int var20 = (int)(255.0D * var12);
                     if(var3) {
                        var20 = 255;
                     }

                     var20 = (int)((float)var20 * var6);
                     ++var4;
                     if(var20 > 3) {
                        byte var15 = 0;
                        int var16 = -var9 * 9;
                        String var17 = var10.func_151461_a().getFormattedText();
                        Wrapper.getFontRenderer().drawStringWithShadow(var17, var15, var16 - 8, 16777215 + (var20 << 24));
                        this.height += 10;
                        int width = Wrapper.getFontRenderer().getStringWidth(StringUtils.stripControlCodes(var17));
                        if(this.width < width) {
                           this.width = width;
                        }
                     }
                  }
               }
            }

            GL11.glPopMatrix();
         }
      }

   }

   public void func_146234_a(IChatComponent p_146234_1_, int p_146234_2_) {
      super.func_146234_a(p_146234_1_, p_146234_2_);
   }
}
