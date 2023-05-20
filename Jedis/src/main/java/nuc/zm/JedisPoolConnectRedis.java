package nuc.zm;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * jedis连接池,
 *
 * @author zm
 * @date 2023/05/20
 */
public class JedisPoolConnectRedis {
    private static final JedisPool jedisPool;

    static {
        // 连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大连接数 默认8
        jedisPoolConfig.setMaxTotal(5);
        // 最大空闲数量
        jedisPoolConfig.setMaxIdle(5);
        // 最小空闲数量
        jedisPoolConfig.setMinIdle(0);
        // 设置等待时间 1000 ms
        jedisPoolConfig.setMaxWait(Duration.ofMillis(1000));

        // 初始化 jedis pool 对象
        jedisPool = new JedisPool(jedisPoolConfig,"192.168.66.101",6379,100,"zhangmeng*0504");
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
