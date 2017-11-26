package com.letv.tbtSps.service.impl;

import com.letv.tbtSps.service.JodisServcie;
import io.codis.jodis.BoundedExponentialBackoffRetryUntilElapsed;
import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * Description
 * Created by ygd on 2017/11/22.
 */
@Service
public class JodisServcieImpl implements JodisServcie {
//    private static final int CURATOR_RETRY_BASE_SLEEP_MS = 100;
//    private boolean closeCurator;
//    private String zkProxyDir;
//    private JedisPoolConfig poolConfig;
//    private int connectionTimeoutMs = Protocol.DEFAULT_TIMEOUT;
//    private int soTimeoutMs = Protocol.DEFAULT_TIMEOUT;
//    private String password;
//    private int database = Protocol.DEFAULT_DATABASE;
//    private String clientName;
//    private static final int CURATOR_RETRY_MAX_SLEEP_MS = 30 * 1000;
//    public JodisServcieImpl(){
//        JedisResourcePool jedisResourcePool = this.build();
//        try {
//            Jedis jedis = jedisResourcePool.getResource();
//            jedis.set("foo", "bar");
//            String value = jedis.get("foo");
//        }catch (Exception e){
//
//        }
//    }
//
//    public RoundRobinJedisPool build() {
//
//        CuratorFramework curatorClient = CuratorFrameworkFactory.builder().connectString("10.37.251.221:2181,10.37.251.222:2181,10.37.251.223:2181").sessionTimeoutMs(11111).retryPolicy(new BoundedExponentialBackoffRetryUntilElapsed(
//                CURATOR_RETRY_BASE_SLEEP_MS, CURATOR_RETRY_MAX_SLEEP_MS, -1L)).build();
//
//        return RoundRobinJedisPool.create().curatorClient(curatorClient, true).zkProxyDir("/jodis/codis-dev").database(0).build();
//    }
//
//    public static void main(String[] args) {
//        JedisResourcePool jedisResourcePool = new JodisServcieImpl().build();
//        Jedis jedis = jedisResourcePool.getResource();
//        jedis.set("test122321","lillll");
//        System.out.println(jedis.get("test122321"));
//    }

}
