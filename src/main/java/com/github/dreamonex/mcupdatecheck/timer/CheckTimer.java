// Mirai Minecraft Update Checker
// This file is part of Mirai Minecraft Update Checker
//  Copyright (C) 2022 DreamOneX <me@dreamonex.eu.org> and contributors
//
//  Mirai Minecraft Update Checker is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Affero General Public License as
//  published by the Free Software Foundation, either version 3 of the
//  License, or any later version.
//
//  Mirai Minecraft Update Checker is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Affero General Public License for more details.
//
//  You should have received a copy of the GNU Affero General Public License
//  along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.github.dreamonex.mcupdatecheck.timer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.github.dreamonex.mcupdatecheck.MCUpdateCheckMain;
import com.github.dreamonex.mcupdatecheck.check.MinecraftCheckHelper;
import com.github.dreamonex.mcupdatecheck.utils.CheckType;
import com.github.dreamonex.mcupdatecheck.utils.DataManager;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

public class CheckTimer {
    private Timer timer = new Timer();
    private class CheckMCTask extends TimerTask {
        private Map<Long, List<CheckType>> groups = DataManager.getGroups();
        private Map<Long, List<CheckType>> freshGroups() {
            return DataManager.getGroups();
        }
        private void checkMCRelease() {
            String latestold = DataManager.getLatestMinecraftRelease();
            String latest;
            try {
                latest = MinecraftCheckHelper.getVersion(CheckType.MC_RELEASE);
            } catch (IOException e) {
                MCUpdateCheckMain.INSTANCE.getLogger().error("疑似网络问题", e);
                return;
            }
            if (latestold.equals(latest)) return;
            DataManager.setLatestMinecraftRelease(latest);
            for (Bot bot : MCUpdateCheckMain.INSTANCE.getBots()) {
                for (Map.Entry<Long, List<CheckType>> group : this.groups.entrySet()) {
                    Group target = bot.getGroup(group.getKey());
                    if (target == null) continue;
                    if (group.getValue().contains(CheckType.MC_RELEASE)) {
                        MessageChain chain = new MessageChainBuilder()
                            .append("Bugjang 发布了 MC 新的 Release 版本")
                            .append("版本号为: " + latest)
                            .build();
                        target.sendMessage(chain);
                    }
                }
            }
        }
        private void checkMCSnapshot() {
            String latestold = DataManager.getLatestMinecraftSnapshot();
            String latest;
            try {
                latest = MinecraftCheckHelper.getVersion(CheckType.MC_SNAPSHOT);
            } catch (IOException e) {
                MCUpdateCheckMain.INSTANCE.getLogger().error("疑似网络问题", e);
                return;
            }
            if (latestold.equals(latest)) return;
            DataManager.setLatestMinecraftSnapshot(latest);
            for (Bot bot : MCUpdateCheckMain.INSTANCE.getBots()) {
                for (Map.Entry<Long, List<CheckType>> group : this.groups.entrySet()) {
                    Group target = bot.getGroup(group.getKey());
                    if (target == null) continue;
                    if (group.getValue().contains(CheckType.MC_SNAPSHOT)) {
                        MessageChain chain = new MessageChainBuilder()
                            .append("Bugjang 发布了 MC 新的 Snapshot 版本")
                            .append("版本号为: " + latest)
                            .build();
                        target.sendMessage(chain);
                    }
                }
            }
        }
        public void run() {
            freshGroups();
            checkMCRelease();
            checkMCSnapshot();
        }
    }
    public CheckTimer() {
        this.timer = new Timer();
    }

    public void go() {
        this.timer.schedule(new CheckMCTask(), 1000, 300000);
    }

    public void stop() {
        this.timer.cancel();
    }
}
