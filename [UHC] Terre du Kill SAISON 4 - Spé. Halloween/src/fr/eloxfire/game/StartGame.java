package fr.eloxfire.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.eloxfire.events.TimerBORDER;
import fr.eloxfire.events.TimerPVP;

public class StartGame implements Listener{
	
	public static List<Player> Ready = new ArrayList<>();
	public static int taskTitle;
	public static int TITLE = 0;
	public static int LocX;
	public static int LocY;
	public static int LocZ;
	
	@EventHandler
	public void onCommands(PlayerCommandPreprocessEvent e){
		Player player = e.getPlayer();
		String msg = e.getMessage();
		String args[] = msg.split(" ");
		
		if (args[0].equalsIgnoreCase("/c")) {
			if(player.isOp()){
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage("§2Vous êtes passé en §6CREATIVE");
			}else{
				player.sendMessage("§4Vous n'avez pas la permission de faire cette commande !");
			}
			e.setCancelled(true);
		}
		
		if (args[0].equalsIgnoreCase("/s")) {
			if(player.isOp()){
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage("§2Vous êtes passé en §6SURVIVAL");
			}else{
				player.sendMessage("§4Vous n'avez pas la permission de faire cette commande !");
			}
			e.setCancelled(true);
		}
		
		if (args[0].equalsIgnoreCase("/sp")) {
			if(player.isOp()){
				player.setGameMode(GameMode.SPECTATOR);
				player.sendMessage("§2Vous êtes passé en §6SPECTATOR");
			}else{
				player.sendMessage("§4Vous n'avez pas la permission de faire cette commande !");
			}
			e.setCancelled(true);
		}
		
		if (args[0].equalsIgnoreCase("/a")) {
			if(player.isOp()){
				player.setGameMode(GameMode.ADVENTURE);
				player.sendMessage("§2Vous êtes passé en §6ADVENTURE");
			}else{
				player.sendMessage("§4Vous n'avez pas la permission de faire cette commande !");
			}
			e.setCancelled(true);
		}
		
		if(args[0].equalsIgnoreCase("/lantern")){
			Random r = new Random();
			
			for(int i = 0; i<1200; i++){
				 int x = player.getLocation().getBlockX() + r.nextInt(3000);
				 int z = player.getLocation().getBlockZ() + r.nextInt(3000);
				 int y = player.getWorld().getHighestBlockYAt(x, z);
				
				Location lantern = new Location(player.getWorld(), x, y, z);
				lantern.getBlock().setType(Material.JACK_O_LANTERN);
				
			}
			player.sendMessage("§21000 Jack O' Lantern ont été placées sur la map !");
			
			e.setCancelled(true);
		}
		
		
		if(args[0].equalsIgnoreCase("/ready")){
			if(main.getInstance().isState(states.PREGAME)){
				if(Ready.contains(player)){
					player.sendMessage("§6[T.D.K] §2Tu es déjà prêt !");
				}
				if(!Ready.contains(player)){
					Ready.add(player);
					player.getInventory().clear();
					player.setHealth(20);
					player.setFoodLevel(20);
					player.sendMessage("§6[T.D.K] §2Tu es prêt !");
					Bukkit.broadcastMessage("§2" + Ready.size() + "§6/§2" + Bukkit.getOnlinePlayers().size() + " §2joueurs ready !");
				}
			}else{
				player.sendMessage("§6[T.D.K] §2La partie à déjà commençée !");
			}
			e.setCancelled(true);
		}
		
		if(args[0].equalsIgnoreCase("/setspawn")){
			if(main.getInstance().isState(states.PREGAME)){
				if(player.isOp()){
					 LocX = (int) player.getLocation().getX();
					 LocY = (int) player.getLocation().getY();
					 LocZ = (int) player.getLocation().getZ();
				
					@SuppressWarnings("unused")
					Location spawn = new Location(player.getWorld(), LocX, LocY, LocZ);
					player.getServer().getWorld("world").setSpawnLocation(LocX,  LocY, LocZ);
					//ON CRIS HAUT ET FORT LES NOUVELLES COORD DU SplayerAWN
					Bukkit.broadcastMessage("§6[T.D.K.] §2Spawn défini en :");
					Bukkit.broadcastMessage("§6X§2: " + LocX);
					Bukkit.broadcastMessage("§6Y§2: " + LocY);
					Bukkit.broadcastMessage("§6Z§2: " + LocZ);
				}
				e.setCancelled(true);
			}
			
			if(main.getInstance().isState(states.GAME) || main.getInstance().isState(states.PVP) || main.getInstance().isState(states.BORDER)){
				player.sendMessage("§6[T.D.K] §4Vous ne pouvez pas faire ça en plein jeu !");
			}
		}
			
			if(args[0].equalsIgnoreCase("/setwb")){
		            if(args.length == 1 && player.isOp()){
		                player.sendMessage("§7Tape : §6/setwb 10");
		                player.sendMessage("§7   ou : §6/setwb 20");
		                player.sendMessage("§7   ou : §6/setwb 50");
		                player.sendMessage("§7   ou : §6/setwb 100");
		                player.sendMessage("§7   ou : §6/setwb 500");
		                player.sendMessage("§7   ou : §6/setwb 1000");
		                player.sendMessage("§7   ou : §6/setwb 2000");
		                player.sendMessage("§7   ou : §6/setwb 3000");
		                player.sendMessage("§7   ou : §6/setwb 4000");
		            }
		            if(args.length == 2 && player.isOp()){
		 
		                int wbx = (int) player.getLocation().getX();
		                int wbz = (int) player.getLocation().getZ();
		 
		                
		                if(args[1].equalsIgnoreCase("10")){
		                    World world = Bukkit.getWorld("world");
		                    WorldBorder wb = world.getWorldBorder();
		                    wb.setCenter(wbx, wbz);
		                    wb.setSize(10);
		                    player.sendMessage("§2Vous avez set la bordure de map à 10x10 blocks.");
		                    Bukkit.broadcastMessage("§6Centre §2de la bordure §6défini en §2:");
		                    Bukkit.broadcastMessage("§6X §2: " + wbx);
		                    Bukkit.broadcastMessage("§6Z §2: " + wbz);
		                }
		                if(args[1].equalsIgnoreCase("20")){
		                    World world = Bukkit.getWorld("world");
		                    WorldBorder wb = world.getWorldBorder();
		                    wb.setCenter(wbx, wbz);
		                    wb.setSize(20);
		                    player.sendMessage("§2Vous avez set la bordure de map à 20x20 blocks.");
		                    Bukkit.broadcastMessage("§6Centre §2de la bordure §6défini en §2:");
		                    Bukkit.broadcastMessage("§6X §2: " + wbx);
		                    Bukkit.broadcastMessage("§6Z §2: " + wbz);
		                }
		                if(args[1].equalsIgnoreCase("50")){
		                    World world = Bukkit.getWorld("world");
		                    WorldBorder wb = world.getWorldBorder();
		                    wb.setCenter(wbx, wbz);
		                    wb.setSize(50);
		                    player.sendMessage("§2Vous avez set la bordure de map à 50x50 blocks.");
		                    Bukkit.broadcastMessage("§6Centre §2de la bordure §6défini en §2:");
		                    Bukkit.broadcastMessage("§6X §2: " + wbx);
		                    Bukkit.broadcastMessage("§6Z §2: " + wbz);
		                }
		                if(args[1].equalsIgnoreCase("100")){
		                    World world = Bukkit.getWorld("world");
		                    WorldBorder wb = world.getWorldBorder();
		                    wb.setCenter(wbx, wbz);
		                    wb.setSize(100);
		                    player.sendMessage("§2Vous avez set la bordure de map à 100x100 blocks.");
		                    Bukkit.broadcastMessage("§6Centre §2de la bordure §6défini en §2:");
		                    Bukkit.broadcastMessage("§6X §2: " + wbx);
		                    Bukkit.broadcastMessage("§6Z §2: " + wbz);
		                }
		                if(args[1].equalsIgnoreCase("500")){
		                    World world = Bukkit.getWorld("world");
		                    WorldBorder wb = world.getWorldBorder();
		                    wb.setCenter(wbx, wbz);
		                    wb.setSize(500);
		                    player.sendMessage("§2Vous avez set la bordure de map à 500x500 blocks.");
		                    Bukkit.broadcastMessage("§6Centre §2de la bordure §6défini en §2:");
		                    Bukkit.broadcastMessage("§6X §2: " + wbx);
		                    Bukkit.broadcastMessage("§6Z §2: " + wbz);
		                }
		                if(args[1].equalsIgnoreCase("1000")){
		                    World world = Bukkit.getWorld("world");
		                    WorldBorder wb = world.getWorldBorder();
		                    wb.setCenter(wbx, wbz);
		                    wb.setSize(1000);
		                    player.sendMessage("§2Vous avez set la bordure de map à 1000x1000 blocks.");
		                    Bukkit.broadcastMessage("§6Centre §2de la bordure §6défini en §2:");
		                    Bukkit.broadcastMessage("§6X §2: " + wbx);
		                    Bukkit.broadcastMessage("§6Z §2: " + wbz);
		                }
		                if(args[1].equalsIgnoreCase("2000")){
		                    World world = Bukkit.getWorld("world");
		                    WorldBorder wb = world.getWorldBorder();
		                    wb.setCenter(wbx, wbz);
		                    wb.setSize(2000);
		                    player.sendMessage("§2Vous avez set la bordure de map à 2000x2000 blocks.");
		                    Bukkit.broadcastMessage("§6Centre §2de la bordure §6défini en §2:");
		                    Bukkit.broadcastMessage("§6X §2: " + wbx);
		                    Bukkit.broadcastMessage("§6Z §2: " + wbz);
		                }
		                if(args[1].equalsIgnoreCase("3000")){
		                    World world = Bukkit.getWorld("world");
		                    WorldBorder wb = world.getWorldBorder();
		                    wb.setCenter(wbx, wbz);
		                    wb.setSize(3000);
		                    player.sendMessage("§2Vous avez set la bordure de map à 3000x3000 blocks.");
		                    Bukkit.broadcastMessage("§6Centre §2de la bordure §6défini en §2:");
		                    Bukkit.broadcastMessage("§6X §2: " + wbx);
		                    Bukkit.broadcastMessage("§6Z §2: " + wbz);
		                }
		                if(args[1].equalsIgnoreCase("4000")){
		                    World world = Bukkit.getWorld("world");
		                    WorldBorder wb = world.getWorldBorder();
		                    wb.setCenter(wbx, wbz);
		                    wb.setSize(4000);
		                    player.sendMessage("§2Vous avez set la bordure de map à 4000x4000 blocks.");
		                    Bukkit.broadcastMessage("§6Centre §2de la bordure §6défini en §2:");
		                    Bukkit.broadcastMessage("§6X §2: " + wbx);
		                    Bukkit.broadcastMessage("§6Z §2: " + wbz);
		                }
		            }
				e.setCancelled(true);
			}
			
		if(args[0].equalsIgnoreCase("/reduceborder")){
			if(player.isOp() && !main.getInstance().isState(states.BORDER)){
				//TITLE = 2380;
				TimerBORDER.BORDER = 20;
				main.getInstance().setState(states.BORDER);
				World world = Bukkit.getWorld("world");
			    WorldBorder wb = world.getWorldBorder();
                wb.setSize(2000);
                wb.setSize(50, 2400);
				for(Player joueur : Bukkit.getOnlinePlayers()){
					main.getInstance().title.sendTitle(joueur, "§4Réduction des bordures dans 20s", "§4Rapprochez-vous du spawn !", 35);
				}
			
			}
			
			e.setCancelled(true);
		}
		
		if(args[0].equalsIgnoreCase("/pvp")){
			if(player.isOp() && !main.getInstance().isState(states.PVP) || !main.getInstance().isState(states.BORDER)){
				main.getInstance().setState(states.PVP);
				TimerPVP.PVP = 10;
			}
		}
		
		
		if(args[0].equalsIgnoreCase("/starttdk")){
			if(Ready.size() == Bukkit.getOnlinePlayers().size()){
				main.getInstance().setState(states.GAME);
				
				main.teleportTeams();
				
				TimerBORDER.TimerBorder();
				TimerPVP.TimerPvp();
				
			for(Player ready : Ready){
					ready.setHealth(20);
					ready.setFoodLevel(20);
					ready.setGameMode(GameMode.SURVIVAL);
					
					ready.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					ready.removePotionEffect(PotionEffectType.SATURATION);
						
					taskTitle = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable(){
							
						@Override
						public void run() {
							TITLE++;
							
							if(TITLE == 1){
								main.getInstance().title.sendTitle(ready, "§2Bonne chance !", "§2Puisse le SEL vous être favorable !", 40);
							}
							if(TITLE == 10){
								main.getInstance().title.sendTitle(ready, "§420min", "§4Activation du PVP", 35);
							}
							if(TITLE == 15){
								main.getInstance().title.sendTitle(ready, "§440min", "§4Reduction des bordures", 35);
							}
							
							/*if(TITLE == 2400){
								main.getInstance().setState(states.BORDER);
								World world = Bukkit.getWorld("world");
							    WorldBorder wb = world.getWorldBorder();
			                    wb.setSize(2000);
			                    wb.setSize(50, 2400);
			                    
							}*/
						}
						
					},20,20);
					
					ready.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60, 20));
					
					ItemStack cotelette = new ItemStack(Material.COOKED_MUTTON, 12);
					ready.getInventory().addItem(cotelette);
					ItemStack tarte = new ItemStack(Material.PUMPKIN_PIE, 1);
					ready.getInventory().addItem(tarte);
				}
				
				Bukkit.broadcastMessage("§6[T.D.K] §2Vous possédez §41 minute §2d'§4invincibilité §2!");
				
			}
			if(Ready.size() != Bukkit.getOnlinePlayers().size()){
				player.sendMessage("§6[T.D.K] §4Erreur, pas tous les joueurs sont ready !");
			}
		}
		
		e.setCancelled(true);
	
	}
	

}
