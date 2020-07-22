package com.atguigu.redis.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestAPI {
	public static void main(String[] args) 
	{
		Jedis jedis = new Jedis("ip",6379);
		
		//key
		System.out.println("----------key------------");
		jedis.set("k1","v1");
		jedis.set("k2","v2");
		jedis.set("k3","v3");	
		System.out.println("k3:"+jedis.get("k3"));
		
		Set<String> sets = jedis.keys("*");
		System.out.println("size:"+sets.size());
//		for(Iterator<String> iterator = sets.iterator();iterator.hasNext();) {
//		    String key = (String)iterator.next();
//		    System.out.print(key+":"+jedis.get(key)+"\t");
//		}System.out.println();
		
		System.out.println("exits:"+jedis.exists("k3"));
		System.out.println("expire:设置过期时间");
		System.out.println("ttl:"+jedis.ttl("k3"));
		System.out.println("type:"+jedis.type("k3"));
		
		//string
		System.out.println("----------String------------");
		System.out.println(jedis.get("k1"));
		jedis.append("k1", "v2");
		System.out.println(jedis.get("k1")+"\t"+jedis.getrange("k1", 0, 2)+"\t"+jedis.getrange("k1", 0, -1));
		
		jedis.mset("str1","v1","str2","v2");
		System.out.println(jedis.mget("str1","str2"));
		
		jedis.set("num1","1");
		System.out.print(jedis.get("num1")+"\t");
		jedis.incr("num1");
		System.out.print(jedis.get("num1")+"\t");
		jedis.decr("num1");
        System.out.print(jedis.get("num1")+"\t");
        jedis.incrBy("num1",3);
        System.out.print(jedis.get("num1")+"\t");
        jedis.decrBy("num1",2);
        System.out.println(jedis.get("num1")+"\t");
        
        System.out.println(jedis.getSet("num1", "num1")+"\t"+jedis.get("num1"));
		
		//list
		System.out.println("----------List------------");
		jedis.lpush("list1", ":one:");
		jedis.rpush("list1", "two:");
		List<String> list = jedis.lrange("list1", 0, -1);
		for(String temp:list) {
		    System.out.print(temp+"\t");
		}System.out.println();

		jedis.lpop("list1");
        jedis.rpop("list1");
        list = jedis.lrange("list1", 0, -1);
        for(String temp:list) {
            System.out.print(temp+"\t");
        }System.out.println();
        
        System.out.println(jedis.llen("list1")+"\t"+jedis.lindex("list1",jedis.llen("list1")-1));
        
		
		//set
		System.out.println("----------Set------------");
		jedis.sadd("set1", "set001");
		jedis.sadd("set1", "set002");
		jedis.sadd("set1", "set003");
		Set<String> set1 = jedis.smembers("set1");
        for (Iterator<String> iterator = set1.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            System.out.print(key + ":" + jedis.get(key) + "\t");
        }System.out.println();
        
        jedis.srem("set1", "set002");
        System.out.println(jedis.smembers("set1").size());
		
		//hash
		System.out.println("----------Hash------------");
		jedis.hset("hash1", "name", "zhangsan");
		System.out.println(jedis.hget("hash1", "name"));
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("sex", "man");
		map.put("telphone","13018023456");
		jedis.hmset("hash1", map);
		List<String> result = jedis.hmget("hash1", "name","sex","telphone");
		for(String temp:result) {
		    System.out.print(temp+"\t");
		}System.out.println();
		
		Map<String,String> result2 = jedis.hgetAll("hash1");
        for(Map.Entry<String, String> temp:result2.entrySet()) {
            System.out.print(temp.getKey()+":"+temp.getValue()+"\t");
        }System.out.println();
        
        System.out.println("exists:"+jedis.hexists("hash1", "name"));

		//zset
		System.out.println("----------ZSet------------");
		jedis.zadd("zset1", 70d,"v1");
		jedis.zadd("zset1", 60d,"v2");
		jedis.zadd("zset1", 80d,"v3");
		jedis.zadd("zset1", 90d,"v4");
		
		Set<String> zset1 = jedis.zrange("zset1", 0, -1);
        for (Iterator iterator = zset1.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            System.out.print(key + ":" + jedis.get(key) + "\t");
        }System.out.println();
        
	}
}
