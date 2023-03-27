package com.PhosanisQOL;

import com.PhosanisQOL.module.panel.PhosanisQOLPanelOverlay;
import com.PhosanisQOL.module.spore.PhosanisQOLSpore;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import com.PhosanisQOL.module.panel.PhosanisQOLPanel;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Phosani's QoL"
)
public class PhosanisQOL extends Plugin
{


	private static final int VARBIT_PNM = 12401;
	@Inject
	private Client client;

	@Inject
	private PhosanisQOLConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ClientThread clientThread;

	@Inject
	PhosanisQOLPanelOverlay overlay;

	@Inject
	PhosanisQOLPanel phosanisQOLPanel;

	@Inject
	PhosanisQOLSpore phosanisQOLSpore;



	@Override
	protected void startUp() throws Exception
	{
		phosanisQOLPanel.start();
		phosanisQOLSpore.start();
	}

	@Override
	protected void shutDown() throws Exception
	{

		phosanisQOLPanel.stop();
		phosanisQOLSpore.stop();
	}






	@Provides
	PhosanisQOLConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PhosanisQOLConfig.class);
	}
}
