package fr.eloxfire.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class setGame implements CommandExecutor {

	public static int LocX;
	public static int LocY;
	public static int LocZ;

	public static List<Player> ready = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			// COMMAND SETSPAWN
			if (cmd.getName().equalsIgnoreCase("setspawn")) {
				if (main.getInstance().isState(states.WAIT)) {
					player.sendMessage("§6§l[T.D.K] §2Tu viens de définir le spawn du jeu !");
					LocX = (int) player.getLocation().getX();
					LocY = (int) player.getLocation().getY();
					LocZ = (int) player.getLocation().getZ();
					player.getServer().getWorld("world").setSpawnLocation(LocX, LocY, LocZ);
				} else {
					player.sendMessage("§6§l[T.D.K] §4Vous ne pouvez pas changer le spawn en plein jeu !");
				}

				return true;
			}
			// COMMANDE SET WORLDBORDER
			if (cmd.getName().equalsIgnoreCase("wb")) {
				if (args.length == 0) {
					player.sendMessage("§2Fait : §c/wb <taille>");
				}
				if (args.length == 1) {
					if (main.getInstance().isState(states.WAIT)) {
						player.sendMessage("§6§l[T.D.K] §2Tu viens de définir la taille de la bordure à : 2000x2000");

						int wbx = (int) player.getLocation().getX();
						int wbz = (int) player.getLocation().getZ();
						World world = Bukkit.getWorld("world");
						WorldBorder wb = world.getWorldBorder();
						wb.setCenter(wbx, wbz);
						wb.setSize(2000);
					} else {
						player.sendMessage("§6§l[T.D.K] §4Vous ne pouvez plus definir la barrière de base");
					}
				}
				return true;
			}
			// COMMANDE READY
			if (cmd.getName().equalsIgnoreCase("ready")) {
				if (main.getInstance().isState(states.GAME) || main.getInstance().isState(states.PVP)
						|| main.getInstance().isState(states.BORDER) || main.getInstance().isState(states.FINISH)) {
					player.sendMessage("§6[T.D.K] §4Vous êtes prêt puisque le jeu à démaré :p");
				}
				if (ready.contains(player)) {
					player.sendMessage("§6§l[T.D.K] §2Vous êtes déjà prêt !");
				}
				if (!ready.contains(player)) {
					player.sendMessage("§6§l[T.D.K] §2Vous êtes prêt !");
					ready.add(player);
					player.setHealth(20);
					player.setFoodLevel(20);
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 9999), true);
				}
				return true;
			}
			// COMMANDE UNREADY
			if (cmd.getName().equalsIgnoreCase("unready")) {
				if (main.getInstance().isState(states.GAME) || main.getInstance().isState(states.PVP)
						|| main.getInstance().isState(states.BORDER) || main.getInstance().isState(states.FINISH)) {
					player.sendMessage("§6[T.D.K] §4Vous êtes prêt puisque le jeu à démaré :p");
				}
				if (!ready.contains(player)) {
					player.sendMessage("§6§l[T.D.K] §2Vous n'avez jamais été ready :p");
				}
				if (ready.contains(player)) {
					ready.remove(player);
					player.sendMessage("§6§l[T.D.K] §2Vous n'êtes plus prêt.. Pourquoi ça ?");
				}
			}
		}

		return false;
	}
}
