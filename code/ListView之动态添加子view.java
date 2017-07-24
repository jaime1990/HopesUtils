//�ܶ�ʱ��Ҫ��view�������Ŀ���ж�̬�޸Ĳ�
public View getView(int position, View convertView, ViewGroup parent) {
	View view;
	ViewHolder holder;
	if (convertView != null && convertView instanceof RelativeLayout) {
		view = convertView;
		holder = (ViewHolder) view.getTag();
	} else {
		view = View.inflate(getApplicationContext(),R.layout.list_app_item, null);
		holder = new ViewHolder();
		//.......
		holder.ll_container = (LinearLayout) view.findViewById(R.id.ll_appstatus_container);//����Ҫ���Ҫ�޸�layout����
		view.setTag(holder);
	}
	//ÿ�����֮ǰҪ���һ��layout��Ϊ������holder������,��Ȼlayout�����view����.
	holder.ll_container.removeAllViews();
	//�����ٸ���������layout�����view
	if(appinfo.isUsecontact()){
		ImageView iv = new ImageView(getApplicationContext());
		iv.setImageResource(R.drawable.contact);
		holder.ll_container.addView(iv, DensityUtil.dip2px(getApplicationContext(), 25), DensityUtil.dip2px(getApplicationContext(), 25));
	}if(appinfo.isUsegps()){
		ImageView iv = new ImageView(getApplicationContext());
		iv.setImageResource(R.drawable.gps);
		holder.ll_container.addView(iv, DensityUtil.dip2px(getApplicationContext(), 25), DensityUtil.dip2px(getApplicationContext(), 25));
	}if(appinfo.isUsesms()){
		ImageView iv = new ImageView(getApplicationContext());
		iv.setImageResource(R.drawable.sms);
		holder.ll_container.addView(iv, DensityUtil.dip2px(getApplicationContext(), 25), DensityUtil.dip2px(getApplicationContext(), 25));
	}if(appinfo.isUsenet()){
		ImageView iv = new ImageView(getApplicationContext());
		iv.setImageResource(R.drawable.net);
		holder.ll_container.addView(iv, DensityUtil.dip2px(getApplicationContext(), 25), DensityUtil.dip2px(getApplicationContext(), 25));
	}
	//ll_container����view�е�һ��layout,����ll_container�޸���,��view��Ȼ���޸�,��󷵻�view�Ϳ�����.
	return view;
}



	static class ViewHolder {
		TextView tv_name;
		TextView tv_location;
		TextView tv_version;
		ImageView iv_icon;
		LinearLayout ll_container;
	}
	//����һ��ll_container��xml
	<LinearLayout
        android:id="@+id/ll_appstatus_container"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:layout_alignParentTop="true"
        android:orientation="horizontal" >
    </LinearLayout>