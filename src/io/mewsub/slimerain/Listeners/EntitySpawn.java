package io.mewsub.slimerain.Listeners;

import io.mewsub.slimerain.SlimeRain;

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

import org.bukkit.metadata.FixedMetadataValue;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener {
    
    @EventHandler
    public void onEntitySpawn( EntitySpawnEvent evt ) {
        Entity ent = evt.getEntity();
        EntityType type = ent.getType();
        int size = 0;
        boolean slime = false;
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
        if( slime ) {
            evt.setCancelled( true );
            int players = SlimeRain.server.getOnlinePlayers().size();

            int count = 0;
            int limit = 2;
            if( spawned == 0 ) {
                count = SlimeRain.spawned_animals;
                limit = SlimeRain.ANIMALS;
            } else if( spawned == 1 ) {
                count = SlimeRain.spawned_ambient;
                limit = SlimeRain.AMBIENT;
            } else if( spawned == 2 ) {
                count = SlimeRain.spawned_water_animals;
                limit = SlimeRain.WATER_ANIMALS;
            } else if( spawned == 3 ) {
                count = SlimeRain.spawned_monsters;
                limit = SlimeRain.MONSTERS;
            }
            boolean allow = true;
            if( count + 1 > limit * players ) {
                allow = false;
                if( spawned == 0 ) SlimeRain.spawned_animals = limit * players;
                else if( spawned == 1 ) SlimeRain.spawned_ambient = limit * players;
                else if( spawned == 2 ) SlimeRain.spawned_water_animals = limit * players;
                else if( spawned == 3 ) SlimeRain.spawned_monsters = limit * players;
            }
            if( allow ) {
                if( spawned == 0 ) SlimeRain.spawned_animals += 1;
                else if( spawned == 1 ) SlimeRain.spawned_ambient += 1;
                else if( spawned == 2 ) SlimeRain.spawned_water_animals += 1;
                else if( spawned == 3 ) SlimeRain.spawned_monsters += 1;

                EntityType slimeType = EntityType.SLIME;
                switch( type ) {
                    case BLAZE:
                    case PIG_ZOMBIE:
                    case GHAST:
                    case WITHER_SKELETON:
                    case ENDER_DRAGON:
                        slimeType = EntityType.MAGMA_CUBE;
                }

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

                Slime slimeEnt = ( Slime ) ( ent.getWorld().spawnEntity( ent.getLocation(), slimeType ) );
                slimeEnt.setSize( size + 1 );
                slimeEnt.setGliding( true );

                LivingEntity liveEnt = ( LivingEntity ) ent;

                AttributeInstance entHealthAttr = liveEnt.getAttribute( Attribute.GENERIC_MAX_HEALTH );
                AttributeInstance slimeHealthAttr = slimeEnt.getAttribute( Attribute.GENERIC_MAX_HEALTH );
                slimeHealthAttr.setBaseValue( entHealthAttr.getBaseValue() );
                slimeEnt.setHealth( entHealthAttr.getBaseValue() );

                AttributeInstance entDmgAttr = liveEnt.getAttribute( Attribute.GENERIC_ATTACK_DAMAGE );
                AttributeInstance slimeDmgAttr = slimeEnt.getAttribute( Attribute.GENERIC_ATTACK_DAMAGE );
                if( slimeDmgAttr != null && entDmgAttr != null ) {
                    slimeDmgAttr.setBaseValue( entDmgAttr.getBaseValue() );
                }

                AttributeInstance entRangeAttr = liveEnt.getAttribute( Attribute.GENERIC_FOLLOW_RANGE );
                AttributeInstance slimeRangeAttr = slimeEnt.getAttribute( Attribute.GENERIC_FOLLOW_RANGE );
                if( slimeRangeAttr != null && entRangeAttr != null ) {
                    slimeRangeAttr.setBaseValue( entRangeAttr.getBaseValue() );
                }

                AttributeInstance entSpeedAttr = liveEnt.getAttribute( Attribute.GENERIC_MOVEMENT_SPEED );
                AttributeInstance slimeSpeedAttr = slimeEnt.getAttribute( Attribute.GENERIC_MOVEMENT_SPEED );
                slimeSpeedAttr.setBaseValue( entSpeedAttr.getBaseValue() );
                if( slimeSpeedAttr != null && entSpeedAttr != null ) {
                    slimeSpeedAttr.setBaseValue( entSpeedAttr.getBaseValue() );
                }

                if( ent instanceof Mob ) {
                    Mob mob = ( Mob ) ent;
                    Mob slimeMob = ( Mob ) slimeEnt;
                    slimeMob.setLootTable( mob.getLootTable() );
                }

                if( type == EntityType.ENDER_DRAGON ) {
                    slimeEnt.setRemoveWhenFarAway( false );
                    liveEnt.setHealth( 0 );
                    evt.setCancelled( false );
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
            }
        }
    }

}