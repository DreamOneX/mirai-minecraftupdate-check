// Mirai Minecraft Update Checker
//  Copyright (C) 2022 DreamOneX
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Affero General Public License as
//  published by the Free Software Foundation, either version 3 of the
//  License, or any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Affero General Public License for more details.
//
//  You should have received a copy of the GNU Affero General Public License
//  along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.github.dreamonex.mcupdatecheck;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotOfflineEvent;
import net.mamoe.mirai.event.events.BotOnlineEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.dreamonex.mcupdatecheck.check.MinecraftCheckHelper;
import com.github.dreamonex.mcupdatecheck.command.CheckMCCommand;
import com.github.dreamonex.mcupdatecheck.command.SubscribeCommand;
import com.github.dreamonex.mcupdatecheck.data.SubscribeData;
import com.github.dreamonex.mcupdatecheck.handlers.BotOfflineHandler;
import com.github.dreamonex.mcupdatecheck.handlers.BotOnlineHandler;
import com.github.dreamonex.mcupdatecheck.timer.CheckTimer;
import com.github.dreamonex.mcupdatecheck.utils.CheckType;
import com.github.dreamonex.mcupdatecheck.utils.DataManager;

public final class MCUpdateCheckMain extends JavaPlugin {
    public static final MCUpdateCheckMain INSTANCE = new MCUpdateCheckMain();
    private List<Bot> bots = new ArrayList<>();

    private MCUpdateCheckMain() {
        super(
            new JvmPluginDescriptionBuilder(
                "com.github.dreamonex.mcupdatecheck",
                "0.0.1"
            )
            .name("MinecraftUpdateChecker")
            .author("DreamOnex")
            .info("Minecraft Update Checker")
            .build());
    }

    public void addBot(Bot bot) {
        if (!this.bots.contains(bot)) {
            this.bots.add(bot);
        }
    }

    public void removeBot(Bot bot) {
        if (this.bots.contains(bot)) {
            this.bots.remove(bot);
        }
    }

    public List<Bot> getBots() {
        return this.bots;
    }

    private void checkFirstRun() {
        if (DataManager.getFirstRun()) {
            try {
                DataManager.setLatestMinecraftRelease(
                    MinecraftCheckHelper.getVersion(CheckType.MC_RELEASE)
                );
                DataManager.setLatestMinecraftSnapshot(
                    MinecraftCheckHelper.getVersion(CheckType.MC_SNAPSHOT)
                );
            } catch (IOException e) {
                getLogger().error(e);
                getLogger().error("我怎么连不上网了呜呜呜");
                checkFirstRun();
            }
            DataManager.setFirstRun(false);
        }
    }
    @Override
    public void onEnable() {
        CommandManager.INSTANCE.registerCommand(CheckMCCommand.INSTANCE, false);
        CommandManager.INSTANCE.registerCommand(SubscribeCommand.INSTANCE, false);
        reloadPluginData(SubscribeData.INSTANCE);
        checkFirstRun();
        CheckTimer checker = new CheckTimer();
        GlobalEventChannel.INSTANCE.subscribeOnce(
            BotOnlineEvent.class,
            event -> {
                checker.go();
            }
        );
        GlobalEventChannel.INSTANCE.subscribeAlways(
            BotOnlineEvent.class,
            BotOnlineHandler::handle
        );
        GlobalEventChannel.INSTANCE.subscribeAlways(
            BotOfflineEvent.class,
            BotOfflineHandler::handle
        );
    }
}
