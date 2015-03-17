package me.madness.toggles.combat;

import me.madness.base.Module;
import me.madness.toggles.TypeCategory;

import org.lwjgl.input.Keyboard;

public class Autoblock
extends Module
{
public Autoblock()
{
	super("AutoBlock", TypeCategory.COMBAT); 
	this.setColor(0xFF947659); 
	this.setKey(Keyboard.KEY_B);
	this.setVisible(false);
	this.setDescription("Ti para i colpi.");
}

public static boolean block = false;

public void onUpdate()
{
  if (this.getState()) {
    block = true;
  } else {
    block = false;
  }
}
	}