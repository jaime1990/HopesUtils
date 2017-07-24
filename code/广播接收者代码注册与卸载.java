//�㲥�����߿���ͨ�����봴����ע��,�����Ϳ����ڴ�����ʵ������������(�����)ʵ�ְ�,��������,������ȥ�ñ��.
//ʾ����������:
public class AddressService extends Service {

	private InnerOutcallReceiver outcallReceiver;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	//1.����һ���㲥�������ڲ���
	private class InnerOutcallReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
		//������Ҫ�Ĵ������.....��:
			showAddress(getResultData());
			
		}
	}
	@Override
	public void onCreate() {
		super.onCreate();
		//2.���������߶���
		outcallReceiver = new InnerOutcallReceiver();
		//3.��������������
		IntentFilter filter = new IntentFilter();
		//4.ƥ�䶯��,����ƥ����Ƕ��Ⲧ��
		filter.addAction("android.intent.action.NEW_OUTGOING_CALL");
		//5.ע��㲥������
		registerReceiver(outcallReceiver, filter);
	}
	@Override
	public void onDestroy() {
		//ж�ع㲥ע��
		unregisterReceiver(outcallReceiver);
		outcallReceiver=null;
		super.onDestroy();
	}
}