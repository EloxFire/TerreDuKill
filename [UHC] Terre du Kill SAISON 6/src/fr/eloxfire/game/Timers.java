package fr.eloxfire.game;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Timers implements Listener {

	// ON DECLARE ET INITIALISE NOS VARIABLES
	public static int taskPVP;
	public static int taskBORDER;
	public static int taskGENE;
	public static int PVP = 1200;
	public static int BORDER = 2400;
	public static int GENE = 0;

	// CONVERTISSEUR DES TIMERS EN SECONDES AU FORMAT HHMMSS
	public static String formatIntoHHMMSS(int secs) {
		int remainder = secs % 3600;
		int minutes = remainder / 60;
		int seconds = remainder % 60;

		return new StringBuilder().append(minutes).append(":").append(seconds < 10 ? "0" : "").append(seconds)
				.toString();
	}

	// ON CREE UNE PROCEDURE POUR LE TIMER PVP
	public static void TPVP() {
		if (main.getInstance().isState(states.GAME)) {
			taskPVP = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {

				@Override
				public void run() {
					PVP--; // ON RETIRE 1 A PVP CHAQUE SECONDES
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (PVP == 70) {
							main.getInstance().title.sendTitle(player, "§4Final Heal dans 10s", "", 35);
						}

						if (PVP == 60) { // FINAL HEAL 60S AVANT L'ACTIVATION DU PVP
							player.setHealth(20);
							player.setFoodLevel(20);
							main.getInstance().title.sendTitle(player, "§4Final Heal !", "Ne me remerciez pas :)", 35);
						}
						// MESSAGES DE PREVENTIONS A L'ACTIVATION DU PVP
						if (PVP == 5 || PVP == 4 || PVP == 3 || PVP == 2 || PVP == 1) {
							main.getInstance().title.sendTitle(player, "§4Activation du PVP dans",
									"§4" + PVP + "§4 secondes", 20);
						}

						// ACTIVATION DU PVP DANS LE MONDE ET ANNONCE DE SON ACTIVATION
						if (PVP == 0) {
							Bukkit.getWorld("world").setPVP(true);
							main.getInstance().title.sendTitle(player, "§4Attention, le PVP est actif !",
									"§4Sortez les épées !", 35);
							Bukkit.getScheduler().cancelTask(taskPVP);

						}
					}
				}

			}, 20, 20);
		}
	}

	// ON CREE UNE PROCEDURE POUR LE TIMER BORDER
	public static void TBORDER() {
		if (main.getInstance().isState(states.GAME)) {
			taskBORDER = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {

				@Override
				public void run() {
					BORDER--; // ON RETIRE 1 A BORDER CHAQUE SECONDE
					for (Player player : Bukkit.getOnlinePlayers()) {

						if (BORDER == 60) {
							main.getInstance().title.sendTitle(player, "§4Réduction des bordures dans", "§41 minute !",
									35);
						}
						if (BORDER == 10 || BORDER == 5 || BORDER == 4 || BORDER == 3 || BORDER == 2 || BORDER == 1) {
							main.getInstance().title.sendTitle(player, "§4Réduction des bordures dans",
									"§4" + BORDER + "§4 secondes", 20);
						}
						if (BORDER == 0) {

							main.getInstance().title.sendTitle(player, "§4Réduction en cours...",
									"§4Rapprochez-vous du spawn !", 35);
							Bukkit.getScheduler().cancelTask(taskBORDER);
						}
					}
				}

			}, 20, 20);
		}
	}

	// ON CREE UNE PROCEDURE POUR LE TIMER GENERAL
	public static void TGENE() {
		if (main.getInstance().isState(states.GAME)) {
			taskGENE = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {

				@Override
				public void run() {
					GENE++; // ON AJOUTE 1 SECONDE A GENERAL CHAQUE SECONDE
				}

			}, 20, 20);
		}
	}

}
