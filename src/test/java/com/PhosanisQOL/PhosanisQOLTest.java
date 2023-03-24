package com.PhosanisQOL;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PhosanisQOLTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PhosanisQOL.class);
		RuneLite.main(args);
	}
}