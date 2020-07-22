package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;

public class TestPing {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("ip",6379);
		System.out.println(jedis.ping());
	}
}
