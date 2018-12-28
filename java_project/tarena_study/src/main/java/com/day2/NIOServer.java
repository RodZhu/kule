package com.day2;

import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Set;

/**
 * Created by rod on 2018/10/6.
 */
public class NIOServer {
    public static void main(String[] args) {
        try{

            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(6666));
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while(true) {
                int select = selector.select();
                //获取时间集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for(SelectionKey key : selectionKeys) {
                    if (key.isAcceptable()) {
                        ServerSocketChannel sscc = (ServerSocketChannel)key.channel();
                        SocketChannel accept = null;
                        while(accept == null) {
                            accept = sscc.accept();
                        }
                        accept.configureBlocking(false);
                        System.out.println("current id:  " + Thread.currentThread().getId());
                        accept.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                    if (key.isReadable()){
                        SocketChannel sc = (SocketChannel) key.channel();
//                        sc.read()
                    }
                    selectionKeys.remove(key);
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
