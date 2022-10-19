package com.atguigu.redis;

import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 *
 */
public class SecKill_redis {

	public static void main(String[] args) {
		Jedis jedis =new Jedis("192.168.6.100",6379);
		System.out.println(jedis.ping());
		jedis.close();
	}

	//秒杀过程
	public static boolean doSecKill(String uid,String prodid) throws IOException {
//		是否为空 ？ 验证空参

//		拼接 redis 中保存的key
		String kcKey = "sk:" + prodid + ":qt";
		String cgUsers = "sk:" + prodid + ":user";

		Jedis jedis = new Jedis("192.168.6.100");
//		获取库存
		String s = jedis.get(kcKey);
		if (Integer.parseInt(s) == 0) {
//				秒杀完毕
			System.err.println("秒杀完毕,");
			return false;
		}

//		有库存 减 库存
//		保存抢购人名单


		jedis.decr(kcKey);
		jedis.sadd(cgUsers,uid);
		System.err.println("成功");
		return true;
	}
}
















