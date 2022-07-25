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
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import java.io.IOException;

import com.github.dreamonex.mcupdatecheck.MCUpdateCheckMain;
import com.github.dreamonex.mcupdatecheck.check.MinecraftCheckHelper;
import com.github.dreamonex.mcupdatecheck.utils.CheckType;

public final class CheckMCCommand extends JCompositeCommand {
    public static final CheckMCCommand INSTANCE = new CheckMCCommand();

    private CheckMCCommand() {
        super(MCUpdateCheckMain.INSTANCE, "checkver");
        setDescription("检查版本");
    }

    @SubCommand("minecraft")
    public void onCommandMinecraft(CommandContext context, CheckType type) {
        try {
            String version = MinecraftCheckHelper.getVersion(type);
            context.getSender().sendMessage("Minecraft最新版本为" + version);
        } catch (IllegalArgumentException e) {
            MessageChain chain = new MessageChainBuilder()
            .append("参数错误")
            .append(e.getMessage())
            .build();
            context.getSender().sendMessage(chain);
            MCUpdateCheckMain.INSTANCE.getLogger().error(e);
        } catch (IOException e) {
            MessageChain chain = new MessageChainBuilder()
                .append("醒醒你网可能炸啦")
                .append(e.getMessage())
                .build();
            context.getSender().sendMessage(chain);
            MCUpdateCheckMain.INSTANCE.getLogger().error(e);
        }
    }
}
