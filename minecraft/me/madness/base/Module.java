package me.madness.base;

import me.madness.toggles.TypeCategory;
import me.madness.utils.Wrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;


public class Module extends Wrapper{
	
	protected String name;
	
	protected String displayName; 
	
	protected String description;
	
	protected int key = 0;
	
	protected int color = -1;
	
	protected TypeCategory ToggleCategory;
	
	protected boolean state;
	
	protected boolean visible;
	
	public Module(String nome,  TypeCategory categoria){
		this.ToggleCategory = categoria;
		this.name = nome;
		this.displayName = nome;
	}

	/**
	 * Metodi di toggle.
	 */
	public void onEnable(){
		if(Wrapper.getWorld() != null && Wrapper.getPlayer() != null){
		}
	}
	
	public void onDisable(){
	}
	
	public final void onToggle(){
		this.state = !this.state;
		if(this.state){
			onEnable();
			
		}else
			onDisable();
		
	}

	
	/**
	 * 
	 * @return Name.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @param name
	 * @return New name.
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 
	 * @return Description
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	/**
	 * 
	 * @return Key.
	 */
	public int getKey(){
		return this.key;
	}
	
	/**
	 * 
	 * @param key
	 */
	public void setKey(int key){
		 this.key = key;
	}
	
	/**
	 * 
	 * @return State.
	 */
	public boolean getState(){
		return this.state;
	}
	
	/**
	 * 
	 * @param state
	 */
	public void setState(boolean state){
		this.state = state;
		if(this.state)
		{
			onEnable();
		}
	}
	
	/**
	 * 
	 * @return Visible.
	 */
	public boolean isVisible(){
		return this.visible;
	}
	
	/**
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	/**
	 * 
	 * @return Color
	 */
	public int getColor(){
		return this.color;
	}
	
	/**
	 * 
	 * @param color
	 */
	public void setColor(int color){
		this.color = color;
	}
	
	/**
	 * 
	 * @return displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 
	 * @param displayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 
	 * @return 
	 * @return getModuleCategory
	 */
	public TypeCategory getModuleCategory(){
		return ToggleCategory;
	}

	/*
	 * HOOKS
	 */	
	public void onUpdate(){}
	
	public void onRender(){}
	
	public void sendMotionUpdates(){}
	
	  public void onAttackEntity(EntityPlayer ep, Entity e) {}
	  
	  public boolean EntityVelocity()
	  {
	    return true;
	  }
}