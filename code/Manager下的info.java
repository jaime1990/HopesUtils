RunningAppProcessInfo
//��ȡ����ϵͳ���еĽ���
public List<RunningAppProcessInfo> getRunningAppProcesses()
RunningAppProcessInfo������Ի�ý������ͽ���pid 
	//������������Ա�ֶ�,����:
	public String processName;
	public int pid;
MemoryInfo
//MemoryInfo���Ի�ý���ռ�õ��ڴ��С.
long memsize = activityManager.getProcessMemoryInfo(new int[]{processInfo.pid})[0].getTotalPrivateDirty()*1024;