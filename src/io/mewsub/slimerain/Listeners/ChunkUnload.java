package io.mewsub.slimerain.Listeners;

import io.mewsub.slimerain.SlimeRain;

import java.util.List;

import org.bukkit.Chunk;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkUnload implements Listener {
    
    @EventHandler
    public void onChunkUnload( ChunkUnloadEvent evt ) {
        for( Entity ent : evt.getChunk().getEntities() ) {
            if( ent instanceof Slime ) {
                List<MetadataValue> values = ent.getMetadata( "slimeType" );
                if( !values.isEmpty() ) {
                    EntityType type = SlimeRain.entityTypes[ values.get( 0 ).asInt() ];

                    byte spawned = 0;
                    switch( type ) {
                        case CHICKEN:
                        case BEE:
                        case PARROT:
                        case RABBIT:
                        case PIG:
                        case CAT:
                        case FOX:
                        case OCELOT:
                        case COW:
                        case SHEEP:
                        case MUSHROOM_COW:
                        case WOLF:
                        case DONKEY:
                        case MULE:
                        case VILLAGER:
                        case WANDERING_TRADER:
                        case HORSE:
                        case LLAMA:
                        case PANDA:
                        case POLAR_BEAR:
                        case SKELETON_HORSE:
                        case TRADER_LLAMA:
                        case ZOMBIE_HORSE:
                            spawned = 0;
                            break;
                        case BAT:
                            spawned = 1;
                            break;
                        case COD:
                        case PUFFERFISH:
                        case TROPICAL_FISH:
                        case SALMON:
                        case SQUID:
                        case TURTLE:
                        case DOLPHIN:
                            spawned = 2;
                            break;
                        case ENDERMITE:
                        case SILVERFISH:
                        case VEX:
                        case SPIDER:
                        case CAVE_SPIDER:
                        case GUARDIAN:
                        case SHULKER:
                        case PHANTOM:
                        case BLAZE:
                        case CREEPER:
                        case EVOKER:
                        case DROWNED:
                        case HUSK:
                        case PILLAGER:
                        case SKELETON:
                        case STRAY:
                        case VINDICATOR:
                        case ZOMBIE_VILLAGER:
                        case PIG_ZOMBIE:
                        case ZOMBIE:
                        case WITCH:
                        case ENDERMAN:
                        case ELDER_GUARDIAN:
                        case WITHER_SKELETON:
                        case GHAST:
                        case RAVAGER:
                            spawned = 3;
                            break;
                    }
                    if( spawned == 0 && SlimeRain.spawned_animals >= 1 ) SlimeRain.spawned_animals -= 1;
                    else if( spawned == 1 && SlimeRain.spawned_ambient >= 1 ) SlimeRain.spawned_ambient -= 1;
                    else if( spawned == 2 && SlimeRain.spawned_water_animals >= 1 ) SlimeRain.spawned_water_animals -= 1;
                    else if( spawned == 3 && SlimeRain.spawned_monsters >= 1 ) SlimeRain.spawned_monsters -= 1;
                }
            }
        }
    }
}