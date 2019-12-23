package com.jeiker.redis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SpringBootRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        String StringKey = "test:string1";
        redisTemplate.opsForValue().set(StringKey, "value1");
        Object object = redisTemplate.opsForValue().get(StringKey);

        System.out.println(object.toString());
        Assert.isTrue("value1".equals(object), "string 值不正确");

    }

    @Test
    public void testHash() {
        String hashName = "test:map1";
        redisTemplate.opsForHash().put(hashName, "key1", "value1");
        Object object = redisTemplate.opsForHash().get(hashName, "key1");

        System.out.println(object.toString());
        Assert.isTrue("value1".equals(object), "hash 值不正确");
    }

    @Test
    public void testHashPutAll() {
        String hashName = "test:map2";
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        redisTemplate.opsForHash().putAll(hashName, map);
        Object object = redisTemplate.opsForHash().get(hashName, "key1");

        System.out.println(object.toString());
        Assert.isTrue("value1".equals(object), "hash put all 值不对。");
    }

    @Test
    public void testList() {
        String listKey = "test:list1";
        redisTemplate.opsForList().leftPush(listKey, "value1");
        redisTemplate.opsForList().leftPush(listKey, "value1");
        Object object = redisTemplate.opsForList().rightPop(listKey);

        System.out.println(object.toString());
        Assert.isTrue("value1".equals(object), "hash put all 值不对。");
    }


    @Test
    public void testSet() {
        String setKey = "test:set1";
        redisTemplate.opsForSet().add(setKey, "value1");
        redisTemplate.opsForSet().add(setKey, "value2");
        Object object = redisTemplate.opsForSet().members(setKey);

        System.out.println(object.toString());
        Assert.isTrue("value1".equals(object), "hash put all 值不对。");
    }

    @Test
    public void testZSet() {
        String zsetKey = "test:zset1";
        redisTemplate.opsForZSet().add(zsetKey, "value1", 1);
        redisTemplate.opsForZSet().add(zsetKey, "value2", 2);
        Object object = redisTemplate.opsForZSet().zCard(zsetKey);

        System.out.println(object.toString());
        Assert.isTrue("2".equals(object), "hash put all 值不对。");
    }

    @Test
    public void testMq() {
        redisTemplate.convertAndSend("demoChannel", "this message");
    }
}
