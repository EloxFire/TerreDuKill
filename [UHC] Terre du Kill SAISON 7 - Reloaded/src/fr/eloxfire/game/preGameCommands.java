package fr.eloxfire.game;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class preGameCommands implements CommandExecutor {

	public static int locX, locY, locZ;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			// COMMANDE SETSPAWN
			if (cmd.getName().equalsIgnoreCase("setspawn")) {
				if (main.getInstance().isState(states.WAIT)) {
					locX = player.getLocation().getBlockX();
					locY = player.getLocation().getBlockY();
					locZ = player.getLocation().getBlockZ();

					player.getWorld().setSpawnLocation(locX, locY, locZ);
					player.sendMessage("§6[TDK] §2Tu viens de définir le spawn du jeu.");

				} else {
					player.sendMessage("§6[TDK] §4Tu ne peux pas définir le spawn de jeu maintenant.");
				}
			}
			
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
		}
		return false;
	}

}
