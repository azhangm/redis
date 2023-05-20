package nuc.zm.redistemplate;

import nuc.zm.redistemplate.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedistemplateApplicationTests {


    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void testString() {
        // set (obj,obj) 底层自动序列化
        redisTemplate.opsForValue().set("name","zm");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
    @Test
    void testSaveUser(){
        redisTemplate.opsForValue().set("user:1",new User(1,"zm",18));
        User o = ((User) redisTemplate.opsForValue().get("user:1"));
        System.out.println(o);
    }

}
