package com.itheima.blacklist;

import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
//�ֻ��յ����žͻᷢ������Ź㲥,��������������Ӧ���յ�֮ǰ�жϹ㲥,�ұ����������.
public class SmsRecever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");		// ��ȡ��������(���),��������pdusΪ���ֵ��������intent�е�.
		for (Object pdu : pdus) {										// ѭ������
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);	// ���ֽ����ݷ�װΪSmsMessage����
			Date date = new Date(sms.getTimestampMillis());//��ö���
			String address = sms.getOriginatingAddress();//��õ�ַ
			String body = sms.getMessageBody();//��ö�����������
			System.out.println(date + " " + address + " " + body);
			
			if ("18600012345".equals(address))//�����ĳ������,���ж�.
				abortBroadcast();
		}
	}

}
