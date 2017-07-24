//��ó��򻺴� �����С  ���ݴ�С.
//.ע:android.content.pmaidl���� ��Ҫ����aidl�ļ� IPackageStatsObserver.aidl ��PackageStats.aidl ��������������Ӧ��Ŀ¼��.
private void getPackageSize(String packname) {
	try {
		//ͨ������ȥ����PackageManager��getPackageSizeInfo���ط���ȥ��û����С.���ݴ�С,�����С
		Method method = PackageManager.class.getMethod(
				"getPackageSizeInfo", new Class[] { String.class,IPackageStatsObserver.class });
		//���������Ҫ��������һ���ǰ���,һ����IPackageStatsObserver���������
		method.invoke(pm,new Object[] { packname, new MyObserver(packname) });
	} catch (Exception e) {
		e.printStackTrace();
	}
}
		//ͨ��aidl��IPackageStatsObserver�ഫ�ݹ���,�̳�Stub��
private class MyObserver extends IPackageStatsObserver.Stub {
	private String packname;
	public MyObserver(String packname) {
		this.packname = packname;
	}
	//�����invoke����getPackageSizeInfoһ��ִ�гɹ�,�ͻ�����������.
	@Override
	public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
			throws RemoteException {
		long cachesize = pStats.cacheSize;
		long codesize = pStats.codeSize;
		long datasize = pStats.dataSize;
	}
}