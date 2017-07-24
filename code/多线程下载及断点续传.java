package com.itheima.downloader;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultiThreadDownloader {
	
	private URL url;		// Ŀ���ַ
	private File file;		// ����·��
	private long threadLen;	// ÿ���߳����ض���
	
	private static final int THREAD_AMOUNT = 3;				// �߳���
	private static final String DIR_PATH = "/mnt/sdcard/";	// ����Ŀ¼

	public MultiThreadDownloader(String address) throws IOException {	
		url = new URL(address);															// ��ס������ļ����ص�ַ
		file = new File(DIR_PATH, address.substring(address.lastIndexOf("/") + 1));		// ��ȡ��ַ�е��ļ���, ���������ļ�·��
	}

	public void download() throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(3000);
		
		long totalLen = conn.getContentLength();					// ��ȡ�ļ�����
		threadLen = (totalLen + THREAD_AMOUNT - 1) / THREAD_AMOUNT;	// ����ÿ���߳�Ҫ���صĳ���. (100 + 3 - 1) / 3 = 34
		
		RandomAccessFile raf = new RandomAccessFile(file, "rws");	// �ڱ��ش���һ���ͷ���˴�С��ͬ���ļ�
		raf.setLength(totalLen);									// �����ļ��Ĵ�С
		raf.close();
		
		for (int i = 0; i < THREAD_AMOUNT; i++)		// �����߳���ѭ��
			new DownloadThread(i).start();			// �����߳�, ÿ���߳̽�������һ�������ݵ������ļ���
	}
	
	private class DownloadThread extends Thread {
		private int id; 	// ������ǵ�ǰ�߳������������еĵڼ����߳�
		
		public DownloadThread(int id) {
			this.id = id;
		}
		
		public void run() {
			long start = id * threadLen;					// ��ʼλ��
			long end = id * threadLen + threadLen - 1;		// ����λ��. 
			System.out.println("�߳�" + id + ": " + start + "-" + end);
			
			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(3000);
				conn.setRequestProperty("Range", "bytes=" + start + "-" + end);	// ���õ�ǰ�߳����صķ�Χ
				
				InputStream in = conn.getInputStream();							// ��ȡ���ӵ�������, ������ȡ���������
				RandomAccessFile raf = new RandomAccessFile(file, "rws");		// �����д�ļ�, �����򱾵��ļ�д��
				raf.seek(start);												// ���ñ������ݵ�λ��
				
				byte[] buffer = new byte[1024 * 100];	// ÿ�ο���100KB
				int len;
				while ((len = in.read(buffer)) != -1)
					raf.write(buffer, 0, len);			// �ӷ���˶�ȡ����, д�������ļ�
				raf.close();
				
				System.out.println("�߳�" + id + "�������");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

