
1.����֮�����ƶ�
//TranslateAnimation���ƶ��Ķ���Ч���������������캯�����ֱ��ǣ�

	1.public��TranslateAnimation(Context context,AttributeSet attrs)   �Թ�

	2.public��TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)

	���������������õ�һ�����췽����

	����float fromXDelta:���������ʾ������ʼ�ĵ��뵱ǰView X�����ϵĲ�ֵ��

	����float toXDelta, ���������ʾ���������ĵ��뵱ǰView X�����ϵĲ�ֵ��

	����float fromYDelta, ���������ʾ������ʼ�ĵ��뵱ǰView Y�����ϵĲ�ֵ��

	����float toYDelta)���������ʾ������ʼ�ĵ��뵱ǰView Y�����ϵĲ�ֵ��

	�������view��A(x,y)�� ��ô�������Ǵ�B��(x+fromXDelta, y+fromYDelta)���ƶ���C ��(x+toXDelta,y+toYDelta)��.

	3.public��TranslateAnimation (int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)

	����fromXType:��һ��������x�᷽���ֵ�Ĳ���(Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF,or Animation.RELATIVE_TO_PARENT);

	����fromXValue:�ڶ��������ǵ�һ���������͵���ʼֵ��

	����toXType,toXValue:��������������ĸ�������x�᷽����յ�������Ӧֵ��

�����ĸ������Ͳ��ý����ˡ����ȫ��ѡ��Animation.ABSOLUTE����ʵ���ǵڶ������캯����

��x��Ϊ�����ܲ������Ӧֵ�Ĺ�ϵ:

//���ѡ�����ΪAnimation.ABSOLUTE����ô��Ӧ��ֵӦ���Ǿ��������ֵ������100��300��ָ���Ե���Ļ���ص�λ
//���ѡ�����ΪAnimation.RELATIVE_TO_SELF���� Animation.RELATIVE_TO_PARENTָ�������������򸸿ؼ�����ӦֵӦ�����Ϊ�����������߸��ؼ��ļ�����ٷ�֮����
1.ע:1.0��ʾ��view��ȫ��,float����,����д1.0f.
//��������:
	//1.����ƽ������ʧ.
		TranslateAnimation ta = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, 0,
						Animation.RELATIVE_TO_SELF, 1.0f,
						Animation.RELATIVE_TO_SELF, 0,
						Animation.RELATIVE_TO_SELF, 0);
		ta.setDuration(800);//����ִ��ʱ��
		view.startAnimation(ta);//��������.
	//1.����ƽ������ʧ.
		TranslateAnimation ta = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0,//��ʾ��0Ϊ������
				Animation.RELATIVE_TO_SELF, -1.0f,//��ʾ��ʼ���ƶ������������ʧ����
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0);
		ta.setDuration(800);
		view.startAnimation(ta);