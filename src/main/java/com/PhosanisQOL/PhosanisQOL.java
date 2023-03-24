package com.PhosanisQOL;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "Phosani's QoL"
)
public class PhosanisQOL extends Plugin
{

	private static final String NEED_SANFEW = "Impregnate";
	@Inject
	private Client client;

	@Inject
	private PhosanisQOLConfig config;

	@Inject
	private Notifier notifier;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	PhosanisQOLOverlay overlay;

	@Override
	protected void startUp() throws Exception
	{

	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
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

	@Provides
	PhosanisQOLConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PhosanisQOLConfig.class);
	}
}
