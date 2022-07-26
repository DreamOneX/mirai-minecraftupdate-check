// Mirai Minecraft Update Checker
// This file is part of Mirai Minecraft Update Checker
// 	Copyright (C) 2022 DreamOneX
//
// 	Mirai Minecraft Update Checker is free software: you can redistribute it and/or modify
// 	it under the terms of the GNU Affero General Public License as
// 	published by the Free Software Foundation, either version 3 of the
// 	License, or any later version.
//
// 	Mirai Minecraft Update Checker is distributed in the hope that it will be useful,
// 	but WITHOUT ANY WARRANTY; without even the implied warranty of
// 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// 	GNU Affero General Public License for more details.
//
// 	You should have received a copy of the GNU Affero General Public License
// 	along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.github.dreamonex.mcupdatecheck.data;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.github.dreamonex.mcupdatecheck.utils.CheckType;

import net.mamoe.mirai.console.data.Value;
import net.mamoe.mirai.console.data.java.JavaAutoSavePluginData;


public final class SubscribeData extends JavaAutoSavePluginData {
    public static final SubscribeData INSTANCE = new SubscribeData();

    public final Value<Boolean> firstRun = value("FirstRun", true);
    public final Value<String> latestMinecraftRelease = value("LatestMinecraftRelease", "1.19");
    public final Value<String> latestMinecraftSnapshot = value("LatestMinecraftSnapshot", "11w45a");
    public final Value<Map<Long,List<CheckType>>> groups = typedValue(
                "groups",
    createKType(Map.class, createKType(Long.class), createKType(List.class, createKType(CheckType.class))), new HashMap<Long, List<CheckType>>() {
        {
            put(114514114514L, List.of(CheckType.MC_RELEASE, CheckType.MC_SNAPSHOT));
        }
    }
            );

    private SubscribeData() {
        super("SubscribeData");
    }
}
