package io.mewsub.slimerain.Listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.entity.SlimeSplitEvent;

public class SlimeSplit implements Listener {
    
    @EventHandler
    public void onSlimeSplit( SlimeSplitEvent evt ) {
        evt.setCancelled( true );
    }

}