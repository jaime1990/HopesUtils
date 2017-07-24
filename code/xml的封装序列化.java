xml���л��ķ�װ
1.����װҪ����������:
	//����٣�ѧЭ�飨���磩 ����ˡ���2.5��
	//����ڣ�����һ��                                     2.5��
	//����ۣ�100�����󡪡��״�
	//        ��ȡ:��ͬ�̶ȳ�ȡ��������ܶ�
	//����ܣ�Э����
		
2.��װ��:		
	//��ȡ����ȡ�����ϵ����ⲻ������
	//һ����
	//һ�ݴ���
	//��������
3.����:
<?xml version='1.0' encoding='utf-8' standalone='no' ?>
<message version="1.0">
	<header>
		<agenterid>889931</agenterid>
		<source>ivr</source>
		<compress>DES</compress>
		<messengerid>20130106075159469857</messengerid>
		<timestamp>20130106075159</timestamp>
		<digest>c87434208b1737fe0a23bd0b20fd9567</digest>//����body��
		<transactiontype>14001</transactiontype>
		<username>1320000000</username>//�����ÿ������һ����,�û���
	</header>
	<body>
		cgYNf1rUkTlcXIFjWR1NnvH8iusYVv9RnPZwf1jwKdNUi4J/Re9gua2WYHkkzkn+5fnTxCH6QeQ7azLAVYgQTw4S8p5XqRVHdFxQBzWp5bA=//���Ǽ��ܺ��body����
	</body>
</message>
1.������С���ӱ�ǩ,��:<agenterid>889931</agenterid>
/**
 * Ҷ�ӽڵ�
 * 
 * @author Administrator
 * 
 */
public class Leaf {
	private String tagName;
	private String value;

	public Leaf(String tagName, String value) {
		super();
		this.tagName = tagName;
		this.value = value;
	}

	public Leaf(String tagName) {
		super();
		this.tagName = tagName;
	}

	public void serializer(XmlSerializer serializer) {
		try {
			serializer.startTag(null, tagName);
			// value=null
			if (StringUtils.isBlank(value)) {
				value = "";
			}
			serializer.text(value);
			serializer.endTag(null, tagName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTagName() {
		return tagName;
	}
}
2.����header
/**
 * ͷ��Ϣ��װ
 * 
 * @author Administrator
 * 
 */
public class Header {
	private Leaf agenterid = new Leaf("agenterid", ConstantValue.AGENTERID);
	private Leaf source = new Leaf("source", ConstantValue.SOURCE);
	private Leaf compress = new Leaf("compress", ConstantValue.COMPRESS);

	private Leaf messengerid = new Leaf("messengerid");
	private Leaf timestamp = new Leaf("timestamp");
	private Leaf digest = new Leaf("digest");

	private Leaf transactiontype = new Leaf("transactiontype");
	private Leaf username = new Leaf("username");

	public void serializer(XmlSerializer serializer, String body) {
		// ʱ���
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// �����
		Random random = new Random();
		int num = random.nextInt(999999) + 1;
		// ��λ�����
		DecimalFormat format = new DecimalFormat("000000");
		String nums = format.format(num);

		messengerid.setValue(time + nums);
		timestamp.setValue(time);

		// MD5��ʱ���+���루�Ӵ����̣�+body

		String md5Info = time + ConstantValue.AGENTER_PASSWORD + body;
		String md5=DigestUtils.md5Hex(md5Info);
		digest.setValue(md5);//����ϢժҪ����������.
		
		try {
			serializer.startTag(null, "header");

			agenterid.serializer(serializer);
			source.serializer(serializer);
			compress.serializer(serializer);

			messengerid.serializer(serializer);
			timestamp.serializer(serializer);
			digest.serializer(serializer);

			transactiontype.serializer(serializer);
			username.serializer(serializer);
			serializer.endTag(null, "header");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//ÿ��xml���������һ����ͬ
	public Leaf getTransactiontype() {
		return transactiontype;
	}

	public Leaf getUsername() {
		return username;
	}
}
3.����body�Ķ����ӱ�ǩ������
/**
 * ������Ϣ��װ
 */
public abstract class Element {
	// ���������õ�����35
	/**
	 * �����������ݵ����л�
	 */
	public abstract void serializer(XmlSerializer serializer);
	
	/**
	 * ÿ�������ʾ�Ļ�ȡ
	 * @return
	 */
	public abstract String getTransactiontype();
}
4.���������ӱ�ǩ
/**
 * �û���¼
 * @author Administrator
 *
 */
public class UserLoginElement extends Element {
	private Leaf actpassword=new Leaf("actpassword");
	@Override
	public void serializer(XmlSerializer serializer) {
		try {
			serializer.startTag(null, "element");
			actpassword.serializer(serializer);
			serializer.endTag(null, "element");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getTransactiontype() {
		return "14001";
	}
	public Leaf getActpassword() {
		return actpassword;
	}

}
5.����body
/**
 * ��Ϣ���װ
 * 
 * @author Administrator
 * 
 */
public class Body {
	private List<Element> elements = new ArrayList<Element>();

	public void serializer(XmlSerializer serializer) {
		try {
			serializer.startTag(null, "body");
			serializer.startTag(null, "elements");
			for (Element item : elements) {
				item.serializer(serializer);
			}
			serializer.endTag(null, "elements");
			serializer.endTag(null, "body");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Element> getElements() {
		return elements;
	}
	
	
	//����һ��header������MD5��Ϣ����ʱ��Ҫ�õ�body
	
	public String getBodyInfo()
	{
		try {
			XmlSerializer temp=Xml.newSerializer();
			StringWriter writer=new StringWriter();
			temp.setOutput(writer);
			this.serializer(temp);
			temp.flush();
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	//���������Ҫ�������õģ�<elements></elements>������DES����
	//DES���ܣ��ӷ������õ�DES.java
	
	public String getElementsDESInfo()
	{
		String bodyInfo = getBodyInfo();
		//<elements></elements>
		String elementsInfo=StringUtils.substringBetween(bodyInfo, "<body>", "</body>");
		//DESʹ��
		DES des=new DES();
		return des.authcode(elementsInfo, "DECODE", ConstantValue.DES_PASSWORD);
	}
}
6.����������Ϣ��
/**
 * ��Ϣ��װ
 * 
 * @author Administrator
 * 
 */
public class Message {
	private Header header = new Header();
	private Body body = new Body();

	private void serializer(XmlSerializer serializer) {
		try {
			serializer.startTag(null, "message");
			serializer.attribute(null, "version", "1.0");

			Element element = body.getElements().get(0);

			header.getTransactiontype().setValue(element.getTransactiontype());

			header.serializer(serializer, body.getBodyInfo());// ����<body>......</body>
			Log.i("Message", body.getBodyInfo());
			// body.serializer(serializer);
			
			serializer.startTag(null, "body");
			serializer.text(body.getElementsDESInfo());
			serializer.endTag(null, "body");
			

			serializer.endTag(null, "message");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * ��ָ��������д��xml����
 * @param element
 * @return
 */
	public String getXml(Element element) {
		// ��������ӵ�body
		body.getElements().add(element);

		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("utf-8", false);

			this.serializer(serializer);

			serializer.endDocument();

			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public Header getHeader() {
		return header;
	}
}
7.�����÷�.
public void testGetXml(){
	UserLoginElement element=new UserLoginElement();
	element.getActpassword().setValue("00000000");	//������	
	Message message=new Message();	
	message.getHeader().getUsername().setValue("1320000000");//���û���.
	String xml=message.getXml(element);
}

