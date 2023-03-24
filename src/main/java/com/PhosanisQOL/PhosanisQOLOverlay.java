package com.PhosanisQOL;

import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

import java.awt.*;

public class PhosanisQOLOverlay extends OverlayPanel {
    private final Client client;

    @Inject
    private PhosanisQOLOverlay(Client client) {
        this.client = client;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        panelComponent.getChildren().clear();

        panelComponent.getChildren().add((LineComponent.builder())
                .left("You need to drink a sanfew")
                .build());

        if (client.getGameCycle() % 40 >= 20)
        {
            panelComponent.setBackgroundColor(new Color(255, 0, 0, 150));
        } else
        {
            panelComponent.setBackgroundColor(new Color(70, 61, 50, 150));
        }

        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        return panelComponent.render(graphics);

    }

}
