package fr.eloxfire.game;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener{

	//LISTE DES PLAYER READY
	public List<Player> Pready = new ArrayList<Player>();
	//LISTE POUR JOUEURS EN VIE
	public static List<Player> PlayerAlive = new ArrayList<Player>();
	//LISTE POUR TP TOUS LES PLAYER EN CAS DE FORCESTOP DU GAME
	public List<Player> FinishGame = new ArrayList<Player>();	
	//TIMER
	public static int heure=0, minutes=0, secondes=0;
	public static int episode=0;

	
	//POUR EMPECHER LES PLAYERS DE POSER DES BLOCKS
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(!p.hasPermission("operator")){
			if(main.getInstance().isState(states.PREGAME) || main.getInstance().isState(states.FINISH)){
				e.setCancelled(true);
			}
		}
	}
	
	//POUR EMPECHER LES PLAYERS DE CASSER DES BLOCKS
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		if(!p.hasPermission("operator")){
			if(main.getInstance().isState(states.PREGAME) || main.getInstance().isState(states.FINISH)){
				e.setCancelled(true);
			}
		}
	}
	
	//MODIFICATION MESSAGE DE CONNEXION
	@EventHandler
	public void joinEvent(PlayerJoinEvent e){
		Player p = e.getPlayer();
		e.setJoinMessage("§6[§2+§6] §2" + p.getName() + " à rejoinds la partie !");
		
		FinishGame.add(p);
	}
	
	//MODIFICATION MESSAGE DE DECONNEXION
	@EventHandler
	public void quiEvent(PlayerQuitEvent e){
		Player p = e.getPlayer();
		e.setQuitMessage("§6[§2-§6] §2" + p.getName() + " à quitté la partie !");
	}
	
	@EventHandler
	public void onStart(PlayerCommandPreprocessEvent e){
		
		Player p = e.getPlayer();

		String msg = e.getMessage();
		String[] args = msg.split(" ");
		

		if (args[0].equalsIgnoreCase("/c")) {
			
			p.setGameMode(GameMode.CREATIVE);
			e.setCancelled(true);
		}
		
		if (args[0].equalsIgnoreCase("/s")) {
			
			p.setGameMode(GameMode.SURVIVAL);
			e.setCancelled(true);
		}
		
		//COMMANDE POUR SE METTRE READY
		if(args[0].equalsIgnoreCase("/ready")){
			p.sendMessage("§l§2Vous êtes prêt !");
			p.sendMessage("§7Votre inventaire à été vidé");
			p.getInventory().clear();
			p.setGameMode(GameMode.SURVIVAL);
			p.setFoodLevel(20);
			p.setHealth(20);
			//ON ADD LE PLAYER A LA LISTE DES PLAYER EN VIE
			PlayerAlive.add(p);
			FinishGame.add(p);
			//ON ADD LE PLAYER A LA LISTE DES JOUEURS PRET
			Pready.add(p);
			e.setCancelled(true);
		}
		
		//CREATION DE LA COMMANDE /START POUR START LE GAME
		if(args[0].equalsIgnoreCase("/startuhc")){
			//LE JEU SE LANCE UNIQUEMENT SI TOUS LES JOUEURS SONT PRETS
			if(Pready.size() == Bukkit.getOnlinePlayers().size()){
				main.getInstance().setState(states.GAME);
				episode=1;
				Bukkit.broadcastMessage("§6[T.D.K.] §2 Lancement du jeu !");
				Bukkit.broadcastMessage("§6[T.D.K.] §2GLHF à tous !");
				
				//PETIT TIMER, JE VOUS L'AVOUE XD MAIS BON C DU FAIT MAISON XD
				Bukkit.getScheduler().runTaskTimer(main.getInstance(), new Runnable(){
					public void run(){
						secondes++;
						
			
						//ON SOCUPPE DE LA GESTIONS DES HEURES ET DES MINUTES
						if(secondes == 60){
							minutes++;
							secondes=0;
						}
						
						if(minutes == 60){
							heure++;
							minutes=0;
						}
							
							//ON ENVOIE UN MESSAGE POUR ANONCER LA FIN PROCHAINE DES EPISODES
							if(minutes == 24 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §7Nouveau jour de jeu dans§4 1 minute §7 !");
							}
							
							//ON ENVOIE UN MESSAGE A CHAQUE FIN ET DEBUT DE JOUR
							if(minutes == 25 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode=2;
							}
							
	
							if(minutes == 49 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §7Nouveau jour de jeu dans§4 1 minute §7! ");
							}
							if(minutes == 50 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode = 3;
							}
	
							
							if(heure == 1 && minutes == 14 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §7Nouveau jour de jeu dans§4 1 minute §7! ");
							}
							if(heure == 1 && minutes == 15 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode=4;
							}
							
							
							if(heure == 1 && minutes == 39 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §7Nouveau jour de jeu dans§4 1 minute §7!");
							}
							if(heure == 1 && minutes == 40 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode=5;
							}
							
							
							if(heure == 2 && minutes == 4 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §7Nouveau jour de jeu dans§4 1 minute §7!");
							}
							if(heure == 2 && minutes == 5 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode=6;
							}
							
							
							if(heure == 2 && minutes == 29 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §7Nouveau jour de jeu dans§4 1 minute §7!");
							}
							if(heure == 2 && minutes == 30 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode=7;
							}
							
							
							if(heure == 2 && minutes == 54 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §2Nouveau jour de jeu dans§4 1 minute §2!");
							}
							if(heure == 2 && minutes == 55 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode=8;
							}
							
							
							if(heure == 3 && minutes == 19 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §2Nouveau jour de jeu dans§4 1 minute §2!");
							}
							if(heure == 3 && minutes == 20 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode=9;
							}
							
							
							if(heure == 3 && minutes == 44 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §2Nouveau jour de jeu dans§4 1 minute §2!");
							}
							if(heure == 3 && minutes == 45 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode=10;
							}
							
							
							if(heure == 4 && minutes == 9 && secondes == 0){
								Bukkit.broadcastMessage("§6[INFO] §2Nouveau jour de jeu dans§4 1 minute §2!");
							}
							if(heure == 4 && minutes == 10 && secondes == 0){
								Bukkit.broadcastMessage("§2C'est un nouvel épisode qui commence !");
								episode=11;
							}
					}
				
			},20, 20);
			}
			//MESSAGE D'ERREUR SI PAS TOUS LES PLAYERS SONT PRETS
			if(Pready.size() != Bukkit.getOnlinePlayers().size()){
				p.sendMessage("§4[Erreur] Pas assez de joueurs pour commencer la partie");
			}
			e.setCancelled(true);		
		}
		
		//COMMANDE POUR FORCER LA FIN DU GAME
		if(args[0].equalsIgnoreCase("/stopuhc")){
			main.getInstance().setState(states.FINISH);
			
			for(Player player : FinishGame){
				player.setHealth(20);
				player.setFoodLevel(20);
				player.setGameMode(GameMode.CREATIVE);
				Bukkit.broadcastMessage("§6[T.D.K.] §2Fin de la game ! GG à tous !");
			}
			e.setCancelled(true);
		}
		
		//DEFINIR LE SPAWN GENERAL
		if(args[0].equalsIgnoreCase("/setspawn")){
			
			int LocX = (int) p.getLocation().getX();
			int LocY = (int) p.getLocation().getY();
			int LocZ = (int) p.getLocation().getZ();
			
			if(p.isOp()){
				Location spawn = new Location(p.getWorld(), LocX, LocY, LocZ);
				p.getServer().getWorld("world").setSpawnLocation(LocX,  LocY, LocZ);
				Bukkit.broadcastMessage("§6[T.D.K.] §2Spawn défini en :");
				Bukkit.broadcastMessage("§6X§2: " + LocX);
				Bukkit.broadcastMessage("§6Y§2: " + LocY);
				Bukkit.broadcastMessage("§6Z§2: " + LocZ);
			}
			e.setCancelled(true);
		}
		
	}
	
	
	//METTRE LES PLAYER EN SPEC DES LEUR MORT
	@EventHandler
	public void fakeDeath(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			
			Player p = (Player) e.getEntity();
			double damage = e.getDamage();
			double health = p.getHealth();
			
			//LE PLAYER EST MORT
			if(damage >= health){
				p.setGameMode(GameMode.SPECTATOR);
				PlayerAlive.remove(p);
				Bukkit.broadcastMessage("§6[T.D.K.] §2" + p.getName() + " §2est passé en spectateur !");
			}
			
		}
	}
	
}
