ActivityӦ�û���
//ֻҪ����ͼ���������Activity,�������Activity����ʲô״̬,����ִ�����·���,���԰�Ҫʵ�ָ��µ����ݷ�������.
@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.i(TAG,"onnewintent");
		String blacknumber = intent.getStringExtra("blacknumber");
		if(!TextUtils.isEmpty(blacknumber)){
			showAddBlackNumberDialog(blacknumber);
		}
	}