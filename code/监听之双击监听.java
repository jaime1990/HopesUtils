1.�������֮˫��.
//����˫������	
iv_drag_view.setOnClickListener(new OnClickListener() {

	@Override
	public void onClick(View v) {
		//����һ��ʱ�����0,��ʾ�Ѿ������һ����
		if (fristTime>0) {
			long secondTime = System.currentTimeMillis();
			if (secondTime-fristTime<500) {
				iv_drag_view.layout((screenWidth-iv_drag_view.getWidth())/2, iv_drag_view.getTop(), (screenWidth+iv_drag_view.getWidth())/2, iv_drag_view.getBottom());
				Editor editor = sp.edit();
				editor.putInt("lastx", iv_drag_view.getLeft());
				editor.putInt("lasty", iv_drag_view.getTop());
				editor.commit();
			}
				fristTime=0;//ֻҪ���ε������,���õ�һ�ε�ʱ��Ϊ0
		}
		fristTime = System.currentTimeMillis();
		//��������һ�κ�ʱ��,���Զ������һ�ε��ʱ��
		new Thread(){
			public void run() {
				SystemClock.sleep(500);
				fristTime=0;
			};
		}.start();
	}
});
