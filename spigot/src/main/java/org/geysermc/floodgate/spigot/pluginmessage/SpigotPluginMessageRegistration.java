/*
 * Copyright (c) 2019-2023 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Floodgate
 */

package org.geysermc.floodgate.spigot.pluginmessage;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.geysermc.floodgate.core.pluginmessage.PluginMessageChannel;
import org.geysermc.floodgate.core.pluginmessage.PluginMessageRegistration;

@Singleton
public class SpigotPluginMessageRegistration implements PluginMessageRegistration {
    @Inject JavaPlugin plugin;

    @Override
    public void register(PluginMessageChannel channel) {
        Messenger messenger = plugin.getServer().getMessenger();

        messenger.registerIncomingPluginChannel(
                plugin,
                channel.getIdentifier(),
                (channel1, player, message) ->
                        channel.handleServerCall(message, player.getUniqueId(), player.getName()));

        messenger.registerOutgoingPluginChannel(plugin, channel.getIdentifier());
    }
}
