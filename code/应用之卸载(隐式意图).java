//��ʾ��ͼ����ж�����
public void removeInfo(PackageInfo info){
Intent intent = new Intent();
intent.setAction("android.intent.action.DELETE");
intent.addCategory(Intent.CATEGORY_DEFAULT);
intent.setData(Uri.parse("package:"+info.packageName));
startActivity(intent);
}