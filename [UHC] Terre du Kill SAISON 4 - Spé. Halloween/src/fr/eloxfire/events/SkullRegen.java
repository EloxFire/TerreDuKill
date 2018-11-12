package fr.eloxfire.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkullRegen implements Listener {
   
	@EventHandler
    public void skullClick(PlayerInteractEvent e){
       
        Player player = e.getPlayer();
        Material mat = e.getMaterial();
       
        //ON VERIFIE SI LE JOUEUR CLICK BIEN AVEC L ITEM SUR LE SOL
        if(e.getAction() == Action.RIGHT_CLICK_AIR){
           
            //SI LITEM AVEC LEQUEL IL A CLIQUER NEST PAS NULL
            if(mat != null){
                //ON VERIFIE QUE CEST UN CRANE
                if(mat == Material.SKULL_ITEM){
                    //ON SUPPRIME LE CRANE DE LINV DU JOUEUR
                	player.getInventory().remove(e.getItem());
                    //ON UPDATE LINV
                	player.updateInventory();
                    //LE JOUEUR GAGNERA EN ECHANGE DU CRANE UN BOOST DE REGEN
                	player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5*20, 2));
                	player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 120*20, 0));
                	
                	Bukkit.broadcastMessage("§c" + player.getName() + "§2 a dévoré l'âme d'un joueur");
                }
               
            }
        }
       
    }
 
}