//����֮GridView��Ŀ�������
gv_home = (GridView) findViewById(R.id.gv_home);
gv_home.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent;
				//ͨ������,��0��ʼ,������,���ϵ���
				switch (position) {
				case 0:
					// �ж��û��Ƿ����ù�����.
					if (isSetupPwd()) {
						showNormalEntryDialog();
					} else {
						showFirstEntryDialog();
					}
					break;
				
				//...............
				case 8:
					intent = new Intent(HomeActivity.this,
							SettingActivity.class);
					startActivity(intent);
					break;
				}
			}
		});