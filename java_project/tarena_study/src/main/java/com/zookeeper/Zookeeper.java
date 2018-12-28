package com.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by rod on 2018/11/28.
 */
public class Zookeeper {

    ZooKeeper zk;

    @Before
    public void testcreateZKClient() throws Exception{
        final CountDownLatch cout = new CountDownLatch(1);
        zk = new ZooKeeper("10.199.175.55:2181", 3000, new Watcher() {
            public void process(WatchedEvent event) {
                if (event.getState().equals(Watcher.Event.KeeperState.SyncConnected)) {
                    System.out.println("创建成功zk");
                    cout.countDown();
                }
            }
        });
        cout.await();
    }

    /**
     * 创建节点
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception{
        String s = zk.create("/pade12dederk01euu", "test park 01".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取数据
     * @throws Exception
     */
    @Test
    public void getData() throws Exception{
        byte[] data = zk.getData("/park01", null, null);
        System.out.println(new String(data));
    }



    @Test
    public void setData() throws Exception {
        // 第三个参数必须和节点version一致。或者 -1，表示忽略版本号更新
        zk.setData("/park01", "park01 set data".getBytes(), 0);
    }

    @Test
    public void delet() throws Exception {
        // 第三个参数必须和节点version一致。或者 -1，表示忽略版本号更新
        zk.delete("/park01", -1);
    }

    @Test
    public void getChild() throws Exception {
        // 第三个参数必须和节点version一致。或者 -1，表示忽略版本号更新
        List<String> children = zk.getChildren("/park01", null);
        System.out.println(Arrays.toString(children.toArray()));
    }

    @Test
    public void watcher() throws Exception {
        for(;;) {
            final CountDownLatch lock = new CountDownLatch(1);
            Stat stat = new Stat();
            // 获取变化之前的数据
            byte[] gett = zk.getData("/park01", new Watcher() {
                public void process(WatchedEvent event) {
                    if (event.getType().equals(Event.EventType.NodeDataChanged)) {
                        System.out.println("数据发生改变");
                        // 第二次调用getdata方法获取变化之后的数据。
                        try {
                            zk.getData("/park01", null, null);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        lock.countDown();
                    }
                }
            }, stat);
            System.out.println("===> " + new String(gett));
            lock.await();
        }
    }

}
