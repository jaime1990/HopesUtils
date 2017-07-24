//���л��洢��Զ��ԱȽϰ�ȫ,����Ҳ�Ƚϼ�,ֻҪ�Ѷ���ʵ�����л��ӿھͿ���,�����Դ漯��,����.��Object���Ϳ���,������������ֻ����һ��Ӧ����.
public class CallLogDao {
	private  ContentResolver resolver ;
	private  Uri uri=Uri.parse("content://call_log/calls");
	public  CallLogDao(Context context){
		resolver = context.getContentResolver();
	}	
	//��չ:�����ṩ���ݵĽ�������,������ʲô��ʽչ�ֽ����ɵ����߾���,����ǻص�����.
	public interface BackUpProcessListener{
		void beforeBackup(int max);
		void onProcessUpdate(int process);
	}
	
	//��ȡ����,�����л���SD����
	public void backUpSms(BackUpProcessListener listener) throws Exception{
		Uri uri = Uri.parse("content://sms/");
		Cursor cursor = resolver.query(uri, new String[]{"address","date","type","body"} , null, null, null);
		listener.beforeBackup(cursor.getCount());
		int total = 0;
		List<MySms> list=new ArrayList<MySms>();
		while(cursor.moveToNext()){
			MySms sms=new MySms();
			String address = cursor.getString(0);
			String date  =cursor.getString(1);
			String type  =cursor.getString(2);
			String body  =cursor.getString(3);
			sms.setAddress(address);
			sms.setBody(body);
			sms.setDate(date);
			sms.setType(type);
			list.add(sms);
			total++;
			listener.onProcessUpdate(total);
		}
		
			File file=new File(Environment.getExternalStorageDirectory(), "sms.dat");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()));
			out.writeObject(list); // !!!!!!�����������д���ϡ�����
			out.close();
			cursor.close();
	}
	
	/**
	 * �����л���ȡ����
	 * ע:�����л��Ĺؽ�����,֮ǰ���л��Ķ������������,�Ұ���������һ��,��Ҳ�Ƿ����л��ľ�����,����Ҫ��ͬһ��Ӧ����,����û�������.
	 * @throws Exception
	 */
	public void writeSms() throws Exception{
		File file=new File(Environment.getExternalStorageDirectory(), "sms.dat");
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()));
		//��ü��϶���
		Object object = in.readObject();
		List<MySms> sms=(List<MySms>) object;
			for (int i = 0; i < sms.size(); i++) {
				//��ö���
				MySms mysms=sms.get(i);
				System.out.println(mysms.getAddress());
				System.out.println(mysms.getBody());
			}
			in.close();
		}
}
