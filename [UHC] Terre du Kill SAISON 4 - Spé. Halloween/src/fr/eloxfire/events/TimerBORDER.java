package fr.eloxfire.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.eloxfire.game.main;
import fr.eloxfire.game.states;

public class TimerBORDER implements Listener{

	public static int BORDER = 2400;
	public static int taskBORDER;
	public static Player player;
	
	public static void TimerBorder(){
		if(main.getInstance().isState(states.GAME)){
			taskBORDER = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					BORDER--;
					
					for(Player pls : Bukkit.getOnlinePlayers()){
						if(BORDER == 10){
							main.getInstance().title.sendTitle(pls, "§4Réduction des bordures...", "§410s", 30);
							pls.getWorld().playSound(pls.getLocation(), Sound.NOTE_PLING, 8, 8);
						}
						
						if(BORDER == 5){
							main.getInstance().title.sendTitle(pls, "§45s", " ", 20);
							pls.getWorld().playSound(pls.getLocation(), Sound.NOTE_PLING, 8, 8);
						}
						if(BORDER == 4){
							main.getInstance().title.sendTitle(pls, "§44s", " ", 20);
							pls.getWorld().playSound(pls.getLocation(), Sound.NOTE_PLING, 8, 8);
						}
						if(BORDER == 3){
							main.getInstance().title.sendTitle(pls, "§43s", " ", 20);
							pls.getWorld().playSound(pls.getLocation(), Sound.NOTE_PLING, 8, 8);
						}
						if(BORDER == 2){
							main.getInstance().title.sendTitle(pls, "§42s", " ", 20);
							pls.getWorld().playSound(pls.getLocation(), Sound.NOTE_PLING, 8, 8);
						}
						if(BORDER == 1){
							main.getInstance().title.sendTitle(pls, "§61s", " ", 10);
							pls.getWorld().playSound(pls.getLocation(), Sound.NOTE_PLING, 8, 8);
						}
					}
					
					if(BORDER == 0){
						main.getInstance().setState(states.BORDER);
						Bukkit.broadcastMessage("§6[T.D.K] §4Reduction des bordures en cours !");
						Bukkit.getScheduler().cancelTask(taskBORDER);
						
					}
				}
			}, 20, 20);
		}
	}
	
}
