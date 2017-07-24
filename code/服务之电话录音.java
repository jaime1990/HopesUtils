package com.itheima.phonelistener;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {

	private MediaRecorder mRecorder;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		System.out.println("�����绰״̬!");
		TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);	// ��ȡ�绰������
		manager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);	// �õ绰������ע��һ��������, �����绰״̬
	}
	
	private class MyPhoneStateListener extends PhoneStateListener {
		private String num;
		
		public void onCallStateChanged(int state, String incomingNumber) {		// �绰״̬�ı�ʱִ�и÷���
			switch (state) {
				case TelephonyManager.CALL_STATE_RINGING:
					num = incomingNumber;	// ����ʱ, ��¼����
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					startRecording();		// ժ��ʱ, ��ʼ¼��
					break;
				case TelephonyManager.CALL_STATE_IDLE:
					stopRecording();		// ����ʱ, ����¼��
					break;
			}
		}
		
		private void stopRecording() {
			if (mRecorder != null) {
				mRecorder.stop();		// ֹͣ
		        mRecorder.release();	// �ͷ���Դ
		        mRecorder = null;		// ��������
			}
		}

		private void startRecording() {
			try {
				mRecorder = new MediaRecorder();												// ����ý���¼��
				mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);						// ������ƵԴ
				mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);				// ���������ʽ
				mRecorder.setOutputFile("/mnt/sdcard/" + num + "_" + System.currentTimeMillis() + ".3gp");	// ��������ļ�·��
				mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);					// ���ñ���
				mRecorder.prepare();	// ׼��
				mRecorder.start();		// ��ʼ
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
