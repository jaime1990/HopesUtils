package time.auto;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimeActivity extends Activity {
	private long startTime;
	private TextView timeTV;
	private String time;
	Handler handler;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	//����һ�����߳�.
	Runnable updateThread = new Runnable() {
		public void run() {
			if (handler != null) {
				//��ʾÿ�����,��handle������δ���
				handler.postDelayed(updateThread, 1000);
				//ʵ�����ʱ��
				long longtime = (System.currentTimeMillis() - startTime) / 1000;
				time = String.format("%02d:%02d:%02d", longtime / 3600,
						longtime / 60, longtime % 60);
				timeTV.setText(time);
			}
		}
	};
//���尴ť�¼�
	public void start(View view) {
		Button button = (Button) view;
//ͨ���жϰ�ť��ʾʵ��,ȥʵ��ʱ����л�.
		if ("¼��".equals(button.getText()) && handler == null) {
			//ÿ�ο�ʼ,���¼�һ��handlerȥ,�����»�õ�ǰʱ��.
			handler = new Handler();
			startTime = System.currentTimeMillis();
			//��handler���ʹ���
			handler.post(updateThread);
			button.setText("ֹͣ");
		} else {
			//һ��ֹͣ,����handleΪ��
			handler = null;
			button.setText("¼��");
		}
	}
}
