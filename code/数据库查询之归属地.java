//ͨ�����ݿ��ѯ������
public class AddressDao {

	public static final String path = "/data/data/com.example.safe/files/address.db";

	public static String getAddress(String num) {
		String address="";
		SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null,
				SQLiteDatabase.OPEN_READONLY);
		//�����ֿ���,һ�����ֻ�����11λ,һ���ǹ̻����10λ
		//������ֻ�����
		if (num.matches("^1[3458]\\d{9}$")) {
			Cursor cursor = database
					.rawQuery(
							"select location from data2 where id=(select outkey from data1 where id=?)",
							new String[] { num.substring(0, 7) });

			if (cursor.moveToNext()) {
				address = cursor.getString(0);
				database.close();
			}
			//������ʣ�����ֿ���
		} else {
			//һ��С��10λ��
			switch (num.length()) {
			case 3:
				address = "�������";
				break;
			case 4:
				address = "ģ����";
				break;
			case 5:
				address = "�������";
				break;
			case 7:
					address = "���غ���";
				break;
			case 8:
				address = "���غ���";
				break;
			default:
				//�������������������,�Һ����ִ��ڵ���10λ,����0��β��ô���ǹ̻���.
				if (num.length() >= 10 && num.startsWith("0")) {
					// ��������λ��:ȥ��ǰ��λ
					Cursor cursor = database.rawQuery(
							"select location from data2 where area =?",
							new String[] { num.substring(1, 3) });
					if (cursor.moveToNext()) {
						String text = cursor.getString(0);
						address = text.substring(0, text.length() - 2);
					}
					cursor.close();
					// ��������λ��.ȥ��ǰ��λ
					cursor = database.rawQuery(
							"select location from data2 where area =?",
							new String[] { num.substring(1, 4) });
					if (cursor.moveToNext()) {
						String text = cursor.getString(0);
						address = text.substring(0, text.length() - 2);
					}
					cursor.close();
				}
				break;
			}
		}
		return address;

	}
}