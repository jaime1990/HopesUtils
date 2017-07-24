/**
 * ��ʱִ�еĹ�����
 * @author Administrator
 *
 */
public abstract class DelayExecuter {
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			onPostExecute();
		};
	};
	
	public abstract void onPostExecute();
	/**
	 * ��ʱִ��
	 * @param delayTime ����ֵ
	 */
	public void execute(final long delayTime){
		new Thread(){
			public void run() {
				SystemClock.sleep(delayTime);
				handler.sendEmptyMessage(0);
			};
		}.start();
	}
}
