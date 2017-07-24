package time.auto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class TimeActivity extends Activity {

	private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	private TextView timeTV;
	private String time;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		handler.post(updateThread);
		// ����Ҫִ�е��̶߳�����ӵ��̶߳��е��У���ʱ����Ѹ��̶߳�����ӵ�handler������̶߳��е���
		timeTV = (TextView) findViewById(R.id.timeTV);
	}
	// ����Handler����
	Handler handler = new Handler();
	// �½�һ���̶߳���
	Runnable updateThread = new Runnable() {
		// ��Ҫִ�еĲ���д���̶߳����run��������
		public void run() {
			handler.postDelayed(updateThread, 1000);
			// ����Handler��postDelayed()����
			// ��������������ǣ���Ҫִ�е��̶߳�����뵽���е��У���ʱ������������ƶ����̶߳���
			// ��һ��������Runnable���ͣ���Ҫִ�е��̶߳���
			// �ڶ���������long���ͣ��ӳٵ�ʱ�䣬�Ժ���Ϊ��λ
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					DEFAULT_TIME_FORMAT);
			time = dateFormatter.format(Calendar.getInstance().getTime());
			timeTV.setText(time);
		}
	};

}