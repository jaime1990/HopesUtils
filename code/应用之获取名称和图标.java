ͨ��������ȡӦ�ó�����Ϣ����,����ȡӦ�ó�������ƺ�ͼ��
//content.ContextWrapper.getPackageManager()
PackageManager pm = getPackageManager();
ApplicationInfo info = pm.getApplicationInfo(packname, 0);
Drawable icon = info.loadIcon(pm);
CharSequence name = info.loadLabel(pm);
//��ú�Ϳ����õ������õĵط�
TextView tv = (TextView) findViewById(R.id.tv_name);
tv.setText(name);
ImageView iv = (ImageView) findViewById(R.id.iv_icon);
iv.setImageDrawable(icon);