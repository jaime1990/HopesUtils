
/* ʵ���ı����ƹ��� 
*/ 
public static void copy(String content, Context context) { 
// �õ������������ 
	ClipboardManager cmb = (ClipboardManager) context .getSystemService(Context.CLIPBOARD_SERVICE); 
	cmb.setText(content.trim()); 
} 
/** 
���ƴ��� ��������:
* ʵ��ճ������ 
*/ 
public static String paste(Context context) { 
// �õ������������ 
ClipboardManager cmb = (ClipboardManager) context .getSystemService(Context.CLIPBOARD_SERVICE); 
return cmb.getText().toString().trim(); 
} 
