package fr.eloxfire.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.eloxfire.game.main;

public class TailleBORDER implements Listener{

	public static int TAILLE = 0;
	public static int taskTAILLE;
	public static int Taille;
	public static Player player;
	
	public static void TailleBorder(){
		taskTAILLE = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				TAILLE++;
				
				World world = Bukkit.getWorld("world");
                WorldBorder wb = world.getWorldBorder();
                Taille = (int) wb.getSize();
				
			}
		}, 20, 20);
	}
	
}
