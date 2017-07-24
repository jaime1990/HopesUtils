
/**
	 * �жϷ����Ƿ�������״̬.
	 * @param servicename
	 * @param context
	 * @return
	 */
	public static boolean isServiceRunning(String servicename,Context context){
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo>  infos = am.getRunningServices(100);
		for(RunningServiceInfo info: infos){
			if(servicename.equals(info.service.getClassName())){
				return true;
			}
		}
		return false;
	}