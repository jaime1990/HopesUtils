//����ֻ�sim�����к�
public  String getSimNum() {
	TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
	String simnum = tm.getSimSerialNumber();
	}