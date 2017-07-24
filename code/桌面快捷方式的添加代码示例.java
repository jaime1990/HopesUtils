Android��Ϊ���Ӧ�ó�����������ݷ�ʽ
��ֻ��Ҫ�����Ӧ�ó��������ĵ�һ��Activity�����������һ��������
1.ע��Ȩ��!!!:<uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT"/>//��Ҫ��Ȩ��
public class AndroidLayoutActivity extendsActivity {
       /**Called when the activity is first created. */
       @Override
       publicvoid onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.view_personal_info);
              SharedPreferencespreferences = getSharedPreferences("first",
                            Context.MODE_PRIVATE);
              boolean isFirst = preferences.getBoolean("isfrist", true);
              if(isFirst) {
                     createDeskShortCut();
              }
              SharedPreferences.Editoreditor = preferences.edit();
              editor.putBoolean("isfrist",false);
              editor.commit();
       }
 
       /**
        * ������ݷ�ʽ
        */
       publicvoid createDeskShortCut() {
 
              Log.i("coder","------createShortCut--------");
              //������ݷ�ʽ��Intent
              Intent shortcutIntent = new Intent( "com.android.launcher.action.INSTALL_SHORTCUT");
              //�������ظ�����
              shortcutIntent.putExtra("duplicate",false);
              //��Ҫ��ʾ������
              shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name)); 
              //���ͼƬ
              Parcelableicon = Intent.ShortcutIconResource.fromContext(getApplicationContext(),R.drawable.ic_launcher);
              shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,icon);
              Intentintent = new Intent(getApplicationContext(),AndroidLayoutActivity.class);
              //��������������Ϊ�˵�Ӧ�ó���ж��ʱ���� �ϵĿ�ݷ�ʽ��ɾ��
              intent.setAction("android.intent.action.MAIN");
              intent.addCategory("android.intent.category.LAUNCHER");
              //������ͼƬ�����еĳ��������,Ҳ���ǰ���ͼ���,�������intent�����AndroidLayoutActivity
              shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,intent);
              //���͹㲥��OK
              sendBroadcast(shortcutIntent);
       }
}