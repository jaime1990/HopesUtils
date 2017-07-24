�쳣���������£�����ǲ�������ʱ�쳣��
//��ΰ�һ���쳣�������ʱ�쳣�׳�? catch (Exception e) {  throw new RuntimeException(e); }�Ϳ�����

/** 
* @author Stay 
*      ��Application��ͳһ�����쳣�����浽�ļ����´��ٴ�ʱ�ϴ� 
*/
public class CrashHandler implements UncaughtExceptionHandler {   
    /** �Ƿ�����־���,��Debug״̬�¿���,  
     * ��Release״̬�¹ر�����ʾ��������  
     * */  
    public static final boolean DEBUG = true;   
    /** ϵͳĬ�ϵ�UncaughtException������ */  
    private Thread.UncaughtExceptionHandler mDefaultHandler;   
    /** CrashHandlerʵ�� */  
    private static CrashHandler INSTANCE;   
    /** �����Context���� */  
//    private Context mContext;   
    /** ��ֻ֤��һ��CrashHandlerʵ�� */  
    private CrashHandler() {}   
    /** ��ȡCrashHandlerʵ�� ,����ģʽ*/  
    public static CrashHandler getInstance() {   
        if (INSTANCE == null) {   
            INSTANCE = new CrashHandler();   
        }   
        return INSTANCE;   
    }   
    
    /**  
     * ��ʼ��,ע��Context����,  
     * ��ȡϵͳĬ�ϵ�UncaughtException������,  
     * ���ø�CrashHandlerΪ�����Ĭ�ϴ�����  
     *   
     * @param ctx  
     */  
    public void init(Context ctx) {   
//        mContext = ctx;   
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();   
        Thread.setDefaultUncaughtExceptionHandler(this);   
    }   
    
    /**  
     * ��UncaughtException����ʱ��ת��ú���������  
     */  
    @Override  
    public void uncaughtException(Thread thread, Throwable ex) {   
        if (!handleException(ex) && mDefaultHandler != null) {   
            //����û�û�д�������ϵͳĬ�ϵ��쳣������������   
            mDefaultHandler.uncaughtException(thread, ex);   
        } else {  //����Լ��������쳣���򲻻ᵯ��ϵͳ�Դ�����Ի�������Ҫ�ֶ��˳�app 
		
            //����:��������Զ���һ�����̵߳���һ���Ի���,�Ѻ���ʾ��.
			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
					new AlertDialog.Builder(mContext).setTitle("��ʾ").setCancelable(false)
							.setMessage("���������...").setNeutralButton("��֪����", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									System.exit(0);//������Ի���ر�.�������ڴ�.
								}
							})
							.create().show();
					Looper.loop();
				}
			}.start();
			//�������ֶ��˳�,����ɱ�����ѵ��߳�
            android.os.Process.killProcess(android.os.Process.myPid());   
            System.exit(10);   
        }   
    }   
    
    /**  
     * �Զ��������,�ռ�������Ϣ  
     * ���ʹ��󱨸�Ȳ������ڴ����.  
     * �����߿��Ը����Լ���������Զ����쳣�����߼�  
     * @return  
     * true��������쳣�������������쳣�� 
     * false����������쳣(���Խ���log��Ϣ�洢����)Ȼ�󽻸��ϲ�(����͵���ϵͳ���쳣����)ȥ���� 
     * ����˵����true���ᵯ���Ǹ�������ʾ��false�ͻᵯ�� 
     */  
    private boolean handleException(final Throwable ex) {   
        if (ex == null) {   
            return false;   
        }   
//        final String msg = ex.getLocalizedMessage();   
        final StackTraceElement[] stack = ex.getStackTrace(); 
        final String message = ex.getMessage(); 
        //ʹ��Toast����ʾ�쳣��Ϣ  ,�����쳣������SD����,���ϴ���������. 
        new Thread() {   
            @Override  
            public void run() {   
		//Looper���ڷ�װ��android�߳��е���Ϣѭ����Ĭ�������һ���߳��ǲ�������Ϣѭ����message loop���ģ�
		//��Ҫ����Looper.prepare()�����̴߳���һ����Ϣѭ��������Looper.loop()��ʹ��Ϣѭ�������ã�����Ϣ������ȡ��Ϣ��������Ϣ��
		
                Looper.prepare();   
//   ��ȻҲ���Ե�һ��Toast��ʾ:Toast.makeText(mContext, "���������:" + message, Toast.LENGTH_LONG).show();  
 
//       �����쳣��SD����:����ֻ����һ���ļ����Ժ�ȫ��������appendȻ���ͣ������ͻ����ظ�����Ϣ�����˲��Ƽ� 
                String fileName = "crash-" + System.currentTimeMillis()  + ".log";   
                File file = new File(Environment.getExternalStorageDirectory(), fileName); 
                try { 
                    FileOutputStream fos = new FileOutputStream(file,true); 
                    fos.write(message.getBytes()); 
                    for (int i = 0; i < stack.length; i++) { 
                        fos.write(stack.toString().getBytes()); 
                    } 
                    fos.flush(); 
                    fos.close(); 
                } catch (Exception e) { 
                } 
				//ע��д��Looper.loop()֮��Ĵ��벻�ᱻ����ִ�У������ú�mHandler.getLooper().quit()��loop�Ż���ֹ��
				//���Ĵ�����ܵ������С�Looper����ͨ��MessageQueue�������Ϣ���¼���һ���߳�ֻ����һ��Looper����Ӧһ��MessageQueue
                Looper.loop();   
            }   
    
        }.start();   
        return false;   
    }   
    
    // TODO ʹ��HTTP Post ���ʹ��󱨸浽������  ���ﲻ��׸�� 
//    private void postReport(File file) {   
//      ���ϴ���ʱ�򻹿��Խ���app��version�����ֻ��Ļ��͵���Ϣһ�����͵ķ������� 
//      Android�ļ�����������֪�����Կ��ܴ�����ÿ���ֻ����ᱨ������������Ե�ȥdebug�ȽϺ� 
//    }   
} 



2.��Application onCreateʱ��ע��ExceptionHandler���˺�ֻҪ���������쳣����ܲ��񵽡�
//�������һ������,���Ⱦͻ�ִ��������onCreate����,����������ȼ�����ߵ�.
public class App extends Application{ 
        @Override  
        public void onCreate() {   
            super.onCreate();   
            CrashHandler crashHandler = CrashHandler.getInstance();   
            //ע��crashHandler   
            crashHandler.init(getApplicationContext());   
        }   
} 
3.��Activity�ж����쳣ȫ�ֲ���.
public class LogActivity extends Activity { 
    @Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main); 
        try { 
            //try���ܻ���쳣�Ĵ���
        } catch (Exception e) { 
		//�����Ҫ��log��Ϣ�������������׳�runtime�쳣�� 
          //ע��:���������RuntimeException�����catch��throw��Ĭ�����Լ������ˣ�ExceptionHandler���Ჶ���쳣�ˡ�	
            throw new RuntimeException(e); //���������Զ����handler������ͳһ���ļ����������ϴ� 
        } 
    } 
} 

