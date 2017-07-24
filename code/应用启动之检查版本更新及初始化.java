1.Ӧ������֮���汾���¼���ʼ��
public class SplashActivity extends Activity {
	public static final int PARSE_XML_ERROR = 10;
	public static final int PARSE_XML_SUCCESS = 11;
	public static final int SERVER_ERROR = 12;
	public static final int URL_ERROR = 13;
	public static final int NETWORK_ERROR = 14;
	private static final int DOWNLOAD_SUCCESS = 15;
	private static final int DOWNLOAD_ERROR = 16;
	protected static final String TAG = "SplashActivity";
	protected static final int COPY_ERROR = 17;
	private TextView tv_splash_version;
	private UpdateInfo updateInfo;

	private ProgressDialog pd;// ���ؽ��ȵĶԻ���

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PARSE_XML_ERROR:
				Toast.makeText(getApplicationContext(), "����xmlʧ��", 0).show();
				// �������������
				loadMainUI();
				break;
			case SERVER_ERROR:
				Toast.makeText(getApplicationContext(), "�������쳣", 0).show();
				// �������������
				loadMainUI();
				break;
			case URL_ERROR:
				Toast.makeText(getApplicationContext(), "��������ַ�쳣", 0).show();
				// �������������
				loadMainUI();
				break;
			case NETWORK_ERROR:
				Toast.makeText(getApplicationContext(), "�����쳣", 0).show();
				// �������������
				loadMainUI();
				break;
			case PARSE_XML_SUCCESS:
				if (getAppVersion().equals(updateInfo.getVersion())) {
					// �������������
					Logger.i(TAG, "�汾����ͬ,����������");
					loadMainUI();
				} else {
					Logger.i(TAG, "�汾�Ų���ͬ,������������ʾ�Ի���");
					showUpdateDialog();
				}
				break;
			case DOWNLOAD_ERROR:
				Toast.makeText(getApplicationContext(), "����ʧ��", 0).show();
				// �������������
				loadMainUI();
				break;
			case DOWNLOAD_SUCCESS:
				File file = (File) msg.obj;
				Logger.i(TAG, "��װapk" + file.getAbsolutePath());
				// ��װapk
				installApk(file);
				finish();
				break;
			case COPY_ERROR:
				Toast.makeText(getApplicationContext(), "�������ݿ��쳣", 0).show();
				break;
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_splash);
		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("�汾:" + getAppVersion());

		// ���ӷ����� ���汾����.
		new Thread(new CheckVersionTask()).start();
		//�Զ���һ������
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(2000);
		findViewById(R.id.rl_splash).startAnimation(aa);

		// �����ؼ��ļ���ϵͳĿ¼.
		copyAddressDB();
	}

	/**
	 * �ͷ� ���ݿ��ļ���ϵͳĿ¼
	 */
	private void copyAddressDB() {
		final File file = new File(getFilesDir(), "address.db");
		if (file.exists() && file.length() > 0) {
			//do nothing
		} else {
			new Thread() {
				public void run() {
					Message msg = Message.obtain();
					try {
						InputStream is = getAssets().open("address.db");
						File f = CopyFileUtils.copyFile(is, file.getAbsolutePath());
						if(f!=null){
							// �����ɹ�.
						}else{
							msg.what = COPY_ERROR;
						}
					} catch (Exception e) {
						e.printStackTrace();
						msg.what = COPY_ERROR;
					}finally{
						handler.sendMessage(msg);
					}
				};
			}.start();
		}
	}
	
	/**
	 * ��װһ��apk�ļ�
	 * 
	 * @param file
	 */
	protected void installApk(File file) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}

	/**
	 * �Զ���������ʾ�Ի���
	 */
	protected void showUpdateDialog() {

		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("��������");
		builder.setMessage(updateInfo.getDescription());
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String apkurl = updateInfo.getApkurl();
				pd = new ProgressDialog(SplashActivity.this);
				pd.setTitle("��������");
				pd.setMessage("��������...");
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pd.show();
				Logger.i(TAG, "���غ�װ:" + apkurl);
				final File file = new File(Environment
						.getExternalStorageDirectory(), DownLoadUtil
						.getFileName(apkurl));
				// �ж�sd���Ƿ����,ֻ�п���״̬.
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					new Thread() {
						public void run() {
							File savedFile = DownLoadUtil.download(
									updateInfo.getApkurl(),
									file.getAbsolutePath(), pd);
							Message msg = Message.obtain();
							if (savedFile != null) {
								// ���سɹ�
								msg.what = DOWNLOAD_SUCCESS;
								msg.obj = savedFile;
							} else {
								// ����ʧ��
								msg.what = DOWNLOAD_ERROR;

							}
							handler.sendMessage(msg);
							pd.dismiss();
						};
					}.start();
				} else {
					Toast.makeText(getApplicationContext(), "sd��������", 0).show();
					loadMainUI();
				}
			}
		});
		builder.setNegativeButton("ȡ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				loadMainUI();
			}
		});
		builder.create().show();
	    //builder.show();
	}

	/**
	 * ����������
	 */
	private void loadMainUI() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}
//������汾�Ƿ���µ��߳�
	private class CheckVersionTask implements Runnable {
		@Override
		public void run() {
			SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
			boolean isupdate = sp.getBoolean("update", true);
			if (!isupdate) {
				loadMainUI();
				return;
			}
			long startTime = System.currentTimeMillis();
			Message msg = Message.obtain();
			try {
				URL url = new URL(getResources().getString(R.string.serverurl));
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(1500);
				int code = conn.getResponseCode();
				if (code == 200) {
					InputStream is = conn.getInputStream();
					updateInfo = UpdateInfoParser.getUpdateInfo(is);
					if (updateInfo == null) {
						// TODO:����xmlʧ��
						msg.what = PARSE_XML_ERROR;
					} else {
						// �����ɹ�
						msg.what = PARSE_XML_SUCCESS;
					}
				} else {
					// TODO:�������ڲ�����.
					msg.what = SERVER_ERROR;
				}
			} catch (MalformedURLException e) {
				msg.what = URL_ERROR; // http://
				e.printStackTrace();
			} catch (NotFoundException e) {
				msg.what = URL_ERROR; //
				e.printStackTrace();
			} catch (IOException e) {
				msg.what = NETWORK_ERROR;
				e.printStackTrace();
			} finally {
				long endTime = System.currentTimeMillis();
				long dTime = endTime - startTime;
				if (dTime < 2000) {
					try {
						Thread.sleep(2000 - dTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				handler.sendMessage(msg);
			}

		}
	}

	/**
	 * ��ȡӦ�ó���İ汾��
	 * 
	 * @return
	 */
	private String getAppVersion() {
		// �õ�ϵͳ�İ�������
		PackageManager pm = getPackageManager();
		try {
			PackageInfo packinfo = pm.getPackageInfo(getPackageName(), 0);
			return packinfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			// can't reach
			return "";
		}

	}
}