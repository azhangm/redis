package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;

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
		System.out.println(uid);
//		Jedis jedis = new Jedis("192.168.6.100");
//		获取库存


//		从池子里拿 jedis 对象
		JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
		Jedis jedis = jedisPoolInstance.getResource();


		jedis.watch(kcKey);
		String s = jedis.get(kcKey);

		if (Integer.parseInt(s) == 0) {
//				秒杀完毕
			JedisPoolUtil.release(jedisPoolInstance,jedis);

			System.err.println("秒杀失!!!");
			return false;
		}

		//		saled库存在Redis缓存扣减的时候，同时只能有一个线程得到执行

//		有库存 减 库存
//		保存抢购人名单

//	  开启事务 组队运行
		Transaction multi = jedis.multi();
//		jedis.decr(kcKey);
//		multi.append()
		multi.decr(kcKey);
		multi.sadd(cgUsers,uid);
//		jedis.sadd(cgUsers,uid);
		List<Object> exec = multi.exec();
		if (exec == null || exec.size() == 0) {
			System.err.println("秒杀失败!!!");
			JedisPoolUtil.release(jedisPoolInstance,jedis);

			return false;
		}
		System.err.println("秒杀成功");
//		jedis.close();
		JedisPoolUtil.release(jedisPoolInstance,jedis);
		return true;
	}
}
















