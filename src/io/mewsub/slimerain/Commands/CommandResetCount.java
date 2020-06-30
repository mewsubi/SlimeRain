package io.mewsub.slimerain.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import io.mewsub.slimerain.SlimeRain;

public class CommandResetCount implements CommandExecutor {

	@Override
	public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
		SlimeRain.spawned_monsters = 0;
        SlimeRain.spawned_animals = 0;
        SlimeRain.spawned_water_animals = 0;
        SlimeRain.spawned_ambient = 0;
		return true;
	}
	
}