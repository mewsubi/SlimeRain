package io.mewsub.slimerain.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntity implements Listener {
    
    @EventHandler
    public void onPlayerInteractEntity( PlayerInteractEntityEvent evt ) {
        Player player = evt.getPlayer();
        Entity e = evt.getRightClicked();
    }

}