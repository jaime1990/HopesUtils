//1,�㲥�����߽��յ绰����
//ע��Ȩ��:
<uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
//ע��receiver:
<receiver android:name=".receiver.OutCallReceiver" >
            <intent-filter android:priority="1000" >
                <action android:name="android.intent.action.NEW_OUTGOING_CALL" />
            </intent-filter>
        </receiver>
//���չ㲥����
public class OutCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String data = getResultData();
		//����һ��������Ӧ����,һ��ƥ��,��Ӧ����
		if ("8816".equals(data)) {
			Intent lostIntent = new Intent(context,LostFindActivity.class);
			//�ڷ�Activity�е�context������������Activityʱ,��Ҫ�ֶ�����һ����ʶ,������ӵ�����ջ,��Ȼ�޷�����Activity.
			lostIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(lostIntent);
			setResultData(null);
			//abortBroadcast();
		}

	}
}

//2.�㲥�����߽��տ����㲥
//ע��receiver:�����㲥û������,���Բ���Ҫ�������ݽ���
<receiver android:name="com.example.receiver.SafeNumBroadcast" >
	<intent-filter android:priority="1000" >
		<action android:name="android.intent.action.BOOT_COMPLETED" />
	</intent-filter>
</receiver>

//3.�㲥�����߽��ն��Ź㲥

//ע��receiver:
	<receiver android:name=".receiver.SmsReceiver" >
		<intent-filter android:priority="1000" >
			<action android:name="android.provider.Telephony.SMS_RECEIVED" />
		</intent-filter>
	</receiver>
		public class SmsReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");//��ö���,��һ���ֽ����������.
		//�����������,�����Ƕ��ŵ��ֽ�����
		for (Object pdu : pdus) {
		//ͨ���ֽ������ö��Ŷ���
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);		
			String sender = smsMessage.getOriginatingAddress();//��÷��͵ĵ绰����	
			String body = smsMessage.getMessageBody();//��÷��͵�����
			}
	}
}