package com.PhosanisQOL.module.spore;

import com.PhosanisQOL.module.Module;
import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Singleton
public class PhosanisQOLSpore implements Module {
    //hole: 1767 , spore: 37739 A:8631
    private final int SPORE_ID = 37739; //37738
    private NPC pnm;
    @Getter(AccessLevel.PACKAGE)
    private boolean inFight;
    public PhosanisQOLSpore(){
        inFight = false;
    }

    @Getter(AccessLevel.PACKAGE)
    private final Map<LocalPoint, GameObject> spores = new HashMap<>();

    @Inject
    EventBus eventBus;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    PhosanisQOLSporeOverlay phosanisQOLSporeOverlay;

    @Override
    public void start() {
        eventBus.register(this);
        overlayManager.add(phosanisQOLSporeOverlay);

    }

    @Override
    public void stop() {
        eventBus.unregister(this);
        overlayManager.remove(phosanisQOLSporeOverlay);
    }


    @Subscribe
    private void onGameObjectSpawned(final GameObjectSpawned event) {
        GameObject gameObject = event.getGameObject();
        int id = gameObject.getId();
        if (id == SPORE_ID || id == 37738)
        {
            spores.put(gameObject.getLocalLocation(), gameObject);
        }

    }

    @Subscribe
    public void onGameObjectDespawned(GameObjectDespawned event) {
        GameObject gameObject = event.getGameObject();
        int id = gameObject.getId();

        if (id == SPORE_ID || id == 37738)
        {
            spores.remove(gameObject.getLocalLocation());
        }


    }
    @Subscribe
    public void onAnimationChanged(AnimationChanged event) {
        Actor actor = event.getActor();
        if(!(actor instanceof NPC))
        {
            return;
        }
        NPC npc = (NPC) actor;

        if(pnm == null && npc.getName() != null && npc.getName().equalsIgnoreCase("Phosani's Nightmare"))
        {
            spores.clear();
            pnm = npc;
            inFight = true;
        }

        if(!inFight || !npc.equals(pnm))
        {
            return;
        }
    }
    @Subscribe
    public void onGameStateChanged(GameStateChanged event) {
        GameState gameState = event.getGameState();
        if (gameState == GameState.LOADING && inFight)
        {
            inFight = false;
            pnm = null;
        }
    }
}
