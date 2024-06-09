package org.punkcraft.oneworld;

import org.spongepowered.api.network.ChannelBuf;
import org.spongepowered.api.network.Message;

public class TestMessage implements Message {
    private String string;

    public TestMessage(String string) {
        this.string = string;
    }

    @Override
    public void readFrom(ChannelBuf buf) {
        this.string = buf.readString();
    }

    @Override
    public void writeTo(ChannelBuf buf) {
        buf.writeString(this.string);
    }

    public String getContent() {
        return string;
    }

    public void setContent(String content) {
        this.string = content;
    }
}
