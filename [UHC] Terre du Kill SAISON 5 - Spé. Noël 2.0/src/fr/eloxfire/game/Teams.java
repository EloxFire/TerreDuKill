package fr.eloxfire.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Teams implements Listener, CommandExecutor {

	private static List<String> redTeam = new ArrayList<String>();
	private static List<String> blueTeam = new ArrayList<String>();
	private static List<String> greenTeam = new ArrayList<String>();
	private static List<String> yellowTeam = new ArrayList<String>();
	private static List<String> goldTeam = new ArrayList<String>();
	private static List<String> limeTeam = new ArrayList<String>();
	private static List<String> bcTeam = new ArrayList<String>();
	private static List<String> cyanTeam = new ArrayList<String>();
	private static List<String> pinkTeam = new ArrayList<String>();
	private static List<String> purpleTeam = new ArrayList<String>();
	private static List<String> whiteTeam = new ArrayList<String>();
	private static List<String> greyTeam = new ArrayList<String>();

	private static List<Player> Teamrouge = new ArrayList<>();
	private static List<Player> Teambleu = new ArrayList<>();
	private static List<Player> Teamvert = new ArrayList<>();
	private static List<Player> Teamjaune = new ArrayList<>();
	private static List<Player> Teamlime = new ArrayList<>();
	private static List<Player> Teambc = new ArrayList<>();
	private static List<Player> Teamcyan = new ArrayList<>();
	private static List<Player> Teamrose = new ArrayList<>();
	private static List<Player> Teamviolet = new ArrayList<>();
	private static List<Player> Teamblanc = new ArrayList<>();
	private static List<Player> Teamgris = new ArrayList<>();
	private static List<Player> Teamgold = new ArrayList<>();

	public static void addToTeam(TeamType type, Player player) {
		if (isInTeam(player)) {
			player.sendMessage("§6§l[T.D.K]§2 Tu est déjà dans une team !");
			player.sendMessage("§6§l[T.D.K]§2 Si tu veux quitter ta team fait §c/leaveteam");
			return;
		}
		switch (type) {
		case RED:
			Teamrouge.add(player);
			redTeam.add(player.getName());
			break;
		case BLUE:
			Teambleu.add(player);
			blueTeam.add(player.getName());
			break;
		case GREEN:
			Teamvert.add(player);
			greenTeam.add(player.getName());
			break;
		case YELLOW:
			Teamjaune.add(player);
			yellowTeam.add(player.getName());
			break;
		case GOLD:
			Teamgold.add(player);
			goldTeam.add(player.getName());
			break;
		case LIME:
			Teamlime.add(player);
			limeTeam.add(player.getName());
			break;
		case BC:
			Teambc.add(player);
			bcTeam.add(player.getName());
			break;
		case CYAN:
			Teamcyan.add(player);
			cyanTeam.add(player.getName());
			break;
		case PINK:
			Teamrose.add(player);
			pinkTeam.add(player.getName());
			break;
		case PURPLE:
			Teamviolet.add(player);
			purpleTeam.add(player.getName());
			break;
		case WHITE:
			Teamblanc.add(player);
			whiteTeam.add(player.getName());
			break;
		case GREY:
			Teamgris.add(player);
			greyTeam.add(player.getName());
			break;
		}
		player.sendMessage("§6§l[T.D.K]§2 Ajouté à la team : §r" + type.toString() + "§2 !");

	}

	public static boolean isInTeam(Player player) {
		return redTeam.contains(player.getName()) || blueTeam.contains(player.getName())
				|| greenTeam.contains(player.getName()) || yellowTeam.contains(player.getName())
				|| goldTeam.contains(player.getName()) || limeTeam.contains(player.getName())
				|| bcTeam.contains(player.getName()) || cyanTeam.contains(player.getName())
				|| pinkTeam.contains(player.getName()) || pinkTeam.contains(player.getName())
				|| purpleTeam.contains(player.getName()) || whiteTeam.contains(player.getName())
				|| greyTeam.contains(player.getName());
	}

	public static void clearTeams() {
		redTeam.clear();
		blueTeam.clear();
		greenTeam.clear();
		yellowTeam.clear();
		goldTeam.clear();
		limeTeam.clear();
		bcTeam.clear();
		cyanTeam.clear();
		pinkTeam.clear();
		purpleTeam.clear();
		whiteTeam.clear();
		greyTeam.clear();
	}

	public static List<String> getRedTeam() {
		return redTeam;
	}

	public static List<String> getBlueTeam() {
		return blueTeam;
	}

	public static List<String> getGreenTeam() {
		return greenTeam;
	}

	public static List<String> getYellowTeam() {
		return yellowTeam;
	}

	public static List<String> getGoldTeam() {
		return goldTeam;
	}

	public static List<String> getLimeTeam() {
		return limeTeam;
	}

	public static List<String> getBcTeam() {
		return bcTeam;
	}

	public static List<String> getCyanTeam() {
		return cyanTeam;
	}

	public static List<String> getPinkTeam() {
		return pinkTeam;
	}

	public static List<String> getPurpleTeam() {
		return purpleTeam;
	}

	public static List<String> getWhiteTeam() {
		return whiteTeam;
	}

	public static List<String> getGreyTeam() {
		return greyTeam;
	}

	public static List<String> getAllPlayersInAllTeams() {
		List<String> combinedTeams = new ArrayList<String>();
		combinedTeams.addAll(redTeam);
		combinedTeams.addAll(blueTeam);
		combinedTeams.addAll(greenTeam);
		combinedTeams.addAll(yellowTeam);
		combinedTeams.addAll(goldTeam);
		combinedTeams.addAll(limeTeam);
		combinedTeams.addAll(bcTeam);
		combinedTeams.addAll(cyanTeam);
		combinedTeams.addAll(pinkTeam);
		combinedTeams.addAll(purpleTeam);
		combinedTeams.addAll(whiteTeam);
		combinedTeams.addAll(greyTeam);
		return combinedTeams;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("rouge")) {
				player.setPlayerListName("§4" + player.getName());
				player.setDisplayName("§4" + player.getName());
				Teams.addToTeam(TeamType.RED, player);
			}
			if (cmd.getName().equalsIgnoreCase("bleu")) {
				player.setPlayerListName("§3" + player.getName());
				player.setDisplayName("§3" + player.getName());
				Teams.addToTeam(TeamType.BLUE, player);
			}
			if (cmd.getName().equalsIgnoreCase("vert")) {
				player.setPlayerListName("§2" + player.getName());
				player.setDisplayName("§2" + player.getName());
				Teams.addToTeam(TeamType.GREEN, player);
			}
			if (cmd.getName().equalsIgnoreCase("jaune")) {
				player.setPlayerListName("§e" + player.getName());
				player.setDisplayName("§e" + player.getName());
				Teams.addToTeam(TeamType.YELLOW, player);
			}
			if (cmd.getName().equalsIgnoreCase("gold")) {
				player.setPlayerListName("§6" + player.getName());
				player.setDisplayName("§6" + player.getName());
				Teams.addToTeam(TeamType.GOLD, player);
			}
			if (cmd.getName().equalsIgnoreCase("lime")) {
				player.setPlayerListName("§a" + player.getName());
				player.setDisplayName("§a" + player.getName());
				Teams.addToTeam(TeamType.LIME, player);
			}
			if (cmd.getName().equalsIgnoreCase("cyan")) {
				player.setPlayerListName("§b" + player.getName());
				player.setDisplayName("§b" + player.getName());
				Teams.addToTeam(TeamType.CYAN, player);
			}
			if (cmd.getName().equalsIgnoreCase("pink")) {
				player.setPlayerListName("§d" + player.getName());
				player.setDisplayName("§d" + player.getName());
				Teams.addToTeam(TeamType.PINK, player);
			}
			if (cmd.getName().equalsIgnoreCase("violet")) {
				player.setPlayerListName("§5" + player.getName());
				player.setDisplayName("§5" + player.getName());
				Teams.addToTeam(TeamType.PURPLE, player);
			}
			if (cmd.getName().equalsIgnoreCase("blanc")) {
				player.setPlayerListName("§f" + player.getName());
				player.setDisplayName("§f" + player.getName());
				Teams.addToTeam(TeamType.WHITE, player);
			}
			if (cmd.getName().equalsIgnoreCase("gris")) {
				player.setPlayerListName("§8" + player.getName());
				player.setDisplayName("§8" + player.getName());
				Teams.addToTeam(TeamType.GREY, player);
			}

			if (cmd.getName().equalsIgnoreCase("clearteams")) {
				player.sendMessage("§6§l[T.D.K]§2 Vous venez de clear toutes les teams !");
				clearTeams();
			}
		}
		return true;
	}

	public static void teleportTeams() {

		if (Teamrouge.size() != 0) {
			Random rdmrouge = new Random();
			Player playerSelectRouge = Teamrouge.get(rdmrouge.nextInt(Teamrouge.size()));

			playerSelectRouge.teleport(playerSelectRouge.getWorld().getSpawnLocation());

			int xr = playerSelectRouge.getLocation().getBlockX() + rdmrouge.nextInt(1000);
			int yr = playerSelectRouge.getLocation().getBlockY();
			int zr = playerSelectRouge.getLocation().getBlockZ() + rdmrouge.nextInt(1000);
			Location TpRandomRouge = new Location(Bukkit.getWorld("world"), xr, yr, zr);

			playerSelectRouge.teleport(TpRandomRouge);

			for (Player pls : Teamrouge) {
				pls.teleport(playerSelectRouge);
			}
		}

		if (Teambleu.size() != 0) {
			Random rdmbleu = new Random();
			Player playerSelectBleu = Teambleu.get(rdmbleu.nextInt(Teambleu.size()));

			playerSelectBleu.teleport(playerSelectBleu.getWorld().getSpawnLocation());

			int xb = playerSelectBleu.getLocation().getBlockX() + rdmbleu.nextInt(1000);
			int yb = playerSelectBleu.getLocation().getBlockY();
			int zb = playerSelectBleu.getLocation().getBlockZ() + rdmbleu.nextInt(1000);
			Location TpRandomBleu = new Location(Bukkit.getWorld("world"), xb, yb, zb);

			playerSelectBleu.teleport(TpRandomBleu);

			for (Player pls : Teambleu) {
				pls.teleport(playerSelectBleu);
			}
		}

		if (Teamjaune.size() != 0) {
			Random rdmj = new Random();
			Player PlayerSelectJaune = Teamjaune.get(rdmj.nextInt(Teamjaune.size()));

			PlayerSelectJaune.teleport(PlayerSelectJaune.getWorld().getSpawnLocation());

			int xj = PlayerSelectJaune.getLocation().getBlockX() + rdmj.nextInt(50);
			int yj = PlayerSelectJaune.getLocation().getBlockY();
			int zj = PlayerSelectJaune.getLocation().getBlockZ() + rdmj.nextInt(50);
			Location TpRandomJaune = new Location(Bukkit.getWorld("world"), xj, yj, zj);

			PlayerSelectJaune.teleport(TpRandomJaune);

			for (Player pls : Teamjaune) {
				pls.teleport(PlayerSelectJaune);
			}
		}

		if (Teamvert.size() != 0) {
			Random rdmv = new Random();
			Player PlayerSelectVert = Teamvert.get(rdmv.nextInt(Teamvert.size()));

			PlayerSelectVert.teleport(PlayerSelectVert.getWorld().getSpawnLocation());

			int xv = PlayerSelectVert.getLocation().getBlockX() + rdmv.nextInt(1000);
			int yv = PlayerSelectVert.getLocation().getBlockY();
			int zv = PlayerSelectVert.getLocation().getBlockZ() + rdmv.nextInt(1000);
			Location TpRandomVert = new Location(Bukkit.getWorld("world"), xv, yv, zv);

			PlayerSelectVert.teleport(TpRandomVert);

			for (Player pls : Teamvert) {
				pls.teleport(PlayerSelectVert);
			}
		}

		if (Teamlime.size() != 0) {
			Random rdml = new Random();
			Player PlayerSelectLime = Teamlime.get(rdml.nextInt(Teamlime.size()));

			PlayerSelectLime.teleport(PlayerSelectLime.getWorld().getSpawnLocation());

			int xl = PlayerSelectLime.getLocation().getBlockX() + rdml.nextInt(1000);
			int yl = PlayerSelectLime.getLocation().getBlockY();
			int zl = PlayerSelectLime.getLocation().getBlockZ() + rdml.nextInt(1000);
			Location TpRandomLime = new Location(Bukkit.getWorld("world"), xl, yl, zl);

			PlayerSelectLime.teleport(TpRandomLime);

			for (Player pls : Teamlime) {
				pls.teleport(PlayerSelectLime);
			}
		}

		if (Teambc.size() != 0) {
			Random rdmbc = new Random();
			Player PlayerSelectBc = Teambc.get(rdmbc.nextInt(Teambc.size()));

			PlayerSelectBc.teleport(PlayerSelectBc.getWorld().getSpawnLocation());

			int xbc = PlayerSelectBc.getLocation().getBlockX() + rdmbc.nextInt(1000);
			int ybc = PlayerSelectBc.getLocation().getBlockY();
			int zbc = PlayerSelectBc.getLocation().getBlockZ() + rdmbc.nextInt(1000);
			Location TpRandomBC = new Location(Bukkit.getWorld("world"), xbc, ybc, zbc);

			PlayerSelectBc.teleport(TpRandomBC);

			for (Player pls : Teambc) {
				pls.teleport(PlayerSelectBc);
			}
		}

		if (Teamcyan.size() != 0) {
			Random rdmcyan = new Random();
			Player PlayerSelectCyan = Teamcyan.get(rdmcyan.nextInt(Teamcyan.size()));

			PlayerSelectCyan.teleport(PlayerSelectCyan.getWorld().getSpawnLocation());

			int xc = PlayerSelectCyan.getLocation().getBlockX() + rdmcyan.nextInt(1000);
			int yc = PlayerSelectCyan.getLocation().getBlockY();
			int zc = PlayerSelectCyan.getLocation().getBlockZ() + rdmcyan.nextInt(1000);
			Location TpRandomcyan = new Location(Bukkit.getWorld("world"), xc, yc, zc);

			PlayerSelectCyan.teleport(TpRandomcyan);

			for (Player pls : Teamcyan) {
				pls.teleport(PlayerSelectCyan);
			}
		}

		if (Teamrose.size() != 0) {
			Random rdmpink = new Random();
			Player PlayerSelectPink = Teamrose.get(rdmpink.nextInt(Teamrose.size()));

			PlayerSelectPink.teleport(PlayerSelectPink.getWorld().getSpawnLocation());

			int xp = PlayerSelectPink.getLocation().getBlockX() + rdmpink.nextInt(1000);
			int yp = PlayerSelectPink.getLocation().getBlockY();
			int zp = PlayerSelectPink.getLocation().getBlockZ() + rdmpink.nextInt(1000);
			Location TpRandomPink = new Location(Bukkit.getWorld("world"), xp, yp, zp);

			PlayerSelectPink.teleport(TpRandomPink);

			for (Player pls : Teamrose) {
				pls.teleport(PlayerSelectPink);
			}
		}

		if (Teamviolet.size() != 0) {
			Random rdmpurple = new Random();
			Player PlayerSelectPurple = Teamviolet.get(rdmpurple.nextInt(Teamviolet.size()));

			PlayerSelectPurple.teleport(PlayerSelectPurple.getWorld().getSpawnLocation());

			int xvi = PlayerSelectPurple.getLocation().getBlockX() + rdmpurple.nextInt(1000);
			int yvi = PlayerSelectPurple.getLocation().getBlockY();
			int zvi = PlayerSelectPurple.getLocation().getBlockZ() + rdmpurple.nextInt(1000);
			Location TpRandomViolet = new Location(Bukkit.getWorld("world"), xvi, yvi, zvi);

			PlayerSelectPurple.teleport(TpRandomViolet);

			for (Player pls : Teamviolet) {
				pls.teleport(PlayerSelectPurple);
			}
		}

		if (Teamblanc.size() != 0) {
			Random rdmblanc = new Random();
			Player PlayerSelectBlanc = Teamblanc.get(rdmblanc.nextInt(Teamblanc.size()));

			PlayerSelectBlanc.teleport(PlayerSelectBlanc.getWorld().getSpawnLocation());

			int xblanc = PlayerSelectBlanc.getLocation().getBlockX() + rdmblanc.nextInt(1000);
			int yblanc = PlayerSelectBlanc.getLocation().getBlockY();
			int zblanc = PlayerSelectBlanc.getLocation().getBlockZ() + rdmblanc.nextInt(1000);
			Location TpRandomBlanc = new Location(Bukkit.getWorld("world"), xblanc, yblanc, zblanc);

			PlayerSelectBlanc.teleport(TpRandomBlanc);

			for (Player pls : Teamblanc) {
				pls.teleport(PlayerSelectBlanc);
			}
		}

		if (Teamgris.size() != 0) {
			Random rdmgris = new Random();
			Player PlayerSelectGris = Teamgris.get(rdmgris.nextInt(Teamgris.size()));

			PlayerSelectGris.teleport(PlayerSelectGris.getWorld().getSpawnLocation());

			int xgris = PlayerSelectGris.getLocation().getBlockX() + rdmgris.nextInt(1000);
			int ygris = PlayerSelectGris.getLocation().getBlockY();
			int zgris = PlayerSelectGris.getLocation().getBlockZ() + rdmgris.nextInt(1000);
			Location TpRandomGris = new Location(Bukkit.getWorld("world"), xgris, ygris, zgris);

			PlayerSelectGris.teleport(TpRandomGris);

			for (Player pls : Teamgris) {
				pls.teleport(PlayerSelectGris);
			}
		}

		if (Teamgold.size() != 0) {
			Random rdmrdmgf = new Random();
			Player PlayerSelectGf = Teamgold.get(rdmrdmgf.nextInt(Teamgold.size()));

			PlayerSelectGf.teleport(PlayerSelectGf.getWorld().getSpawnLocation());

			int xgf = PlayerSelectGf.getLocation().getBlockX() + rdmrdmgf.nextInt(1000);
			int ygf = PlayerSelectGf.getLocation().getBlockY();
			int zgf = PlayerSelectGf.getLocation().getBlockZ() + rdmrdmgf.nextInt(1000);
			Location TpRandomGf = new Location(Bukkit.getWorld("world"), xgf, ygf, zgf);

			PlayerSelectGf.teleport(TpRandomGf);

			for (Player pls : Teamgold) {
				pls.teleport(PlayerSelectGf);
			}
		}

	}
}
