1.�ֻ���̬�ڴ�RAM�Ļ�ȡ
//��ȡ�ֻ��ܶ�̬�ڴ�ram��С
	public  String getTotalRam() {
			try {
				File file = new File("/proc/meminfo");
				FileInputStream fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				// MemTotal: 253604 kB
				String result = br.readLine();
				//��ȡһ�����������
				StringBuffer sb = new StringBuffer();
				char[] chars = result.toCharArray();
				for (char c : chars) {
					if (c >= '0' && c <= '9') {
						sb.append(c);
					}
				}
		//��λ��kB,����Ҫת����byte,������Formatter.formatFileSize������ʽ�����ַ���.
				long number= Long.parseLong(sb.toString()) * 1024;
				return Formatter.formatFileSize(this, number);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

//��ȡ�ֻ�ʣ�ද̬�ڴ�ram��С	
1.��ʽһ:
	public String getAvailRam(Context context) {
			ActivityManager am = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			ActivityManager.MemoryInfo outInfo = new MemoryInfo();
			am.getMemoryInfo(outInfo);
			 long number=outInfo.availMem;
			return Formatter.formatFileSize(this, number);
		}
2.��ʽ��:
	public String getInternalAvailSize() {
		StatFs mDataFileStats = new StatFs("/data");
		long freeStorage = (long) mDataFileStats.getAvailableBlocks()
				* mDataFileStats.getBlockSize();
		return Formatter.formatFileSize(this, freeStorage);
}