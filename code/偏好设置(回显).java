package com.itheima.sp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText nameET;
	private EditText phoneET;
	private EditText emailET;
	private SharedPreferences sp;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // ��ȡ3��EditText
        nameET = (EditText) findViewById(R.id.nameET);
        phoneET = (EditText) findViewById(R.id.phoneET);
        emailET = (EditText) findViewById(R.id.emailET);
        
        // �õ�һ��SharedPrefereneces����
        sp = getSharedPreferences("config", MODE_PRIVATE);
        
        // ��ȡ��ǰ�洢������, �����ݷŵ�EditText��
        nameET.setText(sp.getString("name", ""));
        phoneET.setText(sp.getString("phone", ""));
        emailET.setText(sp.getString("email", ""));
    }
    
    public void save(View view) {
    	// �õ�Ҫ���������
    	String name = nameET.getText().toString();
    	String phone = phoneET.getText().toString();
    	String email = emailET.getText().toString();
    	
    	// ��SharedPrefereneces�洢����
    	Editor editor = sp.edit();			// �õ��༭��
    	editor.putString("name", name);		// �洢����
    	editor.putString("phone", phone);
    	editor.putString("email", email);
    	editor.commit();					// �ύ�޸�
    	
    	// ������ʾ
    	Toast.makeText(this, "����ɹ�", 0).show();
    }
}