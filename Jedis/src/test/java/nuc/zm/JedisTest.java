package nuc.zm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    Jedis jedis = null;
    @Before
    public void testInit() {
//    初始化 Jedis 客户端、声明主机和端口
        jedis = new Jedis("192.168.66.101",6379);
        jedis.auth("zhangmeng*0504");
//      ping pong 心跳机制 检测是否连接成功
        String ping = jedis.ping();
        System.out.println("连接成功 ~" + ping);
    }


    @Test
    public void testString(){
        String set = jedis.set("username", "zhangsan");
        System.out.println(set);
        String username = jedis.get("username");
        System.out.println(username);

        // 插入
        jedis.set("nuc:user:1","zhangsan");
        // 获取
        String s = jedis.get("nuc:user:1");
        System.out.println(s);
    }



    @After
    public void close(){
        if (jedis != null) {
            jedis.close();
        }
    }
}
