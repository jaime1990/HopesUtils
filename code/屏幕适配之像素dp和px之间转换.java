//Ϊ������Ļ�ڴ�����ʵ��dip��Ч��,Ҳ�����ڲ�ͬ�ֱ��ʵ��ֻ�����ʾ��ͬ�Ĵ�С,�������ǲ�����dip��,ֻ����pix,��ô�Ϳ���ȥ����һ��dipȥת��dip2px��pix,
//���ʵ���˲�ͬ��Ļ�ϲ�ͬ��ʾ.��:
//��������������Ҫpix,�ⶨ���25dipȥת,��ô��ͬ����Ļ�ͻ�ת���ɲ�ͬ��pix.
holder.ll_container.addView(iv, DensityUtil.dip2px(getApplicationContext(), 25), DensityUtil.dip2px(getApplicationContext(), 25));
public class DensityUtil {  
  
    /** 
     * �����ֻ��ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
}  