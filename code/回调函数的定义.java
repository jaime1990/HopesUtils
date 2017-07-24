//�ص�����:���ǰѳ��������в����������ýӿڵķ�ʽ�ύ��ȥ,��������������ʲô�����ɵ�����ʵ��.
public class SmsUtils {
	private Context context;

	public SmsUtils(Context context) {
		this.context = context;
	}
	/**
	 *����һ���ӿ� ,��ui����Ҫ�����ݶ���Ϊ�ӿ��﷽���Ĳ���
	 *�ڰѽӿڵĶ�����Ϊ������Ĳ���,���﹤��������������ӿڷ���,�ѹ�����������ݵ������Ĳ���.
	 *ʹ���߿�����Ϊprogressbar,Ҳ������������,����ź���Ծ͵���,�����Ծ͸���.
	 * @author fada
	 *
	 */
	public interface BackUpProcessListener{
		void beforeBackup(int max);
		void onProcessUpdate(int process);
	}
	
	//�ṩ���ⲿ�õķ���
	public void backUpSms(BackUpProcessListener listener) throws Exception{
		Uri uri = Uri.parse("content://sms/");
		XmlSerializer  serializer = Xml.newSerializer();
		serializer.setOutput(os, "utf-8");
		serializer.startDocument("utf-8", true);
		serializer.startTag(null, "smss");
		Cursor cursor = context.getContentResolver().query(uri, new String[]{"address","date","type","body"} , null, null, null);
		//���ǹؽ�,�����ݵ�����,�����˳��󷽷�,���������߾����õ�������
		listener.beforeBackup(cursor.getCount());
		int total = 0;
		while(cursor.moveToNext()){
			//��������ľ��������.......
			total++;
			//ͬ����������ݵ����󷽷�����
			listener.onProcessUpdate(total);
		}
		os.flush();
		os.close();
		
	}
	//�ⲿ�߱�ʵ��,�±�ľ����ǰ����ݸ��˽�������.
	//��һ���첽��ʵ�ֹ���.
	public void back(View v) {
		new AsyncTask<Void, Integer, Boolean>() {
			// ����ִ��ǰ
			@Override
			protected void onPreExecute() {
				pd = new ProgressDialog(ReadSmsActivity.this);
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pd.setTitle("��ʾ:");
				pd.setMessage("���ڱ��ݶ���....");
				pd.show();
				super.onPreExecute();
			}

			// ����ִ����
			@Override
			protected Boolean doInBackground(Void... params) {
				try {
				//���ﶨ���˾���ʵ��.
					dao.backUpSms(new BackUpProcessListener() {
						@Override
						public void onProcessUpdate(int process) {
							pd.setProgress(process);
						}

						@Override
						public void beforeBackup(int max) {
							pd.setMax(max);
						}
					});
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}

			// ����ִ�к�
			@Override
			protected void onPostExecute(Boolean result) {
				pd.dismiss();
				if (result) {
					Toast.makeText(getApplicationContext(), "���ݳɹ�", 0).show();
				} else {
					Toast.makeText(getApplicationContext(), "����ʧ��", 0).show();
				}
				super.onPostExecute(result);
			}

			// ִ�н���
			@Override
			protected void onProgressUpdate(Integer... values) {
				// TODO Auto-generated method stub
				super.onProgressUpdate(values);
			}

		}.execute();
	}