6.������п���������Ӧ����
public List<String> getStartupPackname() {
		//��ѯ�ֻ������Ӧ�ó������ͼ������, ���ĸ�Ӧ�ó��������� android.intent.action.BOOT_COMPLETED,������ͱ�ʾ�Ὺ������.
		List<String> packnames=new ArrayList<String>();
		PackageManager pm = getPackageManager();
		Intent intent = new Intent("android.intent.action.BOOT_COMPLETED");
		//���
		List<ResolveInfo> infos = pm.queryBroadcastReceivers(intent, PackageManager.GET_INTENT_FILTERS);
		for(ResolveInfo info : infos){
			String receivername = info.activityInfo.name;//��ù㲥�����ߵ�����
			String packname = info.activityInfo.packageName;//����
			packnames.add(packname);
		}
		return packnames;
}