1.�ж�����״̬�Ƿ����
public boolean getNetWorkStatus() {

		boolean netSataus = false;
		ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cwjManager.getActiveNetworkInfo() != null) {//������Ӷ���Ϊ��,��ʾ�������.
			netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
		}
		//���û������,�����Ի���ѯ���û��Ƿ�Ҫ����.
		if (!netSataus) {
			Builder b = new AlertDialog.Builder(this).setTitle("û�п��õ�����")
					.setMessage("�Ƿ������������ã�");
			b.setPositiveButton("��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					Intent mIntent = new Intent("/");
					//�������ý���ʱ��������.
					ComponentName comp = new ComponentName(
							"com.android.settings",
							"com.android.settings.WirelessSettings");
					mIntent.setComponent(comp);
					mIntent.setAction("android.intent.action.VIEW");
					startActivityForResult(mIntent, 0);
				}
			}).setNeutralButton("��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.cancel();
				}
			}).show();
		}
		return netSataus;
	}