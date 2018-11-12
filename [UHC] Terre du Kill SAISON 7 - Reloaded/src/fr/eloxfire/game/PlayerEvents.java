package fr.eloxfire.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerEvents implements Listener, CommandExecutor {

	public static List<Player> playerReady = new ArrayList<>();
	public static List<Player> playerDead = new ArrayList<>();

	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		player.setScoreboard(Scoreboards.s);

		if (main.getInstance().isState(states.GAME) || main.getInstance().isState(states.BORDER)
				|| main.getInstance().isState(states.PVP)) {
			e.setJoinMessage("§6[§2+§6] §f" + player.getName() + "§2 vient de reprendre sa partie.");
		} else if (main.getInstance().isState(states.WAIT) || main.getInstance().isState(states.FINISH)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 25), true);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 99999, 25), true);
			e.setJoinMessage("§6[§2+§6] §f" + player.getName() + "§2 vient de rejoindre la partie.");

			main.getInstance().title.sendTitle(player, "§6[TDK] SAISON VI", "§6-RELOADED-", 40);
		}

	}

	@EventHandler
	public void playerDeath(PlayerDeathEvent e) {
		Player player = e.getEntity().getPlayer();
		String message = e.getDeathMessage();

		if (playerReady.contains(player)) {
			playerReady.remove(player);
			playerDead.add(player);
			player.sendMessage("§6[TDK] §4Tu est mort ! Tu passe en spectateur.");
		} else if (!playerReady.contains(player)) {
			player.sendMessage("§6[TDK] §2Tu est -selon les dires- déjà mort !");
		}

		if (message.contains("was killed by")) {
			main.getInstance().title.sendTitle(player, "§4Tu a été tué !", "§4GG !", 35);
		} else if (message.contains("drowned")) {
			main.getInstance().title.sendTitle(player, "§4Tu t'est noyé !", "§4GG !", 35);
		} else if (message.contains("blown up")) {
			main.getInstance().title.sendTitle(player, "§4Tu a explosé !", "§4GG !", 35);
		}

	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("operator") || main.getInstance().isState(states.WAIT)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("operator") || main.getInstance().isState(states.WAIT)) {
			e.setCancelled(true);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("ready")) {
				if (playerReady.contains(player)) {
					player.sendMessage("§6[TDK] §2Tu est déjà prêt.");
				} else if (!playerReady.contains(player)) {
					player.sendMessage("§6[TDK] §2Tu est maintenant prêt.");
					playerReady.add(player);
				}
				if (playerDead.contains(player)) {
					player.sendMessage("§6[TDK] §4Je t'ai vu !");
					main.getInstance().title.sendTitle(player, "§4N'essaye pas de casser", "§4LE JEU !!!", 35);
				}
			}

			if (cmd.getName().equalsIgnoreCase("unready")) {
				if (!playerReady.contains(player)) {
					player.sendMessage("§6[TDK] §2Avant de pouvoir ne plus être prêt il faut l'être en premier.");
				} else if (playerReady.contains(player)) {
					playerReady.remove(player);
					player.sendMessage("§6[TDK] §4Tu n'est plus prêt !");
				}
			}

		}
		return false;
	}

}
