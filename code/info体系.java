info
1.ActivityInfo 
ActivityInfo extends ComponentInfo extends PackageItemInfo:��ʾ��������װ��minifest��Ϣ.
name���Ա�ʾ:ÿһ����Ŀ��"android:name" ����.


2.ApplicationInfo
public class ApplicationInfo extends PackageItemInfo implements Parcelable {

    public String taskAffinity;

    public String permission; //Ȩ��
   
    public String processName; //������

    public String className; //����

    public int descriptionRes;    //

    public int theme; //��ʽ
  
    public String manageSpaceActivityName;    
}
    public String backupAgentName;
ApplicationInfo  applicationInfo =	pm.getApplicationInfo(packName, 0);
	applicationInfo.loadIcon(pm);
	applicationInfo.loadLabel(pm).toString();
