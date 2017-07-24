//�Ż��ĺ���˼����ǰ�view��view�����view����޶ȵĸ���,����view����,adapter�Զ���¼��,��convertView
//����view��û��,���Ծ�Ҫ��һ���ڲ���ȥ������,view��һ������setTag���Դ�һ������,����view�����ڲ�����,���ڲ�����view��,
//ͬʱview.getTag�Ϳ��԰Ѷ���ȡ����,ǿת�ɱ���������,�Ϳ��Եõ��������view
ע:
1.ListView.getCount()��ListView.getChildCount()����:
//getCount()���ص����� Adapter.getCount() ���ص�ֵ��Ҳ���ǡ��������� Item �ܸ�������
//��getChildCount()(ViewGroup.getChildCount) ���ص�����ʾ�����ϵġ����������� View ��������Ҳ����˵getChildCount()���ص��ǵ�ǰ�ɼ��� Item ������
public class MainActivity extends Activity {

    private ListView calllog;
	private List<MyCallLog> list;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calllog = (ListView)findViewById(R.id.calllog);
        CallLogDao dao=new CallLogDao(this);
        list =dao.getCallog();
        calllog.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
	//�ȶ���������Ա����;
				ViewHolder holder;
				View view;
				//���������,�򴴽�
				if (convertView==null) {
					view = View.inflate(MainActivity.this, R.layout.listview_item, null);
					holder=new ViewHolder();
					holder.tv_name = (TextView) view.findViewById(R.id.tv_name//����Ҫע��Ҫ�ò�����viewȥ��ò��������view
					holder.tv_phone = (TextView) view.findViewById(R.id.tv_phone);
					holder.tv_duration = (TextView)view.findViewById(R.id.tv_duration);
					holder.type = (TextView) view.findViewById(R.id.type);
					view.setTag(holder);
				}else{//������ֱ��������
					view=convertView;
					holder=(ViewHolder) view.getTag();
				}
				//�õ�holder�Ϳ���ȡ����������Ķ�����,���ѽ�����Ӧ�Ĳ���.
				MyCallLog myCallLog=list.get(position);
				holder.tv_name.setText(myCallLog.getName());
				holder.tv_phone.setText(myCallLog.getPhone());
				holder.tv_duration.setText(myCallLog.getDuration());
				holder.type.setText(myCallLog.getType());
				
				return view;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return list.get(position);
			}
			
			@Override
			public int getCount() {
				return list.size();
			}
		});
    }
	//������view�Ĵ洢��
	static class ViewHolder {
		public TextView tv_phone;
		public TextView tv_time;
		public TextView tv_duration;
		public TextView type;
		public TextView tv_name;
	}
}
