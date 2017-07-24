/**
	 * �����ļ�����
	 * 
	 * @param serverPath
	 *            �������ļ���·��
	 * @param savedPath
	 *            ���ر����·��
	 * @param pd �������Ի���
	 * @return ���سɹ� �����ļ����� ����ʧ�� ����null
	 */
	 //������Activity��ȡ��Щ����,�ٴ�����
public void onClick(DialogInterface dialog, int which) {
//����һ������������
	ProgressDialog pd = new ProgressDialog(SplashActivity.this);
		pd.setTitle("��������");
		pd.setMessage("��������...");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//���ƽ�������ʽ:�����ʾ������
		pd.show();//�ǵ�Ҫshow!
		//����һ���ļ�,ͨ��sd��·�����ļ��� 
		String apkurl = updateInfo.getApkurl();
		final File file = new File(Environment.getExternalStorageDirectory(),apkurl.substring(apkurl.lastIndexOf("/")+1);
		// �ж�sd���Ƿ����,��ΪҪ���ص�sd��,�ǵø����������sdд��Ȩ��.
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			//������ʶ������߳�	
			new Thread() {
				public void run() {
					File savedFile = DownLoadUtil.download(updateInfo.getApkurl(),file.getAbsolutePath(),pd);
					Message msg = Message.obtain();
					if(savedFile!=null){
						//���سɹ�,��message���ݸ�handlerȥ����װ����
						msg.what = DOWNLOAD_SUCCESS;
						msg.obj = savedFile;
					}else{
						//����ʧ��,����һ���������handler
						msg.what = DOWNLOAD_ERROR;						
					}
					handler.sendMessage(msg);
					pd.dismiss();//һ��Ҫ�ǵ��ύ!
				};
			}.start();
		}else{
			Toast.makeText(getApplicationContext(), "sd��������", 0).show();
			loadMainUI();
		}
}
//���صķ���
public static File download(String serverPath, String savedPath, ProgressDialog pd) {
		try {
			URL url = new URL(serverPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {
				pd.setMax(conn.getContentLength());//�趨�����������ֵ
				InputStream is = conn.getInputStream();
				File file = new File(savedPath);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				int total = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					total +=len;
					pd.setProgress(total);//���ƽ�������ʱ����,���䶯̬�仯
					Thread.sleep(20);
				}
				fos.flush();//�ǵû�ˢ
				fos.close();
				is.close();
				return file;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}