package fr.eloxfire.game;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {

	public Titles title = new Titles();
	
	public states current;

	public static main instance;

	public static main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage("§2Terre du Kill §6- §2SAISON 6 §6- §2Reloaded");
		getServer().getConsoleSender().sendMessage("§c-------------Plugin ON-------------");

		instance = this;
		current = states.WAIT;

		// CLASSES DES COMMANDES
		getCommand("setSpawn").setExecutor(new preGameCommands());
		getCommand("info").setExecutor(new inGameCommands());
		getCommand("ready").setExecutor(new PlayerEvents());
		getCommand("unready").setExecutor(new PlayerEvents());
		getCommand("starttdk").setExecutor(new inGameCommands());
		getCommand("wb").setExecutor(new preGameCommands());
		getCommand("reduceborder").setExecutor(new inGameCommands());
		// COMMANDES DE TEAMS
		getCommand("rouge").setExecutor(new Teams());
		getCommand("bleue").setExecutor(new Teams());
		getCommand("vert").setExecutor(new Teams());
		getCommand("jaune").setExecutor(new Teams());
		getCommand("gold").setExecutor(new Teams());
		getCommand("lime").setExecutor(new Teams());
		getCommand("cyan").setExecutor(new Teams());
		getCommand("rose").setExecutor(new Teams());
		getCommand("violet").setExecutor(new Teams());
		getCommand("blanc").setExecutor(new Teams());
		getCommand("gris").setExecutor(new Teams());

		// CLASSES NORMALES
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerEvents(), this);
		pm.registerEvents(new Teams(), this);
		pm.registerEvents(new Scoreboards(), this);
		pm.registerEvents(new Timers(), this);
		pm.registerEvents(new Titles(), this);

		// DIFFERENTES GAMERULES POUR LE JEU
		getServer().getWorld("world").setGameRuleValue("doDaylightCycle", "false");
		getServer().getWorld("world").setGameRuleValue("naturalRegeneration", "false");
		getServer().getWorld("world").setTime(6000L);
		getServer().getWorld("world").setStorm(false);
		getServer().getWorld("world").setDifficulty(Difficulty.NORMAL);
		
		if (main.getInstance().isState(states.WAIT) || main.getInstance().isState(states.GAME)) {
			Bukkit.getWorld("world").setPVP(false);
		}
		
		new ScoreboardRefresh().runTaskTimer(this, 0L, 20L);
		Scoreboards.initObjectives();
		Scoreboards.initScores();
		
		ItemStack arc = new ItemStack(Material.BOW, 1);
		ItemMeta arcM = arc.getItemMeta();
		arcM.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		arc.setItemMeta(arcM);
		ShapedRecipe arcInfinite = new ShapedRecipe(new ItemStack(arc));
		arcInfinite.shape(new String[] { " D ", "OBO", " A " });
		arcInfinite.setIngredient('O', Material.OBSIDIAN);
		arcInfinite.setIngredient('B', Material.BOW);
		arcInfinite.setIngredient('D', Material.DIAMOND);
		arcInfinite.setIngredient('A', Material.ARROW);
		getServer().addRecipe(arcInfinite);
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§c-------------Plugin OFF------------");
		getServer().getConsoleSender().sendMessage("§2Terre du Kill §6- §2SAISON 6 §6- §2Reloaded");
	}

	// PROCEDURE POUR DEFINIR UN STATUT DE JEU DURANT LA PARTIE
	public void setState(states state) {
		current = state;
	}

	// PROCEDURE POUR VERIFIER SI LE JEU EST DANS UN CERTAIN STATUT DURANT LA PARTIE
	public boolean isState(states state) {
		return current == state;
	}

}
