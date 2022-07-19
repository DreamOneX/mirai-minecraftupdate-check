package com.github.dreamonex.mcupdatecheck.check;

import java.net.URL;
import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.github.dreamonex.mcupdatecheck.utils.CheckType;

public class MinecraftCheckHelper {
  public static String getVersion(CheckType ver) {
    URL url;
    try {
      url = new URL("https://launchermeta.mojang.com/mc/game/version_manifest.json");
    } catch (Exception e) {
      e.printStackTrace();
      return "failed";
    }
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode;
    try {
      jsonNode = objectMapper.readTree(url);
    } catch (IOException e) {
      e.printStackTrace();
      return "failed";
    }
    JsonNode node = jsonNode.get("latest");
	switch(ver) {
		case MC_RELEASE:
			return node.get("release").asText();
		case MC_SNAPSHOT:
			return node.get("snapshot").asText();
		default:
			throw new IllegalArgumentException("Invalid check type");
	}
  }
}
