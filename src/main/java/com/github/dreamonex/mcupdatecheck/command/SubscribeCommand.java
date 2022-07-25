// Mirai Minecraft Update Checker
// 	Copyright (C) 2022 DreamOneX
//
// 	This program is free software: you can redistribute it and/or modify
// 	it under the terms of the GNU Affero General Public License as
// 	published by the Free Software Foundation, either version 3 of the
// 	License, or any later version.
//
// 	This program is distributed in the hope that it will be useful,
// 	but WITHOUT ANY WARRANTY; without even the implied warranty of
// 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// 	GNU Affero General Public License for more details.
//
// 	You should have received a copy of the GNU Affero General Public License
// 	along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.github.dreamonex.mcupdatecheck.command;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JCompositeCommand;

import java.util.Map;
import java.util.List;

import com.github.dreamonex.mcupdatecheck.utils.CheckType;
import com.github.dreamonex.mcupdatecheck.utils.DataManager;
import com.github.dreamonex.mcupdatecheck.MCUpdateCheckMain;

public final class SubscribeCommand extends JCompositeCommand {
    public static final SubscribeCommand INSTANCE = new SubscribeCommand();

    private SubscribeCommand() {
        super(MCUpdateCheckMain.INSTANCE, "subupdate");
        setDescription("您关注的美女MC更新了");
    }

    @SubCommand("minecraft")
    public void onCommandMinecraft(final CommandContext context, CheckType type) {
        Long id = context.getSender().getSubject().getId();
        Map<Long, List<CheckType>> data = DataManager.getGroups();
        if (data.get(id) != null) {
            data.get(id).add(type);
        } else {
            data.put(id, List.of(type));
        }
        DataManager.setGroups(data);
    }
}
