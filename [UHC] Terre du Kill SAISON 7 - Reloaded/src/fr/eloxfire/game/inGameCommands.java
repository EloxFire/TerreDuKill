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

public class inGameCommands implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			
			if(cmd.getName().equalsIgnoreCase("info")) {
				if(args.length == 0) {
					player.sendMessage("§6[TDK] §2Utilise §6/info <message> §2pour envoyer un message à tout les joueurs du serveur.");
				}else if(args.length >= 1) {
					StringBuilder bc = new StringBuilder();
					for (String part : args) {
						bc.append(part + " ");
					}
					Bukkit.broadcastMessage("§6[TDK INFO] §c" + bc.toString());
				}
			}
			
			if(cmd.getName().equalsIgnoreCase("starttdk")) {
				if(main.getInstance().isState(states.PVP) || main.getInstance().isState(states.BORDER) || main.getInstance().isState(states.FINISH)) {
					player.sendMessage("§6[TDK] §4Vous avez déjà lançé le jeu !");
				}else if(main.getInstance().isState(states.WAIT) && PlayerEvents.playerReady.size() == Bukkit.getOnlinePlayers().size()) {
					main.getInstance().setState(states.GAME);
					
					for(Player players : Bukkit.getOnlinePlayers()) {
						players.setHealth(20);
						players.setFoodLevel(20);
						players.setExp(0);
						players.getInventory().clear();
						players.setGameMode(GameMode.SURVIVAL);
						players.removePotionEffect(PotionEffectType.SATURATION);
						players.updateInventory();
						
						Timers.chrono();
						Timers.pvp();
						Timers.border();
						
						Teams.teleportTeams();
					}
				}else if(main.getInstance().isState(states.WAIT) && PlayerEvents.playerReady.size() != Bukkit.getOnlinePlayers().size()) {
					Bukkit.broadcastMessage("§6[TDK] §4Erreur ! Pas tous les joueurs sont prêts !");
				}
			}
			
			
			// COMMANDE REDUCEBORDER
						if (cmd.getName().equalsIgnoreCase("reduceborder")) {
							if (main.getInstance().isState(states.BORDER)) {
								player.sendMessage("§6§l[T.D.K] §4La réduction des bordures est déjà effective !");
							} else {
								player.sendMessage("§6§l[T.D.K] §4Vous venez d'accelerer le jeu jusqu'à la phase de réduction de bordures !");
								Timers.border = 10;
							}
							return true;
						}
		}
		return false;
	}

}
