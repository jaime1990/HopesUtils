//����֮�������������ֶ���
1.��ʽһ:
bt_ok.setOnClickListener(new OnClickListener() {

	@Override
	public void onClick(View v) {
		int id = rg.getCheckedRadioButtonId();
		String mode = "1";
		switch (id) {
		case R.id.rb_all:
			mode ="1";
			break;
		case R.id.rb_phone:
			mode ="2";
			break;
		case R.id.rb_sms:
			mode ="3";
			break;
		}
		dao.update(info.getNumber(), mode);
		//�����µ�����ģʽ��info����.
		info.setMode(mode);
		setResult(200);
		finish();
		
	}
});
//2.��ʽ��:��ʵ�ּ���,Ȼ����дonClick����
public class AppManagerActivity extends Activity implements OnClickListener {
		tv_unlock.setOnClickListener(this);
			tv_locked.setOnClickListener(this);
}
		
		@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_unlock:// δ����.
			tv_unlock.setBackgroundResource(R.drawable.tab_left_pressed);
			tv_locked.setBackgroundResource(R.drawable.tab_right_default);
			ll_unlock.setVisibility(View.VISIBLE);
			ll_locked.setVisibility(View.INVISIBLE);
			break;

		case R.id.tv_locked:// �Ѽ���
			tv_unlock.setBackgroundResource(R.drawable.tab_left_default);
			tv_locked.setBackgroundResource(R.drawable.tab_right_pressed);
			ll_unlock.setVisibility(View.INVISIBLE);
			ll_locked.setVisibility(View.VISIBLE);
			break;
		}
	}