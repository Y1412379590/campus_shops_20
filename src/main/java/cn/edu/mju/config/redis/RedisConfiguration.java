package cn.edu.mju.config.redis;

import cn.edu.mju.cache.JedisPoolWriper;
import cn.edu.mju.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    @Value("${redis.hostname}")
    private String hostname;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.pool.maxActive}")
    private int maxTotal;

    @Value("${redis.pool.maxIdle}")
    private int maxIdle;

    @Value("${redis.pool.maxWait}")
    private long maxWaitMillis;

    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private JedisPoolWriper jedisPoolWriper;

    @Autowired
    private JedisUtil jedisUtil;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle(maxIdle);

        jedisPoolConfig.setMaxTotal(maxTotal);

        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        jedisPoolConfig.setTestOnBorrow(testOnBorrow);

        return jedisPoolConfig;

    }


    @Bean(name = "jedisPoolWriper")
    public JedisPoolWriper jedisPoolWriper(){

        return new JedisPoolWriper(jedisPoolConfig,hostname,port);

    }


    @Bean("jedisUtil")
    public JedisUtil jedisUtil(){
        JedisUtil jedisUtil = new JedisUtil();

        jedisUtil.setJedisPool(jedisPoolWriper);
        return jedisUtil;
    }


    @Bean(name = "jedisStrings")
    public JedisUtil.Strings jedisStrings(){
        return jedisUtil.new Strings(jedisUtil);
    }

    @Bean(name = "jedisKeys")
    public JedisUtil.Keys jedisKeys(){
        return jedisUtil.new Keys(jedisUtil);
    }

    @Bean(name = "jedisSets")
    public JedisUtil.Sets jedisSets(){
        return jedisUtil.new Sets(jedisUtil);
    }

    @Bean(name = "jedisLists")
    public JedisUtil.Lists jedisLists(){
        return jedisUtil.new Lists(jedisUtil);
    }


    @Bean(name = "jedisHash")
    public JedisUtil.Hash jedisHash(){
        return jedisUtil.new Hash(jedisUtil);
    }





}
