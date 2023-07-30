package com.zhy.middleware.netty.protocoldesign.customer.message;

public class PongMessage extends Message {
    @Override
    public int getMessageType() {
        return PongMessage;
    }
}
