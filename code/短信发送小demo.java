package com.itheima.sms;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText numET;
	private EditText contentET;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Activity������ʱ��, ��ȡ�����ı���
        numET = (EditText) findViewById(R.id.numET);
        contentET = (EditText) findViewById(R.id.contentET);
    }
    
    public void sendSms(View view) {
    	// �����ťʱ, ��ȡ�ı����е��ı�
    	String num = numET.getText().toString().trim();
    	String content = contentET.getText().toString();
    	
    	if (num.length() == 0) {
    		Toast.makeText(getApplicationContext(), R.string.num_err, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	if (content.length() == 0) {
    		Toast.makeText(getApplicationContext(), R.string.insert_content, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	try {
			// ���Ͷ���
			SmsManager manager = SmsManager.getDefault();				// ��ȡ���Ź�����
			ArrayList<String> list = manager.divideMessage(content);	// �����ŷֶ� ��Ϊ���ſ��ܳ�������,��ô�ͻ�ָ������.
			//int i=SmsMessage.MAX_USER_DATA_BYTES;// ������Եõ�һ�����ŵ���󳤶�
			for (String sms : list) {									// ѭ������
				manager.sendTextMessage(num, null, sms, null, null);	// ���Ͷ���
			}
			
			// ����ı�, ������ʾ
			contentET.setText("");
			Toast.makeText(getApplicationContext(), R.string.send_success, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}