//Ų���Ի���λ��
Window mWindow = dialog.getWindow();  
WindowManager.LayoutParams lp = mWindow.getAttributes();  
lp.x = 10;   //��λ��X����  
lp.y = -100; //��λ��Y����  
dialog.onWindowAttributesChanged(lp);

//���ƶԻ���λ��
 window =dialog.getWindow();// �������õ��Ի���Ĵ��ڣ�  
      WindowManager.LayoutParams wl = window.getAttributes();  
       wl.x = x;//�����������˶Ի����λ�ã�0Ϊ�м�  
       wl.y =y;  
       wl.width =w;  
       wl.height =h;  
       wl.alpha =0.6f;// ��������˶Ի����͸����   
