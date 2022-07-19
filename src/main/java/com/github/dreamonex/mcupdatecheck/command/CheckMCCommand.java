package com.github.dreamonex.mcupdatecheck.command;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JCompositeCommand;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

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
			e.printStackTrace();
        }
	}
}
