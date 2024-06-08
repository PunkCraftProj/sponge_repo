package org.punkcraft.oneworld;

import org.spongepowered.api.network.ChannelBuf;
import org.spongepowered.api.network.Message;

public class TestMessage implements Message {
    private String content;

    public TestMessage() {
        this.content = "Это тестовое сообщение";
    }

    @Override
    public void readFrom(ChannelBuf buf) {
        this.content = buf.readString();
    }

    @Override
    public void writeTo(ChannelBuf buf) {
        buf.writeString(this.content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
