package me.madness.toggles.combat;

import me.madness.base.Module;
import me.madness.toggles.TypeCategory;
import me.madness.utils.Wrapper;

import org.lwjgl.input.Keyboard;


public class NoVelocity extends Module{
	
	  public NoVelocity()
	  {
		  super("NoVelocity", TypeCategory.COMBAT); 
			this.setColor(0xcc666666); 
			this.setKey(Keyboard.KEY_O);
			this.setVisible(true);
			this.setDescription("Non fa subbire il contraccolpo.");
	  }
	  
	  public void onUpdate()
	  {
		  if(getState()){
	  }
	}
}		  
		
	  