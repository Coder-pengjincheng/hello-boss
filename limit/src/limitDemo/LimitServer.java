package limitDemo;
/**
 * @description: 提供10qps的限流能力。
 * @author jinchengpeng
 * @date 2021年4月7日
 */
public class LimitServer implements limit {

	public static final int LIMIT = 10;
	public static int requestCounter = 0 ;
	public static long startTime = System.currentTimeMillis();
	public static int interval = 60 * 1000;
	
	@Override
	public boolean couldExec() {
		long now = System.currentTimeMillis();
		if(now > startTime + interval) {
			startTime = now;
			requestCounter = 0;
			return true;
		}
		requestCounter ++ ;
		return requestCounter < LIMIT;
	}

	@Override
	public void release() {
		if(requestCounter > 0) {
			requestCounter--;
		}
	}
}
