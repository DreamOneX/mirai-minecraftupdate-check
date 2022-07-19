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
