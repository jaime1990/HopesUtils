//��������PackageManager���÷�
//int flag��ʶ���÷�.�����ʶ���ڱ�ʶҪ�������Ӧ�õ�ʲô��Ϣ.
1.GET_ACTIVITIES:	PackageInfo��־�������йػ���ڻ�İ���
2.GET_CONFIGURATIONS:	�й�Ӳ����ϲ����PackageInfo.configPreferences��Ҫ��Ĺ���PackageInfo.reqFeatures PackageInfo��־�����ص���Ϣ ��
3.GET_DISABLED_COMPONENTS:	PackageInfo��־���ڷ��ص���Ϣ�а������õ������
4.GET_GIDS:	PackageInfo��־��������Ӧ�ó�������� ��ID��
5.GET_INSTRUMENTATION:	PackageInfo��־�����ص���Ϣ���������Ǳ��е� ��
6.GET_INTENT_FILTERS	PackageInfo��־���֧�ֵ���ͼ�������ķ�����Ϣ��
7.GET_META_DATA	ComponentInfo��־������Ԫ���ݵ� ���ݰ� s��ʾ����������ġ�
8.GET_PERMISSIONS	PackageInfo��־�����ص���Ϣ�ڰ��е�Ȩ�޵�Ȩ�� ��
9.GET_PROVIDERS	PackageInfo��־�����ذ����ṩ��Ϣ�������ṩ�� ��
10.GET_RECEIVERS	PackageInfo��־�����е���ͼ�������ڽ������ķ�����Ϣ ��
11.GET_RESOLVED_FILTER	ResolveInfo��־���˻ص�IntentFilter�ģ���ƥ��Ĺ�������һ���ض���ResolveInfo ��
12.GET_SERVICES	PackageInfo��־�����ص���Ϣ�����ڷ�����еġ�
13.GET_SHARED_LIBRARY_FILES	ApplicationInfo��־������ ·���Ĺ���� ��Ӧ�ó���������ġ�
14.GET_SIGNATURES	PackageInfo��־�����ص���Ϣ���а�����ǩ����
15.GET_UNINSTALLED_PACKAGES	��־�����йص�����Ӧ�ó�������ж�صģ�������������Ŀ¼����һЩ��Ϣ��
16.GET_URI_PERMISSION_PATTERNS	ProviderInfo��־�����ص� URI �����������ṩ�̵����ģʽ��




2.����Ӧ��:

1.������г���ǩ��:
public String[] getSignstr(){
	//��ð�����
	PackageManager pm = Context.getPackageManager();
	//������а��������û��ж�ظɾ���.GET_SIGNATURES��ʾ�Ѱ�ǩ����ϢҲ���.GET_UNINSTALLED_PACKAGES,��ʾ��������Ѱ�װ��Ӧ��.
	List<PackageInfo> packinfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES| PackageManager.GET_SIGNATURES);
	String[] sign=new String[packinfos.size()];
	int i=0;
	for (PackageInfo packinfo : packinfos) {
		//��ð���ǩ����Ϣ����,��Ȼֻ��һ��ǩ��,��ֻ��
		Signature[] signatures = packinfo.signatures;
		//���ǩ��
		String signstr = signatures[0].toCharsString();
		sign[i]=signstr;
		i++;
	}
	return sign;
}
2.��ȡ�����Ȩ��.
public  String[] getAppInfos(Context context) {
	PackageManager pm = context.getPackageManager();
	//PackageManager.GET_PERMISSIONS��ʾҪ���Ӧ�õ�Ȩ����Ϣ
	List<PackageInfo> packinfos = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS);
	for(PackageInfo packinfo : packinfos){
	//���һ��Ӧ�õ�����Ȩ������		
		String[] permissions = packinfo.requestedPermissions;
		//��ú�Ϳ��Ա���ȥ�ж�Ӧ����ʲôȨ��,������Ӧ�Ĵ���,���������ж����Ӧ���Ƿ��ڶ�ȡ�û�����˽
		if(permissions!=null&&permissions.length>0){
			for(String permission : permissions){
				if("android.permission.INTERNET".equals(permission)){
					//���Ӧ���з�������Ȩ��
				}else if("android.permission.READ_CONTACTS".equals(permission)){
					//���Ӧ���з�������ϵ��Ȩ��
				}else if("android.permission.ACCESS_FINE_LOCATION".equals(permission)){
					//���Ӧ���з����û�λ��Ȩ��
				}else if("android.permission.SEND_SMS".equals(permission)){
					//���Ӧ���з����û�����Ȩ��
				}
				
			}
		}	
	}
	return permissions;
}

3.�ж�һ��Ӧ�õ�����.
 
	 //�Ƿ���ϵͳ��� trueΪϵͳ���
    public boolean isSystemApp(PackageInfo pInfo) {  
        return ((pInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);  
    }  
	//�Ƿ���ϵͳ����ĸ������  trueΪϵͳ����ĸ������
    public boolean isSystemUpdateApp(PackageInfo pInfo) {  
        return ((pInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0);  
    }  
	//�Ƿ����û���� trueΪ�û����
    public boolean isUserApp(PackageInfo pInfo) {  
	//���������ϵͳ���Ҳ����ϵͳ����ĸ������,�Ǿ;����û�
        return (!isSystemApp(pInfo) && !isSystemUpdateApp(pInfo));  
    }  
	//�ж�һ��Ӧ���Ƿ����ֻ��ڴ� true��װ���ֻ��ڴ� falseΪ��װ��SD��
	public boolean isInRom(PackageInfo pInfo) {  
		if((packinfo.applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == 0){
			return true;//���ֻ��ڴ�
		}else{
			return false;//��SD��
		}  
	}
	
4.���Ӧ�õİ�����ͼ���Ӧ����
��ʽһ:
public void getAppInfo(PackageInfo packinfo) {
	String version = packinfo.versionName;//��ȡӦ�ð汾
	String packname = packinfo.packageName;//��ȡӦ�ð���
	String appname = packinfo.applicationInfo.loadLabel(pm).toString();//��ȡӦ���� pm��ʾPackageManger
	Drawable appicon = packinfo.applicationInfo.loadIcon(pm);//��ȡӦ��ͼ��.
	//��ȡ��Щ��Ϣ���Է�װ��һ������,������������.	
}
��ʽ��:
public void getAppInfo(String packName) {
	ApplicationInfo  applicationInfo =	pm.getApplicationInfo(packName, 0);
	rawable appicon = applicationInfo.loadIcon(pm);
	String appname = applicationInfo.loadLabel(pm).toString();
}

5.ͨ����������һ��Ӧ�ó���
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
5.����Ӧ��.

	private void shareApk() {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.SEND");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setType("text/plain
		//�����Ϣ��㶨��
		intent.putExtra(Intent.EXTRA_TEXT,"�Ƽ���ʹ��һ�����.����Ϊ:" + selectedAppInfo.getAppname() + ",�汾:"+ selectedAppInfo.getVersion());
		startActivity(intent);
	}
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