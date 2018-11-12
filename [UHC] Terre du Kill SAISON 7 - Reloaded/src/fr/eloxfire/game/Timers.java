package fr.eloxfire.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

public class Timers implements Listener {

	public static int timerChrono;
	public static int chrono = 0;
	public static int timerPvp;
	public static int pvp = 1200;
	public static int timerBorder;
	public static int border = 2400;

	// CONVERTISSEUR TIMERS FORMAT HH:MM:SS
	public static String formatIntoHHMMSS(int secs) {
		int remainder = secs % 3600;
		int minutes = remainder / 60;
		int seconds = remainder % 60;

		return new StringBuilder().append(minutes).append(":").append(seconds < 10 ? "0" : "").append(seconds).toString();
	}
	
	
	public static void chrono() {
		if(main.getInstance().isState(states.GAME)) {
			timerChrono = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {

				@Override
				public void run() {
					chrono++;
					
					if(chrono == 60) {
						for(Player players : Bukkit.getOnlinePlayers()) {
							players.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
							players.sendMessage("§6[TDK] §2Vous n'êtes §4plus invincible !");
						}
					}
				}
				
			},20,20);
		}
	}
	
	public static void pvp() {
		if(main.getInstance().isState(states.GAME)) {
			timerPvp = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {

				@Override
				public void run() {
					pvp--;
					
					if(pvp == 60) {
						for(Player players : Bukkit.getOnlinePlayers()) {
							players.setHealth(20);
							players.setFoodLevel(20);
							players.sendMessage("§6[TDK] §2Final Heal !!");
						}
					}
					if(pvp == 10) {
						Bukkit.broadcastMessage("§6[TDK] §4Activation du PVP dans 10 secondes !");
					}
					if(pvp == 0) {
						Bukkit.getScheduler().cancelTask(timerPvp);
						Bukkit.getWorld("world").setPVP(true);
						main.getInstance().setState(states.PVP);
						Bukkit.broadcastMessage("§6[TDK] §4Le PVP est actif ! Préparez-vous au combat !");
					}
				}
				
			},20,20);
		}
	}
	
	public static void border() {
		if(main.getInstance().isState(states.GAME)) {
			timerBorder = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {

				@Override
				public void run() {
					border--;
					
					if(border == 60) {
						Bukkit.broadcastMessage("§6[TDK] §4Réduction des bordures dans§6 1 §4minute !");
					}
					if(border == 0) {
						Bukkit.getScheduler().cancelTask(timerBorder);
						Bukkit.broadcastMessage("§6[TDK] §4Réduction des bordures ! Rapprochez vous du spawn !");
						Bukkit.getWorld("world").getWorldBorder().setSize(50, 2400);
						main.getInstance().setState(states.BORDER);
					}
					
				}
				
			},20,20);
		}
	}

}
