//listview��������
lv_app_manager.setOnScrollListener(new OnScrollListener() {

		//�������״̬�����ı�ʱ,�����������.		
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		//scrollState������״̬:���Ը�������״̬�Ĳ�ͬȥʵ�ֲ�ͬ�Ĳ���.
		1.����Ļֹͣ����ʱΪ0:int SCROLL_STATE_IDLE = 0:
		2.����Ļ�������û�ʹ�õĴ�������ָ������Ļ��ʱΪ1:int SCROLL_STATE_TOUCH_SCROLL = 1;
		3.�����û��Ĳ�������Ļ�������Ի���ʱΪ2 :int SCROLL_STATE_FLING = 2;
	}
	
	// ��listview������ʱ�� ����onscroll�ķ���.
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
	
	1.firstVisibleItem:��ʾ����ʱ��Ļ��һ��ListItem(������ʾ��ListItemҲ��)������ListView��λ�ã��±��0��ʼ�� 
	2.visibleItemCount:��ʾ����ʱ��Ļ���Լ�����ListItem(������ʾ��ListItemҲ��)���� 
	3.totalItemCount:��ʾListView��ListItem����
	4.listView.getLastVisiblePosition():��ʾ����ʱ��Ļ���һ��ListItem(���ListItemҪ��ȫ��ʾ��������,	  ������ListView��λ�ã��±��0��ʼ�� 
	}
});