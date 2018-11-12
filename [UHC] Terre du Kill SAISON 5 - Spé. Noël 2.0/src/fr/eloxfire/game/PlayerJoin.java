package fr.eloxfire.game;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.eloxfire.game.Scoreboards;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		if (main.getInstance().isState(states.GAME) || main.getInstance().isState(states.PVP)
				|| main.getInstance().isState(states.BORDER) && setGame.ready.contains(player)) {
			e.setJoinMessage("§6§l[T.D.K] §2" + player.getName()
					+ "§2 veut continuer à se battre pour avoir un cadeau de Noël !");
			main.getInstance().title.sendTitle(player, "§6Bonne continuation !", "", 35);
		} else if (main.getInstance().isState(states.GAME) || main.getInstance().isState(states.PVP)
				|| main.getInstance().isState(states.BORDER) && !setGame.ready.contains(player)) {
			e.setJoinMessage("§6§l[T.D.K] §2" + player.getName() + "§2 veux voir qui va gagner cette saison V !");
			main.getInstance().title.sendTitle(player, "§6Tu rejoinds en SPECTATEUR !", "", 35);
			player.setGameMode(GameMode.SPECTATOR);
		} else if (main.getInstance().isState(states.WAIT)) {
			e.setJoinMessage(
					"§6§l[T.D.K] §2" + player.getName() + "§2 veut se battre pour gagner des cadeaux de Noël !");
			main.getInstance().title.sendTitle(player, "§6Bienvenue pour ce TDK", "§6Sp§4éc§2ia§7l §9No§5ël §6!", 40);
			player.setGameMode(GameMode.ADVENTURE);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 99999, 25), true);
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 25), true);
		}

		player.setScoreboard(Scoreboards.s);
	}

}
