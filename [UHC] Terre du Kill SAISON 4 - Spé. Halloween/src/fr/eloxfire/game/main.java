package fr.eloxfire.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fr.eloxfire.events.DeathEvent;
import fr.eloxfire.events.JoinQuitEvent;
import fr.eloxfire.events.SkullRegen;
import fr.eloxfire.events.TimerBORDER;
import fr.eloxfire.events.TimerPVP;
import fr.eloxfire.utils.ArbresDepop;
import fr.eloxfire.utils.ScoreboardRefresh;
import fr.eloxfire.utils.Scoreboards;
import fr.eloxfire.utils.TailleBORDER;
import fr.eloxfire.utils.Titles;

public class main extends JavaPlugin implements Listener{
	
	//TEAMS
	public static org.bukkit.scoreboard.Team rouge;
	public static org.bukkit.scoreboard.Team bleu;
	public static org.bukkit.scoreboard.Team jaune;
	public static org.bukkit.scoreboard.Team gris;
	public static org.bukkit.scoreboard.Team vert;
	public static org.bukkit.scoreboard.Team lime;
	public static org.bukkit.scoreboard.Team bc;
	public static org.bukkit.scoreboard.Team cyan;
	public static org.bukkit.scoreboard.Team rose;
	public static org.bukkit.scoreboard.Team violet;
	public static org.bukkit.scoreboard.Team blanc;
	public static org.bukkit.scoreboard.Team gold;
	
	//ARRAYLISTD ES TEAM
	public static List<Player> Teamrouge = new ArrayList<>();
	public static List<Player> Teambleu = new ArrayList<>();
	public static List<Player> Teamjaune = new ArrayList<>();
	public static List<Player> Teamgris = new ArrayList<>();
	public static List<Player> Teamvert = new ArrayList<>();
	public static List<Player> Teamlime = new ArrayList<>();
	public static List<Player> Teambc = new ArrayList<>();
	public static List<Player> Teamcyan = new ArrayList<>();
	public static List<Player> Teamrose = new ArrayList<>();
	public static List<Player> Teamviolet = new ArrayList<>();
	public static List<Player> Teamblanc = new ArrayList<>();
	public static List<Player> Teamgold = new ArrayList<>();
	
	public Titles title = new Titles();
	
	public states current;
	public static main instance;
	public static main getInstance(){
		return instance;
	}
	
	
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage("§6[TDK]§a SAISON 4 - Special HALLOWEEN !");
		
		current = states.PREGAME;
		instance = this;
		
		getServer().getPluginManager().registerEvents(this, this);	
		getServer().getPluginManager().registerEvents(new JoinQuitEvent(), this);
		getServer().getPluginManager().registerEvents(new DeathEvent(), this);
		getServer().getPluginManager().registerEvents(new Scoreboards(), this);
		getServer().getPluginManager().registerEvents(new StartGame(), this);
		getServer().getPluginManager().registerEvents(new TimerPVP(), this);
		getServer().getPluginManager().registerEvents(new TimerBORDER(), this);
		getServer().getPluginManager().registerEvents(new TailleBORDER(), this);
		getServer().getPluginManager().registerEvents(new Titles(), this);
		getServer().getPluginManager().registerEvents(new ArbresDepop(), this);
		getServer().getPluginManager().registerEvents(new SkullRegen(), this);

		
		new ScoreboardRefresh().runTaskTimer(this, 0L, 20L);
		
		if(main.getInstance().isState(states.PREGAME) || main.getInstance().isState(states.GAME)){
			Bukkit.getWorld("world").setPVP(false);
		}

		getServer().getWorld("world").setGameRuleValue("doDaylightCycle", "false");
		getServer().getWorld("world").setGameRuleValue("naturalRegeneration", "false");
		getServer().getWorld("world").setTime(13000L);
		getServer().getWorld("world").setStorm(false);
		getServer().getWorld("world").setDifficulty(Difficulty.NORMAL);
		

