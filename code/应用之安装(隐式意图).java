 /* ��װapk����,���ڰ�װ���غõ�Apk
	 * @param file
	 */
	protected void installApk(File file) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		//ע��:������Ҫһ������������,һ����uri,һ����type,��Ϊ������Ļ������.���ǰһ��������.
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		startActivity(intent);
	}