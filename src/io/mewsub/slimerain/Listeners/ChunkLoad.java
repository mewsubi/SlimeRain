package io.mewsub.slimerain.Listeners;

import io.mewsub.slimerain.SlimeRain;

import java.util.List;

import org.bukkit.Chunk;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoad implements Listener {
    
    @EventHandler
    public void onChunkLoad( ChunkLoadEvent evt ) {
        EntityType type;
        int size;
        boolean slime;
        EntityType slimeType;
        boolean gliding;
        Slime slimeEnt;
        LivingEntity liveEnt;
        AttributeInstance entHealthAttr;
        AttributeInstance slimeHealthAttr;
        AttributeInstance entDmgAttr;
        AttributeInstance slimeDmgAttr;
        AttributeInstance entRangeAttr;
        AttributeInstance slimeRangeAttr;
        AttributeInstance entSpeedAttr;
        AttributeInstance slimeSpeedAttr;
        Mob mob;
        Mob slimeMob;
        for( Entity ent : evt.getChunk().getEntities() ) {
            if( !( ent instanceof Slime ) ) {
                type = ent.getType();
                size = 0;
                slime = false;
                switch( type ) {
                    case CHICKEN: /* SIZE 0 MOBS */
                    case BAT:
                    case BEE:
                    case COD:
                    case ENDERMITE:
                    case PARROT:
                    case PUFFERFISH:
                    case RABBIT:
                    case SILVERFISH:
                    case TROPICAL_FISH:
                    case VEX:
                        slime = true;
                        size = 0;
                        break;
                    case PIG: /* SIZE 1 MOBS */
                    case CAT:
                    case SPIDER:
                    case CAVE_SPIDER:
                    case FOX:
                    case GUARDIAN:
                    case OCELOT:
                    case SALMON:
                    case SHULKER:
                        slime = true;
                        size = 1;
                        break;
                    case COW: /* SIZE 2 MOBS */
                    case SHEEP:
                    case MUSHROOM_COW:
                    case PHANTOM:
                    case SQUID:
                    case TURTLE:
                    case WOLF:
                        slime = true;
                        size = 2;
                        break;
                    case BLAZE: /* SIZE 3 MOBS */
                    case CREEPER:
                    case DOLPHIN:
                    case EVOKER:
                    case DROWNED:
                    case DONKEY:
                    case HUSK:
                    case MULE:
                    case PILLAGER:
                    case SKELETON:
                    case STRAY:
                    case VILLAGER:
                    case VINDICATOR:
                    case WANDERING_TRADER:
                    case ZOMBIE_VILLAGER:
                    case PIG_ZOMBIE:
                    case ZOMBIE:
                        slime = true;
                        size = 3;
                        break;
                    case HORSE: /* SIZE 4 MOBS */
                    case LLAMA:
                    case PANDA:
                    case POLAR_BEAR:
                    case SKELETON_HORSE:
                    case TRADER_LLAMA:
                    case WITCH:
                    case ZOMBIE_HORSE:
                        slime = true;
                        size = 4;
                        break;
                    case ENDERMAN: /* SIZE 5 MOBS */
                    case ELDER_GUARDIAN:
                    case WITHER_SKELETON:
                        slime = true;
                        size = 5;
                        break;
                    case RAVAGER: /* SIZE 6 MOBS */
                        slime = true;
                        size = 6;
                        break;
                    case GHAST: /* SIZE 10 MOBS */
                        slime = true;
                        size = 10;
                        break;
                    case ENDER_DRAGON: /* SIZE 28 MOBS */
                        slime = true;
                        size = 28;
                        break;
                }
                if( slime ) {

                    slimeType = EntityType.SLIME;
                    switch( type ) {
                        case BLAZE:
                        case PIG_ZOMBIE:
                        case GHAST:
                        case WITHER_SKELETON:
                        case ENDER_DRAGON:
                            slimeType = EntityType.MAGMA_CUBE;
                    }

                    gliding = false;
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

                    slimeEnt = ( Slime ) ( ent.getWorld().spawnEntity( ent.getLocation(), slimeType ) );
                    slimeEnt.setSize( size + 1 );
                    slimeEnt.setGliding( true );

                    liveEnt = ( LivingEntity ) ent;

                    entHealthAttr = liveEnt.getAttribute( Attribute.GENERIC_MAX_HEALTH );
                    slimeHealthAttr = slimeEnt.getAttribute( Attribute.GENERIC_MAX_HEALTH );
                    slimeHealthAttr.setBaseValue( entHealthAttr.getBaseValue() );
                    slimeEnt.setHealth( entHealthAttr.getBaseValue() );

                    entDmgAttr = liveEnt.getAttribute( Attribute.GENERIC_ATTACK_DAMAGE );
                    slimeDmgAttr = slimeEnt.getAttribute( Attribute.GENERIC_ATTACK_DAMAGE );
                    if( slimeDmgAttr != null && entDmgAttr != null ) {
                        slimeDmgAttr.setBaseValue( entDmgAttr.getBaseValue() );
                    }

                    entRangeAttr = liveEnt.getAttribute( Attribute.GENERIC_FOLLOW_RANGE );
                    slimeRangeAttr = slimeEnt.getAttribute( Attribute.GENERIC_FOLLOW_RANGE );
                    if( slimeRangeAttr != null && entRangeAttr != null ) {
                        slimeRangeAttr.setBaseValue( entRangeAttr.getBaseValue() );
                    }

                    entSpeedAttr = liveEnt.getAttribute( Attribute.GENERIC_MOVEMENT_SPEED );
                    slimeSpeedAttr = slimeEnt.getAttribute( Attribute.GENERIC_MOVEMENT_SPEED );
                    slimeSpeedAttr.setBaseValue( entSpeedAttr.getBaseValue() );
                    if( slimeSpeedAttr != null && entSpeedAttr != null ) {
                        slimeSpeedAttr.setBaseValue( entSpeedAttr.getBaseValue() );
                    }

                    if( ent instanceof Mob ) {
                        mob = ( Mob ) ent;
                        slimeMob = ( Mob ) slimeEnt;
                        slimeMob.setLootTable( mob.getLootTable() );
                    }

                    if( type == EntityType.ENDER_DRAGON ) {
                        slimeEnt.setRemoveWhenFarAway( false );
                        liveEnt.setHealth( 0 );
                        SlimeRain.enderCubeBar = SlimeRain.server.createBossBar( "Ender Cube", BarColor.RED, BarStyle.SOLID );
                        SlimeRain.enderCubeBar.addFlag( BarFlag.CREATE_FOG );
                        SlimeRain.enderCubeBar.addFlag( BarFlag.DARKEN_SKY );
                        SlimeRain.enderCubeBar.addFlag( BarFlag.PLAY_BOSS_MUSIC );
                        for( Player p : slimeEnt.getWorld().getPlayers() ) {
                            SlimeRain.enderCubeBar.addPlayer( p );
                        }
                        SlimeRain.enderCubeBar.setVisible( true );
                    }

                    slimeEnt.setMetadata( "slimeType", new FixedMetadataValue( SlimeRain.plugin, type.ordinal() ) );
                    ent.remove();
                }
            }
        }
    }
}