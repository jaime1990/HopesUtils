1.XML��JSON��ʹ�ýṹ��������������ݣ���������һ���򵥵ıȽϡ�

1.��XML��ʾ�й�����ʡ���������£�

<?xml version="1.0" encoding="utf-8"?>

<country>

    <name>�й�</name>

    <province>

        <name>������</name>

     <cities>

            <city>������</city>

            <city>����</city>

        </cities>

    </province>

    <province>

        <name>�㶫</name>

        <cities>

            <city>����</city>

            <city>����</city>

            <city>�麣</city>

        </cities>

    </province>

</country>

2.��JSON��ʾ���£�

{

{name:"�й�", province:[ { name:"������", cities:{ city:["������","����"] },

{name:"�㶫", cities:{ city:["����","����","�麣"] } 

}
//json��js��Դ�ļ�
private List<Blog> parseJSON(InputStream inputStream) throws Exception {
		List<Blog> blogs = new ArrayList<Blog>();
		
		byte[] data = Util.read(inputStream);		// ��ȡ�����д�ص�����
		String json = new String(data);				// ����һ���ַ���
		
		JSONArray arr = new JSONArray(json);		// ���ַ�����װ��һ��JSON�������
		for (int i = 0; i < arr.length(); i++) {	// ѭ����������
			JSONObject obj = arr.getJSONObject(i);	// �õ�ÿһ��JSON����
			Blog blog = new Blog();
			blog.setPortrait(obj.getString("portrait"));	// ��ȡJSON�����е�����, ���ø�Blog����
			blog.setName(obj.getString("name"));
			blog.setContent(obj.getString("content"));
			String pic = obj.getString("pic");
			blog.setPic(pic.equals("null") ? null : pic);
			blog.setFrom(obj.getString("from"));
			blogs.add(blog);
		}
		
		return blogs;
	}