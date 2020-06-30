package io.mewsub.slimerain.Listeners;

import io.mewsub.slimerain.SlimeRain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Color;
import org.bukkit.Location;

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

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {
    
    @EventHandler
    public void onEntityDamageByEntity( EntityDamageByEntityEvent evt ) {
        Entity ent = evt.getEntity();
        Entity damager = evt.getDamager();
        List<MetadataValue> values;
        EntityType type;
        if( ent instanceof Player ) {
            if( damager instanceof Slime ) {
            	values = damager.getMetadata( "slimeType" );
            	if( !values.isEmpty() ) {
            		type = SlimeRain.entityTypes[ values.get( 0 ).asInt() ];
            		if( type == EntityType.BLAZE ) {
                        ent.setFireTicks( 140 );
                    }
            	}
            }
        }
    }

}