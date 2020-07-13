import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.19.83.149",7379);
        String value = jedis.get("DEFAULT_CODE_KEY_94171594275295058");
        System.out.println("result:"+value);
    }
}
