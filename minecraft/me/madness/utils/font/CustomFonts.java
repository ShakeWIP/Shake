package me.madness.utils.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.StringUtils;

import org.lwjgl.opengl.GL11;

public class CustomFonts {

   private int texID;
   private int[] xPos;
   private int[] yPos;
   private int startChar;
   private int endChar;
   private FontMetrics metrics;
   public String fontName;
   public int fSize;
   private Graphics graphics;


   public CustomFonts(Object obj, float i) {
      this(Minecraft.getMinecraft(), obj, i, 31, 127);
   }

   public CustomFonts(Minecraft minecraft, Object obj, float i, int j, int k) {
      this.startChar = j;
      this.endChar = k;
      this.xPos = new int[k - j];
      this.yPos = new int[k - j];
      BufferedImage bufferedimage = new BufferedImage(256, 256, 2);
      Graphics g = bufferedimage.getGraphics();
      this.fSize = (int)i;

      try {
         Graphics2D var12 = (Graphics2D)g;
         var12.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         Font var13 = Font.createFont(0, (InputStream)obj).deriveFont(i);
         g.setFont(var13);
      } catch (Exception var11) {
         var11.printStackTrace();
      }

      g.setColor(new Color(255, 255, 255, 0));
      g.fillRect(0, 0, 256, 256);
      g.setColor(Color.white);
      this.metrics = g.getFontMetrics();
      int var121 = 2;
      int var131 = 2;

      for(int j1 = j; j1 < k; ++j1) {
         g.drawString("" + (char)j1, var121, var131 + g.getFontMetrics().getAscent());
         this.xPos[j1 - j] = var121;
         this.yPos[j1 - j] = var131 - this.metrics.getMaxDescent();
         var121 += this.metrics.stringWidth("" + (char)j1) + 2;
         if(var121 >= 250 - this.metrics.getMaxAdvance()) {
            var121 = 2;
            var131 = (int)((float)var131 + (float)(this.metrics.getMaxAscent() + this.metrics.getMaxDescent()) + i / 2.0F);
         }
      }

      this.texID = (new DynamicTexture(bufferedimage)).getGlTextureId();
   }

   public CustomFonts(Minecraft mine, Font f, int i, int j) {
      this.startChar = i;
      this.endChar = j;
      this.xPos = new int[j - i];
      this.yPos = new int[j - i];
      BufferedImage bufferedImage = new BufferedImage(256, 256, 2);
      Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
      graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      graphics2D.setFont(f);
      int var7 = f.getSize();
      graphics2D.setColor(new Color(255, 255, 255, 0));
      graphics2D.fillRect(0, 0, 256, 256);
      graphics2D.setColor(Color.white);
      this.metrics = graphics2D.getFontMetrics(f);
      int var8 = 2;
      int var9 = 2;

      for(int var11 = i; var11 < j; ++var11) {
         graphics2D.drawString(String.valueOf((char)var11), var8, var9 + this.metrics.getAscent());
         this.xPos[var11 - i] = var8;
         this.yPos[var11 - i] = var9 - this.metrics.getMaxDescent() + 2;
         var8 += this.metrics.stringWidth("" + (char)var11) + 2;
         if(var8 >= 250 - this.metrics.getMaxAdvance()) {
            var8 = 2;
            var9 += this.metrics.getMaxAscent() + this.metrics.getMaxDescent() + var7 / 2;
         }
      }

      try {
         this.texID = (new DynamicTexture(bufferedImage)).getGlTextureId();
      } catch (NullPointerException var111) {
         var111.printStackTrace();
      }

      this.graphics = graphics2D;
   }

   public void drawStringS(String s, int i, int j) {
      this.drawString(s, (double)(i + 2), (double)(j + 2), true);
      this.drawString(s, (double)i, (double)j, false);
   }

   public void drawGoodStringWithOutScalingAndShit(String s, int i, int j, int colour) {
      float red = (float)(colour >> 16 & 255) / 255.0F;
      float green = (float)(colour >> 8 & 255) / 255.0F;
      float blue = (float)(colour & 255) / 255.0F;
      new Color(red, green, blue, 1.0F);
      i *= 2;
      j *= 2;
      j -= 5;
      GL11.glPushMatrix();
      GL11.glEnable(3553);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
      GL11.glColor4f(red, green, blue, 255.0F);
      this.drawString(s, (double)i, (double)j, false);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
   }

   public void drawGoodString(String s, double i, double j, int colour, boolean flag) {
      if(flag) {
         colour = (colour & 16579836) >> 2 | colour & -16777216;
      }

      float red = (float)(colour >> 16 & 255) / 255.0F;
      float green = (float)(colour >> 8 & 255) / 255.0F;
      float blue = (float)(colour & 255) / 255.0F;
      i *= 2.0D;
      j *= 2.0D;
      GL11.glPushMatrix();
      GL11.glEnable(3553);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
      GL11.glColor4f(red, green, blue, 255.0F);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      this.drawString(s, i, j, false);
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
      GL11.glPopMatrix();
   }

