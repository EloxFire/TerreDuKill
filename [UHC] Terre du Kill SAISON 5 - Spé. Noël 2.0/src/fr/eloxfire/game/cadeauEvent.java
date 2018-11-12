package fr.eloxfire.game;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class cadeauEvent implements Listener {

	public static void gift() {

		// CREATION DE L'ITEM KDO BOOK P2
		ItemStack p2 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta p2m = p2.getItemMeta();
		p2m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		p2m.setDisplayName("§5Cadeau de Noël");
		p2.setItemMeta(p2m);

		// CREATION DE L'ITEM KDO DIAMANDS
		ItemStack d = new ItemStack(Material.DIAMOND, 8);
		ItemMeta dm = d.getItemMeta();
		dm.setDisplayName("§5Cadeau de Noël");
		d.setItemMeta(dm);

		// CREATION DE L'ITEM KDO POMME
		ItemStack p = new ItemStack(Material.GOLDEN_APPLE, 3);
		ItemMeta pm = p.getItemMeta();
		pm.setDisplayName("§5Cadeau de Noël");
		p.setItemMeta(pm);

		// CREATION DE L'ITEM KDO TROLL
		ItemStack s = new ItemStack(Material.STICK, 1);
		s.setDurability((short) 1);
		ItemMeta sm = s.getItemMeta();
		sm.addEnchant(Enchantment.KNOCKBACK, 10, true);
		sm.setDisplayName("§5Cadeau de Noël");
		s.setItemMeta(sm);

		// ON VERIF LE STATU DE JEU ET LE TIMER
		if (main.getInstance().isState(states.GAME) && Timers.GENE == 1200) {
			// POUR TOUS LES PLAYERS ENCORE EN VIE ON SELECTIONNE UN ITEM ET ON LEUR DONNE
			// COMME KDO DE NOEL
			Random r = new Random();
			int nbr = r.nextInt(3);

			for (Player players : setGame.ready) {
				if (nbr == 0) {
					players.sendMessage("§6§l[T.D.K]§2 Vous venez de reçevoir un cadeau de noël !");
					players.getInventory().addItem(p2);
					players.updateInventory();
				} else if (nbr == 1) {
					players.sendMessage("§6§l[T.D.K]§2 Vous venez de reçevoir un cadeau de noël !");
					players.getInventory().addItem(d);
					players.updateInventory();
				} else if (nbr == 2) {
					players.sendMessage("§6§l[T.D.K]§2 Vous venez de reçevoir un cadeau de noël !");
					players.getInventory().addItem(p);
					players.updateInventory();
				} else if (nbr == 3) {
					players.sendMessage("§6§l[T.D.K]§2 Vous venez de reçevoir un cadeau de noël !");
					players.getInventory().addItem(s);
					players.updateInventory();
				}
			}
		}

	}

}
