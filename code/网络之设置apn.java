// APN(Access Point Name)��������������ơ���������ͨ���ֻ�����ʱ�������õ�һ��������
// �������������ֻ�ͨ�����ֽ��뷽ʽ����������,������ʶGPRS��ҵ�����࣬
// Ŀǰ��Ϊ�����ࣺCMWAP/UNIWAP/3GWAP(ͨ��GPRS����WAPҵ��)��CMNET/UNINET/3GNET������WAP����ķ���Ŀǰ����CMNET,���������������ȣ���
ContentValues values = new ContentValues();
values.put(NAME, "CMCC cmwap");//��Ϊ�ƶ�CMCC��������
values.put(APN, "cmwap");
values.put(PROXY, "10.0.0.172");

values.put(PORT, "80");
values.put(MMSPROXY, "");
values.put(MMSPORT, "");
values.put(USER, "");
values.put(SERVER, "");
values.put(PASSWORD, "");
values.put(MMSC, "");         
values.put(TYPE, "");
values.put(MCC, "460");
values.put(MNC, "00");
values.put(NUMERIC, "46000");
reURI = getContentResolver().insert(Uri.parse("content://telephony/carriers"), values);


//��ѡ�����"content://telephony/carriers/preferapn"