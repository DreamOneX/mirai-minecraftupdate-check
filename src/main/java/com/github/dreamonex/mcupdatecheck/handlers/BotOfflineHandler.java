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

package com.github.dreamonex.mcupdatecheck.handlers;

import com.github.dreamonex.mcupdatecheck.MCUpdateCheckMain;

import net.mamoe.mirai.event.events.BotOfflineEvent;

public class BotOfflineHandler {
    public static void handle(BotOfflineEvent event) {
        MCUpdateCheckMain.INSTANCE.removeBot(event.getBot());
        MCUpdateCheckMain.INSTANCE.getLogger()
            .info(String.format("已从Bot列表移除Bot %id", event.getBot().getId()));
    }
}
