package io.mewsub.slimerain.Listeners;

import io.mewsub.slimerain.SlimeRain;

import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.entity.EntityToggleGlideEvent;

public class EntityToggleGlide implements Listener {
    
    @EventHandler
    public void onEntityToggleGlide( EntityToggleGlideEvent evt ) {
        Entity ent = evt.getEntity();
        if( ent instanceof Slime ) {
        	List<MetadataValue> values = ent.getMetadata( "slimeType" );
    		if( !values.isEmpty() ) {
    			EntityType type = SlimeRain.entityTypes[ values.get( 0 ).asInt() ];

    			boolean gliding = false;
		    	switch( type ) {
		    		case CHICKEN:
		    		case BAT:
		    		case BEE:
		    		case PARROT:
		    		case VEX:
		    		case PHANTOM:
		    		case BLAZE:
		    		case GHAST:
		    		//case ENDER_DRAGON:
		        		gliding = true;
		    	}
	            LivingEntity liveEnt = ( LivingEntity ) ent;
	            if( gliding ) {
	            	liveEnt.setGliding( true );
	            	evt.setCancelled( true );
	            }
    		}
        }
    }

}