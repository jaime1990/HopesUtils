��ʽһ:manifest�ж���
 <activity
 ...........
 android:theme="@android:style/Theme.NoTitleBar.Fullscreen"//������Ա�ʾû������ȫ��
  android:theme="@android:style/Theme.NoTitleBar  //������Ա�ʾ����,ֻû�б���
 >
��ʽ��:����δ������onCreate��,�����Activity��ȫ��
public BaseActivity extends Activity{
	@Override
	public void onCreate(Bundle paramBundle) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// ȥ����Ϣ��
}
