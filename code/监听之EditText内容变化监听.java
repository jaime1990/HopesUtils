//���һ��EditText���ݱ仯�ļ�����
		et_query_number.addTextChangedListener(new TextWatcher() {
			//���ݱ仯�ͻ�ִ��
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			//����Ϳ�������Ӧ�Ĵ���,����:ʵ�ֱ�����߲�ѯ
				String number = s.toString();
				String address = AddressDao.getAddress(number);
				tv_query_address.setText("������Ϊ:"+address);
			}
			//���ݱ仯ǰִ�еķ���
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			//���ݱ仯��ִ�еķ���
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});