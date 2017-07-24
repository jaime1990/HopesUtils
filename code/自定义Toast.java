//�Զ���Toast

		view = View.inflate(this, R.layout.ui_toast, null);
		int which = sp.getInt("which", 0);
		view.setBackgroundResource(bgs[which]);
		//�������������
		TextView tv = (TextView) view.findViewById(R.id.tv_toast_address);
		tv.setText(AddressDao.getAddress(incomingNumber));
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		params.gravity = Gravity.TOP | Gravity.LEFT;//������յ�
		int lastx = sp.getInt("lastx", 0);
		int lasty = sp.getInt("lasty", 0);
		//�������λ��
		params.x = lastx;
		params.y = lasty;
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		//������
		params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;// �������ȡ�������Ļ����
		params.format = PixelFormat.TRANSLUCENT;// ��͸��Ч��
		// TYPE_PRIORITY_PHONE��ʾ��������Ϊһ�ֵ绰�̿��ҿ��Դ���������,�����TOAST�������޷�������.Ҫ����System.alert.windowsȨ�޾Ϳ���.		
		params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
		params.setTitle("Toast");
		// ��ӵ绰������Ļ���ƶ�view�ķ���
		windowService.addView(view, params);

	}