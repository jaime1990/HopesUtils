//�������Ŷ��� ��һ��������ʾx��ʼ��СΪ����view,�ڶ���������ʾ���ź�Ĵ�С��ԭ��С,������������y�����ʼ��С,
//���ĸ�������y���ź�Ĵ�С��ԭ��С,�����������������ʱx��y����Ĳ��յ�,������view�����ĵ�
ScaleAnimation sa = new ScaleAnimation(0.2f, 1.0f, 0.2f,1.0f, 0.5f, 0.5f);
sa.setDuration(600);
view.startAnimation(sa);�������������������
view.clearAnimation();//�����������ȡ������