package fr.eloxfire.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardRefresh extends BukkitRunnable {

	@SuppressWarnings("unused")
	@Override
	public void run() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			Scoreboards.refreshScoreboard();
		}

	}
}
