//AsyncTask�첽������÷� �첽�����ڲ�����handler����Ϣ���ݻ���ʵ�ֵ�,
//Ҳ�����ڰ�Ҫ׼������ִ����,��ִ��,ִ�к�message��handler��ִ��֮��ķ���.
//ע��:
1.����ʵ�����봴����UI�߳�
2.execute(Params...)������UI�߳��ϵ���
3.��Ҫ�ֶ�����onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...)
4.�������ִֻ��һ�Σ����ִ�еڶ��ν����׳��쳣��
//Handler+Thread�ʺϽ��д��ܵ��첽������asyncTask������С�ͼ򵥵��첽����

//��Ҫ��������,���������Ҫ,����Void��ʾ
//��һ������:��doInBackground�Ĳ�����ʵ����execute(fos)ִ�з����Ĳ���,
//�ڶ�������:��doInBackground�����е���publishProgress(��������Ƕ������Ҳ���Դ�һ��)��������(��:��������)��onProgressUpdate������
//����������:��doInBackgroundִ�к�ķ���ֵ(�����Ҫ�Ļ�),��onPostExecute�����õ�.
private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
	//��̨�߳�ִ��ǰ�Ĳ���,���翪һ��������֮���
	@Override
	protected void onPreExecute() {
		.......
		super.onPreExecute();
	}
	//���߳�ִ�еķ���
     protected Long doInBackground(URL... urls) {
         int count = urls.length;
         long totalSize = 0;
         for (int i = 0; i < count; i++) {
             totalSize += Downloader.downloadFile(urls[i]);
             publishProgress((int) ((i / (float) count) * 100));//��ִ�н��ȷ�����onProgressUpdate
			 publishProgress(i,i+1);//���Է����������
         }
         return totalSize;
     }
	//���ս���:�ɱ����
     protected void onProgressUpdate(Integer... progress) {
		 //����Ƕ����������,��ô��������ѡ��:
		 if(progress.length==1){
			setProgressPercent(progress[0]);
		 }else if(progress.length==2){
			 int i=progress[0];
			 int ii=progress[1];
		 }
        
     }
	//doInBackgroundִ�����ִ�еķ���,���doInBackground�з���ֵ,��ô���շ���ֵ����,������һЩɨβ�Ĺ���,��رս�����ʾ֮��.��.
     protected void onPostExecute(Long result) {
         showDialog("Downloaded " + result + " bytes");
     }
 }
 //��ס,����ú�ǵ�ִ��execute()!!
  new DownloadFilesTask().execute(url1, url2, url3);