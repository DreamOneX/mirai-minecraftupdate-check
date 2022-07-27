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

package com.github.dreamonex.mcupdatecheck.utils;

import java.util.List;
import java.util.Map;

import com.github.dreamonex.mcupdatecheck.data.SubscribeData;

import net.mamoe.mirai.console.data.Value;

public class DataManager {
    public static Map<Long, List<CheckType>> getGroups() {
        Value<Map<Long, List<CheckType>>> plugindata = SubscribeData.INSTANCE.groups;
        return plugindata.get();
    }
    public static void setGroups(Map<Long, List<CheckType>> data) {
        Value<Map<Long, List<CheckType>>> plugindata = SubscribeData.INSTANCE.groups;
        plugindata.set(data);
    }
    public static String getLatestMinecraftRelease() {
        Value<String> plugindata = SubscribeData.INSTANCE.latestMinecraftRelease;
        return plugindata.get();
    }
    public static void setLatestMinecraftRelease(String data) {
        Value<String> plugindata = SubscribeData.INSTANCE.latestMinecraftRelease;
        plugindata.set(data);
    }
    public static String getLatestMinecraftSnapshot() {
        Value<String> plugindata = SubscribeData.INSTANCE.latestMinecraftSnapshot;
        return plugindata.get();
    }
    public static void setLatestMinecraftSnapshot(String data) {
        Value<String> plugindata = SubscribeData.INSTANCE.latestMinecraftSnapshot;
        plugindata.set(data);
    }
    public static Boolean getFirstRun() {
        Value<Boolean> plugindata = SubscribeData.INSTANCE.firstRun;
        return plugindata.get();
    }
    public static void setFirstRun(Boolean data) {
        Value<Boolean> plugindata = SubscribeData.INSTANCE.firstRun;
        plugindata.set(data);
    }
}
