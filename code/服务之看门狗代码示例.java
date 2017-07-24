Service֮���Ź��������
/**
 * ���������򴴽�һ������ִ�е��߳�,���ϼ�ص�ǰ�������е�����ջ,����������������ջ���������Activity������Ӧ����
 * �鿴Ӧ���Ƿ�������Ӧ�õ����ݿ���
 * �����,���������������Activity.
 * һ��Ӧ�ý�����,�Ͳ����������Ӧ����,ʵ��:�Զ���һ���㲥������,���Ӧ�ñ�����,��Ӧ�÷���һ��������͵Ĺ㲥,Ȼ��һ�����յ�������͹㲥�Ͱ�Ӧ����ӽ���ʱ����������
 * ����Ƶ����ѯ���ݿ�,��ɰ����ݲ�ѯ������һ��������.
 * Ϊ�˱�֤���ݿ��뼯��ͬ��,��ע��һ���Զ������ݹ۲���,�����ݿ����ӻ�ɾ�������»�ȡ��������
 * Ϊ�˲�һֱ�ں�̨����,��ʡ��,�Ͷ�����Ļ�����������Ĺ㲥������,���߳��ڹ�����ֹͣ,������Ļ�����¿����߳�.
 * Ϊ�˱����������������Ӧ����Activityû����ʱ,����������������������Ӧ��û����Activity,����������Activity����ģʽΪsingleInstance
 * <activity
            android:name=".EnterPasswrodActivity"
            android:excludeFromRecents="true"//�������Activity�����ڽ��ڿ�����������
            android:launchMode="singleInstance" >
    </activity>
 * ����һ��ֹͣ,�ͷ���Դ,ж�����й㲥������,�۲���,ֹͣ�߳�,���õ��Ķ���Ϊ��...
 * @author fada
 *
 */
public class WatchDogService extends Service {
	private ActivityManager am;
	private AppLockDao dao;
	private Intent intent;
	private boolean flag;
	private List<String> tempStopProtectPacknames;
	private InnerReceiver receiver;
	private InnerLockScreenReceiver lockScreenReceiver;
	private InnerUnLockScreenReceiver unlockScreenReceiver;
	
	private List<String> lockedPackNames;

	private MyObserver observer;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
//���յ������㲥,���Ӧ����ӽ���ʱ��������
	private class InnerReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String packname = intent.getStringExtra("stoppackname");
			tempStopProtectPacknames.add(packname);
		}
		
	}
	//�Ĺ���Ļ�ر�����,������ֹͣ���ſ��߳�
	private class InnerLockScreenReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			tempStopProtectPacknames.clear();
			flag = false;
		}
		
	}
	//�����Ļ����,���̲߳���ִ��,���¿�ʼִ�п��Ź��߳�
	private class InnerUnLockScreenReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if(!flag){//���� 
				startWatchDog();
			}
		}
		
	}
	//��ʼ����Ҫ�Ĺ㲥,����,��������
	@Override
	public void onCreate() {
		//����һ�����Ͻ�����ʱ��������Ӧ��.
		tempStopProtectPacknames = new ArrayList<String>();
		//����һ���㲥������,���Ӧ�ñ�����,��Ӧ�÷���һ��������͵Ĺ㲥,Ȼ��һ�����յ�������͹㲥�Ͱ�Ӧ����ӽ���ʱ����������
		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("cn.itheima.stopprotect");
		registerReceiver(receiver, filter);
		//���������Ĺ㲥������
		lockScreenReceiver = new InnerLockScreenReceiver();
		IntentFilter lockFilter = new IntentFilter();
		lockFilter.addAction(Intent.ACTION_SCREEN_OFF);
		lockFilter.setPriority(1000);
		registerReceiver(lockScreenReceiver, lockFilter);
		//���������Ļ�Ĺ㲥������
		unlockScreenReceiver = new InnerUnLockScreenReceiver();
		IntentFilter unlockFilter = new IntentFilter();
		unlockFilter.addAction(Intent.ACTION_SCREEN_ON);
		unlockFilter.setPriority(1000);
		registerReceiver(unlockScreenReceiver, unlockFilter);
		
		//���ActivityManager����
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		dao = new AppLockDao(this);
		lockedPackNames = dao.findAll();//���ҵ����б�����Ӧ�õļ���
		//������������������Activity��ͼ
		intent = new Intent(this, EnterPasswrodActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//�ڷ�Activity��Ҫ�������ǲ�������Activity
		//ע��һ���۲���,�������Ӧ�����ݿ���޸�(���ӻ����),�����»�ȡ��������
		observer = new MyObserver(new  Handler());
		getContentResolver().registerContentObserver(AppLockDao.uri, true, observer);
		
		
		super.onCreate();
		// �������Ź� ���� ��ǰϵͳ�ĳ���������Ϣ.
		startWatchDog();

	}

	private void startWatchDog() {
		new Thread() {
			public void run() {
				flag = true;//�����֤,ֻҪ�߳̿���,�ͻ�ѱ�Ǹĳ�ִ��״̬.
				while (flag) {//�ñ�ǿ������ѭ���Ƿ�ִ��
					//�õ���ǰ��������ִ�е�����ջ,һ������ջͨ�����Դ���һ��Ӧ��.
					List<RunningTaskInfo> infos = am.getRunningTasks(1);
					RunningTaskInfo taskinfo = infos.get(0);// ���´򿪵�����ջ.
					//ComponentName���ڱ�ʶһ�����,���Է��ر�ʶ�����Ӧ��Ӧ����.
					ComponentName topActivity = taskinfo.topActivity;// �õ�ջ����activity
					//������Activity��ӦӦ�õİ���													// �û��ɼ���activity
					String packname = topActivity.getPackageName();
					//if (dao.find(packname)) {// ��Ҫ�������Ӧ�� ��ѯ���ݿ� ��!
					if(lockedPackNames.contains(packname)){ //��ѯ�ڴ� 
						// �жϵ�ǰ�����Ƿ�Ҫ��ʱ��ֹͣ����.
						if (tempStopProtectPacknames.contains(packname)) {

						} else {
							intent.putExtra("packname", packname);
							startActivity(intent);
						}
					}
					try {
						Thread.sleep(30);//�����һ��,��ʡһ����Դ,�����ú�̨����������,����ֻ�Ч�ʸ�,��ô��ʱ�����һ��,��֮,�ͳ�һ��.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	@Override
	public void onDestroy() {
		flag = false;//�������ֹͣ,����ѭ��ֹͣ,�߳̾ͻ����
		unregisterReceiver(receiver);//ж�ع㲥������
		receiver = null;
		unregisterReceiver(lockScreenReceiver);
		lockScreenReceiver = null;
		getContentResolver().unregisterContentObserver(observer);  
		observer = null;
		unregisterReceiver(unlockScreenReceiver);
		unlockScreenReceiver = null;
		super.onDestroy();
	}
	//����۲���,һ��������Ӧ�����ݿ����޸�,�����»�ȡ������Ӧ�ü���
	private class MyObserver extends ContentObserver{

		public MyObserver(Handler handler) {
			super(handler);
		}

		@Override
		public void onChange(boolean selfChange) {
			lockedPackNames = dao.findAll();
			super.onChange(selfChange);
		}
		
	}
}
