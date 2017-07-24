ExecutorService �������̵߳Ĳ��裺

1�������߳���
class Handler implements Runnable{
}
2������ExecutorService�̳߳�
ExecutorService executorService = Executors.newCachedThreadPool();

����

int cpuNums = Runtime.getRuntime().availableProcessors();
                //��ȡ��ǰϵͳ��CPU ��Ŀ
ExecutorService executorService =Executors.newFixedThreadPool(cpuNums * POOL_SIZE);
                //ExecutorServiceͨ������ϵͳ��Դ��������̳߳ش�С
3�������̳߳ز���
ѭ����������Ϊdaemon,����ʵ������Executor����
      while(true){
        executorService.execute(new Handler(socket)); 
           // class Handler implements Runnable{
        ����
        executorService.execute(createTask(i));
            //private static Runnable createTask(final int taskID)
      }
1.����ʾ��:
public class ThreadPoolManager {
	private ExecutorService service;
	
	private ThreadPoolManager(){
		int num = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(num*2);
	}
	
	private static final ThreadPoolManager manager= new ThreadPoolManager();
	
	public static ThreadPoolManager getInstance(){
		return manager;
	}
	
	public void addTask(Runnable runnable){
		service.execute(runnable);
	}
}
2.�ⲿ����:
ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance();//��������
this.threadPoolManager.addTask(taskThread);//�����߳�

execute(Runnable����)����
��ʵ���Ƕ�Runnable�������start()����
����Ȼ����һЩ������̨������������У����ȼ���IDLE timeout��active����ȣ�


���ֲ�ͬ��ExecutorService�̳߳ض���
1.newCachedThreadPool() 
-�����ͳ��ӣ��Ȳ鿴������û����ǰ�������̣߳�����У���reuse.���û�У��ͽ�һ���µ��̼߳������
-�����ͳ���ͨ������ִ��һЩ�����ں̵ܶ��첽������
 �����һЩ�������ӵ�daemon��SERVER���õò��ࡣ
-��reuse���̣߳�������timeout IDLE�ڵĳ����̣߳�ȱʡtimeout��60s,�������IDLEʱ�����߳�ʵ��������ֹ���Ƴ��ء�
  ע�⣬����CachedThreadPool���̲߳��ص��������������TIMEOUT���������Զ�����ֹ��
2. newFixedThreadPool
-newFixedThreadPool��cacheThreadPool��࣬Ҳ����reuse���ã���������ʱ���µ��߳�
-�����֮��:����ʱ��㣬���ֻ���й̶���Ŀ�Ļ�̴߳��ڣ���ʱ������µ��߳�Ҫ������ֻ�ܷ�������Ķ����еȴ���ֱ����ǰ���߳���ĳ���߳���ֱֹ�ӱ��Ƴ�����
-��cacheThreadPool��ͬ��FixedThreadPoolû��IDLE���ƣ�����Ҳ�У�����Ȼ�ĵ�û�ᣬ�϶��ǳ��������������ϲ��TCP��UDP IDLE����֮��ģ�������FixedThreadPool�������һЩ���ȶ��̶ܹ������沢���̣߳������ڷ�����
-�ӷ�����Դ���뿴��cache�غ�fixed �ص��õ���ͬһ���ײ�أ�ֻ����������ͬ:
fixed���߳����̶���������0��IDLE����IDLE��
cache���߳���֧��0-Integer.MAX_VALUE(��Ȼ��ȫû������������Դ������������60��IDLE  
3.ScheduledThreadPool
-�������̳߳�
-�����������߳̿��԰�schedule����delayִ�У�������ִ��
4.SingleThreadExecutor
-�����̣߳�����ʱ�����ֻ����һ���߳�
-�õ��Ǻ�cache�غ�fixed����ͬ�ĵײ�أ����߳���Ŀ��1-1,0��IDLE����IDLE��


���������̳߳أ���ʹ��Executor��ȱʡ�̹߳��������̣߳�Ҳ�ɵ��������Լ����̹߳���
������ȱʡ�̹߳�������:
    static class DefaultThreadFactory implements ThreadFactory {
        static final AtomicInteger poolNumber = new AtomicInteger(1);
        final ThreadGroup group;
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null)? s.getThreadGroup() :Thread.currentThread().getThreadGroup();
          
            namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,namePrefix + threadNumber.getAndIncrement(),0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
Ҳ���Լ�����ThreadFactory�����뽨���صĲ�����
 public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {


Executor��execute()����
execute() ������Runnableʵ������pool��,������һЩpool size��������ȼ�����
execute() ����������Executor�ӿ��ж���,�ж��ʵ���඼�����˲�ͬ��execute()����
��ThreadPoolExecutor�ࣨcache,fiexed,single���ֳ��Ӷ��ǵ���������execute�������£�
    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        if (poolSize >= corePoolSize || !addIfUnderCorePoolSize(command)) {
            if (runState == RUNNING && workQueue.offer(command)) {
                if (runState != RUNNING || poolSize == 0)
                    ensureQueuedTaskHandled(command);
            }
            else if (!addIfUnderMaximumPoolSize(command))
                reject(command); // is shutdown or saturated
        }
    }