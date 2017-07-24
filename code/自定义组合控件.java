//�Զ�����Ͽؼ�: ������:��һ��Ӧ�������Ƶ�һ�������ȡ����,�γ�һ����Ͽؼ�,������������view������.
//����,�����һ����ϵ�layout.xml
//����: 
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="64dip" >

    <TextView
        android:id="@+id/tv_settingview_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="6dip"
        android:layout_marginTop="5dip"
        android:text="���õı���"
        android:textAppearance="?android:attr/textAppearanceLarge" />

    <TextView
        android:id="@+id/tv_settingview_content"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentLeft="true"
        android:layout_below="@+id/tv_settingview_title"
        android:layout_marginLeft="7dip"
        android:layout_marginTop="5dip"
        android:text="TextView" />

    <CheckBox
        android:clickable="false"
        android:focusable="false"
        android:id="@+id/checkBox1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:layout_centerVertical="true" />

    <View
        android:layout_alignParentBottom="true"
        android:background="@drawable/listview_devider"
        android:layout_width="fill_parent"
        android:layout_height="1dip" >
    </View>

</RelativeLayout>

//����Ҫʵ�ֵ�Ч�����ǰ������װ��һ���Զ���view,��ʱ�����������layout�����þͿ�����
//����:������layout��ֱ����������Ϳ���.
 <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:itheima="http://schemas.android.com/apk/res/cn.itheima.mobilesafe"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >
    <TextView
        android:layout_width="fill_parent"
        android:layout_height="45dip"
        android:background="#355E9E"
        android:gravity="center"
        android:text="��������"
        android:textColor="#EBC950"
        android:textSize="23dip" />
    <cn.itheima.mobilesafe.ui.SettingView
        android:id="@+id/sv_setting_update"
        itheima:title="�Զ���������"
        itheima:checked_text="�Զ������Ѿ�����"
        itheima:unchecked_text="�Զ�����û�п���"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content" >
    </cn.itheima.mobilesafe.ui.SettingView>
	
//�ؼ��л����Զ���һЩ����,��:һЩ�ַ���,��ʱ�����Զ���view�����оͿ���ֱ��������,Ȼ���������Ͽؼ��е���Ҫ��view��.
//������ʱ��,ֻ��������Ͽؼ�ʱ,���岻ͬ������,��Ϳ�������Ͽؼ��е�view����ͬ�����ݻ�״̬��ʾ.
//Ҫʵ����Ҫ��values�а���Ҫ�������Զ����һ��xml  valuesĿ¼ ����declare-styleable  �Զ��������.����:
<?xml version="1.0" encoding="utf-8"?>
<resources>
     <declare-styleable name="setting_view_style">//name��ʾ�����Դ������
//name��ʾ������ format��ʾ����ֵ����.������String ,color��,���Բ���:\android-sdk\platforms\android-10\data\res\values�µ�attrs.xml
         <attr name="title" format="string"></attr>
         <attr name="checked_text" format="string"></attr>
         <attr name="unchecked_text" format="string"></attr>
     </declare-styleable>
</resources>


</LinearLayout>
	
//��������Ҫ�Զ��������Ͽؼ�������,��������:
//1. дһ���� �̳�ViewGroup 
public class SettingView extends RelativeLayout {
	private View view;
	private TextView tv_settingview_title;
	private TextView tv_settingview_content;
	private CheckBox cb_status;
	private String checked_text;
	private String unchecked_text;
	
//2.����һ����ʼ������,����Ҫ������Ǹ�layout�������view,��ö���
	private void initView(Context context) {
		view = View.inflate(context, R.layout.ui_setting_view, this);//�ȼ���
		tv_settingview_title = (TextView) view.findViewById(R.id.tv_settingview_title);
		tv_settingview_content = (TextView) view.findViewById(R.id.tv_settingview_content);
		cb_status = (CheckBox) view.findViewById(R.id.checkBox1);
	}
//3.��д���췽���ѳ�ʼ���������嵽ÿһ������.
	public SettingView(Context context) {
		super(context);
		initView(context);
	}
	public SettingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}
//4.����������а���Ͽؼ���view�����Ժ������Լ���������Լ��Ͻ���ӳ���ϵ,
	public SettingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		//�Ȼ�����Լ���
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.setting_view_style);
		//���ÿһ������
		String title = a.getString(R.styleable.setting_view_style_title);
		checked_text = a.getString(R.styleable.setting_view_style_checked_text);
		unchecked_text = a.getString(R.styleable.setting_view_style_unchecked_text);
		//����õ����Թ�����view��������
		tv_settingview_content.setText(unchecked_text);
		tv_settingview_title.setText(title);
		a.recycle();//�ͷ���Դ.
	}

}
//���Ϳ���������һ��������
 <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:itheima="http://schemas.android.com/apk/res/cn.itheima.mobilesafe"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >
    <TextView
        android:layout_width="fill_parent"
        android:layout_height="45dip"
        android:background="#355E9E"
        android:gravity="center"
        android:text="��������"
        android:textColor="#EBC950"
        android:textSize="23dip" />
    <cn.itheima.mobilesafe.ui.SettingView
        android:id="@+id/sv_setting_update"
        itheima:title="�Զ���������"
        itheima:checked_text="�Զ������Ѿ�����"
        itheima:unchecked_text="�Զ�����û�п���"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content" >
    </cn.itheima.mobilesafe.ui.SettingView>