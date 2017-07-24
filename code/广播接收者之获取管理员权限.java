//��ù����ֻ�����ԱȨ��
//1.���ȶ���һ����̳�DeviceAdminReceiver
import android.app.admin.DeviceAdminReceiver;

public class MyAdmin extends DeviceAdminReceiver{

}

//2.��res�ж���һ��xml�ļ���,���涨��һ��device_admin_sample.xml�ļ�

<?xml version="1.0" encoding="utf-8"?>
<device-admin xmlns:android="http://schemas.android.com/apk/res/android">
  <uses-policies>
    <limit-password />
    <watch-login />
    <reset-password />
    <force-lock />
    <wipe-data />
  </uses-policies>
</device-admin>

//3.��manifest��ע��Recevier�����㲥
<receiver
	android:name="com.example.receiver.MyAdmin"
	android:description="@string/sample_device_admin_description"//����Ա�Ի��������
	android:label="@string/sample_device_admin"//����Ա�Ի���ı���
	android:permission="android.permission.BIND_DEVICE_ADMIN" >//ֻ���������Ȩ�޵�Ӧ�÷��Ĺ㲥
	<meta-data
		android:name="android.app.device_admin"//��������
		android:resource="@xml/device_admin_sample" />//����xml�ļ����µ�xml�ļ�

	<intent-filter>
		<action android:name="android.app.action.DEVICE_ADMIN_ENABLED" />//����㲥�Ľ��յĶ�������
	</intent-filter>
</receiver>
//4.����Ҫ�ٷ��Ĵ������������:��δ���һ���ٷ�,�ͻᵯ����ʾ,ȷ�Ϻ�Ӧ�þͿ��Ի�ù���ԱȨ����
public ....{
Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
	ComponentName who = new ComponentName(this, MyAdmin.class);//��������һ����ʶ����
	intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,who);
	intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"�����ҿ���Զ������,�������....");
	startActivity(intent);
}
//5.���˹���ԱȨ�޾Ϳ��Բ���һЩ���ж���.��:����,����,��������..
//�������һ����������
public void onlock(){
	// �Ȼ�ò��Թ����߶���
	DevicePolicyManager dmp = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
	// �ٶ���һ����̳�DeviceAdminReceiver,��ʾ���Ӧ��Ҫ��ò��Թ���ԱȨ��
	// �������Ķ����ٻ��һ����ʾ���Ӧ�õĹ���������
	ComponentName who = new ComponentName(context, MyAdmin.class);
	// �жϵ�ǰӦ�ó����Ƿ��Ѿ��õ��˹�����Ȩ��
	if (dmp.isAdminActive(who)) {
		// �����Ǹ�0��ʾ��������ʱҪ��������
		dmp.resetPassword("8816", 0);
		dmp.lockNow();
	}
}