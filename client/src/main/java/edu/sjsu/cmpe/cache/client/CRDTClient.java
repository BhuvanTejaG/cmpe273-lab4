package edu.sjsu.cmpe.cache.client;

import java.util.HashMap;
import java.util.Map;

public class CRDTClient {
    public static void readOnRepair(CacheServiceInterface param1, CacheServiceInterface param2, 
    		CacheServiceInterface param3) throws Exception {
    	CacheServiceInterface cache1  = param1;
    	CacheServiceInterface cache2  = param2;
    	CacheServiceInterface cache3  = param3;
    	
        long key = 1;
        String value = "a";
        
        cache1.put(key, value);
        cache2.put(key, value);
        cache3.put(key, value);
        
        System.out.println("Placing value a in all servers");
        Thread.sleep(30000);
        
        cache1.get(1);
	    cache2.get(1);
	    cache3.get(1);
	        
	    System.out.println("Getting value a from all servers");
	    
	    Thread.sleep(1000);
	    
	    System.out.println("Value from Server_A is::: " + cache1.getValue());
	    System.out.println("Value from Server_B is::: " + cache2.getValue());
	    System.out.println("Value from Server_C is::: " + cache3.getValue());
        
        value = "b";
        
        System.out.println("Placing value b in all servers");
        
        cache1.put(key, value);
        cache2.put(key, value);
        cache3.put(key, value);
        
        
        Thread.sleep(30000);
	        
	    cache1.get(1);
	    cache2.get(1);
	    cache3.get(1);
	        
	    System.out.println("Getting value b from all servers");
	    Thread.sleep(1000);
	    
	    System.out.println("Value from Server A is::: " + cache1.getValue());
	    System.out.println("Value from Server B is::: " + cache2.getValue());
	    System.out.println("Value from Server C is::: " + cache3.getValue());
	        
	    String[] values = {cache1.getValue(), cache2.getValue(), cache3.getValue()};
	    
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    String majority = null;
	    for (String val : values) {
	        Integer count = map.get(val);	        
	        map.put(val, count != null ? count+1 : 1);
	        if (map.get(val) > values.length / 2) {
	        	majority = val;
	        	break;
	        }	
	    }
	    
	    cache1.put(key, majority);
        cache2.put(key, majority);
        cache3.put(key, majority);
        
        System.out.println("***Correction of value b in all servers***");
	    Thread.sleep(1000);
	    
	    cache1.get(key);
        cache2.get(key);
        cache3.get(key);
        
        System.out.println("***Fetching value b after correction***");
	    Thread.sleep(1000);
	    
	    System.out.println("Value from Server_A is::: " + cache1.getValue());
	    System.out.println("Value from Server_B is::: " + cache2.getValue());
	    System.out.println("Value from Server_C is::: " + cache3.getValue());
    }
}
