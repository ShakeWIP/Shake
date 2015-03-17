package me.madness.utils.font;

import java.io.InputStream;

public class Fonts {

   public static CustomFonts font1 = new CustomFonts(getStream("Segoe UI"), 30);
   public static CustomFonts font1b = new CustomFonts(getStream("/assets/HelveticaNeue.otf"), 15.0F);
   public static CustomFonts font2 = new CustomFonts(getStream("/assets/HelveticaNeue.otf"), 17.0F);


   private static InputStream getStream(String font) {
      return Fonts.class.getResourceAsStream(font);
   }
}
