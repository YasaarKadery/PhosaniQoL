package com.PhosanisQOL.module.panel;

import com.PhosanisQOL.module.Module;
import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class PhosanisQOLPanel implements Module {
    private static final String NEED_SANFEW = "Impregnate";
    @Inject
    private OverlayManager overlayManager;
    @Inject
    PhosanisQOLPanelOverlay overlay;

    @Inject
    private EventBus eventBus;

    @Override
    public void start() {
        eventBus.register(this);
    }
    @Override
    public void stop() {
        eventBus.unregister(this);
    }

    @Subscribe
    public void onChatMessage(ChatMessage event) {
        Pattern pattern = Pattern.compile(NEED_SANFEW,Pattern.CASE_INSENSITIVE);
        Pattern end = Pattern.compile("weakened",Pattern.CASE_INSENSITIVE);

        final String msg = event.getMessage();
        if(event.getType() == ChatMessageType.GAMEMESSAGE)
        {
            Matcher matcher = pattern.matcher(msg.toString());
            if (matcher.find()){
                //add overlay
                overlayManager.add(overlay);


            }
            Matcher endMatcher = end.matcher(msg.toString());
            if (endMatcher.find()) {
                overlayManager.remove(overlay);
            }

        }

    }
}
