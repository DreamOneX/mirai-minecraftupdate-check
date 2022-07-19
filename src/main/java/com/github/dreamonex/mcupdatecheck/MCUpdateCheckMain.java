package com.github.dreamonex.mcupdatecheck;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

import com.github.dreamonex.mcupdatecheck.command.CheckMCCommand;
import com.github.dreamonex.mcupdatecheck.command.SubscribeCommand;
import com.github.dreamonex.mcupdatecheck.data.SubscribeData;

public final class MCUpdateCheckMain extends JavaPlugin {
    public static final MCUpdateCheckMain INSTANCE = new MCUpdateCheckMain();
    private MCUpdateCheckMain() {
        super(new JvmPluginDescriptionBuilder("com.github.dreamonex.mcupdatecheck", "0.0.1")
                .name("MinecraftUpdateChecker")
				.author("DreamOnex")
                .info("Minecraft Update Checker")
                .build());
    }

    @Override
    public void onEnable() {
		CommandManager.INSTANCE.registerCommand(CheckMCCommand.INSTANCE, false);
        CommandManager.INSTANCE.registerCommand(SubscribeCommand.INSTANCE, false);
        reloadPluginData(SubscribeData.INSTANCE);
    }
}
