package com.example.basic.fanout;

import com.example.basic.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @program: rabbitmq-study
 * @description:
 * @author: liuB
 * @create: 2021-03-18 17:53
 **/
public class Send {

    private final static String EXCHANGE_NAME = "test_fanout_exchange";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明exchange，指定类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        // 消息内容
        String message = "注册成功！！";
        // 发布消息到Exchange
        channel.basicPublish(EXCHANGE_NAME, "msi", null, message.getBytes());
        System.out.println(" [生产者] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}