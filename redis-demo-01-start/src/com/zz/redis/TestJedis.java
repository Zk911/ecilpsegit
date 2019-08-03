package com.zz.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestJedis {
	@SuppressWarnings("resource")
	@Test
	public void testJedisConnetion() {
		try {
			// 第一步：获得连接
			Jedis jedis = new Jedis("192.168.91.66", 6379);
			jedis.auth("123456");
			System.out.println("是否获得连接：" + jedis.isConnected());
			System.out.println("服务器是否允许：" + jedis.ping());
			System.out.println("回显信息：" + jedis.info());
			// 第三步：关闭
			jedis.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// key命令操作
	@Test
	public void key() {
		// 第一步：获得连接
		Jedis jedis = new Jedis("192.168.91.66", 6379);

		// 第二步：输入验证信息
		jedis.auth("123456");

		// 第三步：命令测试
		jedis.set("testname", "testzhangsan");
		String testname = jedis.get("testname");
		System.out.println("获得KEY对应的值：" + testname);
		System.out.println("mget:" + jedis.mget(new String[] { "testname", "name" }));
		System.out.println(jedis.keys("*"));

		// 第四步：关闭
		jedis.disconnect();
	}

	@Test
	public void list() {
		// 第一步：获得连接
		Jedis jedis = new Jedis("192.168.91.66", 6379);

		// 第二步：输入验证信息
		jedis.auth("123456");

		// 第三步：命令测试
		jedis.lpush("testlist", new String[] { "a", "b", "c", "d" });
		System.out.println("长度：" + jedis.llen("testlist"));
		System.out.println("元素：" + jedis.lrange("testlist", 0, 10));
		System.out.println("指定下标的元素：" + jedis.lindex("testlist", 1));
		// 第四步：关闭
		jedis.disconnect();
	}

	@Test
	public void hash() {
		// 第一步：获得连接
		Jedis jedis = new Jedis("192.168.91.66", 6379);

		// 第二步：输入验证信息
		jedis.auth("123456");

		// 第三步：命令测试
		jedis.hset("testhash", "id", "1");
		jedis.hset("testhash", "name", "zhangsan");
		jedis.hset("testhash", "password", "123456");

		// 获得数据
		Map<String, String> hgetAll = jedis.hgetAll("testhash");
		System.out.println(hgetAll);

		// 第四步：关闭
		jedis.disconnect();
	}

	@Test
	public void set() {
		// 第一步：获得连接
		Jedis jedis = new Jedis("192.168.91.66", 6379);

		// 第二步：输入验证信息
		jedis.auth("123456");

		// 第三步：命令测试
		jedis.sadd("testset", new String[] { "a", "b", "c", "d" });

		// 获得数据
		Set<String> smembers = jedis.smembers("testset");
		System.out.println(smembers);

		// 第四步：关闭
		jedis.disconnect();
	}
   
	@Test
	public void sortSet() {
		// 第一步：获得连接
		Jedis jedis = new Jedis("192.168.91.66", 6379);

		// 第二步：输入验证信息
		jedis.auth("123456");

		// 第三步：命令测试
		Map<String, Double> members = new HashMap<>();
		members.put("a", 1d);
		members.put("b", 2d);
		members.put("c", 3d);
		
		
		jedis.zadd("testsortSet", members);
 
		// 获得数据
		Set<String> zrange = jedis.zrange("testsortSet", 0, 10);
		System.out.println(zrange);
		
		

		// 第四步：关闭
		jedis.disconnect();
	}
}
