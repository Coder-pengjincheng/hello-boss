package limitDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 设计一个限流算法，提供10qps的限流能力。
 * 要求：
 * 提供一个http接口，正常调用返回success，限流情况下 返回throttle
 * @author jinchengpeng
 * @date 2021年4月7日
 */
public class LimitTest {

	final static LimitServer limitServer = new LimitServer();
	
	public static void main(String[] args) throws Exception{
		
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 1000; i++) {
			threadPool.submit(new Runnable() {
				
				@Override
				public void run() {
					boolean flag = limitServer.couldExec();
					if(flag) {
						System.out.println("success");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						limitServer.release();
					}else {
						System.out.println("throttle");
					}
					
				}
			});
		}
	}

}
