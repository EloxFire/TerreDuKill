package fr.eloxfire.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import fr.eloxfire.events.DeathEvent;
import fr.eloxfire.events.TimerBORDER;
import fr.eloxfire.events.TimerPVP;
import fr.eloxfire.game.StartGame;
import fr.eloxfire.game.main;
import fr.eloxfire.utils.TailleBORDER;

public class Scoreboards implements Listener{

	//SCOREBOARD
	public static Scoreboard s;
	public static org.bukkit.scoreboard.ScoreboardManager sm;

	//OBJECTIFS
	public static Objective vie;
	public static Objective TDK;
	//SCORES
	public static Score Joueurs;
	public static Score Mort;
	public static Score Space1;
	public static Score PVP;
	public static Score Bordures;
	public static Score Space2;
	public static Score Taille;
	public static Score Spawn;
	public static Score Space3;
	public static Score MOTD1;
	public static Score MOTD2;
	//public static Score Episode;
	
	public static Player player;
	
	public Scoreboards(){
		sm = main.getInstance().getServer().getScoreboardManager();
		s = sm.getNewScoreboard();
	}
	
	public static void initObjectives(){
		vie = s.registerNewObjective("Vie", "health");
		vie.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		
		TDK = s.registerNewObjective("TDK", "dummy");
		TDK.setDisplaySlot(DisplaySlot.SIDEBAR);
		TDK.setDisplayName("§6[T.D.K] - SAISON 4");
	}
	
	public static void initScores(){
		String TPVP = TimerPVP.formatIntoHHMMSS(TimerPVP.PVP);
		String TBORDER = TimerPVP.formatIntoHHMMSS(TimerBORDER.BORDER);
		int PREADY = StartGame.Ready.size();
		int MORT = DeathEvent.MORT.size();
		double TAILLE = TailleBORDER.Taille;
		
		
			Joueurs = TDK.getScore("§7Vivants : " + PREADY);
			Joueurs.setScore(7);
			
			Mort = TDK.getScore("§7Morts : " + MORT);
			Mort.setScore(6);
			
			Space1 = TDK.getScore("§e        ");
			Space1.setScore(5);
			
			PVP = TDK.getScore("§4PVP : " + TPVP);
			PVP.setScore(4);
			
			Bordures = TDK.getScore("§4Bordures : " + TBORDER );
			Bordures.setScore(3);
			
			Taille = TDK.getScore("§cTaille : " + TAILLE/2 + "-" + TAILLE/2);
			Taille.setScore(2);
			
			Spawn = TDK.getScore("§cSpawn : " + "X:" + StartGame.LocX + " Z:" + StartGame.LocZ);
			Spawn.setScore(1);
			
			Space2 = TDK.getScore("§a        ");
			Space2.setScore(0);
			
			MOTD1 = TDK.getScore("§6EDITION SPECIALE");
			MOTD1.setScore(-1);
			
			MOTD2 = TDK.getScore("§6     HALLOWEEN");
			MOTD2.setScore(-2);
			
			//Episode = TDK.getScore("§2Jour n° ");
			//Episode.setScore(2);
	}

	public static void refreshScoreboard() {
		
		String TPVP = TimerPVP.formatIntoHHMMSS(TimerPVP.PVP);
		String TBORDER = TimerPVP.formatIntoHHMMSS(TimerBORDER.BORDER);
		int PREADY = StartGame.Ready.size();
		int MORT = DeathEvent.MORT.size();
		double TAILLE = TailleBORDER.Taille;
		
		for(String ligne : s.getEntries()){
			if(ligne.contains("§7Vivants : ")){
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + PREADY;
				TDK.getScore(newLigne).setScore(7);
			}
		}
		
		for(String ligne : s.getEntries()){
			if(ligne.contains("§7Morts : ")){
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + MORT;
				TDK.getScore(newLigne).setScore(6);
			}
		}
		
		for(String ligne : s.getEntries()){
			if(ligne.contains("§4PVP : ")){
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + TPVP;
				TDK.getScore(newLigne).setScore(4);
			}
		}
		
		for(String ligne : s.getEntries()){
			if(ligne.contains("§4Bordures : ")){
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + TBORDER;
				TDK.getScore(newLigne).setScore(3);
			}
		}
		
		for(String ligne : s.getEntries()){
			if(ligne.contains("§cTaille : ")){
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + TAILLE/2 + "-" + TAILLE/2;
				TDK.getScore(newLigne).setScore(2);
			}
		}
		
		for(String ligne : s.getEntries()){
			if(ligne.contains("§cSpawn : ")){
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": " + "X:" + StartGame.LocX + " Z:" + StartGame.LocZ;
				TDK.getScore(newLigne).setScore(1);
			}
		}
		
		/*for(String ligne : s.getEntries()){
			if(ligne.contains("§2Jour n° ")){
				s.resetScores(ligne);
				String lastLigne = ligne.split(":")[0];
				String newLigne = lastLigne + ": ";
				TDK.getScore(newLigne).setScore(2);
			}
		}*/
	}
	
}
