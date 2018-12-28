package com.day2;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by rod on 2018/10/7.
 */
public class NIOClient {
    public static void main(String[] args) {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress(6666));
            while(true);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
