package com.example.service;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;
import com.android.internal.telephony.ITelephony.Stub;
import com.example.dao.db.BlackNumberDao;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class BlackService extends Service {

	private TelephonyManager telService;
	private PhoneStateListener listener;
	private BlackNumberDao dao;
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}
	@Override
	public void onCreate() {
		dao = new BlackNumberDao(this);
	telService = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
	listener=new MyPhoneStateListener();
	//ǰһ��������ʾ�Ǽ���������,�����ʾ�Ǽ���������
	telService.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
		super.onCreate();
	}
	//����һ���绰״̬������,�����������һ����,�������˿�ʵ��,����Ҫ��Ҫ��д�����onCallStateChanged����
	public class MyPhoneStateListener extends PhoneStateListener{
		private long startTime;

		//����ֻҪ�绰�������,����ֻ������һ��״̬����
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				long endTime = System.currentTimeMillis();
				long time=endTime-startTime;
				if (time<3000) {
					killCall();
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				startTime = System.currentTimeMillis();
				String  blockmode = dao.findMode(incomingNumber);
				if ("1".equals(blockmode)||"2".equals(blockmode)) {
					killCall();
				}
				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}

	}
	//�Ҷϵ绰
	private void killCall() {
		//ͨ�������ù���������,�ù�������������getService�����Ķ���.getMethodǰ��Ĳ�����ʾ������,����ı�ʾ�����������͵�.class
		try {
			Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
			//��÷��������Ϳ��÷��������invoke����ȥִ�з�����,��������Ƿ���һ����Ӧ�Ĺ�����,
			IBinder binder = (IBinder) method.invoke(null, new Object[]{TELEPHONY_SERVICE});
			//��aidl�������������������,ת��ΪITelephony����
			ITelephony iTelephony = Stub.asInterface(binder);
			//���������Ϳ��Զ�̬�������service��ķ���
			iTelephony.endCall();//��ʾ�ҵ绰--��סҪ��Ӳ���绰Ȩ��
		//	iTelephony.dial(number);//��ʾ�Ѻ�����뵽���Ž���,���Ȳ�����
		//	iTelephony.call(number);//����ֱ�Ӳ���绰��
//������Щ����������÷����aidl����telService = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);��ö���,�ǵ��ò�����.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
