//�������Ŷ��� ��һ��������ʾx��ʼ��СΪ����view,�ڶ���������ʾ���ź�Ĵ�С��ԭ��С,������������y�����ʼ��С,
					//���ĸ�������y���ź�Ĵ�С��ԭ��С,�����������������ʱx��y����Ĳ��յ�,������view�����ĵ�
	ScaleAnimation sa = new ScaleAnimation(0.2f, 1.0f, 0.2f,
			1.0f, 0.5f, 0.5f);
	sa.setDuration(600);

	TranslateAnimation ta = new TranslateAnimation(
			Animation.RELATIVE_TO_SELF, 0,
			Animation.RELATIVE_TO_SELF, 0.1f,
			Animation.RELATIVE_TO_SELF, 0,
			Animation.RELATIVE_TO_SELF, 0);
	ta.setDuration(800);
	//AnimationSet���Լ���Animation������֮������AnimationSet�Լ��������Animation����Ч��
	//����true��ô���е�Animation����AnimationSet��Interpolator(�����仯������.)false��ʾ��ÿ���������ѵ�Interpolator.
	AnimationSet set = new AnimationSet(false);
	set.addAnimation(sa);
	set.addAnimation(ta);
	view.startAnimation(set);
2.Ҳ������xml�ļ��ж���,��ǿ������

<?xml version="1.0" encoding="utf-8"?> 
<set xmlns:android="http://schemas.android.com/apk/res/android" 
    android:interpolator="@android:anim/accelerate_interpolator" 
    android:shareInterpolator="true" 
    > 
    <alpha 
        android:fromAlpha="1.0" 
        android:toAlpha="0.0" 
        android:startOffset="500" 
        android:duration="3000" 
            /> 
    <rotate 
        android:fromDegrees="0" 
        android:toDegrees="400" 
        android:pivotX="50%" 
        android:pivotY="50%" 
        android:duration="3000" 
    /> 
 
</set> 

<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:interpolator="@android:anim/accelerate_interpolator"
    android:shareInterpolator="true"
    >
    <alpha
        android:fromAlpha="1.0"
        android:toAlpha="0.0"
        android:startOffset="500"
        android:duration="3000"
            />
    <rotate
        android:fromDegrees="0"
        android:toDegrees="400"
        android:pivotX="50%"
        android:pivotY="50%"
        android:duration="3000"
    />

</set>