   public void drawStringWithShadow(String s, float f, float g, int colour) {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 2.5F, 0.0F);
      this.drawGoodString(StringUtils.stripControlCodes(s), (double)(f + 0.5F), (double)(g - 2.0F), -16777216, false);
      f *= 2.0F;
      g *= 2.0F;
      g -= 5.0F;
      float red = (float)(colour >> 16 & 255) / 255.0F;
      float green = (float)(colour >> 8 & 255) / 255.0F;
      float blue = (float)(colour & 255) / 255.0F;
      new Color(red, green, blue, 1.0F);
      GL11.glPushMatrix();
      GL11.glEnable(3553);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
      GL11.glColor4f(red, green, blue, 255.0F);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      this.drawString(s, (double)f, (double)g, false);
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   public void drawGoodString(String s, int i, int j, Color c) {
      i *= 2;
      j *= 2;
      j -= 5;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
      GL11.glPushMatrix();
      GL11.glEnable(3553);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
      GL11.glColor4f((float)(c.getRed() / 255), (float)(c.getGreen() / 255), (float)(c.getBlue() / 255), 1.0F);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      this.drawString(s, (double)i, (double)j, false);
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
      GL11.glPopMatrix();
   }

   public void drawStringWithShadow(String s, int i, int j, Color c) {
      i *= 2;
      j *= 2;
      j -= 5;
      GL11.glEnable(3106);
      GL11.glColor4f((float)c.getRed(), (float)c.getGreen(), (float)c.getBlue(), 255.0F);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      this.drawStringS(s, i, j);
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      GL11.glDisable(3106);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
   }

   public void drawString(String s, double i, double j, boolean flag) {
      s = s.replace("§§", "§").replace("§r", "§f");
      GL11.glEnable(3553);
      GL11.glEnable(3042);
      GL11.glBindTexture(3553, this.texID);
      if(flag) {
         GL11.glColor4f(0.2F, 0.2F, 0.2F, 255.0F);
      }

      double k = i;
      double realY = j;

      try {
         for(int l = 0; l < s.length(); ++l) {
            char c1 = s.charAt(l);
            if(c1 == 124) {
               j = realY - 1.0D;
            } else if(c1 == 91) {
               j = realY - 1.0D;
            } else if(c1 == 93) {
               j = realY - 1.0D;
            } else {
               j = realY;
            }

            if(c1 == 10) {
               double var10000 = j + (double)(this.metrics.getAscent() + 4);
               i = k;
               ++l;
            } else {
               if(c1 == 225 || c1 == 224 || c1 == 226 || c1 == 228 || c1 == 193 || c1 == 192 || c1 == 194 || c1 == 196) {
                  c1 = 97;
               }

               if(c1 == 233 || c1 == 232 || c1 == 234 || c1 == 235 || c1 == 201 || c1 == 200 || c1 == 202 || c1 == 203) {
                  c1 = 101;
               }

               if(c1 == 237 || c1 == 236 || c1 == 238 || c1 == 239 || c1 == 205 || c1 == 204 || c1 == 206 || c1 == 207) {
                  c1 = 105;
               }

               if(c1 == 243 || c1 == 242 || c1 == 244 || c1 == 246 || c1 == 211 || c1 == 210 || c1 == 212 || c1 == 214) {
                  c1 = 111;
               }

               if(c1 == 250 || c1 == 249 || c1 == 251 || c1 == 252 || c1 == 218 || c1 == 217 || c1 == 219 || c1 == 220) {
                  c1 = 117;
               }

               if(c1 == 167) {
                  ++l;
                  char c = s.charAt(l);
                  if(c == 97 || c == 65 || c == 103) {
                     if(!flag) {
                        GL11.glColor4f(0.25F, 1.0F, 0.25F, 1.0F);
                     } else {
                        GL11.glColor4f(0.05F, 0.2F, 0.05F, 1.0F);
                     }
                  }

                  if(c == 98 || c == 66) {
                     if(!flag) {
                        GL11.glColor4f(0.25F, 1.0F, 1.0F, 1.0F);
                     } else {
                        GL11.glColor4f(0.05F, 0.2F, 0.2F, 1.0F);
                     }
                  }

                  if(c == 99 || c == 67) {
                     if(!flag) {
                        GL11.glColor4f(1.0F, 0.25F, 0.25F, 1.0F);
                     } else {
                        GL11.glColor4f(0.2F, 0.05F, 0.05F, 1.0F);
                     }
                  }

                  if(c == 100 || c == 68) {
                     if(!flag) {
                        GL11.glColor4f(1.0F, 0.25F, 1.0F, 1.0F);
                     } else {
                        GL11.glColor4f(0.2F, 0.05F, 0.2F, 1.0F);
                     }
                  }

                  if(c == 101 || c == 69) {
                     if(!flag) {
                        GL11.glColor4f(1.0F, 1.0F, 0.25F, 1.0F);
                     } else {
                        GL11.glColor4f(0.2F, 0.2F, 0.05F, 1.0F);
                     }
                  }

                  if(c == 102 || c == 70) {
                     if(!flag) {
                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                     } else {
                        GL11.glColor4f(0.2F, 0.2F, 0.2F, 1.0F);
                     }
                  }

                  if(c == 113) {
                     GL11.glColor4f(255.0F, 0.0F, 0.0F, 255.0F);
                  }

                  if(c == 48) {
                     GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
                  }

                  if(c == 49) {
                     if(!flag) {
                        GL11.glColor4f(0.0F, 0.0F, 0.75F, 1.0F);
                     } else {
                        GL11.glColor4f(0.0F, 0.0F, 0.1F, 1.0F);
                     }
                  }

                  if(c == 50) {
                     if(!flag) {
                        GL11.glColor4f(0.0F, 0.75F, 0.0F, 1.0F);
                     } else {
                        GL11.glColor4f(0.0F, 0.1F, 0.0F, 1.0F);
                     }
                  }

                  if(c == 51) {
                     if(!flag) {
                        GL11.glColor4f(0.0F, 0.75F, 0.75F, 1.0F);
                     } else {
                        GL11.glColor4f(0.0F, 0.1F, 0.1F, 1.0F);
                     }
                  }

                  if(c == 52) {
                     if(!flag) {
                        GL11.glColor4f(0.75F, 0.0F, 0.0F, 1.0F);
                     } else {
                        GL11.glColor4f(0.1F, 0.0F, 0.0F, 1.0F);
                     }
                  }

                  if(c == 53) {
                     if(!flag) {
                        GL11.glColor4f(0.75F, 0.0F, 0.75F, 1.0F);
                     } else {
                        GL11.glColor4f(0.1F, 0.0F, 0.1F, 1.0F);
                     }
                  }

                  if(c == 54) {
                     if(!flag) {
                        GL11.glColor4f(1.0F, 0.75F, 0.0F, 1.0F);
                     } else {
                        GL11.glColor4f(0.1F, 0.1F, 0.0F, 1.0F);
                     }
                  }

                  if(c == 55) {
                     if(!flag) {
                        GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
                     } else {
                        GL11.glColor4f(0.1F, 0.1F, 0.1F, 1.0F);
                     }
                  }

                  if(c == 56) {
                     if(!flag) {
                        GL11.glColor4f(0.25F, 0.25F, 0.25F, 1.0F);
                     } else {
                        GL11.glColor4f(0.05F, 0.05F, 0.05F, 1.0F);
                     }
                  }

                  if(c == 57) {
                     if(!flag) {
                        GL11.glColor4f(0.25F, 0.25F, 1.0F, 1.0F);
                     } else {
                        GL11.glColor4f(0.05F, 0.05F, 0.2F, 1.0F);
                     }
                  }
               } else if(c1 <= 126) {
                  this.drawChar(c1, i, j);
                  i = (double)((int)(i + this.metrics.getStringBounds("" + c1, (Graphics)null).getWidth()));
               }
            }
         }
      } catch (Exception var15) {
         ;
      }

   }

