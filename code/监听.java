������:

//��������
new OnClickListener() {
	@Override
	public void onClick(DialogInterface dialog, int which) {
		
	}
});

//view�ĵ������
view.setOnItemClickListener(new  OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
	
	}		
});

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
//ExpandableListView��View������� (�����Ҫ������ôisChildSelectable���뷵��true)
elv = (ExpandableListView) findViewById(R.id.elv);
elv.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_DIAL);
				//��ȡ�绰����:split���ڷָ��ַ����ɶ���.
				String number = childrenCacheInfos.get(groupPosition).get(childPosition).split("\n")[1];
				intent.setData(Uri.parse("tel:"+number));
				startActivity(intent);
				return false;
			}
		});


