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

public class CheckTimer {
    private Timer timer = new Timer();
    private class CheckMCTask extends TimerTask {
        private Map<Long, List<CheckType>> groups = DataManager.getGroups();
        public void run() {
            String latestold = DataManager.getLatestMinecraftRelease();
            String latest;
            try {
                latest = MinecraftCheckHelper.getVersion(CheckType.MC_RELEASE);
            } catch (IOException e) {
                MCUpdateCheckMain.INSTANCE.getLogger().error("疑似网络问题", e);
                return;
            }
            if (latestold.equals(latest)) return;
            for (Bot bot : MCUpdateCheckMain.INSTANCE.getBots()) {
                for (Map.Entry<Long, List<CheckType>> group : this.groups.entrySet()) {
                    if (group.getValue().contains(CheckType.MC_RELEASE)) {
                        bot.getGroup(group.getKey()).sendMessage("mojang 更新了");
                        //TODO: more details and more checkers
                    }
                }
            }
        }
    }
}
