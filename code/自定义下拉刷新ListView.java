package pulldown.refresh;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyListView extends ListView implements OnScrollListener {
	private int state;
	private static final int PULL_TO_REFRESH = 0;// ����ˢ��
	private static final int RELEASE_TO_REFRESH = 1;// �ɿ�ˢ��
	private static final int REFRESHING = 2;// ����ˢ��
	private static final int DONE = 3;// ˢ�����

	private ImageView arrow;
	private ProgressBar refresh;
	private TextView pullTV;
	private TextView timeTV;
	private View headView;
	private int headContentHeight;
	private int firstVisibleIndex;
	private boolean isRecord;
	private float startY;
	private float temp;
	private RotateAnimation animation;
	private RotateAnimation reverseAnimation;
	private boolean isBack;
	private OnRefreshListener refreshListener;
//�����������췽��,ȫ����Ҫ����headView,��Ϊ��ͬʱ�ڴ��������õĹ���Ҳ��һ��
	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public MyListView(Context context) {
		super(context);
		init(context);
	}
//��������ӿ�
	public interface OnRefreshListener {
		abstract void onRefresh();
	}
//�ṩ���Ʒ���.
	public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
		refreshListener = onRefreshListener;
	}
//����ˢ�·���.��������ִ��ˢ�µĲ�����,��ס���ǿ��ж�(�������жϼ��������ʱ,�ò��������),����������Ǽ������ķ���,Ҳ�����м����Ż�ִ��.(Ҳ���Ǳ�Ķ�����ʵ���˼����������)
	private void onRefresh() {
		if (refreshListener != null) {
			refreshListener.onRefresh();
		}
	}

	// ��ʼ��headView.�м�:���ĸ�LayoutView�µ���view,Ҫ�����ViewҪ��ȥ���.
	private void init(Context context) {
		headView = View.inflate(context, R.layout.header, null);
		arrow = (ImageView) headView.findViewById(R.id.arrow);
		refresh = (ProgressBar) headView.findViewById(R.id.refresh);
		pullTV = (TextView) headView.findViewById(R.id.pullTV);
		timeTV = (TextView) headView.findViewById(R.id.timeTV);
		//����ؼ���С��ʾ���
		arrow.setMinimumWidth(70);
		arrow.setMinimumHeight(50);
		measureView(headView);
		//��Ϊ���Ҫ�õ���ֻ�Ǹ߶�,����ֻ��Ҫ��ø߶ȾͿ���
		headContentHeight = headView.getMeasuredHeight();
		headView.setPadding(0, -1 * headContentHeight, 0, 0);
		headView.invalidate();// �ػ�
		addHeaderView(headView);// ���
		setOnScrollListener(this);
		//����ö���.
		// ��ͷ���µ���
		animation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(250);// ��������ʱ��
		animation.setFillAfter(true);
		animation.setInterpolator(new LinearInterpolator());
		// ��ͷ���ϵ���
		reverseAnimation = new RotateAnimation(0, -180,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		reverseAnimation.setDuration(200);// ��������ʱ��
		reverseAnimation.setFillAfter(true);
		/**
		 * Interpolator �����˶����ı仯�ٶȣ�����ʵ�����١������١������١��޹������ٵȣ�
			AccelerateDecelerateInterpolator���ӳټ��٣��ڶ���ִ�е��м��ʱ���ִ�и���Ч��
			AccelerateInterpolator, ��ʹ������(float)�Ĳ��������ٶȡ�
			LinearInterpolator��ƽ�Ȳ����
			DecelerateInterpolator�����м����,��ͷ��
			CycleInterpolator�������˶���Ч��Ҫ����float�͵Ĳ�����
		 */
		reverseAnimation.setInterpolator(new LinearInterpolator());
	}
//�������ʵ���ǰ�װ��measure����,measure�����ְ�װ��onMeasure����.�����Ǹ��ؼ�����ʾ�ӿؼ��ǻص�,�Եõ��ӿؼ��ĵĿ��.�Ӷ���ʾ.
	private void measureView(View child) {
		ViewGroup.LayoutParams lp = child.getLayoutParams();
		if (lp == null) {
			lp = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		//����������ջ��ǵ��� MeasureSpec.makeMeasureSpec����.
		int childMeasureWidth = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
		int childMeasureHeight;
		if (lp.height > 0) {
			childMeasureHeight = MeasureSpec.makeMeasureSpec(lp.height,
					MeasureSpec.EXACTLY);
		} else {
			childMeasureHeight = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childMeasureWidth, childMeasureHeight);

	}

	//
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		//����Ļֹͣ����ʱΪ0������Ļ�������û�ʹ�õĴ�������ָ������Ļ��ʱΪ1�� 
		//�����û��Ĳ�������Ļ�������Ի���ʱΪ2 
		//����ȫ����ʾ����ʱ���д˴����룬���Ҫʵ�ַ�ҳ���ܣ������������һҳ������ 
	}
//ʵ��OnScrollListener���ڻ�õ�ǰ��ֵ
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		firstVisibleIndex = firstVisibleItem;
//		 firstVisibleItem��ʾ����ʱ��Ļ��һ��ListItem(������ʾ��ListItemҲ��)������ListView��λ�ã��±��0��ʼ�� 
//		visibleItemCount��ʾ����ʱ��Ļ���Լ�����ListItem(������ʾ��ListItemҲ��)���� 
//		totalItemCount��ʾListView��ListItem���� 
//		listView.getLastVisiblePosition()��ʾ����ʱ��Ļ���һ��ListItem(���ListItemҪ��ȫ��ʾ��������)������ListView��λ�ã��±��0��ʼ�� 
	}
	//������Ļ�����¼�.�����߼�������
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ����Ļ��������֮ǰû�м�¼ʱ,��ʼ��¼
			if (firstVisibleIndex == 0 && !isRecord) {
				startY = ev.getY();
				isRecord = true;
			}
			break;
			//����������״̬
		case MotionEvent.ACTION_MOVE:
			if (isRecord) {//headViewҪ������ǰ���ǵ�ǰView�ĵ�һ��ΪListView������
				temp = ev.getY();//���ȼ�¼�����ƶ��ľ���.
				//����:�ڻ���ʱ,ҳ�治��������ˢ����ʾ������ˢ����ʾ,������们�������Ƿ񳬹�view���ȥ�ж��Ƿ�
				if (state != RELEASE_TO_REFRESH && state != REFRESHING) {
					if ((temp - startY) / 3 >= headContentHeight) {
						// ������ڿؼ����,��ô��ʾ�ɿ�ˢ��
						state = RELEASE_TO_REFRESH;
						isBack = true;//һ�������ɿ�ˢ��,��ı䶯��,����Ҫ��������������ˢ��ʱ�л�����.
						changeOfHeadViewState();

					} else {
						// ֻҪ���������ڿؼ����,����ʾ����ˢ��
						state = PULL_TO_REFRESH;
						changeOfHeadViewState();
					}
				}
				//�����������ˢ����ʾ״̬,��һ����headView�������ϱ��غ�,������ʾ����ˢ����ʾ.
				if (state == RELEASE_TO_REFRESH) {
					setSelection(0);//��ֹ��ҳʱ,ListView���з����˸ı�,����������������޷������޷�ִ��
					if ((temp - startY) / 3 <= headContentHeight) {
						state = PULL_TO_REFRESH;
						changeOfHeadViewState();
					}
				}
				//�ؼ�:�������ĸ�״̬,ˢ���Ѿ�������ߺ�ˢ������ɼ�����ִ��,��ֻ������������ˢ����û�ж���,����������ݻ��������Զ���ʾ.
				if (state == PULL_TO_REFRESH || state == RELEASE_TO_REFRESH) {
					headView.setPadding(0,(int) ((temp - startY) / 3 - headContentHeight), 0,0);
				}
			}
			break;
			//����ʱ����ֻ��һ�����Ҫ����:��������ˢ����ʾת��ˢ��,��������headView���벻��ʾ״̬
		case MotionEvent.ACTION_UP:
			if (isRecord && state == RELEASE_TO_REFRESH) {
				state = REFRESHING;
				changeOfHeadViewState();
				onRefresh();
			} else {
				state = DONE;
				changeOfHeadViewState();
			}
			isRecord = false;//ͬʱ�����false,���´��ٰ���ʱ,���¼�¼�µ�startYֵ.
			break;
		}
		return super.onTouchEvent(ev);
	}
	//����View����״̬����ʾ��ͼ.
	private void changeOfHeadViewState() {
		switch (state) {
		case PULL_TO_REFRESH:// ����
			setHeadView(View.VISIBLE, View.GONE, "      ��������ˢ��");
			if (isBack) {
				arrow.startAnimation(animation);
				isBack = false;
			}
			break;
		case RELEASE_TO_REFRESH:// �ɿ�ˢ��
			setHeadView(View.VISIBLE, View.GONE, "      ���ֿ���ˢ��");
			arrow.startAnimation(reverseAnimation);
			break;
		case REFRESHING:// ����ˢ��
			setHeadView(View.GONE, View.VISIBLE, "      ���ڼ���....");
			headView.setPadding(0, 0, 0, 0);
			break;
		case DONE:// ˢ�����
			arrow.clearAnimation();
			headView.setPadding(0, -1 * headContentHeight, 0, 0);
			break;
		}
	}
	/**
	 * �趨headView����ʾ״̬
	 * @param arrowVisibility  ��ͷ�Ƿ�ɼ�
	 * @param refreshVisibility   ˢ��ͼ���Ƿ�ɼ�
	 * @param pullTVText ��ʾ����.
	 */
	private void setHeadView(int arrowVisibility, int refreshVisibility,
			String pullTVText) {
		arrow.setVisibility(arrowVisibility);
		refresh.setVisibility(refreshVisibility);
		pullTV.setVisibility(View.VISIBLE);
		pullTV.setText(pullTVText);
		timeTV.setVisibility(View.VISIBLE);
		arrow.clearAnimation();// �������
	}
//����ˢ����ɷ���
	public void refreshCompletion() {
		state = DONE;
		changeOfHeadViewState();
		timeTV.setText("������:" + getCurrentTime());
	}
 // �����õ�ǰʱ�䷽��
	public String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		Date date = new Date();
		String dateString = formatter.format(date);
		return dateString;
	}
//�ض�setAdapter������һ��ʾҳ��ʱ,�͸�ҳ���趨һ����ǰʱ��.
	@Override
	public void setAdapter(ListAdapter adapter) {
		timeTV.setText("������:" + getCurrentTime());
		super.setAdapter(adapter);
	}
}
