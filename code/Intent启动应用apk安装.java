//����һ��Apk��file�Ϳ���
protected void installApk(File file) {
//������Ҫ������Activity��filter����ȥ����Intent���ƾͿ���
//		<action android:name="android.intent.action.VIEW" />
//        <category android:name="android.intent.category.DEFAULT" />
//        <data android:scheme="content" />
//        <data android:scheme="file" />
//        <data android:mimeType="application/vnd.android.package-archive" />
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
//		intent.setType("application/vnd.android.package-archive");
//		intent.setData(Uri.fromFile(file));
		//ע��:������Ҫһ������������,һ����uri,һ����type,��Ϊ������Ļ���������ǰһ��������.
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		startActivity(intent);
	}
