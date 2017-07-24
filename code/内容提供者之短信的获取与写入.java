���ŵĶ�ȡ��д��
//��com..android.provider.telephony�µ�databases�µ�mmsmms.db.�����űȽ���Ҫ�ı�:threads���sms��
//threads���Ӧ������ϵ��,ÿһ�����ݴ���һ����ϵ��,����message_count��ʾ�ж���������,��thread_idԼ��sms��,ÿ��thread_id��Ӧ����sms����
//����sms����:address��ʾ���� person��1��ʾ����ϵ�����Ѵ�,0��ʾ�����û�д�,date��ʾ�յ���ʱ��,read����1��ʾ�Ѷ�,0��ʾδ��,
//type��1��ʾ�ն���,2��ʾ������,body��ʾ��������.
public class SmsUtils {
	private Context context;

	public SmsUtils(Context context) {
		this.context = context;
	}
	/**
	 *����һ���ӿ� ,��ui����Ҫ�����ݶ���Ϊ�ӿ��﷽���Ĳ���
	 *�ڰѽӿڵĶ�����Ϊ������Ĳ���,���﹤��������������ӿڷ���,�ѹ�����������ݵ������Ĳ���.
	 *�ڰѽӿڵĶ�����Ϊ������Ĳ���,���﹤��������������ӿڷ���,�ѹ�����������ݵ������Ĳ���.
	 *ʹ���߿�����Ϊprogressbar,Ҳ������������,����ź���Ծ͵���,�����Ծ͸���.
	 * @author fada
	 *
	 */
	public interface BackUpProcessListener{
		void beforeBackup(int max);
		void onProcessUpdate(int process);
	}
	
	//OutputStream��ʾ��Ҫһ�������,����xml�ļ�д�ŵ�λ��.
	public void backUpSms(OutputStream os, BackUpProcessListener listener) throws Exception{
		Uri uri = Uri.parse("content://sms/");
		XmlSerializer  serializer = Xml.newSerializer();
		serializer.setOutput(os, "utf-8");
		serializer.startDocument("utf-8", true);
		serializer.startTag(null, "smss");
		Cursor cursor = context.getContentResolver().query(uri, new String[]{"address","date","type","body"} , null, null, null);
		listener.beforeBackup(cursor.getCount());
		int total = 0;
		while(cursor.moveToNext()){
			String address = cursor.getString(0);
			String date  =cursor.getString(1);
			String type  =cursor.getString(2);
			String body  =cursor.getString(3);
			serializer.startTag(null, "sms");
			
			serializer.startTag(null, "address");
			serializer.text(address);
			serializer.endTag(null, "address");

			serializer.startTag(null, "date");
			serializer.text(date);
			serializer.endTag(null, "date");
			
			serializer.startTag(null, "type");
			serializer.text(type);
			serializer.endTag(null, "type");
			
			serializer.startTag(null, "body");
			serializer.text(body);
			serializer.endTag(null, "body");
			
			serializer.endTag(null, "sms");
			
			os.flush();
			total++;
			listener.onProcessUpdate(total);
			Thread.sleep(1000);
		}
		cursor.close();
		serializer.endTag(null, "smss");
		serializer.endDocument();
		os.flush();
		os.close();
		
	}
	
	public void restoreSms(){
		//��ȡxml�ļ�. ��ÿһ�����ŵ����ݻ�ȡ����,���뵽ϵͳ�����ݿ�
	}
	
}
