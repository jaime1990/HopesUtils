//ͨ��Search�����ҵ�layout,���ҵ�id,ͨ��id�ҵ�java����.���ڴ��뿴Դ�����ʵ��.��ͨ�������aidl����ϵͳ�ķ���.
public class DemoActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void click(View view){
    	PackageManager pm = getPackageManager();
    	
    	try {
			Method method = PackageManager.class.getMethod("deleteApplicationCacheFiles",
					new Class[] { String.class
				,IPackageDataObserver.class
			});
			
			method.invoke(pm, new Object[]{"cn.itcast.cache",new MyObserver()});
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	private class MyObserver implements IPackageDataObserver{
		@Override
		public IBinder asBinder() {
			return null;
		}
		//����ִ�гɹ���ִ�еķ���
		@Override
		public void onRemoveCompleted(String packageName, boolean succeeded)
				throws RemoteException {
			System.out.println("==="+succeeded);
		}
	}
}