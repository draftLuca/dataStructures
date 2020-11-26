import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.231.128",6379);
        jedis.set("test","test");
        String value = jedis.get("test");
        System.out.println("result:"+value);
    }
}
