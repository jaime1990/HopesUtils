package com.itheima.xml;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class PersonService {

	/**
	 * ͨ��һ������XML�ļ���������, ������һ��List����, ���а�������Person����
	 * @param in	����XML�ļ���������
	 * @return		����Person����ļ���, ���������������XML������Person����, ��ô��õ�һ��sizeΪ0�ļ���
	 */
	public List<Person> getPersons(InputStream in) throws Exception{
		List<Person> persons = new ArrayList<Person>();
		Person p = null;
		
		XmlPullParser parser = Xml.newPullParser();		// �õ�����������
		parser.setInput(in, "UTF-8");					// ����������
		
		for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT; type = parser.next()) {	// ����XML����Person����, װ�뼯��
			if (type == XmlPullParser.START_TAG) {				// ����ǿ�ʼ��ǩ�¼�
				if ("person".equals(parser.getName())){			// ��ʼ����person
					p = new Person();							// ����Person����
					String id = parser.getAttributeValue(0);	// ��ȡid����
					p.setId(Integer.parseInt(id));				// ����ΪPerson��id
					persons.add(p);								// װ�뼯��
				} else if ("name".equals(parser.getName())){	// ��ʼ����name
					String name = parser.nextText();			// ��ȡ��һ���ı�
					p.setName(name);							// ����ΪPerson��name
				} else if ("age".equals(parser.getName())){		// ��ʼ����age
					String age = parser.nextText();				// ��ȡ��һ���ı�
					p.setAge(Integer.parseInt(age));			// ����ΪPerson��age
				}
			}
		}
		
		return persons;
	}
	//��ʽ��:��whileѭ��
		/**
	 * �������������صĸ�����Ϣ
	 * @param is ���������ص���
	 * @return ��������쳣 ����null;
	 */
	public static UpdateInfo getUpdateInfo(InputStream is) {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(is, "utf-8");
			int type = parser.getEventType();
			UpdateInfo info = null;
			while(type!=XmlPullParser.END_DOCUMENT){
				switch (type) {
				case XmlPullParser.START_TAG:
					if("info".equals(parser.getName())){
						info = new UpdateInfo();
					}else if("version".equals(parser.getName())){
						info.setVersion(parser.nextText());
					}else if("description".equals(parser.getName())){
						info.setDescription(parser.nextText());
					}else if("apkurl".equals(parser.getName())){
						info.setApkurl(parser.nextText());
					}
					break;
				}
				type = parser.next();
			}
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
	

	/**
	 * ��ָ���������д��XML����, XML����Ϊָ�������е�Person��Ϣ,д����Ҫ�԰�������е�xml��������,д��һ��xml�ı�������
	 * @param fos		�����.
	 * @param persons	Person����
	 * @throws Exception 
	 */
	public void writePersons(FileOutputStream fos, List<Person> persons) throws Exception {
		XmlSerializer serializer = Xml.newSerializer();		// ��ȡ���л�����
		serializer.setOutput(fos, "UTF-8");					// ���������
		
		serializer.startDocument("UTF-8", true);	// ��ʼ�ĵ�
		serializer.startTag(null, "persons");		// ��ʼ��ǩ
		
		for (Person p : persons) {
			serializer.startTag(null, "person");
			serializer.attribute(null, "id", p.getId().toString());		// ��������
			
			serializer.startTag(null, "name");
			serializer.text(p.getName());			// �����ı�
			serializer.endTag(null, "name");
			
			serializer.startTag(null, "age");
			serializer.text(p.getAge().toString());
			serializer.endTag(null, "age");
			
			serializer.endTag(null, "person");
		}		
		serializer.endTag(null, "persons");			// ������ǩ
		serializer.endDocument();					// �����ĵ�
	}
	
}
