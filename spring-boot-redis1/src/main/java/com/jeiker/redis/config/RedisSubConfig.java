package com.jeiker.redis.config;

import com.jeiker.redis.listener.RedisMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * Description: Redis 消息订阅配置
 * User: jeikerxiao
 * Date: 2019-12-23 12:56
 */
@Configuration
public class RedisSubConfig {

    /**
     * 创建Redis连接工厂类
     *
     * @param connectionFactory
     * @param adapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter adapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 监听对应的channel
        container.addMessageListener(adapter, new PatternTopic("demoChannel"));
        return container;
    }

    @Bean
    public MessageListenerAdapter adapter(RedisMessageListener messageListener) {
        // onMessage 如果RedisMessage 中 没有实现接口，
        // 这个参数必须跟RedisMessage中的读取信息的方法名称一样
        return new MessageListenerAdapter(messageListener);
    }
}
