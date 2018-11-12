package fr.eloxfire.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.eloxfire.game.main;
import fr.eloxfire.game.states;

public class TimerPVP implements Listener{

	public static int PVP = 1200;
	public static int taskPVP;
	
	public static String formatIntoHHMMSS(int secs){
		int remainder = secs % 3600;
		int minutes = remainder / 60;
		int seconds = remainder % 60;
		 
		return new StringBuilder().append(minutes).append(":").append(seconds < 10 ? "0" : "").append(seconds).toString();
		}
	
	
	public static void TimerPvp(){
		if(main.getInstance().isState(states.GAME)){
			taskPVP = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					PVP--;
					
					if(PVP == 60){
						for(Player pls : Bukkit.getOnlinePlayers()){
							pls.setHealth(20);
							pls.setFoodLevel(20);
						}
					}
					if(PVP == 5 || PVP == 4 || PVP == 3 || PVP == 2 || PVP == 1){
						Bukkit.broadcastMessage("§6[T.D.K] §2PVP actif dans §4" + PVP + "§4s");
					}
					
					if(PVP == 0){
						Bukkit.getWorld("world").setPVP(true);
						main.getInstance().setState(states.PVP);
						Bukkit.broadcastMessage("§6[T.D.K] §4Attention, le PVP est maintenant actif !");
						Bukkit.getScheduler().cancelTask(taskPVP);
						
					}
				}
			}, 20, 20);
		}
	}
	
}
