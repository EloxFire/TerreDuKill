package fr.eloxfire.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Team implements Listener {
	
	//LISTE DE TOUTES LES TEAMS -- CHIANT ET LONG MAIS EFFICACE AU MOIN
	public List<Player> red = new ArrayList<Player>();
	public List<Player> purple = new ArrayList<Player>();
	public List<Player> blue = new ArrayList<Player>();
	public List<Player> cyan = new ArrayList<Player>();
	public List<Player> green = new ArrayList<Player>();
	public List<Player> yellow = new ArrayList<Player>();
	public List<Player> grey = new ArrayList<Player>();
	public List<Player> lime = new ArrayList<Player>();
	public List<Player> pink = new ArrayList<Player>();
	public List<Player> orange = new ArrayList<Player>();
	public List<Player> white = new ArrayList<Player>();
	public List<Player> Lgrey = new ArrayList<Player>();
	
	
	
	@EventHandler
	public void onJoinTeam(PlayerCommandPreprocessEvent e){
		
		Player p = e.getPlayer();

		String msg = e.getMessage();
		String[] args = msg.split(" ");
		
		
		if(args[0].equalsIgnoreCase("/teamlist")){
			p.sendMessage("§6[T.D.K.] §2Voici la liste des Teams :");
			p.sendMessage("§6Red " + red.size() + "§2/§65" + "§2 / " + "§6purple " + purple.size() + "§2/§65" + "§2 / " + "§6blue" + blue.size() + "§2/§65" + "§6cyan" + cyan.size() + "§2/§65");
			p.sendMessage("§6green " + green.size() + "§2/§65" + "§2 / " + "§6yellow " + yellow.size() + "§2/§65" + "§2 / " + "§6grey" + grey.size() + "§2/§65" + "§6lime" + lime.size() + "§2/§65");
			p.sendMessage("§6pink " + pink.size() + "§2/§65" + "§2 / " + "§6orange " + orange.size() + "§2/§65" + "§2 / " + "§6white" + white.size() + "§2/§65" + "§6Light Grey" + Lgrey.size() + "§2/§65");
			p.sendMessage("§2Fait §6/[nom de la team] §2pour rejoindre une team !");
			e.setCancelled(true);
		}
		
		if(args[0].equalsIgnoreCase("/changeteam")){
			red.remove(p);
			purple.remove(p);
			blue.remove(p);
			cyan.remove(p);
			green.remove(p);
			yellow.remove(p);
			grey.remove(p);
			lime.remove(p);
			pink.remove(p);
			orange.remove(p);
			white.remove(p);
			Lgrey.remove(p);
			p.sendMessage("§6[T.D.K.] §2Vous avez été retiré de votre team !");
			
			e.setCancelled(true);
		}
		

		if(args[0].equalsIgnoreCase("/red")){
			if(purple.contains(p) || blue.contains(p) || cyan.contains(p) || green.contains(p) || yellow.contains(p) || grey.contains(p) || lime.contains(p) || pink.contains(p) || orange.contains(p) || white.contains(p) || Lgrey.contains(p)){
				red.remove(p);
				p.sendMessage("§4Vous etes déjà dans une team !");
			if(!purple.contains(p) || !blue.contains(p) || !cyan.contains(p) || !green.contains(p) || !yellow.contains(p) || !grey.contains(p) || !lime.contains(p) || !pink.contains(p) || !orange.contains(p) || !white.contains(p) || !Lgrey.contains(p)){
			p.sendMessage("§6[T.D.K.] §2Vous avez rejoinds les §4rouges §2 !");
			red.add(p);
			}
			}
			e.setCancelled(true);
		}
		
	}
	
}
