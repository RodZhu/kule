package com.day01;


import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Channel {

    @Test
    public void testAccept() {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(8888);
            ssc.configureBlocking(false);
            ssc.bind(inetSocketAddress);
            SocketChannel accept = null;
            while (accept == null) {
                accept = ssc.accept();
            }
            ByteBuffer bb = ByteBuffer.allocate(10);
            //accept.configureBlocking(false);
            accept.read(bb);
            System.out.println("====> " + new String(bb.array()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnect() {
        SocketChannel sc = null;
        try {
            sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("localhost", 8888));
            while (!sc.isConnected()) {
                sc.finishConnect();
            }
            ByteBuffer allocate = ByteBuffer.wrap("010101dddd".getBytes("utf-8"));
            sc.write(allocate);
            System.out.println(" connect ssuccess");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
