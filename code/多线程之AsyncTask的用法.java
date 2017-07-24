/*
AsyncTask���÷�

      �ڿ���AndroidӦ��ʱ�������ص��߳�ģ�͵�ԭ�� Android UI�����������̰߳�ȫ�Ĳ�����Щ����������UI�߳���ִ�С��ڵ��߳�ģ����ʼ��Ҫ��ס�������� 
1. ��Ҫ����UI�߳� 
2. ȷ��ֻ��UI�߳��з���Android UI���߰� 
      ��һ�������һ������ʱ��Android��ͬʱ����һ����Ӧ�����߳�(Main Thread)�����߳���Ҫ��������UI��ص��¼����磺�û��İ����¼����û��Ӵ���Ļ���¼��Լ���Ļ��ͼ�¼���������ص��¼��ַ�����Ӧ��������д����������߳�ͨ���ֱ�����UI�̡߳� 
      ����˵�����ϻ�ȡһ����ҳ����һ��TextView�н���Դ������ʾ�����������漰����������ĳ���һ�㶼����Ҫ��һ���߳����������ʣ������ڻ��ҳ��Դ����ǲ���ֱ������������߳��е���TextView.setText()��.��Ϊ�����߳����ǲ���ֱ�ӷ�����UI�̳߳�Ա ��

android�ṩ�˼����������߳��з���UI�̵߳ķ����� 
Activity.runOnUiThread( Runnable ) 
View.post( Runnable ) 
View.postDelayed( Runnable, long ) 
Hanlder 
��Щ��򷽷�ͬ����ʹ��Ĵ���ܸ��Ӻ�����⡣Ȼ��������Ҫʵ��һЩ�ܸ��ӵĲ�������ҪƵ���ظ���UIʱ����ø���⡣ 

     Ϊ�˽��������⣬Android 1.5�ṩ��һ�������ࣺAsyncTask����ʹ������Ҫ���û����潻���ĳ�ʱ�����е������ø��򵥡������˵AsyncTask��������һЩ�������ڼ򵥵��첽��������Ҫ�����̺߳�Handler����ʵ�֡� 
AsyncTask�ǳ�����.AsyncTask���������ַ������� Params��Progress��Result�� 
����Params ��������ִ�е��������������HTTP�����URL�� 
����Progress ��̨����ִ�еİٷֱȡ� 
����Result ��ִ̨���������շ��صĽ��������String�� 

     AsyncTask��ִ�з�Ϊ�ĸ����裬ÿһ������Ӧһ���ص���������Щ������Ӧ����Ӧ�ó�����ã���������Ҫ���ľ���ʵ����Щ������ 
����1) ���໯AsyncTask 
����2) ʵ��AsyncTask�ж��������һ���򼸸����� 
����   onPreExecute(), �÷�������ִ��ʵ�ʵĺ�̨����ǰ��UI thread���á������ڸ÷�������һЩ׼�����������ڽ�������ʾһ���������� 
����  doInBackground(Params...), ����onPreExecute ����ִ�к�����ִ�У��÷��������ں�̨�߳��С����ｫ��Ҫ����ִ����Щ�ܺ�ʱ�ĺ�̨���㹤�������Ե��� publishProgress����������ʵʱ��������ȡ��÷����ǳ��󷽷����������ʵ�֡� 
����  onProgressUpdate(Progress...),��publishProgress���������ú�UI thread��������������Ӷ��ڽ�����չʾ����Ľ�չ���������ͨ��һ������������չʾ�� 
����  onPostExecute(Result), ��doInBackground ִ����ɺ�onPostExecute ��������UI thread���ã���̨�ļ�������ͨ���÷������ݵ�UI thread. 

Ϊ����ȷ��ʹ��AsyncTask�࣬�����Ǽ����������ص�׼�� 
����1) Task��ʵ��������UI thread�д��� 
����2) execute����������UI thread�е��� 
����3) ��Ҫ�ֶ��ĵ���onPreExecute(), onPostExecute(Result)��doInBackground(Params...), onProgressUpdate(Progress...)�⼸������ 
����4) ��taskֻ�ܱ�ִ��һ�Σ������ε���ʱ��������쳣 
      doInBackground������onPostExecute�Ĳ��������Ӧ��������������AsyncTask�����ķ��Ͳ����б���ָ������һ��ΪdoInBackground���ܵĲ������ڶ���Ϊ��ʾ���ȵĲ������ڵ�����ΪdoInBackground���غ�onPostExecute����Ĳ�����


�����ϻ�ȡһ����ҳ����һ��TextView�н���Դ������ʾ����
*/

package test.list;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NetworkActivity extends Activity{
    private TextView message;
    private Button open;
    private EditText url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.network);
       message= (TextView) findViewById(R.id.message);
       url= (EditText) findViewById(R.id.url);
       open= (Button) findViewById(R.id.open);
       open.setOnClickListener(new View.OnClickListener() {
           public void onClick(View arg0) {
              connect();
           }
       });

    }

    private void connect() {
        PageTask task = new PageTask(this);
        task.execute(url.getText().toString());
    }


    class PageTask extends AsyncTask<String, Integer, String> {
        // �ɱ䳤�������������AsyncTask.exucute()��Ӧ
        ProgressDialog pdialog;
        public PageTask(Context context){
            pdialog = new ProgressDialog(context, 0);   
            pdialog.setButton("cancel", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int i) {
              dialog.cancel();
             }
            });
            pdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
             public void onCancel(DialogInterface dialog) {
              finish();
             }
            });
            pdialog.setCancelable(true);
            pdialog.setMax(100);
            pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pdialog.show();


        }
        @Override
        protected String doInBackground(String... params) {

            try{

               HttpClient client = new DefaultHttpClient();
               // params[0]�������ӵ�url
               HttpGet get = new HttpGet(params[0]);
               HttpResponse response = client.execute(get);
               HttpEntity entity = response.getEntity();
               long length = entity.getContentLength();
               InputStream is = entity.getContent();
               String s = null;
               if(is != null) {
                   ByteArrayOutputStream baos = new ByteArrayOutputStream();

                   byte[] buf = new byte[128];

                   int ch = -1;

                   int count = 0;

                   while((ch = is.read(buf)) != -1) {

                      baos.write(buf, 0, ch);

                      count += ch;

                      if(length > 0) {
                          // ���֪����Ӧ�ĳ��ȣ�����publishProgress�������½���
                          publishProgress((int) ((count / (float) length) * 100));
                      }

                      // ���߳�����100ms
                      Thread.sleep(100);
                   }
                   s = new String(baos.toByteArray());              }
               // ���ؽ��
               return s;
            } catch(Exception e) {
               e.printStackTrace();

            }

            return null;

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(String result) {
            // ����HTMLҳ�������
            message.setText(result);
            pdialog.dismiss(); 
        }

        @Override
        protected void onPreExecute() {
            // ����������������������ʾһ���Ի�������򵥴���
            message.setText(R.string.task_started);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // ���½���
              System.out.println(""+values[0]);
              message.setText(""+values[0]);
              pdialog.setProgress(values[0]);
        }

     }

}

//�����Ҫ˵��AsyncTask������ȫȡ���̣߳���һЩ�߼���Ϊ���ӻ�����Ҫ�ں�̨����ִ�е��߼��Ϳ�����Ҫ�߳���ʵ���ˡ