//��ȡ�绰������
TelephonyManager teleService = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//����������
PhoneStateListener listen = new MyPhoneStateListener();
//��Ӽ�����
teleService.listen(listen, PhoneStateListener.LISTEN_CALL_STATE);
//���������
class MyPhoneStateListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:// �绰����
				// �ر���ʾ
				if (view != null) {
					windowService.removeView(view);
					view = null;
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING:// ����״̬.
				// ������ʾ
				showAddress(incomingNumber);
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:// ����ͨ��
				// ���һ��ر���ʾ
				new Thread(){public void run() {
					SystemClock.sleep(1000);
					if (view != null) {
						windowService.removeView(view);
						view = null;
					}
				};}.start();
				break;
			}
		}
		
	}