		initTeams();
		Scoreboards.initObjectives();
		Scoreboards.initScores();
		TailleBORDER.TailleBorder();
		
		//CRAFT DE L'ARC ENCHANT
		ItemStack arc = new ItemStack(Material.BOW, 1);
		ItemMeta arcM = arc.getItemMeta();
		arcM.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		arc.setItemMeta(arcM);
		ShapedRecipe arcInfinite = new ShapedRecipe(new ItemStack(arc));
		arcInfinite.shape(new String[] {" D ", "OBO", " A "});
		arcInfinite.setIngredient('O', Material.OBSIDIAN);
		arcInfinite.setIngredient('B', Material.BOW);
		arcInfinite.setIngredient('D', Material.DIAMOND);
		arcInfinite.setIngredient('A', Material.ARROW);
		getServer().addRecipe(arcInfinite);
		
		//CRAFT DE LA GOLDEN HEAD
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta skullMeta= (SkullMeta) skull.getItemMeta();
		skullMeta.getOwner();
		skull.setItemMeta(skullMeta);
		ShapedRecipe pomme1 = new ShapedRecipe(new ItemStack(Material.GOLDEN_APPLE));
		pomme1.shape(new String[] {"SG ", "   ", "   "});
		pomme1.setIngredient('S', Material.SKULL_ITEM);
		pomme1.setIngredient('G', Material.GOLD_INGOT);
		getServer().addRecipe(pomme1);

	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§6[TDK]§a SAISON 4 OFF !");
	}
	
	
	public static void initTeams(){
		//TEAM ROUGE
		rouge = Scoreboards.s.registerNewTeam("Rouge");
		rouge.setPrefix("§4");
		rouge.setSuffix(" §r");
		rouge.setAllowFriendlyFire(false);
		rouge.setCanSeeFriendlyInvisibles(true);
		rouge.setDisplayName("§4[Rouge] ");
		
		//TEAM BLEUE
		bleu = Scoreboards.s.registerNewTeam("Bleu");
		bleu.setPrefix("§1");
		bleu.setSuffix(" §r");
		bleu.setAllowFriendlyFire(false);
		bleu.setCanSeeFriendlyInvisibles(true);
		bleu.setDisplayName("§1[Bleu] ");
		
		//TEAM JAUNE
		jaune = Scoreboards.s.registerNewTeam("Jaune");
		jaune.setPrefix("§e");
		jaune.setSuffix(" §r");
		jaune.setAllowFriendlyFire(false);
		jaune.setCanSeeFriendlyInvisibles(true);
		jaune.setDisplayName("§e[Jaune] ");
		
		//TEAM grisE
		vert = Scoreboards.s.registerNewTeam("Vert");
		vert.setPrefix("§2");
		vert.setSuffix(" §r");
		vert.setAllowFriendlyFire(false);
		vert.setCanSeeFriendlyInvisibles(true);
		vert.setDisplayName("§2[Vert] ");
		
		//TEAM LIME
		lime = Scoreboards.s.registerNewTeam("Lime");
		lime.setPrefix("§a");
		lime.setSuffix(" §r");
		lime.setAllowFriendlyFire(false);
		lime.setCanSeeFriendlyInvisibles(true);
		lime.setDisplayName("§a[Lime] ");
		
		//TEAM BC
		bc = Scoreboards.s.registerNewTeam("Bleu-clair");
		bc.setPrefix("§b");
		bc.setSuffix(" §r");
		bc.setAllowFriendlyFire(false);
		bc.setCanSeeFriendlyInvisibles(true);
		bc.setDisplayName("§b[Bleu-clair] ");
		
		//TEAM CYAN
		cyan = Scoreboards.s.registerNewTeam("Cyan");
		cyan.setPrefix("§3");
		cyan.setSuffix(" §r");
		cyan.setAllowFriendlyFire(false);
		cyan.setCanSeeFriendlyInvisibles(true);
		cyan.setDisplayName("§3[Cyan] ");
		
		//TEAM ROSE
		rose = Scoreboards.s.registerNewTeam("Rose");
		rose.setPrefix("§d");
		rose.setSuffix(" §r");
		rose.setAllowFriendlyFire(false);
		rose.setCanSeeFriendlyInvisibles(true);
		rose.setDisplayName("§d[Rose] ");
		
		//TEAM VIOLETTTE
		violet = Scoreboards.s.registerNewTeam("Violet");
		violet.setPrefix("§5");
		violet.setSuffix(" §r");
		violet.setAllowFriendlyFire(false);
		violet.setCanSeeFriendlyInvisibles(true);
		violet.setDisplayName("§5[Violet] ");
		
		//TEAM BLANCHE
		blanc = Scoreboards.s.registerNewTeam("Blanc");
		blanc.setPrefix("§f");
		blanc.setSuffix(" §r");
		blanc.setAllowFriendlyFire(false);
		blanc.setCanSeeFriendlyInvisibles(true);
		blanc.setDisplayName("§f[Blanc] ");
		
		//TEAM GRISE
		gris = Scoreboards.s.registerNewTeam("Gris");
		gris.setPrefix("§7");
		gris.setSuffix(" §r");
		gris.setAllowFriendlyFire(false);
		gris.setCanSeeFriendlyInvisibles(true);
		gris.setDisplayName("§7[Gris] ");
		
		//TEAM GF
		gold = Scoreboards.s.registerNewTeam("Gold");
		gold.setPrefix("§6");
		gold.setSuffix(" §r");
		gold.setAllowFriendlyFire(false);
		gold.setCanSeeFriendlyInvisibles(true);
		gold.setDisplayName("§6[Gold] ");
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public static void onTeamCommands(PlayerCommandPreprocessEvent e){
		Player player = e.getPlayer();
		String msg = e.getMessage();
		String args[] = msg.split(" ");
		if(!main.getInstance().isState(states.GAME) || !main.getInstance().isState(states.PVP) || !main.getInstance().isState(states.FINISH)){
		
			
			if(args[0].equalsIgnoreCase("/teamlist")){
				player.sendMessage("§6[T.D.K]§2 Voici la liste des teams :");
				player.sendMessage("§4rouge §r/ §ejaune §r/ §2vert §r/ §alime §r/ §bbleau-clair §r/ §7gris");
				player.sendMessage("§3cyan §r/ §1bleu §r/ §drose §r/ §5violet §r/ §fblanc §r/ §6Gold");
				
				e.setCancelled(true);
			}
			

			if(args[0].equalsIgnoreCase("/join")){
				if(args.length == 1 ){
					player.sendMessage("§6[T.D.K] §2Fait §6/join <team>");
				}
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("rouge")){
						
						if(rouge.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(rouge.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §4Rouge");
						}else{
							rouge.addPlayer(player);
							Teamrouge.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §4Rouge" + "§6 (" + rouge.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("jaune")){
						
						if(jaune.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(jaune.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §eJaune");
						}else{
							jaune.addPlayer(player);
							Teamjaune.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §eJaune" + "§6 (" + jaune.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("vert")){
						
						if(vert.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(vert.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §2Vert");
						}else{
							vert.addPlayer(player);
							Teamvert.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §2Vert" + "§6 (" + vert.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("lime")){
						
						if(lime.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(lime.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §aLime");
						}else{
							lime.addPlayer(player);
							Teamlime.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §aLime" + "§6 (" + lime.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("bc")){
						
						if(bc.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(bc.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §bBleu-clair");
						}else{
							bc.addPlayer(player);
							Teambc.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §bBleu-clair" + "§6 (" + bc.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("cyan")){
						
						if(cyan.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(cyan.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §3Cyan");
						}else{
							cyan.addPlayer(player);
							Teamcyan.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §3Cyan" + "§6 (" + cyan.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("bleu")){
						
						if(bleu.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(bleu.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §1Bleu");
						}else{
							bleu.addPlayer(player);
							Teambleu.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §1Bleu" + "§6 (" + bleu.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("rose")){
						
						
						if(rose.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(rose.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §dRose");
						}else{
							rose.addPlayer(player);
							Teamrose.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §dRose" + "§6 (" + rose.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("violet")){
						
						if(violet.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(violet.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §5Violet");
						}else{
							violet.addPlayer(player);
							Teamviolet.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §5Violet" + "§6 (" + violet.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("gris")){
						
						if(gris.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(gris.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §7Gris");
						}else{
							gris.addPlayer(player);
							Teamgris.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §7Gris" + "§6 (" + gris.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("blanc")){
						
						if(blanc.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(blanc.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §fBlanc");
						}else{
							blanc.addPlayer(player);
							Teamblanc.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §fBlanc" + "§6 (" + blanc.getSize() + "§6/ 3)");
						}
					}
				}
				
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("gold")){
						
						if(gold.getSize() >= 3){
							player.sendMessage("§6[T.D.K] §2La team est pleine !");
						}
						if(gold.hasPlayer(player)){
							player.sendMessage("§6[T.D.K] §2Tu es déjà dans la team : §6Gold");
						}else{
							gold.addPlayer(player);
							Teamgold.add(player);
							player.sendMessage("§6[T.D.K] §2Tu rejoins la team : §6Gold" + "§6 (" + gold.getSize() + "§6/ 3)");
						}
					}
				}
			}
			
			
			e.setCancelled(true);
			
		}
	}
	

	public static void teleportTeams(){
		
		if(Teamrouge.size() != 0){
			Random rdmrouge = new Random();
			Player playerSelectRouge = Teamrouge.get(rdmrouge.nextInt(Teamrouge.size()));
			
			playerSelectRouge.teleport(playerSelectRouge.getWorld().getSpawnLocation());
			
			int xr = playerSelectRouge.getLocation().getBlockX() + rdmrouge.nextInt(1000);
			int yr = playerSelectRouge.getLocation().getBlockY();
			int zr = playerSelectRouge.getLocation().getBlockZ() + rdmrouge.nextInt(1000);
			Location TpRandomRouge = new Location(Bukkit.getWorld("world"), xr, yr, zr);
			
			playerSelectRouge.teleport(TpRandomRouge);
			
			for(Player pls : Teamrouge){
				pls.teleport(playerSelectRouge);
			}
		}
		
		if(Teambleu.size() != 0){
			Random rdmbleu = new Random();
			Player playerSelectBleu = Teambleu.get(rdmbleu.nextInt(Teambleu.size()));
			
			playerSelectBleu.teleport(playerSelectBleu.getWorld().getSpawnLocation());
			
			int xb = playerSelectBleu.getLocation().getBlockX() + rdmbleu.nextInt(1000);
			int yb = playerSelectBleu.getLocation().getBlockY();
			int zb = playerSelectBleu.getLocation().getBlockZ() + rdmbleu.nextInt(1000);
			Location TpRandomBleu = new Location(Bukkit.getWorld("world"), xb, yb, zb);
			
			playerSelectBleu.teleport(TpRandomBleu);
			
			for(Player pls : Teambleu){
				pls.teleport(playerSelectBleu);
			}
		}
		
		if(Teamjaune.size() != 0){
			Random rdmj = new Random();
			Player PlayerSelectJaune = Teamjaune.get(rdmj.nextInt(Teamjaune.size()));
			
			PlayerSelectJaune.teleport(PlayerSelectJaune.getWorld().getSpawnLocation());
			
			int xj = PlayerSelectJaune.getLocation().getBlockX() + rdmj.nextInt(50);
			int yj = PlayerSelectJaune.getLocation().getBlockY();
			int zj = PlayerSelectJaune.getLocation().getBlockZ() + rdmj.nextInt(50);
			Location TpRandomJaune = new Location(Bukkit.getWorld("world"), xj, yj, zj);
			
			PlayerSelectJaune.teleport(TpRandomJaune);
			
			for(Player pls : Teamjaune){
				pls.teleport(PlayerSelectJaune);
			}
		}
		
		if(Teamvert.size() != 0){
			Random rdmv = new Random();
			Player PlayerSelectVert = Teamvert.get(rdmv.nextInt(Teamvert.size()));
			
			PlayerSelectVert.teleport(PlayerSelectVert.getWorld().getSpawnLocation());
			
			int xv = PlayerSelectVert.getLocation().getBlockX() + rdmv.nextInt(1000);
			int yv = PlayerSelectVert.getLocation().getBlockY();
			int zv = PlayerSelectVert.getLocation().getBlockZ() + rdmv.nextInt(1000);
			Location TpRandomVert = new Location(Bukkit.getWorld("world"), xv, yv, zv);
			
			PlayerSelectVert.teleport(TpRandomVert);
			
			for(Player pls : Teamvert){
				pls.teleport(PlayerSelectVert);
			}
		}
		
		if(Teamlime.size() != 0){
			Random rdml = new Random();
			Player PlayerSelectLime = Teamlime.get(rdml.nextInt(Teamlime.size()));
			
			PlayerSelectLime.teleport(PlayerSelectLime.getWorld().getSpawnLocation());
			
			int xl = PlayerSelectLime.getLocation().getBlockX() + rdml.nextInt(1000);
			int yl = PlayerSelectLime.getLocation().getBlockY();
			int zl = PlayerSelectLime.getLocation().getBlockZ() + rdml.nextInt(1000);
			Location TpRandomLime = new Location(Bukkit.getWorld("world"), xl, yl, zl);
			
			PlayerSelectLime.teleport(TpRandomLime);
			
			for(Player pls : Teamlime){
				pls.teleport(PlayerSelectLime);
			}
		}
		
		if(Teambc.size() != 0){
			Random rdmbc = new Random();
			Player PlayerSelectBc = Teambc.get(rdmbc.nextInt(Teambc.size()));
		
			PlayerSelectBc.teleport(PlayerSelectBc.getWorld().getSpawnLocation());
			
			int xbc = PlayerSelectBc.getLocation().getBlockX() + rdmbc.nextInt(1000);
			int ybc = PlayerSelectBc.getLocation().getBlockY();
			int zbc = PlayerSelectBc.getLocation().getBlockZ() + rdmbc.nextInt(1000);
			Location TpRandomBC = new Location(Bukkit.getWorld("world"), xbc, ybc, zbc);
			
			PlayerSelectBc.teleport(TpRandomBC);
			
			for(Player pls : Teambc){
				pls.teleport(PlayerSelectBc);
			}
		}
		
		if(Teamcyan.size() != 0){
			Random rdmcyan = new Random();
			Player PlayerSelectCyan = Teamcyan.get(rdmcyan.nextInt(Teamcyan.size()));
			
			PlayerSelectCyan.teleport(PlayerSelectCyan.getWorld().getSpawnLocation());
			
			int xc = PlayerSelectCyan.getLocation().getBlockX() + rdmcyan.nextInt(1000);
			int yc = PlayerSelectCyan.getLocation().getBlockY();
			int zc = PlayerSelectCyan.getLocation().getBlockZ() + rdmcyan.nextInt(1000);
			Location TpRandomcyan = new Location(Bukkit.getWorld("world"), xc, yc, zc);
			
			PlayerSelectCyan.teleport(TpRandomcyan);
			
			for(Player pls : Teamcyan){
				pls.teleport(PlayerSelectCyan);
			}
		}
		
		if(Teamrose.size() != 0){
			Random rdmpink = new Random();
			Player PlayerSelectPink = Teamrose.get(rdmpink.nextInt(Teamrose.size()));
			
			PlayerSelectPink.teleport(PlayerSelectPink.getWorld().getSpawnLocation());
			
			int xp = PlayerSelectPink.getLocation().getBlockX() + rdmpink.nextInt(1000);
			int yp = PlayerSelectPink.getLocation().getBlockY();
			int zp = PlayerSelectPink.getLocation().getBlockZ() + rdmpink.nextInt(1000);
			Location TpRandomPink = new Location(Bukkit.getWorld("world"), xp, yp, zp);
			
			PlayerSelectPink.teleport(TpRandomPink);
			
			for(Player pls : Teamrose){
				pls.teleport(PlayerSelectPink);
			}
		}
		
		if(Teamviolet.size() != 0){
			Random rdmpurple = new Random();
			Player PlayerSelectPurple = Teamviolet.get(rdmpurple.nextInt(Teamviolet.size()));
			
			PlayerSelectPurple.teleport(PlayerSelectPurple.getWorld().getSpawnLocation());
			
			int xvi = PlayerSelectPurple.getLocation().getBlockX() + rdmpurple.nextInt(1000);
			int yvi = PlayerSelectPurple.getLocation().getBlockY();
			int zvi = PlayerSelectPurple.getLocation().getBlockZ() + rdmpurple.nextInt(1000);
			Location TpRandomViolet = new Location(Bukkit.getWorld("world"), xvi, yvi, zvi);
			
			PlayerSelectPurple.teleport(TpRandomViolet);
			
			for(Player pls : Teamviolet){
				pls.teleport(PlayerSelectPurple);
			}
		}
		
		if(Teamblanc.size() != 0){
			Random rdmblanc = new Random();
			Player PlayerSelectBlanc = Teamblanc.get(rdmblanc.nextInt(Teamblanc.size()));
			
			PlayerSelectBlanc.teleport(PlayerSelectBlanc.getWorld().getSpawnLocation());
			
			int xblanc = PlayerSelectBlanc.getLocation().getBlockX() + rdmblanc.nextInt(1000);
			int yblanc = PlayerSelectBlanc.getLocation().getBlockY();
			int zblanc = PlayerSelectBlanc.getLocation().getBlockZ() + rdmblanc.nextInt(1000);
			Location TpRandomBlanc = new Location(Bukkit.getWorld("world"), xblanc, yblanc, zblanc);
			
			PlayerSelectBlanc.teleport(TpRandomBlanc);
			
			for(Player pls : Teamblanc){
				pls.teleport(PlayerSelectBlanc);
			}
		}
		
		if(Teamgris.size() != 0){
			Random rdmgris = new Random();
			Player PlayerSelectGris = Teamgris.get(rdmgris.nextInt(Teamgris.size()));
			
			PlayerSelectGris.teleport(PlayerSelectGris.getWorld().getSpawnLocation());
			
			int xgris = PlayerSelectGris.getLocation().getBlockX() + rdmgris.nextInt(1000);
			int ygris = PlayerSelectGris.getLocation().getBlockY();
			int zgris = PlayerSelectGris.getLocation().getBlockZ() + rdmgris.nextInt(1000);
			Location TpRandomGris = new Location(Bukkit.getWorld("world"), xgris, ygris, zgris);
			
			PlayerSelectGris.teleport(TpRandomGris);
			
			for(Player pls : Teamgris){
				pls.teleport(PlayerSelectGris);
			}
		}
		
		if(Teamgold.size() != 0){
			Random rdmrdmgf = new Random();
			Player PlayerSelectGf = Teamgold.get(rdmrdmgf.nextInt(Teamgold.size()));
			
			PlayerSelectGf.teleport(PlayerSelectGf.getWorld().getSpawnLocation());
			
			int xgf = PlayerSelectGf.getLocation().getBlockX() + rdmrdmgf.nextInt(1000);
			int ygf = PlayerSelectGf.getLocation().getBlockY();
			int zgf = PlayerSelectGf.getLocation().getBlockZ() + rdmrdmgf.nextInt(1000);
			Location TpRandomGf = new Location(Bukkit.getWorld("world"), xgf, ygf, zgf);
			
			PlayerSelectGf.teleport(TpRandomGf);
			
			for(Player pls : Teamgold){
				pls.teleport(PlayerSelectGf);
			}
		}
		
	}
	
	
	public void setState(states state){
		current = state;
	}

	public boolean isState(states state){
		return current == state;
	}
}
