package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;

public class TestMS {
	public static void main(String[] args) {
		Jedis jedis_M = new Jedis("ip",6379);
		Jedis jedis_S = new Jedis("ip",6380);
		
		jedis_S.slaveof("ip",6379);
	
		
		jedis_M.set("class","1122V2");
		
		String result = jedis_S.get("class");
		System.out.println(result);
	}
}
