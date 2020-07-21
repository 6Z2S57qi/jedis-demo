package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;

public class TestMS {
	public static void main(String[] args) {
		Jedis jedis_M = new Jedis("116.62.170.132",6379);
		Jedis jedis_S = new Jedis("116.62.170.132",6380);
		
		jedis_S.slaveof("116.62.170.132",6379);
		
		jedis_M.set("class","1122V2");
		
		String result = jedis_S.get("class");
		System.out.println(result);
	}
}
