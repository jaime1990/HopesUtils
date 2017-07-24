// java.lang.Object
  // java.text.Format
      // java.text.NumberFormat
          // java.text.DecimalFormat
// DecimalFormat �� NumberFormat ��һ���������࣬���ڸ�ʽ��ʮ�������֡���������и��ֹ��ܣ�ʹ���ܹ������͸�ʽ���������Ի����е�����
// �������������ԡ����������ӡ�������ֵ�֧�֡�����֧�ֲ�ͬ���͵������������� (123)�������� (123.4)����ѧ��������ʾ���� (1.23E4)��
// �ٷ��� (12%) �ͽ�� ($123)��������Щ���ݶ����Ա��ػ��� 

// ���������ʱ����ʱ��Ҫ���������ϵ�λ����ʱ��Ҫ���־���һ���ľ��ȣ�Ҳ��ʱ��Ҫ�ÿ�ѧ��������ʾ���֡�
// �ؼ�����������
// v java.text.DecimalFormat��ר�����ڸ�ʽ�����֡�
// v ��ҪΪDecimalFormat�ṩ��ʽ��ģʽPattern��ͨ�����췽������DecimalFormat��applyPattern��������ģʽ��Pattern������Ϊ�ַ�����
// v ����DecimalFormat��formatʵ������������Ϊ����ʽ�������֣��÷���ʹ��DecimalFormat�����pattern�Բ������и�ʽ����
import java.text.DecimalFormat;
 
public class TestDecimalFormat {
public static void main(String[] args) {
DecimalFormat df = new DecimalFormat();
double data = 1203.405607809;
System.out.println("��ʽ��֮ǰ��" + data);
 
String pattern = "0.0";//1203.4
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
// ������ģʽ������Լ���Ҫ���κ��ַ������絥λ
pattern = "00000000.000kg";//00001203.406kg
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
//#��ʾ������ھ���ʾ�ַ�����������ھͲ���ʾ��ֻ������ģʽ����ͷ
pattern = "##000.000kg";//1203.406kg
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
//-��ʾ���Ϊ���������������ǰ��
pattern = "-000.000";//-1203.406
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
//,�Ƿ���ָ��� ��������12,03.41
pattern = "-0,00.0#";//-12,03.41
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
//E��ʾ���Ϊָ������E��֮ǰ���ַ����ǵ����ĸ�ʽ��֮�����ָ���ĸ�ʽ��
pattern = "0.00E000";//1.20E003
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
//%��ʾ����100����ʾΪ�ٷ�����Ҫ�������
pattern = "0.00%";//120340.56%
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
//"\u2030"��ʾ����1000����ʾΪǧ������Ҫ�������
pattern = "0.00\u2030";//203405.61��
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
//"\u00A4"���ҷ��ţ�Ҫ��������*****1203.41��
pattern = "0.00\u00A4";//1203.41��
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
//'������ǰ׺����׺��Ϊ�����ַ������ţ�Ҫ���������ű���������ʹ�����������ţ�"# o''clock"�� 
pattern = "'#'#" ;//#1203
// pattern = "'#'" ;//#1203
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
 
pattern = "# o''clock" ;//1203 o'clock
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
//''�����м����浥���ž���ʾ����󣬷�����ǰ�浥���ž���ʾ����ǰ
// pattern = "# o''clock.000" ;//1203.406 o'clock
// pattern = "# .000o''clock";//1203.406 o'clock
// pattern = "# .000''";//1203.406 '
// pattern = "# .''000";//1203.406 '
pattern = "''# .000";//'1203.406 
df.applyPattern(pattern);
System.out.println("����" + pattern + "ģʽ��ʽ����" + df.format(data));
}
}
������Ϊ��
��ʽ��֮ǰ��1203.405607809
����0.0ģʽ��ʽ����1203.4
����00000000.000kgģʽ��ʽ����00001203.406kg
����##000.000kgģʽ��ʽ����1203.406kg
����-000.000ģʽ��ʽ����-1203.406
����-0,00.0#ģʽ��ʽ����-12,03.41
����0.00E000ģʽ��ʽ����1.20E003
����0.00%ģʽ��ʽ����120340.56%
����0.00��ģʽ��ʽ����1203405.61��
����0.00��ģʽ��ʽ����1203.41��
����'#'#ģʽ��ʽ����#1203
����# o''clockģʽ��ʽ����1203 o'clock
����''# .000ģʽ��ʽ����'1203.406
������
2 ������java.text.DecimalForm�Ĺ��췽�����ƶ�ģʽ��pattern����Ҳ����ʹ��applyPattern�������ø�ʽģʽ��
2 ��ʽģʽ���ض��Ĺ��򣬲�̫���ַ�����ͬ����˼����ʹ������Ҫ�Լ���Ƹ�ʽ��ģʽ��
���õ�ģʽ�У���.������-������#������%������\u2030������,������E������0����������Щģʽ�ľ��庬���ڳ���ע��������ϸ�Ľ��ܣ�Ҳ���Բ���JDK�İ����ĵ���

// ģʽ��0��# ��ͬ

// ģʽ�е�"#"��ʾ�����λ�����ַ�������ʾ�ַ�����������ڣ�����ʾ��
    //����һ�����ָ�ʽ�����󣬸�ʽ��ģ��Ϊ".##"��������2λС��.
     DecimalFormat a = new DecimalFormat(".##");
     String s= a.format(333.335);
     System.err.println(s);
     //˵�������С������治��2λС�������Ჹ��.�μ�RoundingС��
     //---------------------------------------------

     //-----------------------------------------------
     //����������ʱ���ú���applyPattern(String)�޸ĸ�ʽģ��
     //����2λС�������С������治��2λС���Ჹ��
     a.applyPattern(".00");
     s = a.format(333.3);
     System.err.println(s);
     //------------------------------------------------

     //------------------------------------------------
     //���ǧ�ֺ�
     a.applyPattern(".##\u2030");
     s = a.format(0.78934);
     System.err.println(s);//���ǧλ����,С�������λ������ǧλ��
     //------------------------------------------------

     //------------------------------------------------
     //��Ӱٷֺ�
     a.applyPattern("#.##%");
     s = a.format(0.78645);
     System.err.println(s);
     //------------------------------------------------

    //------------------------------------------------
     //���ǰ���������ַ������ǵ�Ҫ�õ�����������
     a.applyPattern("'�����ҵ�Ǯ$',###.###'��Բ'");
     s = a.format(33333443.3333);
     System.err.println(s);
     //------------------------------------------------

      //------------------------------------------------
     //��ӻ��ұ�ʾ����(��ͬ�Ĺ��ң���ӵķ��Ų�һ��
     a.applyPattern("\u00A4");
     s = a.format(34);
     System.err.println(s);
     //------------------------------------------------

     //-----------------------------------------------
     //����������ģ��,�ǵ�Ҫ�÷ֺŸ���
      a.applyPattern("0.0;'@'-#.0");
      s = a.format(33);
      System.err.println(s);
      s = a.format(-33);
      System.err.println(s);
      //-----------------------------------------------
    
     //�ۺ����ã��������Ĳ�ͬǰ��׺
     String pattern="'my moneny'###,###.##'RMB';'ur money'###,###.##'US'";
     a.applyPattern(pattern);
     System.out.println(a.format(1223233.456));
   }
}