�����ṩ��֮��ȡͨ����¼
//��ϵ�˶�дȨ��
<uses-permission android:name="android.permission.WRITE_CONTACTS"/>
<uses-permission android:name="android.permission.READ_CONTACTS"/>
//Ҫ�������ṩ������Ҫ��Ҫ��ȡUri ������������ṩ�ߵ�����authorities,����Ƕ��������ṩ��ʱ�����,Ҫ��ȡ��Ҫ���Ӧ�������ṩ�õ�Manifest.xml�еĶ���
//D:\Android\Android_src\packages\providers\ContactsProvider�µ�AndroidManifest.xml�в��ҷ�����:
 <provider android:name="CallLogProvider"
            android:authorities="call_log"//������������ṩ�ߵ�����
            android:syncable="false" android:multiprocess="false"
            android:readPermission="android.permission.READ_CONTACTS"
            android:writePermission="android.permission.WRITE_CONTACTS">
   </provider>
   //��ȡ��֮��,���Ǿ���Ҫ�������ݿ��ж���ı���,�Ǿ��������ű�,�����Ҫ�������ݿ��ļ���������
   //��������ϵ����ص����ݶ�������data/data/com.android.providers.contects/databases�µ������ļ���.
   //��calls�����ͨ����¼�ı�,����number�б�ʾ�绰���� date�б�ʾʱ��long�� duration��ʾͨ��ʱ��
	// type��ʾͨ������,1��ʾ����,2��ʾ����.δ�ӱ�ʾ3.name��ʾ��ϵ�˵�����
   //һ�����,�����ṩ�߶��ᶨ����ƥ����UriMatcher,�����ѯ���е����� ,����:CallLogProvider����:
   private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(CallLog.AUTHORITY, "calls", CALLS);//��ͱ�ʾ���ű��������calls
        sURIMatcher.addURI(CallLog.AUTHORITY, "calls/#", CALLS_ID);
        sURIMatcher.addURI(CallLog.AUTHORITY, "calls/filter/*", CALLS_FILTER);
    }
	
//dao����ʵ��
public class CallLogDao {
	private  ContentResolver resolver ;
	private  Uri uri=Uri.parse("content://call_log/calls");
	public  CallLogDao(Context context){
		resolver = context.getContentResolver();
	}
	/**
	 * ��ѯ���к��м�¼
	 * @return ������ϵ��ͨ����¼�ļ���
	 */
public   List<MyCallLog> getCallog(){
	List<MyCallLog> list=new ArrayList<MyCallLog>();
	//��uri�������ĸ�����,����ʾString[] projection(�����ļ���), String selection(��ʾwhere�������:_id=?), 
	//String[] selectionArgs(ǰ��ռλ��������), String sortOrder(����ͷ�ҳ));
	Cursor cursor = resolver.query(uri, new String[]{"number","date","duration","type","name"}, null, null, null);
	while (cursor.moveToNext()) {
		MyCallLog callog=new MyCallLog();
		String phone = cursor.getString(0);
		callog.setPhone(phone);
		String time = cursor.getString(1);
		callog.setTime(time);
		String duration = cursor.getString(2);
		callog.setDuration(duration);
		String type = cursor.getString(3);
		callog.setType(type);
		String name = cursor.getString(4);
		callog.setType(name);
		list.add(callog);
	}
	return list;
}
/**
 * ͨ�������ѯĳһ����ϵ�˼�¼
 * @param incomingNumber
 * @return �����ϵ��ͨ����¼�ļ���
 */
public   List<MyCallLog> findCallogByNumber(String incomingNumber){
	List<MyCallLog> list=new ArrayList<MyCallLog>();
	Cursor cursor = resolver.query(uri, new String[]{"date","duration","type","name"}, "number=?", new String[]{incomingNumber}, null);
	while (cursor.moveToNext()) {
		MyCallLog callog=new MyCallLog();
		callog.setPhone(incomingNumber);
		String time = cursor.getString(0);
		callog.setTime(time);
		String duration = cursor.getString(1);
		callog.setDuration(duration);
		String type = cursor.getString(2);
		callog.setType(type);
		String name = cursor.getString(3);
		callog.setType(name);
		list.add(callog);
	}
	return list;
}
	 // ͨ������ɾ�����м�¼	 
	public void deleteCallLog(String incomingNumber) {
		Uri uri = Uri.parse("content://call_log/calls");
		resolver.delete(uri, "number=?", new String[] { incomingNumber });
	}
}
   
