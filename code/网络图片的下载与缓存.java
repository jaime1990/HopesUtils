package com.itheima.image;

import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
/*
����ͼƬ������,��ΪImageView����ͼƬ��Ҫһ��Bitmap,����Ҫ��ͼƬ��ת��Bitmap����
        ImageView imageIV = (ImageView) findViewById(R.id.imageIV);
        
// ����һ�����߳�, �жϻ����ļ��д�С, �������һ��ָ����ֵ, ɾ��һЩ�Ƚ��ϵ�ͼƬ
    }   
    public void go(View view) {
    	try {
			String address = addressET.getText().toString();
			ImageService service = new ImageService(this);
			Bitmap bm = service.getImage(address);//��ȡbitmap����
			imageIV.setImageBitmap(bm);����ͼƬ
*/
public class ImageService {
	private Context context;

	public ImageService(Context context) {
		this.context = context;
	}

	public Bitmap getImage(String address) throws Exception {
		URL url = new URL(address);		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();	
		conn.setConnectTimeout(3000);
		
		File cacheFile = new File(context.getCacheDir(), URLEncoder.encode(address));		
		if (cacheFile.exists()) {
			conn.setIfModifiedSince(cacheFile.lastModified());	// ��ȡ�ļ�������޸�ʱ��, ��Ϊ����ͷ
		}
		
		int code = conn.getResponseCode();
		System.out.println(code);
		if (code == 200) {
			byte[] data = read(conn.getInputStream());							// ��ȡ�����д�ص�����
			Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);		// ���ֽ����ݽ���ΪͼƬ
			bm.compress(CompressFormat.JPEG, 100, new FileOutputStream(cacheFile));	// �洢ͼƬ������, ��������. �������߳��д���
			return bm;	
		} else if (code == 304) {
			Bitmap bm = BitmapFactory.decodeFile(cacheFile.getAbsolutePath());		// ��ȡcacheFile, ����Bitmap
			return bm;
		}
		
		throw new NetworkErrorException("���ӳ���: " + code);
	}
	//��������ת���ֽ�
	public  byte[] read(InputStream in) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) != -1)
			baos.write(buffer, 0, len);
		in.close();
		baos.close();
		return baos.toByteArray();
	}
}
