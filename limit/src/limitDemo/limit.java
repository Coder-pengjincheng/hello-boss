package limitDemo;
/**
 * @description: 限流接口
 * @author jinchengpeng
 * @date 2021年4月7日
 */
public interface limit {

	/**
     * 可否执行
	 * @return boolean
	 */
	public boolean couldExec();

    /**
     * 限流
	 */
	public void release();
}