   public FontMetrics getMetrics() {
      return this.metrics;
   }

   public int getStringWidth(String s) {
      return (int)this.getBounds(StringUtils.stripControlCodes(s)).getWidth() / 2;
   }

   public int getStringHeight(String s) {
      return (int)this.getBounds(s).getHeight();
   }

   private Rectangle getBounds(String s) {
      try {
         int var8 = 0;
         int j = 0;
         int k = 0;

         for(int l = 0; l < s.length(); ++l) {
            char c = s.charAt(l);
            if(c == 92) {
               char c1 = s.charAt(l + 1);
               if(c1 == 110) {
                  j += this.metrics.getAscent() + 2;
                  if(k > var8) {
                     var8 = k;
                  }

                  k = 0;
               }

               ++l;
            } else {
               k += this.metrics.stringWidth("" + c);
            }
         }

         if(k > var8) {
            var8 = k;
         }

         j += this.metrics.getAscent();
         return new Rectangle(0, 0, var8, j);
      } catch (Exception var81) {
         return new Rectangle(0, 0, 0, 0);
      }
   }

   private void drawChar(char c, double i, double j) {
      Rectangle2D rectangle2d = this.metrics.getStringBounds("" + c, (Graphics)null);
      this.drawTexturedModalRect(i, j - 2.0D, this.xPos[(byte)c - this.startChar], this.yPos[(byte)c - this.startChar], (int)rectangle2d.getWidth(), (int)rectangle2d.getHeight() + this.metrics.getMaxDescent() + 3);
   }

   public void drawTexturedModalRect(double i, double d, int par3, int par4, int par5, int par6) {
      float var7 = 0.00390625F;
      float var8 = 0.00390625F;
      Tessellator var9 = Tessellator.instance;
      var9.startDrawingQuads();
      var9.addVertexWithUV(i + 0.0D, d + (double)par6, 0.0D, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + par6) * var8));
      var9.addVertexWithUV(i + (double)par5, d + (double)par6, 0.0D, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + par6) * var8));
      var9.addVertexWithUV(i + (double)par5, d + 0.0D, 0.0D, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + 0) * var8));
      var9.addVertexWithUV(i + 0.0D, d + 0.0D, 0.0D, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + 0) * var8));
      var9.draw();
   }
}
