//�ָ��ߵĻ���
<View
	android:layout_alignParentBottom="true"
	android:background="@drawable/listview_devider"//�������ɫ������������,�ͳ��˲�ͬ����ɫ��������
	android:layout_width="fill_parent"
	android:layout_height="1dip" >//����������������
</View>

//�Զ�������С��,һ��Ϊ��һ��Ϊ��,ʵ������С�������
    <style name="image_on_style" parent="@style/image_star_style">
        <item name="android:src">@android:drawable/presence_online</item>
         <item name="android:paddingLeft">2dip</item>
    </style>

    <style name="image_off_style" parent="@style/image_star_style">
        <item name="android:src">@android:drawable/presence_invisible</item>
         <item name="android:paddingLeft">2dip</item>
    </style>
