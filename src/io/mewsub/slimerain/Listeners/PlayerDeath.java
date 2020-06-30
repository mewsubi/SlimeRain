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

import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    
    @EventHandler
    public void onPlayerDeath( PlayerDeathEvent evt ) {
    	if( SlimeRain.enderCubeBar != null ) {
    		SlimeRain.enderCubeBar.removePlayer( evt.getEntity() );
    	}
    }

}