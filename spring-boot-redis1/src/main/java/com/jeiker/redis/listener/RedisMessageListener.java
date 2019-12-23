package com.jeiker.redis.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Description: 订阅者监听通道消息
 * User: jeikerxiao
 * Date: 2019-12-23 12:53
 */
@Component
public class RedisMessageListener implements MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        String msg = serializer.deserialize(message.getBody());
        System.out.println("接收到的消息是：" + msg);
    }
}
