ListView�Ķ�̬����
private ListView lv_select_contact;
	private List<ContactInfo>  infos;
	private LinearLayout loading;
	private String limit="_id desc limit 30 offset ";//�������30��ʾÿ�β������������,���Ѷ���
	private int offset;//��ʾ�ӵ������ݿ�ʼ��
	private int total;//�����ʾ����Ŀ��
	private ContactAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_contact);
		lv_select_contact = (ListView) findViewById(R.id.lv_select_contact);
		loading = (LinearLayout) findViewById(R.id.loading);
		refreshData();//���ȵ�һ����չʾһ����
		//����Listview����.ͨ��offset�ı仯ȥʵ�����ݵı仯
		lv_select_contact.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState==OnScrollListener.SCROLL_STATE_IDLE) {//SCROLL_STATE_IDLE��ʾ������ֹͣ״̬.
					int position = lv_select_contact.getLastVisiblePosition();//��õ�ǰ�ɼ���Ŀ�����һ������
					int endPosition=infos.size();//��õ�ǰlist���������һ������
					if (position==endPosition-1) {
						offset+=30;
						if (offset>total) {//�����������Ŀ��ʾ���ݼ�������
							Toast.makeText(SelectContactActivity.this, "û�и���������", 1).show();
						}
						refreshData();//ˢ������
					}
				}
				
			}
			//����������ڻ��һЩ����������
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
		//������Ŀ�ĵ���¼�
		lv_select_contact.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ContactInfo info = infos.get(position);
				String phone = info.getPhone();
				Intent data = new Intent();
				data.putExtra("phone", phone);
				setResult(0, data);
				finish();
			}
		});
		
	}
//��̬��ʾ��ȡ���ݵķ���
	private void refreshData() {
		new  MyAsynTask(){
			@Override
			public void beforeTask() {
				loading.setVisibility(View.VISIBLE);
			}
			@Override
			public void runTask() 
			//ִ�в�ѯǰ,�õ�����Ŀ
				if (total==0) {
					total=ContactInfoProvider.getTotal(SelectContactActivity.this);
				}
				//�õ�adapter��Ҫ�����ݼ���,û��ֱ�Ӵ���
				if (infos==null) {
					infos = ContactInfoProvider.getContactInfos(SelectContactActivity.this,limit+0);
				}else{
				//�еĻ�ֱ�Ӱѵõ��ļ�����ӽ�ȥ
					infos.addAll(ContactInfoProvider.getContactInfos(SelectContactActivity.this,limit+offset));
				}
			}
			@Override
			public void afterTask() {
			//ִ�к�رս�����
				loading.setVisibility(View.INVISIBLE);
				if (adapter==null) {//���adapterΪ����Ӧ���ǵ�һ�����,��ô����һ��
					adapter=new ContactAdapter();
					lv_select_contact.setAdapter(adapter);
				}else{
				//����о�ֱ��ˢ��,�����ͱ�����ÿ�μ����궼������һ��.
				//����ס,��������ݿ���ֱ�ӹ���,������ݼ���û�б仯,�����û��Ч����,ֻ�д��ز�ѯ���ݿ�õ��¼��ϲſ���..
					adapter.notifyDataSetChanged();
				}
			}}.startTask();
	}
	//���������
	private class ContactAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return infos.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder hodler;
			View view ;
			if (convertView!=null&&convertView instanceof RelativeLayout) {
				view = convertView;
				hodler=(ViewHolder) view.getTag();
			}else{
				view = View.inflate(getApplicationContext(), R.layout.list_contact_item, null);
				hodler=new ViewHolder();
				hodler.tv_name=(TextView) view.findViewById(R.id.tv_contact_name);
				hodler.tv_phone=(TextView) view.findViewById(R.id.tv_contact_phone);
				view.setTag(hodler);
			}
			ContactInfo info = infos.get(position);
			hodler.tv_name.setText(info.getName());
			hodler.tv_phone.setText(info.getPhone());
			return view;
		}
		
	}
	//��������
	static class ViewHolder {
		TextView tv_name;
		TextView tv_phone;
	}
}