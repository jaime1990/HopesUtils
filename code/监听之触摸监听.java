//��������
//ע:���ͬʱ������OnClickListener�¼�,��ô���뷵��false,���û�ж���,����뷵��true,��ʾ������������¼�,����Ч��.
//��ͼƬ���һ�������¼�
		iv_drag_view.setOnTouchListener(new OnTouchListener() {
			
			private int startX;
			private int startY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN://����
					startX = (int) event.getRawX();
					startY = (int) event.getRawY();
					break;
					//����ָ�ƶ�ʱѭ��ȥ�õ�������,��������λ��,��������ٴ��ػ�ÿ�ʼλ��,��ȥ��������,һֱѭ����ȥ
				case MotionEvent.ACTION_MOVE://�ƶ�
					//�ƶ����¼��λ��
					int newX = (int) event.getRawX();
					int newY = (int) event.getRawY();
					//��ȡ�µ������
					int dx = newX - startX;
					int dy = newY - startY;
					//�õ�ԭ���ؼ��ĸ�������յ�ľ���(���յ�����Ļ���Ͻǵ�)
					int l = iv_drag_view.getLeft();
					int t = iv_drag_view.getTop();
					int r = iv_drag_view.getRight();
					int b = iv_drag_view.getBottom();
					//�õ��ĸ��ߵ��¾���
					int newl = l + dx;
					int newr = r + dx;
					int newt = t + dy;
					int newb = b + dy;
					//�����µ�λ��
					//��������߾�,������,������������
					if (newl<0||newr>screenWidth||newt<0||newb>screenHeight-30) {
						break;
					}
					iv_drag_view.layout(newl, newt, newr, newb);
					//tv_drag_view.getBottom()-tv_drag_view.getTop();
					//���ͼƬ������Ļ��һ��
					if (newb>=screenHeight/2) {
						tv_drag_view.layout(tv_drag_view.getLeft(), 0, tv_drag_view.getRight(), tv_drag_view.getHeight());
					}else{
					//����Ҫע����Ļ���ǰ�����˵ı�������,����Ҫ��30
						tv_drag_view.layout(tv_drag_view.getLeft(), screenHeight-tv_drag_view.getHeight()-30, tv_drag_view.getRight(), screenHeight-30);
					}
					startX = (int) event.getRawX();
					startY = (int) event.getRawY();
					break;
				case MotionEvent.ACTION_UP:
					//��ָ�뿪ʱ,�����������,����������ʾ�������λ��
					Editor editor = sp.edit();
					editor.putInt("lastx", iv_drag_view.getLeft());
					editor.putInt("lasty", iv_drag_view.getTop());
					editor.commit();
					break;
				}
				return false;
			}
		});