package io.mewsub.slimerain;

import org.bukkit.plugin.Plugin;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Server;

import org.bukkit.boss.BossBar;

import org.bukkit.entity.EntityType;

import io.mewsub.slimerain.Commands.CommandResetCount;

import io.mewsub.slimerain.Listeners.ChunkLoad;
import io.mewsub.slimerain.Listeners.ChunkUnload;
import io.mewsub.slimerain.Listeners.EntityDamage;
import io.mewsub.slimerain.Listeners.EntityDamageByEntity;
import io.mewsub.slimerain.Listeners.EntityDeath;
import io.mewsub.slimerain.Listeners.EntitySpawn;
import io.mewsub.slimerain.Listeners.EntityToggleGlide;
import io.mewsub.slimerain.Listeners.PlayerDeath;
import io.mewsub.slimerain.Listeners.PlayerInteractEntity;
import io.mewsub.slimerain.Listeners.PlayerPortal;
import io.mewsub.slimerain.Listeners.SlimeSplit;

public class SlimeRain extends JavaPlugin {

    public static Plugin plugin;
    public static Server server;

    public static final EntityType entityTypes[] = EntityType.values();

    public static int spawned_monsters = 0;
    public static int spawned_animals = 0;
    public static int spawned_water_animals = 0;
    public static int spawned_ambient = 0;

    public static final int MONSTERS = 70;
    public static final int ANIMALS = 15;
    public static final int WATER_ANIMALS = 5;
    public static final int AMBIENT = 15;

    public static BossBar enderCubeBar = null;

    @Override
    public void onEnable() {
        this.plugin = ( Plugin ) this;
        this.server = this.getServer();
        this.server.getPluginManager().registerEvents( new ChunkLoad(), this );
        this.server.getPluginManager().registerEvents( new ChunkUnload(), this );
        this.server.getPluginManager().registerEvents( new EntityDamage(), this );
        this.server.getPluginManager().registerEvents( new EntityDamageByEntity(), this );
        this.server.getPluginManager().registerEvents( new EntityDeath(), this );
        this.server.getPluginManager().registerEvents( new EntitySpawn(), this );
        this.server.getPluginManager().registerEvents( new EntityToggleGlide(), this );
        this.server.getPluginManager().registerEvents( new PlayerDeath(), this );
        this.server.getPluginManager().registerEvents( new PlayerInteractEntity(), this );
        this.server.getPluginManager().registerEvents( new PlayerPortal(), this );
        this.server.getPluginManager().registerEvents( new SlimeSplit(), this );

        SlimeRain.spawned_monsters = 0;
        SlimeRain.spawned_animals = 0;
        SlimeRain.spawned_water_animals = 0;
        SlimeRain.spawned_ambient = 0;

        this.getCommand( "slimereset" ).setExecutor( new CommandResetCount() );
    }

    @Override
    public void onDisable() {

    }

}
