package com.zhy.middleware.netty.protocoldesign.customer.message;

public class PingMessage extends Message {
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
