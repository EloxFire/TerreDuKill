package fr.eloxfire.game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class gameCommands implements CommandExecutor {

	private static int title = 0;

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			// COMMAND ALERT
			if (cmd.getName().equalsIgnoreCase("alert")) {
				if (args.length == 0) {
					player.sendMessage("§2Fait : §c/alert <message>");
				}
				if (args.length >= 1) {
					StringBuilder bc = new StringBuilder();
					for (String part : args) {
						bc.append(part + " ");
					}
					Bukkit.broadcastMessage("§6§l[Info] §c" + bc.toString());
				}
				return true;
			}

			// COMMANDE PVP
			if (cmd.getName().equalsIgnoreCase("pvp")) {
				if (main.getInstance().isState(states.PVP) || main.getInstance().isState(states.BORDER)) {
					player.sendMessage("§6§l[T.D.K] Le jeu est déjà en phase de PVP !");
				} else {
					main.getInstance().setState(states.PVP);
					player.sendMessage("§6§l[T.D.k] §4Vous venez d'accelerer le jeu jusqu'à la phase de PVP !");
					Timers.PVP = 10;
				}
				return true;
			}

			// COMMANDE REDUCEBORDER
			if (cmd.getName().equalsIgnoreCase("reduceborder")) {
				if (main.getInstance().isState(states.BORDER)) {
					player.sendMessage("§6§l[T.D.K] §4La réduction des bordures est déjà effective !");
				} else {
					player.sendMessage(
							"§6§l[T.D.K] §4Vous venez d'accelerer le jeu jusqu'à la phase de réduction de bordures !");
					Timers.BORDER = 10;
					World world = Bukkit.getWorld("world");
					WorldBorder wb = world.getWorldBorder();
					wb.setSize(50, 2400);
				}
				return true;
			}

			// COMMANDE HEALALL
			if (cmd.getName().equalsIgnoreCase("healall")) {
				if (main.getInstance().isState(states.PVP) || main.getInstance().isState(states.BORDER)) {
					player.sendMessage("§6§l[T.D.K] §4Phase de PVP en cours.. Vous ne pouvez pas heal les joueurs !");
				}
				if (main.getInstance().isState(states.GAME) || main.getInstance().isState(states.WAIT)) {
					player.sendMessage("§6§l[T.D.k] §2Vous venez de heal tous les joueurs !");
					for (Player pls : Bukkit.getOnlinePlayers()) {
						pls.setHealth(20);
						pls.setFoodLevel(20);
						pls.sendMessage("§6§l[Info] §2Vous venez d'être heal !");
					}
				}
				return true;
			}

			// COMMANDE STARTTDK
			if (cmd.getName().equalsIgnoreCase("starttdk")) {
				if (main.getInstance().isState(states.GAME) || main.getInstance().isState(states.PVP)
						|| main.getInstance().isState(states.BORDER) || main.getInstance().isState(states.FINISH)) {
					player.sendMessage("§6[T.D.K] §4Inutile ! Vous avez déjà lancé le jeu !");
				}
				if ((main.getInstance().isState(states.WAIT))
						&& setGame.ready.size() == Bukkit.getOnlinePlayers().size()) {
					main.getInstance().setState(states.GAME);
					// ON HEAL LES PLAYERS ET ON LES TELEPORTE
					for (Player players : Bukkit.getOnlinePlayers()) {

						players.getInventory().clear();
						players.setGameMode(GameMode.SURVIVAL); // ON PASSE TOUS LES PLAYERS EN SURVIE AU CAS OU
						players.setFoodLevel(20); // ON MET LE NIVEAU DE BOUFFE ET
						players.setHealth(20); // DE VIE DES PLAYERS AU MAX AU CAS OU
						players.removePotionEffect(PotionEffectType.SATURATION); // ON ENLEVE LES EFFECTS DE POTTIONS // AUX PLAYERS
						players.updateInventory();

						Timers.TBORDER(); // ON LANCE
						Timers.TPVP(); // TOUS LES
						Timers.TGENE(); // TIMERS

						// ON TP LES TEAMS
						Teams.teleportTeams();

						// TIMER QUI GERE LE MOMENTS D'AFFICHAGE DES TITLES
						Bukkit.getScheduler().scheduleAsyncRepeatingTask(main.getInstance(), new Runnable() {

							@Override
							public void run() {
								title++;

								if (title == 1) {
									main.getInstance().title.sendTitle(players, "§2Début du jeu !",
											"§2Puisse le Sel vous être favorable.", 35);
								}
								if (title == 5) {
									main.getInstance().title.sendTitle(players, "§4PVP", "§420mins", 35);
								}
								if (title == 8) {
									main.getInstance().title.sendTitle(players, "§4Bordures", "§440mins", 35);
								}
								if (title == 11) {
									main.getInstance().title.sendTitle(players, "§6Bonne année !",
											"§2Et bonne chance !", 35);
								}
								
								if(title == 60) {
									for(Player players : Bukkit.getOnlinePlayers()) {
										players.sendMessage("§6§l[T.D.K]§2Vous n'êtes plus invincible !");
										players.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
									}
								}
							}

						}, 20, 20);
					}
				} else if (main.getInstance().isState(states.WAIT)
						&& setGame.ready.size() != Bukkit.getOnlinePlayers().size()) {
					player.sendMessage("§6§l[T.D.K] §4Erreur, pas tous les joueurs sont prêts !");
				}
			}
		}
		return false;
	}

}
