package me.manudev.authenticsc;

import com.google.inject.Inject;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.command.CommandSource;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

@Plugin(id = "authenticsc", name = "AuthenticSc", version = "1.0", authors = {"Manuelih"})
public class AuthenticSc {

    private final ProxyServer server;
    private final Path dataFolder;
    private Config config;

    @Inject
    public AuthenticSc(ProxyServer server, Path dataFolder) {
        this.server = server;
        this.dataFolder = dataFolder;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        loadConfig();
        server.getCommandManager().register("staffchat", new StaffChatCommand(server, config));
        server.getCommandManager().register("sc", new StaffChatCommand(server, config));
    }

    private void loadConfig() {
        try {
            if (!Files.exists(dataFolder)) {
                Files.createDirectories(dataFolder);
            }

            Path configFile = dataFolder.resolve("config.yml");

            if (!Files.exists(configFile)) {
                try (InputStream in = getClass().getResourceAsStream("/config.yml")) {
                    if (in != null) {
                        Files.copy(in, configFile);
                    }
                }
            }

            Yaml yaml = new Yaml();
            try (InputStream input = Files.newInputStream(configFile)) {
                Map<String, Object> data = yaml.load(input);
                this.config = new Config(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class StaffChatCommand implements SimpleCommand {

        private final ProxyServer server;
        private final Config config;
        private final LegacyComponentSerializer legacy = LegacyComponentSerializer.legacyAmpersand();

        public StaffChatCommand(ProxyServer server, Config config) {
            this.server = server;
            this.config = config;
        }

        @Override
        public void execute(Invocation invocation) {
            CommandSource source = invocation.source();
            String[] args = invocation.arguments();

            if (!(source instanceof Player)) {
                source.sendMessage(legacy.deserialize(config.onlyPlayersMessage));
                return;
            }

            if (args.length == 0) {
                source.sendMessage(legacy.deserialize(config.usageMessage));
                return;
            }

            Player player = (Player) source;
            String serverName = player.getCurrentServer()
                    .flatMap(s -> Optional.ofNullable(s.getServerInfo().getName()))
                    .orElse("N/A");

            String message = String.join(" ", args);

            String formattedMessage = config.format
                    .replace("{server}", serverName)
                    .replace("{player}", player.getUsername())
                    .replace("{message}", message);

            Component colored = legacy.deserialize(formattedMessage);

            server.getAllPlayers().stream()
                    .filter(p -> p.hasPermission(config.permission))
                    .forEach(p -> p.sendMessage(colored));
        }
    }

    public static class Config {
        public final String permission;
        public final String format;
        public final String onlyPlayersMessage;
        public final String usageMessage;

        public Config(Map<String, Object> data) {
            this.permission = (String) data.getOrDefault("permission", "authenticsc.use");
            this.format = (String) data.getOrDefault("format", "&7[&bStaffChat&7] &8[{server}] &e{player} &f: {message}");

            Map<String, String> messages = (Map<String, String>) data.getOrDefault("messages", Map.of());
            this.onlyPlayersMessage = messages.getOrDefault("only-players", "&cQuesto comando può essere usato solo da un giocatore.");
            this.usageMessage = messages.getOrDefault("usage", "&cUtilizzo: /staffchat <messaggio> oppure /sc <messaggio>");
        }
    }
}
