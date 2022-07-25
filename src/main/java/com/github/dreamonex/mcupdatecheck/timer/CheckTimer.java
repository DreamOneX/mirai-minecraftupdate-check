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
        public void run() {
            freshGroups();
            checkMCRelease();
        }
    }
    public CheckTimer() {
        this.timer = new Timer();
    }

    public void go() {
        this.timer.schedule(new CheckMCTask(), 1000, 300000);
    }
}
