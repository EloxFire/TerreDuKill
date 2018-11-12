package fr.eloxfire.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Scoreboards implements Listener {

	// SCOREBOARD
	public static Scoreboard s;
	public static org.bukkit.scoreboard.ScoreboardManager sm;

	// OBJECTIFS
	public static Objective vie;
	public static Objective TDK;
	// SCORES
	public static Score titre;
	public static Score nbPlayer;
	public static Score nbDead;
	public static Score Separator1;
	public static Score timer;
	public static Score pvpTimer;
	public static Score borderTimer;
	public static Score Separator2;
	public static Score spawn;
	public static Score end1;
	public static Score end2;
	// public static Score Episode;

	public static Player player;

	public Scoreboards() {
		sm = main.getInstance().getServer().getScoreboardManager();
		s = sm.getNewScoreboard();
	}

	public static void initObjectives() {
		vie = s.registerNewObjective("Vie", "health");
		vie.setDisplaySlot(DisplaySlot.PLAYER_LIST);

		TDK = s.registerNewObjective("TDK", "dummy");
		TDK.setDisplaySlot(DisplaySlot.SIDEBAR);
		TDK.setDisplayName("§6Terre du Kill");
	}

	public static void initScores() {
		String TPVP = Timers.formatIntoHHMMSS(Timers.pvp);
		String TBORDER = Timers.formatIntoHHMMSS(Timers.border);
		String TEMPS = Timers.formatIntoHHMMSS(Timers.chrono);
		
		titre = TDK.getScore("§6      SAISON VI");
		titre.setScore(10);
		
		nbPlayer = TDK.getScore("§7Joueurs : ");
		nbPlayer.setScore(9);
		
		nbDead = TDK.getScore("§7Morts : ");
		nbDead.setScore(8);
		
		Separator1 = TDK.getScore("§6---===---");
		Separator1.setScore(7);
		
		timer = TDK.getScore("§2Timer : " + TEMPS);
		timer.setScore(6);
		
		pvpTimer = TDK.getScore("§2PVP : " + TPVP);
		pvpTimer.setScore(5);
		
		borderTimer = TDK.getScore("§2Bordures : " + TBORDER);
		borderTimer.setScore(4);
		
		spawn = TDK.getScore("§2Spawn : " + "§2X : " + Bukkit.getWorld("world").getSpawnLocation().getBlockX() + " §2Y : " + Bukkit.getWorld("world").getSpawnLocation().getBlockY());
		spawn.setScore(3);
		
		Separator2 = TDK.getScore("§6===---===");
		Separator2.setScore(2);
		
		end1 = TDK.getScore("§4BONNE CHANCE");
		end1.setScore(1);
		
		end2 = TDK.getScore("§4    A TOUS !");
		end2.setScore(0);

	}

	public static void refreshScoreboard() {
		String TPVP = Timers.formatIntoHHMMSS(Timers.pvp);
		String TBORDER = Timers.formatIntoHHMMSS(Timers.border);
		String TEMPS = Timers.formatIntoHHMMSS(Timers.chrono);

		for (String ligne : s.getEntries()) {
			if (ligne.contains("§7Joueurs : ")) {
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + PlayerEvents.playerReady.size();
				TDK.getScore(newLigne).setScore(9);
			}
		}
		
		for (String ligne : s.getEntries()) {
			if (ligne.contains("§7Morts : ")) {
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + PlayerEvents.playerDead.size();
				TDK.getScore(newLigne).setScore(8);
			}
		}
		
		for (String ligne : s.getEntries()) {
			if (ligne.contains("§2Timer : ")) {
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + TEMPS;
				TDK.getScore(newLigne).setScore(6);
			}
		}
		
		for (String ligne : s.getEntries()) {
			if (ligne.contains("§2PVP : ")) {
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + TPVP;
				TDK.getScore(newLigne).setScore(5);
			}
		}
		
		for (String ligne : s.getEntries()) {
			if (ligne.contains("§2Bordures : ")) {
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + TBORDER;
				TDK.getScore(newLigne).setScore(4);
			}
		}
		
		for (String ligne : s.getEntries()) {
			if (ligne.contains("§2Spawn : ")) {
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + "§2X : " + Bukkit.getWorld("world").getSpawnLocation().getBlockX() + " §2Z : " + Bukkit.getWorld("world").getSpawnLocation().getBlockZ();
				TDK.getScore(newLigne).setScore(3);
			}
		}

	}

}
