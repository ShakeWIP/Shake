package me.madness.managers;

import java.util.Arrays;
import java.util.List;

import me.madness.base.Module;
import me.madness.toggles.combat.Aura;
import me.madness.toggles.combat.Autoblock;
import me.madness.toggles.combat.NoVelocity;
import me.madness.toggles.combat.Regen;
import me.madness.toggles.movements.Flight;
import me.madness.toggles.movements.NoSlow;
import me.madness.toggles.movements.Speed;

public class ToggleManager {
	public static NoVelocity novelocity;

	public static List<Module> activeToggles = Arrays.asList(new Module[]
			{	
			new Aura(), novelocity = new NoVelocity(), new Flight(), new Regen(),new Flight(), new NoSlow(), new Speed()
			}
			);				
			
	public static Module getModByClassName(String name)
	{
		for(Module var1: activeToggles) 
		{
			if(var1.getClass().getSimpleName().toLowerCase().trim().equals(name.toLowerCase().trim()))
			{
				return var1;
			}
		}
		return null;
	}
			
	public static Module getModByName(String name) 
	{
		for(Module var1: activeToggles)
		{
			if(var1.getName().trim().equalsIgnoreCase(name.trim()) || var1.toString().trim().equalsIgnoreCase(name.trim())) 
			{
				return var1;
			}
		}
		return null;
	}
							
	public static Module getClass(Class<?extends Module> clazz) 
	{
		for(Module mod: activeToggles)
		{
			if(mod.getClass() == clazz)
			{
				return mod;
			}
		}
		return null;
	}
	
	public static Module getClass(String name)
	{
		Module mod = getModByName(name);
		if(mod != null) 
		{
			return mod;
		}
		mod = getModByClassName(name);
				
		return mod;
	}
}	