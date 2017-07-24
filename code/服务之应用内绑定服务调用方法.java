//����֮Ӧ���ڰ󶨷�����÷���
1.���ȶ�����񷵻ذ󶨵�Binder
public class WatchDogService extends Service {
	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	private class MyBinder extends Binder implements IService {
		@Override
		public void callMethodInService(String packname) {
			tempStopProtecet(packname);
		}
	}
	//��Ҫ��Activity�󶨵ķ���
	private void tempStopProtecet(String packname) {
		tempStopProtectPacknames.add(packname);
	}
}
2.����ӿ�	
public interface IService {
	public void callMethodInService(String packname);
}
3.��Activity�а󶨷�����÷���
public class EnterPasswrodActivity extends Activity {
	private MyConn conn;
	private IService iService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_password);
		//����Ҫ�󶨷������ͼ
		Intent service = new Intent(this,WatchDogService.class);
		conn = new MyConn();
		//�󶨷���
		bindService(service, conn, BIND_AUTO_CREATE);

	}
//��������
private class MyConn implements ServiceConnection{
//���binder,ǿת�ؽӿ�����.
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		iService = (IService) service;
	}
	@Override
	public void onServiceDisconnected(ComponentName name) {			
	}		
}
//����Ҫ�ĵط�,�����˷���ķ���
public void enter(View view){		
	iService.callMethodInService(packname);

}