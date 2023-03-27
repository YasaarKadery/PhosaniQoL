package com.PhosanisQOL.module.spore;

import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;
import java.awt.geom.Area;
import java.util.Map;

import static net.runelite.api.Perspective.getCanvasTileAreaPoly;
import static net.runelite.api.Perspective.getCanvasTilePoly;


public class PhosanisQOLSporeOverlay extends Overlay {

    private final PhosanisQOLSpore phosanisQOLSpore;

    private final Client client;

    @Inject
    public PhosanisQOLSporeOverlay(PhosanisQOLSpore phosanisQOLSpore, Client client) {
        this.phosanisQOLSpore = phosanisQOLSpore;
        this.client = client;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if(!phosanisQOLSpore.isInFight())
        {
            return null;
        }
        renderSpores(graphics);
        return null;
    }

    private void renderSpores(final Graphics2D graphics2D) {
        if (phosanisQOLSpore.getSpores().size() < 1) {
            return;
        }

        Area tiles = new Area();
        for(Map.Entry<LocalPoint,GameObject> entry: phosanisQOLSpore.getSpores().entrySet())
        {

            WorldPoint worldPoint = entry.getValue().getWorldLocation();
            if (worldPoint == null) {
                continue;
            }
            LocalPoint lp = LocalPoint.fromWorld(client,worldPoint);

            if (lp == null)
            {
                continue;
            }

            Polygon polygon = getCanvasTileAreaPoly(client,lp,3,3,3,50);
            if (polygon == null) {
                continue;
            }
            OverlayUtil.renderPolygon(graphics2D, polygon, new Color(0,255,255,90), new Color(0,255,255,50),
                    new BasicStroke(2));
        }





    }
}
