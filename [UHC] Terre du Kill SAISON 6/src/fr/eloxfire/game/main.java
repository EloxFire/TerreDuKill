package fr.eloxfire.game;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import fr.eloxfire.game.gameCommands;
import fr.eloxfire.game.ScoreboardRefresh;
import fr.eloxfire.game.Titles;

public class main extends JavaPlugin {

	public Titles title = new Titles();

	public states current;
	public static main instance;

	public static main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		// ON DEFINI LE STATUT DE JEU DE BASE A L'ALLUMAGE DU PLUGIN SUR 'WAIT'
		current = states.WAIT;
		// L'INSTANCE DE BASE DU PLUGIN EST CETTE CLASSE AVEC CA ON PEUT RECUPERER LA
		// CLASSE PRINCIPALE PARTOUT DANS LES AUTRES CLASSES
		instance = this;
		// ON ENVOIE UN MESSAGE AU DEMARAGE DU PLUGIN
		getServer().getConsoleSender().sendMessage("§6[T.D.K]§2 Terre du Kill SAISON 6 ON !");

		// COMMANDES DE PREPARATION DE LA GAME
		getCommand("setspawn").setExecutor(new setGame());
		getCommand("wb").setExecutor(new setGame());
		getCommand("ready").setExecutor(new setGame());
		getCommand("unready").setExecutor(new setGame());
		getCommand("rouge").setExecutor(new Teams());
		getCommand("bleu").setExecutor(new Teams());
		getCommand("vert").setExecutor(new Teams());
		getCommand("jaune").setExecutor(new Teams());
		getCommand("gold").setExecutor(new Teams());
		getCommand("lime").setExecutor(new Teams());
		getCommand("bc").setExecutor(new Teams());
		getCommand("cyan").setExecutor(new Teams());
		getCommand("rose").setExecutor(new Teams());
		getCommand("violet").setExecutor(new Teams());
		getCommand("blanc").setExecutor(new Teams());
		getCommand("gris").setExecutor(new Teams());
		getCommand("checkTeams").setExecutor(new Teams());
		getCommand("clearteams").setExecutor(new Teams());

		// COMMADNDES IN GAME / MODIFICATIONS IN GAME
		getCommand("alert").setExecutor(new gameCommands());
		getCommand("pvp").setExecutor(new gameCommands());
		getCommand("reduceborder").setExecutor(new gameCommands());
		getCommand("healall").setExecutor(new gameCommands());
		getCommand("starttdk").setExecutor(new gameCommands());

		// INSTANCIATION DES CLASSES QUAND LE PLUGIN DEMARRE
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Timers(), this); // CLASSE QUI GERE LES TIMERS (PVP BORDER TIMER GENERAL)
		pm.registerEvents(new Titles(), this); // CLASSE QUI GERE LES TITLES
		pm.registerEvents(new PlayerJoin(), this); // CLASSE QUI GERE L'EVENEMENT DE JOIN DES PLAYERS
		pm.registerEvents(new Scoreboards(), this); // CLASSE QUI GERE LES SCOREBOARDS
		pm.registerEvents(new DeathEvent(), this); // CLASSE QUI GERE L'EVENEMENT DE MORT DES PLAYERS
		pm.registerEvents(new Teams(), this); // CLASSE QUI GERE LES TEAMS
		pm.registerEvents(new ArbresDepop(), this); // CLASSE QUI GERE LE CUT AUTO DES ARBRES

		// DIFFERENTES GAMERULES POUR LE JEU
		getServer().getWorld("world").setGameRuleValue("doDaylightCycle", "false");
		getServer().getWorld("world").setGameRuleValue("naturalRegeneration", "false");
		getServer().getWorld("world").setTime(6000L);
		getServer().getWorld("world").setStorm(false);
		getServer().getWorld("world").setDifficulty(Difficulty.NORMAL);

		// ON DEFINI LE PVP SUR FALSE DURANT LA PHASE D'ATTENTE DE JOUEURS ET LE STATUT
		// DE DEBUT DE JEU
		if (main.getInstance().isState(states.WAIT) || main.getInstance().isState(states.GAME)) {
			Bukkit.getWorld("world").setPVP(false);
		}

		// ON LANCE LA CLASSE RUNNABALE QUI REFRESH LES SCOREBOARDS
		new ScoreboardRefresh().runTaskTimer(this, 0L, 20L);

		// ON INITIALISE LES OBJECTIFS ET SCORES DES SCOREBOARDS DONNES AUX PLAYERS
		Scoreboards.initObjectives();
		Scoreboards.initScores();
		// ON CLEAR LES TEAMS
		Teams.clearTeams();

		// AJOUT DU CRAFT DE L'ARC ENCHANT
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
		// ON EN VOIE UN MESSAGE LORS DE L'ARRET DU PLUGIN
		getServer().getConsoleSender().sendMessage("§6[T.D.K]§2 Terre du Kill SAISON 6 OFF !");
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
