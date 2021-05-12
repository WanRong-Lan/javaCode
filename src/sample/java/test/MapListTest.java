package sample.java.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapListTest {
    public static Map<String,String> creadMap(String name,String key){
        Map<String,String> map = new HashMap<String,String>();
        int len = (int)(Math.random()*10+1);
        for(int i=0;i<=len;i++){
            map.put(name+i,key+len+i);
        }
        return map;
    }

    public static void main(String[] args) {
        Map<String,String> map = creadMap("name","key");
        for(Map.Entry<String,String> item:map.entrySet()){
            System.out.println(item.getKey()+":"+item.getValue());
        }
        Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> item = it.next();
            System.out.println(item.getKey()+":"+item.getValue());
        }
    }
}
