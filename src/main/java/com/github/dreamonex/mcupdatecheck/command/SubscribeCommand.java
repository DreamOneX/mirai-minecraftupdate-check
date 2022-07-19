package com.github.dreamonex.mcupdatecheck.command;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JCompositeCommand;
import net.mamoe.mirai.console.data.Value;

import java.util.Map;
import java.util.List;

import com.github.dreamonex.mcupdatecheck.MCUpdateCheckMain;
import com.github.dreamonex.mcupdatecheck.utils.CheckType;
import com.github.dreamonex.mcupdatecheck.data.SubscribeData;

public class SubscribeCommand extends JCompositeCommand {
    public static final SubscribeCommand INSTANCE = new SubscribeCommand();
    private SubscribeCommand() {
        super(MCUpdateCheckMain.INSTANCE, "subupdate");
        setDescription("订阅更新提醒");
    }

    @SubCommand("minecraft")
    public void onCommandMinecraft(CommandContext context, CheckType type) {
        Long id = context.getSender().getSubject().getId();
        Value<Map<Long,List<CheckType>>> plugindata = SubscribeData.INSTANCE.groups;
        Map<Long,List<CheckType>> data = plugindata.get();
        if (data.get(id) != null) {
            data.get(id).add(type);
        } else {
            data.put(id, List.of(type));
        }
        plugindata.set(data);
    }
}
