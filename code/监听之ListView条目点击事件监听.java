ListView��Ŀ����¼����� �����GridView��һ����.
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