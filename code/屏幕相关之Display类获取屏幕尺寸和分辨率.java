android.view.Display��ʹ��
�����
Display���ṩ������Ļ�ߴ�ͷֱ��ʵ���Ϣ��

1.�ṹ

�̳й�ϵ

public class Display extends Object



2��������
//ע��:�����ķ����ǰѻ�ȡ��������䵽����������,������֮ǰ����ֵ.

//��ȡ��Ļ�߶�
public int getHeight ()

�˷���������ʹ��

��ʹ��getSize(Point) ����

//��ȡ��������Ļ�ߴ�ͷֱ��ʵ�DisplayMetrics����Ҫ�ȴ���һ��DisplayMetrics��Ϊ����.
public void getMetrics (DisplayMetrics outMetrics)
�˴�С�ǻ��ڵ�ǰ��Ļ��ת�������ġ�
�˷������صĴ�С����һ������ʵ����Ļ��ԭʼ��С��ԭʼ�ֱ��ʣ������صĴ�С���ܻ��������������ų�ĳЩ���ǿɼ���ϵͳ����Ԫ�ء� 
��Ҳ�����Ǳ��������ṩ��ɵ�Ϊ��С�ĳ�ʼ��Ӧ��һ�£���ʼӦ�������Ϊ��С����Ļ����Ƶġ�
����:outMetrics :һ�� DisplayMetrics ������� metrics.

//�����Ļ�ķ���
public int getOrientation ()
�˷���������ʹ��
��ʹ��getRotation()����
 ����ֵ:��Ļ�ķ���
 
 //���ر�����Ļ���ظ�ʽ��
public int getPixelFormat ()

���ر�����Ļ���ظ�ʽ������ֵ������һ��int���͵�PixelFormat�ĳ�����
����:������һ��int���͵�PixelFormat�ĳ���

//��ȡ��Ļ���εĴ�С,������Ϊ��λ. Ҫ�ȴ���һ�����ζ���.
public void getRectSize (Rect outSize)
����:OutSize    һ��Rect������ܴ�С����Ϣ
�μ�
getSize(Point)

//������Ļ֡ÿ���ˢ���ʡ�
public float getRefreshRate ()

//���شӡ���Ȼ��(natural)�������Ļ��ת������
public int getRotation ()

����ֵ����Surface.ROTATION_0������ת����Surface.ROTATION_90��Surface.ROTATION_180����Surface.ROTATION_270��
���磺����豸��һ�������Ļ��ʹ����ת��������򣬴�ʱ����ֵ������Surface.ROTATION_90 ���� Surface.ROTATION_270��ȡ��������ת�ķ���
�Ƕ��ǻ���ͼ������Ļ����ת�������豸������ת���෴����
���磺������豸����ʱ����ת90�ȣ����ֲ���Ⱦ��˳ʱ����ת90�ȣ��Ӷ�����ķ���ֵ������Surface.ROTATION_90��

//��ȡ��Ļ�ĳߴ磬������Ϊ��λ��
public void getSize (Point outSize)
ע�⣬��ֵ��Ӧ�����ڼ��㲼�֣���Ϊһ���豸������ʾͨ������Ļװ��(����״̬��)�����ٵ�Ӧ�ÿռ�ʹ��ԭ�ߴ���֮������
����Ӧ�ø��ô��ڵĴ�С���˴�С�ǻ��ڵ�ǰ��Ļ��ת�������ġ�
�˷������صĴ�С����һ������ʵ����Ļ��ԭʼ��С��ԭʼ�ֱ��ʣ���
���صĴ�С���ܻ��������������ų�ĳЩϵͳ���ǿɼ���װ��Ԫ�ء���Ҳ���������ţ��ṩ��ɣ������ΪС��Ļ��Ƶ�Ӧ�ó���ļ����ԡ�
����:outsize    Point������մ�С����Ϣ��

//��ȡ��Ļ���.
public int getWidth ()
�˷���������ʹ��
��ʹ��getSize(Point) ����