����widget��������
//1.����һ����̳�AppWidgetProvider
	
/**
 * ����widget���ҲҪ��Сʱ����һ��,����һ�㶼���Զ������������,�÷���ȥʵ����.�����ں�̨��������
 */
public class MyWidget extends AppWidgetProvider {
	// ���ܴ�������ɾ������ִ��
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

	// �����µ�widget�ͻ�ִ��,
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Intent intent = new Intent(context, UpdateWidgetService.class);
		context.startService(intent);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	// ��widget�Ƴ��ͻ�ִ��
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Intent intent = new Intent(context, UpdateWidgetService.class);
		context.startService(intent);
		super.onDeleted(context, appWidgetIds);
	}

	// ��һ��widget����ִ�� ,���ڳ�ʼ��,���翪��һ������
	@Override
	public void onEnabled(Context context) {
		Intent intent = new Intent(context, UpdateWidgetService.class);
		context.startService(intent);
		super.onEnabled(context);
	}

	// ���һ��widgetɾ��ִ��,���ڲ�ƨ��,����ֹͣ���widget��ִ�еķ����
	@Override
	public void onDisabled(Context context) {
		Intent intent = new Intent(context, UpdateWidgetService.class);
		context.stopService(intent);
		super.onDisabled(context);
	}
}
//2.��menifest.xmlע��
 <receiver android:name=".MyWidget" >
		<intent-filter>
			<action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
		</intent-filter>
		<meta-data
			android:name="android.appwidget.provider"
			android:resource="@xml/example_appwidget_info" />
</receiver>
//3.��layout�ļ����¶�������ؼ�����ʽ
.....

//4.��res�´���һ��xml�ļ���,�������洴��һ��example_appwidget_info.xml�ļ�
<?xml version="1.0" encoding="utf-8"?>
<appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"
    android:initialLayout="@layout/process_widget"//����ؼ�����ʽ
    android:minHeight="72dp"//��С�߶�
    android:minWidth="294dp"//��С���
    android:updatePeriodMillis="1800000" >//ִ������,��С��Сʱ

</appwidget-provider>

//�����Զ���ĺ�̨���·���
public class UpdateWidgetService extends Service {
	private Timer timer;
	private TimerTask task;
	private AppWidgetManager awm ;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
	//���AppWidgetManager����������,����������ڸ���widget
	/**
		 * AppWidgetManager�������ڸ���widget����Ϊ:updateAppWidget
		 * ������ͨ��appWidgetId(��Ӧ�����ļ���id)���ָ����Widget����  public AppWidgetProviderInfo getAppWidgetInfo(int appWidgetId)
		 * �����Ի�������Ѱ�װ��Widget����  public List<AppWidgetProviderInfo> getInstalledProviders()
		 * ��ϸ���Բ鿴Դ��
		 */
		awm = AppWidgetManager.getInstance(getApplicationContext());	
		// ��������ִ�е�����.
		timer = new Timer();
		task = new TimerTask() {
			
			@Override
			public void run() {
				//����ָ��������һ��widget
				ComponentName provider = new ComponentName(getApplicationContext(), MyWidget.class);
				//���widget�ؼ���Ӧ����view����
				RemoteViews views = new RemoteViews(getPackageName(), R.layout.process_widget);
				//ͨ��view������������䲼������view������.��setImage
				views.setTextViewText(R.id.process_count, "�������е����:"+TaskUtils.getRunningProcessCount(getApplicationContext())+"��");
				views.setTextViewText(R.id.process_memory, "�����ڴ�:"+Formatter.formatFileSize(getApplicationContext(), TaskUtils.getAvailRam(getApplicationContext())));
				Intent intent = new Intent();//�Զ���Ĺ㲥�¼�. 
				intent.setAction("cn.itheima.killbgprocess");
				//�Զ���Ĺ㲥������
//				<receiver android:name=".receiver.MyKillProcessReceiver" >
//	            <intent-filter>
//	                <action android:name="cn.itheima.killbgprocess" />
//	            </intent-filter>
//	            </receiver>
				//����һ��������ͼ
				PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				//��������ĵ���¼�
				views.setOnClickPendingIntent(R.id.btn_clear, pendingIntent);
				//����widget
				awm.updateAppWidget(provider, views);
				
			}
		};
		timer.schedule(task, 1000, 2000);
		
		super.onCreate();
	}

	public void onDestroy() {
		timer.cancel();
		task = null;
		timer = null;
	};

}
