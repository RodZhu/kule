package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by rod on 2019/1/24.
 */
public class TestConMap {
    public static void main(String[] args) {
        Map<String, String> countMap = new ConcurrentHashMap<String, String>();

        countMap.put("ddddd2", "ddddd");
        countMap.put("ddddd4", "ddddd");
        countMap.put("dddddr", "ddddd");
        Iterator<Map.Entry<String, String>> iterator = countMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
//            countMap.remove(key);
            countMap.put("ddd","feee");
        }



    }
}
