//��ϵ�˶�дȨ��
<uses-permission android:name="android.permission.WRITE_CONTACTS"/>
<uses-permission android:name="android.permission.READ_CONTACTS"/>
import java.util.ArrayList;
import java.util.Collections;
//ע:ɾ����ϵ��ɾ������raw_contacts�µ�contact_id�е�ֵ
import android.content.ContentProviderOperation;
//��ϵ����contacts2���ݿ������ݿ������ű����:data,raw_contacts,mimetypes 
//�Ե���ϵ�˶���,ʵ��������data��ʾ,������raw_contactsԼ��,�ж������ϵ�˾Ͷ���������,����raw_contacts���contact_id�е�ÿһ��ֵ��Ӧһ����ϵ��
//,data�������ϵ�˵���������,��ͨ��mimetypes��ʾmimetype������ϵ�˵���������,�ǵ绰,��������....
public class ContactInfoProvider {
	private static List<ContactInfo> list;//����һ���������洢��ϵ��,ContactInfo��һ����ϵ�˶���

	public static List<ContactInfo> getContactInfos(Context context) {
		//��������ṩ�ߵĲ�����
		ContentResolver resolver = context.getContentResolver();
		list = new ArrayList<ContactInfo>();//��ʼ������.
		//�ò�Ѱ��������id��raw_contacts
		Uri uri=Uri.parse("content://com.android.contacts/raw_contacts");
		//��id��������ƥ�������
		Uri datauri = Uri.parse("content://com.android.contacts/data");
		//ɾ����ϵ��ɾ������raw_contacts�µ�contact_id�е�ֵ
		Cursor cursor = resolver.query(uri, new String[]{"contact_id"}, null, null, null);
		//�ӽ�����л��id
		while (cursor.moveToNext()) {
			String id = cursor.getString(0);//��Ϊֻ��һ��,���ǵ�һ��,���Կ���һֱ��0
			if (id!=null) {
				ContactInfo info = new ContactInfo();//ÿ�ο�ʼ�齨��һ������
				//�鵽������ϵ������
				Cursor cursorData = resolver.query(datauri, new String[]{"mimetype", "data1"}, "raw_contact_id=?", new String[]{id}, null);
				//��������������ɸѡ����Ҫ����ϵ�����ݱ��������,
				String data1=null;
				while(cursorData.moveToNext()) {
					String mimetype = cursorData.getString(0);
					data1 = cursorData.getString(1);
					if (data1==null)//�ж��Ƿ�������
						break;
					//���������ͱ��������������ǵ绰//���������������
					
					if("vnd.android.cursor.item/name".equals(mimetype)){
						info.setName(data1);
					}else if("vnd.android.cursor.item/phone_v2".equals(mimetype)){
						info.setPhone(data1);
					}
				}
				if (data1!=null){
				list.add(info);//����������뼯��.
				}
				cursorData.close();//�ر����ݿ�����
			}
		}
		cursor.close();//�ر����ݿ�����
		return list;
	}
	/**
 * ��ҳ��ѯ��ϵ��
orderby="_id desc limit 30 offset "+?;����ӵ�?����ʼ��,ע��,����������������Ϊ�����������ֱ��limit��ͷ,desc��ʾ����,Ҳ�����������ŷ�
 * @param context ������
 * @param orderby ȫ����null,���Ҫ��ҳ�ͼ�"_id limit �ܹ�������  offset �ӵڼ�����ʼ"
 * @return
 */
	public static List<ContactInfo> getContactInfos(Context context,String orderby) {
		//��������ṩ��
		ContentResolver resolver = context.getContentResolver();
		list = new ArrayList<ContactInfo>();
		//�ò�Ѱ��������id��raw_contacts
		Uri uri=Uri.parse("content://com.android.contacts/raw_contacts");
		//��id��������ƥ�������
		Uri datauri = Uri.parse("content://com.android.contacts/data");
			Cursor cursor = resolver.query(uri, new String[]{"contact_id"}, null, null, orderby);
		return getList(resolver, datauri, cursor);
	}

	private static List<ContactInfo> getList(ContentResolver resolver,
			Uri datauri, Cursor cursor) {
		//�ӽ�����л��id
		while (cursor.moveToNext()) {
			String id = cursor.getString(0);//��Ϊֻ��һ��,���ǵ�һ��,���Կ���һֱ��0
			if (id!=null) {
				Cursor cursorData = resolver.query(datauri, new String[]{"mimetype", "data1"}, "raw_contact_id=?", new String[]{id}, null);
				ContactInfo info = new ContactInfo();
				String data1=null;
				while(cursorData.moveToNext()) {
					String mimetype = cursorData.getString(0);
					data1 = cursorData.getString(1);
					if (data1==null)//�ж��Ƿ�������
						break;
						// ���������ͱ��������������ǵ绰//���������������
						if ("vnd.android.cursor.item/name".equals(mimetype)) {
							info.setName(data1);
						} else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
							info.setPhone(data1);
						}
				}
				if (data1!=null)
				list.add(info);
				cursorData.close();
			}
		}
		cursor.close();
		return list;
	}
	//��ϵ�˵�����д��
	public void testWrite() {
		ContentResolver resolver = getContext().getContentResolver();
		ContentValues values = new ContentValues();
		
		// ��raw_contacts���в���һ��id(�Զ�����)
		Uri resultUri = resolver.insert(rawContactsUri, values);
		long id = ContentUris.parseId(resultUri);
		
		// �øող����id��Ϊraw_contact_id�е�ֵ, ��data���в���3������
		values.put("raw_contact_id", id);
		values.put("mimetype", "vnd.android.cursor.item/name");
		values.put("data1", "FLX");
		resolver.insert(dataUri, values);
		
		values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		values.put("data1", "18600056789");
		resolver.insert(dataUri, values);
		
		values.put("mimetype", "vnd.android.cursor.item/email_v2");
		values.put("data1", "lkp@itcast.cn");
		resolver.insert(dataUri, values);
	}
	
	public void testWriteBatch() throws Exception {
		// ����4��ContentProviderOperation, ����4��insert����
		ContentProviderOperation operation1 = ContentProviderOperation.newInsert(rawContactsUri) //
				.withValue("_id", null) //
				.build();
		ContentProviderOperation operation2 = ContentProviderOperation.newInsert(dataUri) //
				.withValueBackReference("raw_contact_id", 0) // ��ͬ���0�Ų����õ��ķ���ֵ��Ϊֵ����
				.withValue("mimetype", "vnd.android.cursor.item/name") //
				.withValue("data1", "ZZH") //
				.build();
		ContentProviderOperation operation3 = ContentProviderOperation.newInsert(dataUri) //
				.withValueBackReference("raw_contact_id", 0) // ��ͬ���0�Ų����õ��ķ���ֵ��Ϊֵ����
				.withValue("mimetype", "vnd.android.cursor.item/phone_v2") //
				.withValue("data1", "18600098765") //
				.build();
		ContentProviderOperation operation4 = ContentProviderOperation.newInsert(dataUri) //
				.withValueBackReference("raw_contact_id", 0) // ��ͬ���0�Ų����õ��ķ���ֵ��Ϊֵ����
				.withValue("mimetype", "vnd.android.cursor.item/email_v2") //
				.withValue("data1", "zzh@itcast.cn") //
				.build();
		
		// ��4����������װ�뼯��
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
		Collections.addAll(operations, operation1, operation2, operation3, operation4);
		
		ContentResolver resolver = getContext().getContentResolver();
		resolver.applyBatch("com.android.contacts", operations);
	}
}
