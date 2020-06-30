package io.mewsub.slimerain.Listeners;

import io.mewsub.slimerain.SlimeRain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage implements Listener {
    
    @EventHandler
    public void onEntityDamage( EntityDamageEvent evt ) {
        Entity ent = evt.getEntity();
        if( ent instanceof Slime ) {
        	List<MetadataValue> values = ent.getMetadata( "slimeType" );
        	if( !values.isEmpty() ) {
        		EntityType type = SlimeRain.entityTypes[ values.get( 0 ).asInt() ];
        		DamageCause cause = evt.getCause();
        		if( type == EntityType.ENDERMAN ) {
        			if( cause == DamageCause.PROJECTILE ) {
        				evt.setCancelled( true );
        			}
        			int randX = ThreadLocalRandom.current().nextInt( -32, 33 );
        			int randZ = ThreadLocalRandom.current().nextInt( -32, 33 );
        			Location newLoc = ent.getLocation().clone();
        			newLoc.add( randX, 0, randZ );
        			newLoc.setY( ent.getWorld().getHighestBlockYAt( newLoc.getBlockX(), newLoc.getBlockZ() ) );
        			ent.teleport( newLoc );
        		} else if( type == EntityType.ENDER_DRAGON ) {
        			if( cause == DamageCause.VOID ) {
        				evt.setCancelled( true );
                        ent.teleport( new Location( ent.getWorld(), 0, 100, 0 ) );
        			}
                    LivingEntity dragon = ( LivingEntity ) ent;
                    AttributeInstance health = dragon.getAttribute( Attribute.GENERIC_MAX_HEALTH );
                    if( SlimeRain.enderCubeBar != null ) {
                        SlimeRain.enderCubeBar.setProgress( dragon.getHealth() / health.getBaseValue() );
                    }
        		}
        		if( cause == DamageCause.FALL ) {
        			evt.setCancelled( true );
        		}
        	}
        }
    }

}