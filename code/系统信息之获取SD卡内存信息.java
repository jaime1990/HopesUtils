1.��ȡsdcard�����ڴ�ռ�

// Sdcard�Ŀ��ÿռ�  
public String getAvailSD() {
		//�ж��Ƿ��в���洢��
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File path = Environment.getExternalStorageDirectory();//��ȡsd·��
			// StatFs:�����й������ϵ�һ���ļ�ϵͳ�Ŀռ���Ϣ 
			StatFs stat = new StatFs(path.getPath());
			 // һ���ļ�ϵͳ�Ŀ��С�����ֽ�Ϊ��λ��  
			long blockSize = stat.getBlockSize();
			 // SD���п��õĿ���  
			long availableBlocks = stat.getAvailableBlocks();
			return Formatter.formatFileSize(this, blockSize * availableBlocks);
		}else{
			return null;
		}
	}

    // Sdcard���ܿռ�  
    public String getALLAvailSdSize() {  
		//�ж��Ƿ��в���洢��
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File path = Environment.getExternalStorageDirectory();  
			// StatFs:�����й������ϵ�һ���ļ�ϵͳ�Ŀռ���Ϣ  
			StatFs stat = new StatFs(path.getPath());  
			// һ���ļ�ϵͳ�Ŀ��С�����ֽ�Ϊ��λ��  
			long blockSize = stat.getBlockCount();  
			// SD���п��õĿ���  
			long availableBlocks = stat.getAvailableBlocks();  
			return Formatter.formatFileSize(this, blockSize * availableBlocks);  
		}else{
			return null;
		}
    }  