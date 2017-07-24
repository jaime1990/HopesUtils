/**
	 * �ͷ�Assets�µ����ݿ��ļ���Ӧ�õ�data/FileĿ¼
	 */
	private void copyAddressDB() {
		final File file = new File(getFilesDir(), "address.db");
		if (file.exists() && file.length() > 0) {
			//do nothing
		} else {
			new Thread() {
				public void run() {
					Message msg = Message.obtain();
					try {
						InputStream is = getAssets().open("address.db");
						File f = CopyFileUtils.copyFile(is, file.getAbsolutePath());
						if(f!=null){
							// �����ɹ�.
						}else{
							msg.what = COPY_ERROR;
						}
					} catch (Exception e) {
						e.printStackTrace();
						msg.what = COPY_ERROR;
					}finally{
						handler.sendMessage(msg);
					}
				};
			}.start();
		}
	}