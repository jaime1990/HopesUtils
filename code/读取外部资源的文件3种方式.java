//��androidϵͳ��ʹ���ⲿ��Դ�ļ��ַ�ʽ:

//1. res - raw Ŀ¼��
InputStream is = getResources().openRawResource(R.raw.address);


//2. assets �ʲ�Ŀ¼.
InputStream is = getAssets().open("address.db");
��2.3 �Ժ����ȫ֧��.
ģ���� 2.2 ֮ǰ���������1M����Դ.


//3. ����srcĿ¼��,���������ʽ.jar�����������.
InputStream is  =  getClassLoader().getResourceAsStream("address.db");


//4. ���ⷽ��.���������������ļ���sd��.

//�����������,д�����.
public class CopyUtils {
//��Ҫһ���ļ�Ŀ¼absolutePath,��һ���ļ�getAbsolutePath���ܻ��
	File file=new File(getFilesDir(),"address.db");
	String absolutePath =file.getAbsolutePath();
	public static File copyAddress(InputStream addressIn, String absolutePath) {
		OutputStream out;
		File file = new File(absolutePath);
		try {
			out = new FileOutputStream(file);
			int len = -1;
			byte[] buffer = new byte[1024];
			while ((len=addressIn.read(buffer))!= -1) {
				out.write(buffer, 0, len);
				out.flush();
			}
			out.close();
			addressIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return file;

	}
}