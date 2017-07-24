package com.itheima.downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Handler;
import android.os.Message;

public class BreakpointDownloader {
	private static final String DIR_PATH = "/mnt/sdcard/";	// ����Ŀ¼
	private static final int THREAD_AMOUNT = 3;				// ���߳���
	
	private URL url;			// Ŀ�����ص�ַ
	private File dataFile;		// �����ļ�
	private File tempFile;		// �����洢ÿ���߳����صĽ��ȵ���ʱ�ļ�
	private long threadLen;		// ÿ���߳�Ҫ���صĳ���
	private long totalFinish;	// �ܹ�����˶���
	private long totalLen;		// ������ļ��ܳ���
	private long begin;			// ������¼��ʼ����ʱ��ʱ��

	private Handler handler;
	private boolean isPause;

	public BreakpointDownloader(String address, Handler handler) throws Exception {
		url = new URL(address);															// ��ס���ص�ַ
		dataFile = new File(DIR_PATH, address.substring(address.lastIndexOf("/") + 1));	// ��ȡ��ַ�е��ļ���, ���������ļ�
		tempFile = new File(dataFile.getAbsolutePath() + ".temp");						// �ڱ����ļ������ļ����д�����ʱ�ļ�
		this.handler = handler;
	}
	
	public void pause() {
		isPause = true;
	}

	public void download() throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(3000);
		
		totalLen = conn.getContentLength();									// ��ȡ����˷��͹������ļ�����
		threadLen = (totalLen + THREAD_AMOUNT - 1) / THREAD_AMOUNT;			// ����ÿ���߳�Ҫ���صĳ���
		
		// �����ļ���С
		Message msg = new Message();
		msg.getData().putLong("totalLen", totalLen);
		msg.what = 1;
		handler.sendMessage(msg);
		
		if (!dataFile.exists()) {											// ��������ļ�������
			RandomAccessFile raf = new RandomAccessFile(dataFile, "rws");	// �ڱ��ش����ļ�
			raf.setLength(totalLen);										// �����ļ��Ĵ�С�ͷ������ͬ
			raf.close();
		}
		
		if (!tempFile.exists()) {											// �����ʱ�ļ�������
			RandomAccessFile raf = new RandomAccessFile(tempFile, "rws");	// ������ʱ�ļ�, ������¼ÿ���߳������ض���
			for (int i = 0; i < THREAD_AMOUNT; i++)							// �����߳���ѭ��
				raf.writeLong(0);											// д��ÿ���̵߳Ŀ�ʼλ��(���Ǵ�0��ʼ)
			raf.close();
		}
		
		for (int i = 0; i < THREAD_AMOUNT; i++)	// �����߳���ѭ��
			new DownloadThread(i).start();		// �����߳�, ÿ���߳̽�������һ�������ݵ������ļ���
		
		begin = System.currentTimeMillis();		// ��¼��ʼʱ��
	}
	
	private class DownloadThread extends Thread {
		private int id; 	// ������ǵ�ǰ�߳������������еĵڼ����߳�
		
		public DownloadThread(int id) {
			this.id = id;
		}
		
		public void run() {
			try {
				RandomAccessFile tempRaf = new RandomAccessFile(tempFile, "rws");		// ������¼���ؽ��ȵ���ʱ�ļ�
				tempRaf.seek(id * 8);						// ��ָ���ƶ�����ǰ�̵߳�λ��(ÿ���߳�д1��longֵ, ռ8�ֽ�)
				long threadFinish = tempRaf.readLong();		// ��ȡ��ǰ�߳�������˶���
				synchronized(BreakpointDownloader.this) {	// ��������߳�֮��ͬ��
					totalFinish += threadFinish;			// ͳ�������߳��ܹ�����˶���
				}
				
				long start = id * threadLen + threadFinish;		// ���㵱ǰ�̵߳���ʼλ��
				long end = id * threadLen + threadLen - 1;		// ���㵱ǰ�̵߳Ľ���λ��
				System.out.println("�߳�" + id + ": " + start + "-" + end);
			
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(3000);
				conn.setRequestProperty("Range", "bytes=" + start + "-" + end);		// ���õ�ǰ�߳����صķ�Χ
				
				InputStream in = conn.getInputStream();								// ��ȡ���ӵ�������
				RandomAccessFile dataRaf = new RandomAccessFile(dataFile, "rws");	// װ�����ݵı����ļ�(�������Ϊ�����)
				dataRaf.seek(start);												// ���õ�ǰ�̱߳������ݵ�λ��
				
				byte[] buffer = new byte[1024 * 100];			// ÿ�ο���100KB
				int len;
				while ((len = in.read(buffer)) != -1) {
					if (isPause)
						return;
					dataRaf.write(buffer, 0, len);				// �ӷ���˶�ȡ����, д�������ļ�
					threadFinish += len;						// ÿ��д������֮��, ͳ�Ƶ�ǰ�߳�����˶���
					tempRaf.seek(id * 8);						// ����ʱ�ļ���ָ��ָ��ǰ�̵߳�λ��
					tempRaf.writeLong(threadFinish);			// ����ǰ�߳�����˶���д�뵽��ʱ�ļ�
					synchronized(BreakpointDownloader.this) {	// ��������߳�֮��ͬ��
						totalFinish += len;						// ͳ�������߳��ܹ�����˶���
						// ���͵�ǰ����
						Message msg = new Message();
						msg.getData().putLong("totalFinish", totalFinish);
						msg.what = 2;
						handler.sendMessage(msg);
					}
				}
				dataRaf.close();
				tempRaf.close();
				
				System.out.println("�߳�" + id + "�������");
				if (totalFinish == totalLen) {					// �������ɳ��ȵ��ڷ�����ļ�����(�����������)
					System.out.println("�������, ��ʱ: " + (System.currentTimeMillis() - begin));
					tempFile.delete();							// ɾ����ʱ�ļ�
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

