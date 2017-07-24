package com.itheima.audiocapture;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
/*�ǵ���manifest��ע��: <application       
        <service android:name=".AudioCaptureService"/>
    </application>
*/
public class AudioCaptureService extends Service {
	private MediaRecorder mRecorder;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		startRecording();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		stopRecording();
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
			mRecorder.setOutputFile("/mnt/sdcard/" + System.currentTimeMillis() + ".3gp");	// ��������ļ�·��
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);					// ���ñ���
			mRecorder.prepare();	// ׼��
			mRecorder.start();		// ��ʼ
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
