//��ȡ�ֻ��Դ������ڴ�rom.
public String getAvailRom() {
		File path = Environment.getDataDirectory();
		//StatFs���ڻ�ȡһ��
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return Formatter.formatFileSize(this, blockSize * availableBlocks);
	}


		