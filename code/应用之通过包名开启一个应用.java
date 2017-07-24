/**
	 * ͨ����������һ��Ӧ�ó���
	 */
	private void startApk(String packname) {
		// �������Ӧ�ó�������ĵ�1��activity�Ͱ�Ӧ�ô���.
		try {
			PackageInfo packinfo = getPackageManager().getPackageInfo(packname,PackageManager.GET_ACTIVITIES);//���Ӧ�ð�����
			ActivityInfo[] activityinfos = packinfo.activities;//������е�Activity����
			if(activityinfos!=null&&activityinfos.length>0){
				ActivityInfo activityinfo = activityinfos[0];//��õ�һ��Activity����
				String className = activityinfo.name;//���Activity����
				Intent intent = new Intent();
				intent.setClassName(selectedAppInfo.getPackname(), className);//ͨ��Ӧ�õİ�����Activity�������������ͼ����Ӧ��.
				startActivity(intent);
			}else{
				Toast.makeText(this, "�޷�����Ӧ�ó���!", 0).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "�޷�����Ӧ�ó���", 0).show();
		}

	}