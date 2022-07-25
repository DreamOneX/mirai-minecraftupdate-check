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

import net.mamoe.mirai.event.events.BotOnlineEvent;

public class BotOnlineHandler {
    public static void handle(BotOnlineEvent event) {
        MCUpdateCheckMain.INSTANCE.addBot(event.getBot());
        MCUpdateCheckMain.INSTANCE.getLogger()
            .info(String.format("已添加智能寄器人 %d进入Bot列表", event.getBot().getId()));
    }
}
