1.��Ӧ�����ý���Ӷ�������ʵ�ֻ�������Ȳ���.
view.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		System.out.println("�������...");
		String packname = info.packname;
		if (Build.VERSION.SDK_INT >= 9) {//2.2���ϰ汾��������
			// �������ý���.
			Intent intent = new Intent();
			intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
			intent.setData(Uri.parse("package:"+ packname));
			startActivity(intent);
		} else {// 2.2 ���°汾,���������ʽ�������ý���
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addCategory("android.intent.category.VOICE_LAUNCH");
			intent.putExtra("pkg", packname);
			startActivity(intent);
		}
	}
});