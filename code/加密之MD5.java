����֮MD5
public class Md5Util {
	/**
	 * md5���ܵķ��� 
	 * @param text
	 * @return
	 */
	public static String encode(String text){
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] result = digest.digest(text.getBytes());
			StringBuilder sb = new StringBuilder();
			for(byte b : result){
				int number = b&0xff; //����Ϊ�˷�ֹ��վ�ƽ�,���ڱ�����������ٶ�����(����),������ֻ���������ܽ�����,������ԿҪ��ס.
				String hex = Integer.toHexString(number);
				if(hex.length()==1){
					sb.append("0");
				}
				sb.append(hex);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
}