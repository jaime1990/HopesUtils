//view���Ҷ�����
//1.��res�ж���һ��anim�ļ���
//2.���ļ����ж�������xml�ļ�

//xmlһ
<?xml version="1.0" encoding="utf-8"?>

<cycleInterpolator xmlns:android="http://schemas.android.com/apk/res/android"
    android:cycles="7" />

//xml��
<?xml version="1.0" encoding="utf-8"?>

<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="1000"
    android:fromXDelta="0"
    android:interpolator="@anim/cycle_7"
    android:toXDelta="10" />
	
//3.��view��Ӷ���
Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
view.startAnimation(animation);// view��ʾһ��view����