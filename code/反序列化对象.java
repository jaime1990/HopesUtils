�����л�����.����Ҫʵ��Serializeable�ӿ� ��ԭ������Ľṹ�����Ա仯,����,����������һ��,
	File file=new File("C:/sms.dat");
	ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()));
	Object object = in.readObject();
	List<SmsName> sms=(List<SmsName>) object;
	for (int i = 0; i < sms.size(); i++) {
		System.out.println(sms);
	}