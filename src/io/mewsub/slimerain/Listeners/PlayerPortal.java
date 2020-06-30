package io.mewsub.slimerain.Listeners;

import io.mewsub.slimerain.SlimeRain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World.Environment;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerPortal implements Listener {
    
    @EventHandler
    public void onPlayerPortal( PlayerPortalEvent evt ) {
    	if( SlimeRain.enderCubeBar != null ) {
    		if( evt.getTo().getWorld().getEnvironment() == Environment.THE_END ) {
    			SlimeRain.enderCubeBar.addPlayer( evt.getPlayer() );
                SlimeRain.enderCubeBar.setVisible( true );
    		}
    	}
    }

}