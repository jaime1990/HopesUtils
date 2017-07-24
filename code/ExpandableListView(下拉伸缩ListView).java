//��������ListView 
//����ExpandableListView.xmllayout----����map����-----�����������̳�BaseExpandableListAdapter---���������---�����������view�¼�(�����ҪisChildSelectable����true)
public class CommonNumberActivity extends Activity  {
	private ExpandableListView elv;
	private List<String> groupNames;
	private Map<Integer, List<String>> childrenCacheInfos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��������,ǰ���ʾ������,�����ʾ������view�ļ���
		childrenCacheInfos = new HashMap<Integer, List<String>>();
		setContentView(R.layout.activity_common_num);
		//����չ��Listview,����Listview
		elv = (ExpandableListView) findViewById(R.id.elv);
		elv.setAdapter(new MyAdapter());
		listBrand_ELV.setGroupIndicator(null);//���岻��ʾĬ�ϼ�ͷ.
		listBrand_ELV.setDivider(null);//���岻��ʾĬ�ϵ�����.
		// չ�����˵�,�����ʾȫ��չ��.
		for (int i = 0; i < paramObject.size(); i++) {
				listBrand_ELV.expandGroup(i);
			}
		//������view�µ���view����¼� ,������ֱ�ӵõ����벦��绰
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
1.ע��Ҫ������getView������view�ļ���:��://view.setOnClickListener(CommonNumberActivity.this);ע:��������this��Activity����ʵ��OnClickListener�ӿ�.
2.isChildSelectableҪ����true.
3.�����¼��Ĵ���.
			sellELV.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				itemLongClick(position);
				return false;
			}
		});
	}
	private void itemLongClick(int position){
		switch (position) {
		case -1://������item
			
			break;

		default://����1,�ǾͶ��ǲ�����view
			
			break;
		}
	}
//getGroupCount��getChildrenCount������getViewִ�е�,���Կ��԰Ѳ�ѯ���ݿ��ȡ���ݵķ�������������,����һ��һ���ȡ.
	private class MyAdapter extends BaseExpandableListAdapter {

		// ���ض��ٸ����� ע:���һ��Ҫ
		@Override
		public int getGroupCount() {
			// return CommonNumDao.getGroupCount();
			groupNames = CommonNumDao.getGroupInfos();
			return groupNames.size();
		}
		//����������ض�Ӧ��ÿ�������view���� ע:���һ��Ҫ
		@Override
		public int getChildrenCount(int groupPosition) {// 0 ��ʼ
			List<String> childreninfos;
		//childrenCacheInfos��һ��Map<Integer, List<String>> ����,��ôInteger��ӦgroupPosition����
			//��ô�Ϳ���ͨ��groupPosition��ö�Ӧ���鼯��,�Ӷ����ϸ��������������Ŀ��
			if (childrenCacheInfos.containsKey(groupPosition)) {//���������ж�һ���Ƿ񼯺����Ƿ�������������
				childreninfos = childrenCacheInfos.get(groupPosition);
			} else {
				//���û�оͲ�ѯ����id ,��ü��ϲ������,map��
				childreninfos = CommonNumDao.getChildrenInfosByPosition(groupPosition);
				childrenCacheInfos.put(groupPosition, childreninfos);
			}
			return childreninfos.size();
		}
		//���ض�Ӧ������������
		@Override
		public Object getGroup(int groupPosition) {
			return childrenCacheInfos.get(groupPosition);
		}
		//���ض�Ӧ���ж�Ӧ����view���������
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return childrenCacheInfos.get(groupPosition).get(childPosition);
		}
		//���ض�Ӧ��������
		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}
		//���ض�Ӧ����view����
		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}
		//��hasStableIds()��true����ôgetChildId����Ҫ�����ȶ���ID
		@Override
		public boolean hasStableIds() {
			//�����Ԫ���Ƿ�����ȶ���ID,Ҳ���ǵײ����ݵĸı䲻��Ӱ�쵽���ǡ�
			//����ֵ:����һ��Boolean���͵�ֵ�����ΪTRUE����ζ����ͬ��ID��Զ������ͬ�Ķ���
			return false;
		}
		//���ض�Ӧ����view ע:������
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			TextView tv;
			if(convertView!=null&&convertView instanceof TextView){
				tv = (TextView) convertView;
			}else{
				tv = new TextView(getApplicationContext());
			}
			//����view������
			tv.setTextSize(25);
			tv.setTextColor(Color.RED);
			tv.setText("      " + groupNames.get(groupPosition));
			return tv;
		}
		//������view�µ���view ע:������
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			TextView tv;
			if(convertView!=null&&convertView instanceof TextView){
				tv = (TextView) convertView;
			}else{
				tv = new TextView(getApplicationContext());
			}
			tv.setTextSize(18);
			tv.setTextColor(Color.BLUE);
			
			tv.setText(childrenCacheInfos.get(groupPosition).get(childPosition));
			return tv;
		}
		//���ŷ�����ʾ��view�Ƿ������Ӧ����¼�.true��ʾ����,false��ʾ������.
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
}