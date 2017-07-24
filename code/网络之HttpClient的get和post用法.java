//Android HttpClient����ʹ�÷���
httpclientgetpost 
����ֻ�������ʹ��HttpClient����GET����POST����

1.GET��ʽ
//�Ƚ���������List���ٶԲ�������URL����
List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
params.add(new BasicNameValuePair("param1", "�й�"));
params.add(new BasicNameValuePair("param2", "value2"));

//�Բ�������
String param = URLEncodedUtils.format(params, "UTF-8");

//baseUrl			
String baseUrl = "http://ubs.free4lab.com/php/method.php";

	//��URL�����ƴ��
	HttpGet getMethod = new HttpGet(baseUrl + "?" + param);
	HttpParams params = new BasicHttpParams();// 
	HttpConnectionParams.setConnectionTimeout(params, 8000);   //���ӳ�ʱ
	HttpConnectionParams.setSoTimeout(params, 5000);   //��Ӧ��ʱ
	getMethod.setParams(params);//���Ʋ���
	getMethod.setHeaders(headers);//����������ͷ
	
HttpClient httpClient = new DefaultHttpClient();

try {
    HttpResponse response = httpClient.execute(getMethod); //����GET����

    Log.i(TAG, "resCode = " + response.getStatusLine().getStatusCode()); //��ȡ��Ӧ��
    Log.i(TAG, "result = " + EntityUtils.toString(response.getEntity(), "utf-8"));//��ȡ��������Ӧ����
} catch (ClientProtocolException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
2.POST��ʽ
public  static BasicHeader[] headers = new BasicHeader[10];
	static {
		headers[0] = new BasicHeader("Appkey", "12343");
		headers[1] = new BasicHeader("Udid", "");//�ֻ�����
		headers[2] = new BasicHeader("Os", "android");//
		headers[3] = new BasicHeader("Osversion", "");//
	}
//��GET��ʽһ�����Ƚ���������List
params = new LinkedList<BasicNameValuePair>();
params.add(new BasicNameValuePair("param1", "Post����"));
params.add(new BasicNameValuePair("param2", "�ڶ�������"));
			
try {
    HttpPost postMethod = new HttpPost(baseUrl);
	HttpParams params = new BasicHttpParams();// 
	HttpConnectionParams.setConnectionTimeout(params, 8000);   //���ӳ�ʱ
	HttpConnectionParams.setSoTimeout(params, 5000);   //��Ӧ��ʱ
	postMethod.setParams(params);//���Ʋ���
	postMethod.setHeaders(headers);//����������ͷ
    postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8")); //����������POST Entity��
				
    HttpResponse response = httpClient.execute(postMethod); //ִ��POST����
    Log.i(TAG, "resCode = " + response.getStatusLine().getStatusCode()); //��ȡ��Ӧ��
    Log.i(TAG, "result = " + EntityUtils.toString(response.getEntity(), "utf-8")); //��ȡ��Ӧ����
				
} catch (UnsupportedEncodingException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (ClientProtocolException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}