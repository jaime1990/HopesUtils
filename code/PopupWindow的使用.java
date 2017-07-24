1.PopupWindow��ʹ��
//ע��:
//1.��Ҫǿ����������PopupWindow������ĳ���¼������Ż���ʾ������Ȼ�ܻᱧ��
//2.������ʼ������PopupWindow��ʾ����,�����˶�ʱ��Timer��ʵ��������Ч��,��Ȼ�����Ҫ�õ�Handler��



//����Ӧ��
1.����һ��ռ��ȫ����popwindow����Ŀ��ʱ�����Ե��.

public class AppLockActivity extends Activity implements OnClickListener {
	private PopupWindow popwindow;	
	private ListView lv_unlock, lv_locked;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Display���ṩ������Ļ�ߴ�ͷֱ��ʵ���Ϣ��
		Display display = getWindowManager().getDefaultDisplay();
		//��ȡһ����������Ļ�Ŀ��һ����popwindow����
		popwindow = new PopupWindow(new View(this), display.getWidth(), display.getHeight());		
		//��һ��������Ŀ�ĵ���¼�����ʾ����
		lv_unlock.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,//���view���������Ŀ
					int position, long id) {
				//����popwindow����Ϊ͸��
				popwindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				//��ʾpopwindow�������Gravity.TOP|Gravity.LEFT(��ʾ���Ͻ�)��0,0λ����.
				popwindow.showAtLocation(parent, Gravity.TOP|Gravity.LEFT, 0, 0);
				//���Ǹ��Զ��������ִ�й�����,��ʾ����800����ر�popwindow
				new DelayExecuter() {
					@Override
					public void onPostExecute() {
						popwindow.dismiss();
					}
				}.execute(800);
			}
		});
	}
}
2.������view�󵯳�һ����������popupwindow
public class AppLockActivity extends Activity implements OnClickListener {
	private PopupWindow popwindow;	
	private ListView lv_app_manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		lv_app_manager.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				// �ڵ�����������ж� �����ǰ�����Ѿ������˵������� ��ȥ�ر���.
				dismissPopupWindow();
				// ����positionȥ��ȡ�������Ŀ��Ӧ�Ķ���.
				Object obj = lv_app_manager.getItemAtPosition(position);
				if (obj instanceof AppInfo) {
					selectedAppInfo = (AppInfo) obj;
					Logger.i(TAG, "packname:" + selectedAppInfo.getPackname());
					View contentView = View.inflate(getApplicationContext(),R.layout.ui_popupwindow_app, null);
					//����PopupWindow��view���������view�͵���¼�
					ll_share = (LinearLayout) contentView.findViewById(R.id.ll_share);
					ll_start = (LinearLayout) contentView.findViewById(R.id.ll_start);
					ll_uninstall = (LinearLayout) contentView.findViewById(R.id.ll_uninstall);
					ll_share.setOnClickListener(AppManagerActivity.this);
					ll_start.setOnClickListener(AppManagerActivity.this);
					ll_uninstall.setOnClickListener(AppManagerActivity.this);
					//����һ��PopupWindow����
					popwindow = new PopupWindow(contentView,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
					//���屳��ɫ
					popwindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
					int[] location = new int[2];//����һ��int������,���ڴ��x��y������
					view.getLocationInWindow(location);//��ø�view������,����int������
					popwindow.showAtLocation(parent,Gravity.TOP | Gravity.LEFT//��ʾ���յ�
												, location[0] + 60//��x�᷽������ƫ��60��dip
												,location[1]);//y�᷽���븸viewһ��
					//�������Ŷ��� ��һ��������ʾx��ʼ��СΪ����view,�ڶ���������ʾ���ź�Ĵ�С��ԭ��С,������������y�����ʼ��С,
					//���ĸ�������y���ź�Ĵ�С��ԭ��С,�����������������ʱx��y����Ĳ��յ�,������view�����ĵ�
					ScaleAnimation sa = new ScaleAnimation(0.2f, 1.0f, 0.2f,1.0f, 0.5f, 0.5f);//�������Ŷ���
					sa.setDuration(600);
					//����ƽ�ƶ���,x��0λ������ȫ��,y���򱣳ֲ���.
					TranslateAnimation ta = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0,
							Animation.RELATIVE_TO_SELF, 0.1f,
							Animation.RELATIVE_TO_SELF, 0,
							Animation.RELATIVE_TO_SELF, 0);
					ta.setDuration(800);
					//AnimationSet���Լ���Animation������֮������AnimationSet�Լ��������Animation����Ч��
					//����true��ô���е�Animation����AnimationSet��Interpolator(�����仯������.)false��ʾ��ÿ���������ѵ�Interpolator.
					AnimationSet set = new AnimationSet(false);
					set.addAnimation(sa);
					set.addAnimation(ta);
					contentView.startAnimation(set);
					contentView.clearAnimation();
				}
			}
		});
	}
}