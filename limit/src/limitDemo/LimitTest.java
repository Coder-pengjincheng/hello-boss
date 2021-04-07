package limitDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: ���һ�������㷨���ṩ10qps������������
 * Ҫ��
 * �ṩһ��http�ӿڣ��������÷���success����������� ����throttle
 * @author jinchengpeng
 * @date 2021��4��7��
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
