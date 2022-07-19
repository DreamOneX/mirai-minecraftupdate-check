package com.github.dreamonex.mcupdatecheck.data;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.github.dreamonex.mcupdatecheck.utils.CheckType;

import net.mamoe.mirai.console.data.java.JavaAutoSavePluginData;
import net.mamoe.mirai.console.data.Value;


public class SubscribeData extends JavaAutoSavePluginData {
	public static final SubscribeData INSTANCE = new SubscribeData();

	public final Value<String> latestMinecraftRelease = value("latestMinecraftRelease", "1.19");
	public final Value<String> latestMinecraftSnapshot = value("latestMinecraftSnapshot", "11w45a");
	public final Value<Map<Long,List<CheckType>>> groups = typedValue(
			"groups",
			createKType(Map.class, createKType(Long.class), createKType(List.class, createKType(CheckType.class))), new HashMap<Long, List<CheckType>>() {{
				put(114514114514L, List.of(CheckType.MC_RELEASE, CheckType.MC_SNAPSHOT));
			}}
			);

	private SubscribeData() {
		super("SubscribeData");
	}
}

