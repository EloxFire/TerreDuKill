package fr.eloxfire.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.eloxfire.game.setGame;

public class DeathEvent implements Listener {

	public static List<Player> MORT = new ArrayList<>();

	@EventHandler
	public void onMortDeMerde(PlayerDeathEvent e) {
		String message = e.getDeathMessage();
		Player player = e.getEntity().getPlayer();

		if (Timers.GENE < 60) {
			e.setDeathMessage(player.getName() + "§2 est surement mort à cause d'un bug !");
			player.teleport(player.getWorld().getSpawnLocation());
		} else {
			player.teleport(player.getWorld().getSpawnLocation());
			player.setGameMode(GameMode.SPECTATOR);
			setGame.ready.remove(player);
			MORT.add(player);
		}

		main.getInstance().title.sendTitle(player, "§6Tu passe en Spectateur", "", 35);
		Bukkit.broadcastMessage("§6§l[T.D.K] §2 " + player.getName() + " §2est passé en spectateur !");

		if (player.getKiller() instanceof Player) {
			player.getKiller().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 3, 1));
		}

		for (Player players : Bukkit.getOnlinePlayers()) {
			players.getWorld().playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 8, 8);
		}

		if (message.contains("drowned")) {
			e.setDeathMessage(player.getName() + "§7 a été frappé par la mort ! §r(noyade)");
		}
		if (message.contains("ground") || message.contains("fall") || message.contains("fell")) {
			e.setDeathMessage(player.getName() + "§7 a été frappé par la mort ! §r(chute)");
		}
		if (message.contains("lava")) {
			e.setDeathMessage(player.getName() + "§7 a été frappé par la mort ! §r(lave)");
		}
		if (message.contains("was slain by")) {
			e.setDeathMessage(player.getName() + " §7a été frappé par la mort ! §r(tué par : "
					+ e.getEntity().getKiller().getName() + ")");
		}
		if (message.contains("blown up")) {
			e.setDeathMessage(player.getName() + " §7 a été frappé par la mort ! §r(tué par un creeper");
		}
	}

}
