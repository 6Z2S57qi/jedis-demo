package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;

public class TestPing {
	public static void main(String[] args) 
	{
		Jedis jedis = new Jedis("116.62.170.132",6379);
		System.out.println(jedis.ping());
	}
}
