/**
	 * ����Ӧ��.
	 */
	private void shareApk() {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.SEND");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setType("text/plain");
		//���������Ϣ��㶨��
		intent.putExtra(Intent.EXTRA_TEXT,"�Ƽ���ʹ��һ�����.����Ϊ:" + selectedAppInfo.getAppname() + ",�汾:"+ selectedAppInfo.getVersion());
		startActivity(intent);
	}