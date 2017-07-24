StringUtils ������ĳ��÷���

StringUtils Դ�룬ʹ�õ���commons-lang3-3.1����
���ص�ַ http://commons.apache.org/lang/download_lang.cgi

������StringUtils�ĸ����÷�
1.���ַ������
ʹ�ú���: StringUtils.isBlank(testString)
��������: ��testStringΪ��,����Ϊ����߽��ɿհ��ַ�(whitespace)���ʱ,����True;���򷵻�False
����:
    String test = "";
    String test2 = "\n\n\t";
    String test3 = null;
    String test4 = "Test";
    System.out.println( "test blank? " + StringUtils.isBlank( test ) );
    System.out.println( "test2 blank? " + StringUtils.isBlank( test2 ) );
    System.out.println( "test3 blank? " + StringUtils.isBlank( test3 ) );
    System.out.println( "test4 blank? " + StringUtils.isBlank( test4 ) );
�������:
test blank? true
test2 blank? true
test3 blank? true
test4 blank? False
����StringUtils.isNotBlank(testString)�Ĺ�����StringUtils.isBlank(testString)�෴.

2.����հ��ַ�
ʹ�ú���: StringUtils.trimToNull(testString)
��������:�����testString��β�Ŀհ��ַ�,�����testStringȫ�ɿհ��ַ�
(whitespace)����򷵻�null
����:
    String test1 = "\t";
    String test2 = "  A  Test  ";
    String test3 = null;
    System.out.println( "test1 trimToNull: " + StringUtils.trimToNull( test1 ) );
    System.out.println( "test2 trimToNull: " + StringUtils.trimToNull( test2 ) );
    System.out.println( "test3 trimToNull: " + StringUtils.trimToNull( test3 ) );
�������:
test1 trimToNull: null
test2 trimToNull: A  Test
test3 trimToNull: null
ע�⣺����StringUtils.trim(testString)��
StringUtils.trimToNull(testString)�������ƣ���testString�ɿհ��ַ�
(whitespace)���ʱ�����㳤���ַ�����

3.ȡ���ַ�������д
ʹ�ú���: StringUtils.abbreviate(testString,width)��StringUtils.abbreviate(testString,offset��width)
��������:�ڸ�����width��ȡ��testString����д,��testString�ĳ���С��width�򷵻�ԭ�ַ���.
����:
    String test = "This is a test of the abbreviation.";
    String test2 = "Test";
    System.out.println( StringUtils.abbreviate( test, 15 ) );
    System.out.println( StringUtils.abbreviate( test, 5,15 ) );
    System.out.println( StringUtils.abbreviate( test2, 10 ) );
�������:
This is a te...
...is a test...
Test
4.�����ַ���
ʹ�ú���: StringUtils.split(testString,splitChars,arrayLength)
��������:splitChars�п��԰���һϵ�е��ַ���������testString,�������趨��
������ĳ���.ע���趨����arrayLength�������ַ������еִ���ϵ,����һ�������
��Ҫ�趨����.
����:
    String input = "A b,c.d|e";
    String input2 = "Pharmacy, basketball funky";
    String[] array1 = StringUtils.split( input, " ,.|");
    String[] array2 = StringUtils.split( input2, " ,", 2 );

    System.out.println( ArrayUtils.toString( array1 ) );
    System.out.println( ArrayUtils.toString( array2 ) );
�������:
{A,b,c,d,e}
{Pharmacy,basketball funky}
5.����Ƕ���ַ���
ʹ�ú���:StringUtils.substringBetween(testString,header,tail)
�������ܣ���testString��ȡ��header��tail֮����ַ������������򷵻ؿ�
���̣�
    String htmlContent = "ABC1234ABC4567";
    System.out.println(StringUtils.substringBetween(htmlContent, "1234", "4567"));
    System.out.println(StringUtils.substringBetween(htmlContent, "12345", "4567"));
������£�
    ABC
    null

6.ȥ��β�����з�
ʹ�ú���:StringUtils.chomp(testString)
��������:ȥ��testStringβ���Ļ��з�
����:
    String input = "Hello\n";
    System.out.println( StringUtils.chomp( input ));
    String input2 = "Another test\r\n";
    System.out.println( StringUtils.chomp( input2 ));
�������:
    Hello
    Another test

7.�ظ��ַ���
ʹ�ú���:StringUtils.repeat(repeatString,count)
��������:�õ���repeatString�ظ�count�κ���ַ���
����:
    System.out.println( StringUtils.repeat( "*", 10));
    System.out.println( StringUtils.repeat( "China ", 5));
�������:
    **********
    China China China China China
��������:StringUtils.center( testString, count,repeatString );
��������:��testString���뽫repeatString�ظ���κ���ַ����м�,�õ��ַ���
���ܳ�Ϊcount
����:
    System.out.println( StringUtils.center( "China", 11,"*"));
�������:
    ***China***

8.�ߵ��ַ���
ʹ�ú���:StringUtils.reverse(testString)
��������:�õ�testString���ַ��ߵ�����ַ���
����:
    System.out.println( StringUtils.reverse("ABCDE"));
�������:
    EDCBA
9.�ж��ַ������ݵ�����
��������:
StringUtils.isNumeric( testString ) :���testStringȫ��������ɷ���True
StringUtils.isAlpha( testString ) :���testStringȫ����ĸ��ɷ���True
StringUtils.isAlphanumeric( testString ) :���testStringȫ�����ֻ�������
�ɷ���True
StringUtils.isAlphaspace( testString )  :���testStringȫ����ĸ��ո���
�ɷ���True
����:
    String state = "Virginia";
    System.out.println( "Is state number? " + StringUtils.isNumeric(
state ) );
    System.out.println( "Is state alpha? " + StringUtils.isAlpha( state )
);
    System.out.println( "Is state alphanumeric? " +StringUtils.isAlphanumeric( state ) );
    System.out.println( "Is state alphaspace? " + StringUtils.isAlphaSpace( state ) );
�������:
    Is state number? false
    Is state alpha? true
    Is state alphanumeric? true
    Is state alphaspace? true
10.ȡ��ĳ�ַ�������һ�ַ����г��ֵĴ���
ʹ�ú���:StringUtils.countMatches(testString,seqString)
��������:ȡ��seqString��testString�г��ֵĴ���,δ�����򷵻���
����:
    System.out.println(StringUtils.countMatches( "Chinese People", "e"
));
���:
    4
11.���ֽ�ȡ�ַ���
ʹ�ú���:
StringUtils.substringBetween(testString,fromString,toString ):ȡ�����ַ�
֮����ַ���
StringUtils.substringAfter( ):ȡ��ָ���ַ�������ַ���
StringUtils.substringBefore( )��ȡ��ָ���ַ���֮ǰ���ַ���
StringUtils.substringBeforeLast( )��ȡ�����һ��ָ���ַ���֮ǰ���ַ���
StringUtils.substringAfterLast( )��ȡ�����һ��ָ���ַ���֮����ַ���
�������ܣ�����Ӧ�ö��������˰ɡ�
���̣�
    String formatted = " 25 * (30,40) [50,60] | 30";
    System.out.print("N0: " + StringUtils.substringBeforeLast( formatted, "*" ) );
    System.out.print(", N1: " + StringUtils.substringBetween( formatted, "(", "," ) );
    System.out.print(", N2: " + StringUtils.substringBetween( formatted, ",", ")" ) );
    System.out.print(", N3: " + StringUtils.substringBetween( formatted, "[", "," ) );
    System.out.print(", N4: " + StringUtils.substringBetween( formatted, ",", "]" ) );
    System.out.print(", N5: " + StringUtils.substringAfterLast( formatted, "|" ) );
������£�
    N0:  25 , N1: 30, N2: 40, N3: 50, N4: 40) [50,60, N5:  30

1. ����ַ����Ƿ�Ϊ�գ�
 static boolean isBlank(CharSequence str)  �ж��ַ����Ƿ�Ϊ�ջ�null;
 static boolean isNotBlank(CharSequence str) �ж��ַ����Ƿ�ǿջ��null;
 StringUtils.isBlank("a");
 ���ؽ��Ϊ: false;
2. �����ַ�����
 static String abbreviate(String str, int maxWidth) �����ַ������ڶ�����������Ϊ4������...��
 StringUtils.abbreviate("abcdefg", 20);
 ���ؽ��Ϊ��abcdefg ��������ʾ��
 StringUtils.abbreviate("abcdefg", 4);
 ���ؽ��Ϊ��a...
3. ����ĸ��д��
 static String capitalize(String str) ����ĸ��д
 static String uncapitalize(String str)����ĸСд  
 StringUtils.capitalize("abcdefg");
 ���ؽ����Abcdefg
4. �ַ�����ʾ��һ�����ַ�����λ�ã�
 static String center(String str, int size);  Ĭ���Կո����
 static String center(String str, int size, String padString); ����λ���ַ������
 public static String leftPad(String str,int size); ���ո����
 public static String leftPad(String str,int size,String padStr);����ַ������
 public static String rightPad(String str,int size); ���ո����
 public static String rightPad(String str,int size,String padStr);����ַ������
 
 StringUtils.center("abcdefg", 20)��
 ���ؽ����      abcdefg      
 StringUtils.center("abcdefg", 20,"*_");
 ���ؽ����*_*_*_abcdefg*_*_*_*
 StringUtils.leftPad("abc", 10, "*");
 ���ؽ����*******abc
5. �ظ��ַ�������
 static String repeat(String str, int repeat);
 StringUtils.repeat("abc", 5); 
 ���ؽ����abcabcabcabcabc
6. �Ƿ�ȫ�Ǵ�д���Ƿ�ȫ��Сд��3.0�汾��
 public static boolean isAllLowerCase(String str);
 public static boolean isAllUpperCase(String str);
 StringUtils.isAllLowerCase("abC");
 ���ؽ����false
7. �Ƿ�������ĸ��ɣ�
 public static boolean isAlpha(String str);  ֻ����ĸ���
 public static boolean isAlphaSpace(String str); ֻ����ĸ�Ϳո����
 public static boolean isAlphanumeric(String str);ֻ����ĸ���������
 public static boolean isAlphanumericSpace(String str);ֻ����ĸ���ֺͿո����
 public static boolean isNumeric(String str);ֻ���������
 public static boolean isNumericSpace(String str);ֻ�����ֺͿո����
 StringUtils.isAlpha("a2bdefg");
 ���ؽ����false
8. С�ַ����ڴ��ַ����е�ƥ�����
public static int countMatches(String str,String sub);
StringUtils.countMatches("ababsssababa", "ab");
 ���ؽ����4
9. �ַ�����ת
 public static String reverse(String str);
 StringUtils.reverse("abcdef");
 ���ؽ����fedcba
10. ��Сдת�����ո񲻶�
 
 public static String swapCase(String str);
 StringUtils.swapCase("I am a-A*a")
 ���ؽ����i AM A-a*A
StringUtils�������ʹ��
һ������ת���ַ����� 
1�� �������е��ַ�ת��Ϊһ���ַ��� 
�������е��ַ�ת��Ϊһ���ַ��� 

@param strToConv Ҫת�����ַ��� ,Ĭ���Զ��ŷָ� 
@return ����һ���ַ��� 
String[3] s={"a","b","c"} 
StringUtil.convString��s)="a,b,c" 
2�� static public String converString(String strToConv) 
@param strToConv Ҫת�����ַ��� , 
@param conv �ָ���,Ĭ���Զ��ŷָ� 
@return ͬ������һ���ַ��� 

String[3] s={"a","b","c"} 
StringUtil.convString��s,"@")="a@b@c" 
static public String converString(String strToConv, String conv) 


������ֵ��⣺ 
3�� 

Checks if a String is empty ("") or null. 


�ж�һ���ַ����Ƿ�Ϊ�գ��ո����ǿմ��� StringUtils.isEmpty(null) = true StringUtils.isEmpty("") = true StringUtils.isEmpty(" ") = false StringUtils.isEmpty("bob") = false StringUtils.isEmpty(" bob ") = false 

NOTE: This method changed in Lang version 2.0. 

It no longer trims the String. 
That functionality is available in isBlank(). 


@param str the String to check, may be null 
@return true if the String is empty or null 
public static boolean isEmpty(String str) 


�����ǿմ��� 
4�� 
Checks if a String is not empty ("") and not null. 


�ж�һ���ַ����Ƿ�ǿգ��ո����ǿմ���. StringUtils.isNotEmpty(null) = false StringUtils.isNotEmpty("") = false StringUtils.isNotEmpty(" ") = true StringUtils.isNotEmpty("bob") = true StringUtils.isNotEmpty(" bob ") = true 

@param str the String to check, may be null 
@return true if the String is not empty and not null 
public static boolean isNotEmpty(String str) 

5�� 

Checks if a String is not empty (""), not null and not whitespace only. 


�ж�һ���ַ����Ƿ�ǿգ��ո����մ���. StringUtils.isNotBlank(null) = false StringUtils.isNotBlank("") = false StringUtils.isNotBlank(" ") = false StringUtils.isNotBlank("bob") = true StringUtils.isNotBlank(" bob ") = true 

@param str the String to check, may be null 
@return true if the String is 
not empty and not null and not whitespace 
@since 2.0 
public static boolean isNotBlank(String str) 


�ġ� �ո��� 
6�� 
Removes control characters (char <= 32) from both 

ends of this String, handling null by returning 
null. 


The String is trimmed using {@link String#trim()}. 

Trim removes start and end characters <= 32. 
To strip whitespace use {@link //strip(String)}. 


To trim your choice of characters, use the 

{@link //strip(String, String)} methods. 


��ʽ��һ���ַ����еĿո��зǿ��жϴ��� StringUtils.trim(null) = null StringUtils.trim("") = "" StringUtils.trim(" ") = "" StringUtils.trim("abc") = "abc" StringUtils.trim(" abc ") = "abc" 

@param str the String to be trimmed, may be null 
@return the trimmed string, null if null String input 
public static String trim(String str) 

7�� 


Removes control characters (char <= 32) from both 

ends of this String returning null if the String is 
empty ("") after the trim or if it is null. 

The String is trimmed using {@link String#trim()}. 

Trim removes start and end characters <= 32. 
To strip whitespace use {@link /stripToNull(String)}. 


��ʽ��һ���ַ����еĿո��зǿ��жϴ������Ϊ�շ���null�� StringUtils.trimToNull(null) = null StringUtils.trimToNull("") = null StringUtils.trimToNull(" ") = null StringUtils.trimToNull("abc") = "abc" StringUtils.trimToNull(" abc ") = "abc" 

@param str the String to be trimmed, may be null 
@return the trimmed String, 
null if only chars <= 32, empty or null String input 
@since 2.0 
public static String trimToNull(String str) 

8�� 


Removes control characters (char <= 32) from both 

ends of this String returning an empty String ("") if the String 
is empty ("") after the trim or if it is null. 

The String is trimmed using {@link String#trim()}. 

Trim removes start and end characters <= 32. 
To strip whitespace use {@link /stripToEmpty(String)}. 


��ʽ��һ���ַ����еĿո��зǿ��жϴ������Ϊ�շ���""�� StringUtils.trimToEmpty(null) = "" StringUtils.trimToEmpty("") = "" StringUtils.trimToEmpty(" ") = "" StringUtils.trimToEmpty("abc") = "abc" StringUtils.trimToEmpty(" abc ") = "abc" 

@param str the String to be trimmed, may be null 
@return the trimmed String, or an empty String if null input 
@since 2.0 
public static String trimToEmpty(String str) 


�塢 �ַ����Ƚ�: 
9�� 
Compares two Strings, returning true if they are equal. 


nulls are handled without exceptions. Two null 

references are considered to be equal. The comparison is case sensitive. 


�ж������ַ����Ƿ���ȣ��зǿմ��� StringUtils.equals(null, null) = true StringUtils.equals(null, "abc") = false StringUtils.equals("abc", null) = false StringUtils.equals("abc", "abc") = true StringUtils.equals("abc", "ABC") = false 

@param str1 the first String, may be null 
@param str2 the second String, may be null 
@return true if the Strings are equal, case sensitive, or 
both null 
@see java.lang.String#equals(Object) 
public static boolean equals(String str1, String str2) 


10�� 

Compares two Strings, returning true if they are equal ignoring 

the case. 


nulls are handled without exceptions. Two null 

references are considered equal. Comparison is case insensitive. 


�ж������ַ����Ƿ���ȣ��зǿմ������Դ�Сд StringUtils.equalsIgnoreCase(null, null) = true StringUtils.equalsIgnoreCase(null, "abc") = false StringUtils.equalsIgnoreCase("abc", null) = false StringUtils.equalsIgnoreCase("abc", "abc") = true StringUtils.equalsIgnoreCase("abc", "ABC") = true 

@param str1 the first String, may be null 
@param str2 the second String, may be null 
@return true if the Strings are equal, case insensitive, or 
both null 
@see java.lang.String#equalsIgnoreCase(String) 
public static boolean equalsIgnoreCase(String str1, String str2) 


���� IndexOf ���� 
11�� 


Finds the first index within a String, handling null. 

This method uses {@link String#indexOf(String)}. 


A null String will return -1. 


����Ҫ���ҵ��ַ�������λ�ã��зǿմ��� StringUtils.indexOf(null, *) = -1 StringUtils.indexOf(*, null) = -1 StringUtils.indexOf("", "") = 0 StringUtils.indexOf("aabaabaa", "a") = 0 StringUtils.indexOf("aabaabaa", "b") = 2 StringUtils.indexOf("aabaabaa", "ab") = 1 StringUtils.indexOf("aabaabaa", "") = 0 

@param str the String to check, may be null 
@param searchStr the String to find, may be null 
@return the first index of the search String, 
-1 if no match or null string input 
@since 2.0 
public static int indexOf(String str, String searchStr) 

12�� 

Finds the first index within a String, handling null. 

This method uses {@link String#indexOf(String, int)}. 


A null String will return -1. 

A negative start position is treated as zero. 
An empty ("") search String always matches. 
A start position greater than the string length only matches 
an empty search String. 


����Ҫ��ָ��λ�ÿ�ʼ���ҵ��ַ�������λ�ã��зǿմ��� StringUtils.indexOf(null, *, *) = -1 StringUtils.indexOf(*, null, *) = -1 StringUtils.indexOf("", "", 0) = 0 StringUtils.indexOf("aabaabaa", "a", 0) = 0 StringUtils.indexOf("aabaabaa", "b", 0) = 2 StringUtils.indexOf("aabaabaa", "ab", 0) = 1 StringUtils.indexOf("aabaabaa", "b", 3) = 5 StringUtils.indexOf("aabaabaa", "b", 9) = -1 StringUtils.indexOf("aabaabaa", "b", -1) = 2 StringUtils.indexOf("aabaabaa", "", 2) = 2 StringUtils.indexOf("abc", "", 9) = 3 

@param str the String to check, may be null 
@param searchStr the String to find, may be null 
@param startPos the start position, negative treated as zero 
@return the first index of the search String, 
-1 if no match or null string input 
@since 2.0 
public static int indexOf(String str, String searchStr, int startPos) 


�ߡ� ���ַ������� 
13�� 
Gets a substring from the specified String avoiding exceptions. 


A negative start position can be used to start n 

characters from the end of the String. 


A null String will return null. 

An empty ("") String will return "". 


����ָ��λ�ÿ�ʼ���ַ����е������ַ� StringUtils.substring(null, *) = null StringUtils.substring("", *) = "" StringUtils.substring("abc", 0) = "abc" StringUtils.substring("abc", 2) = "c" StringUtils.substring("abc", 4) = "" StringUtils.substring("abc", -2) = "bc" StringUtils.substring("abc", -4) = "abc" 

@param str the String to get the substring from, may be null 
@param start the position to start from, negative means 
count back from the end of the String by this many characters 
@return substring from start position, null if null String input 
public static String substring(String str, int start) 

14�� 

Gets a substring from the specified String avoiding exceptions. 


A negative start position can be used to start/end n 

characters from the end of the String. 


The returned substring starts with the character in the start 

position and ends before the end position. All postion counting is 
zero-based -- i.e., to start at the beginning of the string use 
start = 0. Negative start and end positions can be used to 
specify offsets relative to the end of the String. 


If start is not strictly to the left of end, "" 

is returned. 


�����ɿ�ʼλ�õ�����λ��֮������ַ��� StringUtils.substring(null, *, *) = null StringUtils.substring("", * , *) = ""; StringUtils.substring("abc", 0, 2) = "ab" StringUtils.substring("abc", 2, 0) = "" StringUtils.substring("abc", 2, 4) = "c" StringUtils.substring("abc", 4, 6) = "" StringUtils.substring("abc", 2, 2) = "" StringUtils.substring("abc", -2, -1) = "b" StringUtils.substring("abc", -4, 2) = "ab" 

@param str the String to get the substring from, may be null 
@param start the position to start from, negative means 
count back from the end of the String by this many characters 
@param end the position to end at (exclusive), negative means 
count back from the end of the String by this many characters 
@return substring from start position to end positon, 
null if null String input 
public static String substring(String str, int start, int end) 


15�� SubStringAfter/SubStringBefore��ǰ�����ַ������� 


Gets the substring before the first occurance of a separator. 

The separator is not returned. 


A null string input will return null. 

An empty ("") string input will return the empty string. 
A null separator will return the input string. 


����ָ���ַ���֮ǰ�������ַ� StringUtils.substringBefore(null, *) = null StringUtils.substringBefore("", *) = "" StringUtils.substringBefore("abc", "a") = "" StringUtils.substringBefore("abcba", "b") = "a" StringUtils.substringBefore("abc", "c") = "ab" StringUtils.substringBefore("abc", "d") = "abc" StringUtils.substringBefore("abc", "") = "" StringUtils.substringBefore("abc", null) = "abc" 

@param str the String to get a substring from, may be null 
@param separator the String to search for, may be null 
@return the substring before the first occurance of the separator, 
null if null String input 
@since 2.0 
public static String substringBefore(String str, String separator) 

16�� 

Gets the substring after the first occurance of a separator. 

The separator is not returned. 


A null string input will return null. 

An empty ("") string input will return the empty string. 
A null separator will return the empty string if the 
input string is not null. 


����ָ���ַ���֮��������ַ� StringUtils.substringAfter(null, *) = null StringUtils.substringAfter("", *) = "" StringUtils.substringAfter(*, null) = "" StringUtils.substringAfter("abc", "a") = "bc" StringUtils.substringAfter("abcba", "b") = "cba" StringUtils.substringAfter("abc", "c") = "" StringUtils.substringAfter("abc", "d") = "" StringUtils.substringAfter("abc", "") = "abc" 

@param str the String to get a substring from, may be null 
@param separator the String to search for, may be null 
@return the substring after the first occurance of the separator, 
null if null String input 
@since 2.0 
public static String substringAfter(String str, String separator) 

17�� 

Gets the substring before the last occurance of a separator. 

The separator is not returned. 


A null string input will return null. 

An empty ("") string input will return the empty string. 
An empty or null separator will return the input string. 


�������һ��ָ���ַ���֮ǰ�������ַ� StringUtils.substringBeforeLast(null, *) = null StringUtils.substringBeforeLast("", *) = "" StringUtils.substringBeforeLast("abcba", "b") = "abc" StringUtils.substringBeforeLast("abc", "c") = "ab" StringUtils.substringBeforeLast("a", "a") = "" StringUtils.substringBeforeLast("a", "z") = "a" StringUtils.substringBeforeLast("a", null) = "a" StringUtils.substringBeforeLast("a", "") = "a" 

@param str the String to get a substring from, may be null 
@param separator the String to search for, may be null 
@return the substring before the last occurance of the separator, 
null if null String input 
@since 2.0 
public static String substringBeforeLast(String str, String separator) 

18�� 

Gets the substring after the last occurance of a separator. 

The separator is not returned. 


A null string input will return null. 

An empty ("") string input will return the empty string. 
An empty or null separator will return the empty string if 
the input string is not null. 


�������һ��ָ���ַ���֮��������ַ� StringUtils.substringAfterLast(null, *) = null StringUtils.substringAfterLast("", *) = "" StringUtils.substringAfterLast(*, "") = "" StringUtils.substringAfterLast(*, null) = "" StringUtils.substringAfterLast("abc", "a") = "bc" StringUtils.substringAfterLast("abcba", "b") = "a" StringUtils.substringAfterLast("abc", "c") = "" StringUtils.substringAfterLast("a", "a") = "" StringUtils.substringAfterLast("a", "z") = "" 

@param str the String to get a substring from, may be null 
@param separator the String to search for, may be null 
@return the substring after the last occurance of the separator, 
null if null String input 
@since 2.0 
public static String substringAfterLast(String str, String separator) 


�ˡ� Replacing���ַ����滻�� 
19�� 
Replaces all occurances of a String within another String. 


A null reference passed to this method is a no-op. 


��ָ���ַ����滻ԭ���ַ����ĵ�ָ���ַ��� StringUtils.replace(null, *, *) = null StringUtils.replace("", *, *) = "" StringUtils.replace("aba", null, null) = "aba" StringUtils.replace("aba", null, null) = "aba" StringUtils.replace("aba", "a", null) = "aba" StringUtils.replace("aba", "a", "") = "aba" StringUtils.replace("aba", "a", "z") = "zbz" 

@param text text to search and replace in, may be null 
@param repl the String to search for, may be null 
@param with the String to replace with, may be null 
@return the text with any replacements processed, 
null if null String input 
@see #replace(String text, String repl, String with, int max) 
public static String replace(String text, String repl, String with) 

20�� 

Replaces a String with another String inside a larger String, 

for the first max values of the search String. 


A null reference passed to this method is a no-op. 


��ָ���ַ�������滻ԭ���ַ����ĵ�ָ���ַ��� StringUtils.replace(null, *, *, *) = null StringUtils.replace("", *, *, *) = "" StringUtils.replace("abaa", null, null, 1) = "abaa" StringUtils.replace("abaa", null, null, 1) = "abaa" StringUtils.replace("abaa", "a", null, 1) = "abaa" StringUtils.replace("abaa", "a", "", 1) = "abaa" StringUtils.replace("abaa", "a", "z", 0) = "abaa" StringUtils.replace("abaa", "a", "z", 1) = "zbaa" StringUtils.replace("abaa", "a", "z", 2) = "zbza" StringUtils.replace("abaa", "a", "z", -1) = "zbzz" 

@param text text to search and replace in, may be null 
@param repl the String to search for, may be null 
@param with the String to replace with, may be null 
@param max maximum number of values to replace, or -1 if no maximum 
@return the text with any replacements processed, 
null if null String input 
public static String replace(String text, String repl, String with, int max) 


�š� Case conversion����Сдת���� 
21�� 

Converts a String to upper case as per {@link String#toUpperCase()}. 


A null input String returns null. 


��һ���ַ�����Ϊ��д StringUtils.upperCase(null) = null StringUtils.upperCase("") = "" StringUtils.upperCase("aBc") = "ABC" 

@param str the String to upper case, may be null 
@return the upper cased String, null if null String input 
public static String upperCase(String str) 22�� 

Converts a String to lower case as per {@link String#toLowerCase()}. 


A null input String returns null. 


��һ���ַ���ת��ΪСд StringUtils.lowerCase(null) = null StringUtils.lowerCase("") = "" StringUtils.lowerCase("aBc") = "abc" 

@param str the String to lower case, may be null 
@return the lower cased String, null if null String input 
public static String lowerCase(String str) 23�� 

Capitalizes a String changing the first letter to title case as 

per {@link Character#toTitleCase(char)}. No other letters are changed. 


For a word based alorithm, see {@link /WordUtils#capitalize(String)}. 

A null input String returns null. 


StringUtils.capitalize(null) = null StringUtils.capitalize("") = "" StringUtils.capitalize("cat") = "Cat" StringUtils.capitalize("cAt") = "CAt" 

@param str the String to capitalize, may be null 
@return the capitalized String, null if null String input 
@see /WordUtils#capitalize(String) 
@see /uncapitalize(String) 
@since 2.0 
���ַ����е�����ĸ��д 
public static String capitalize(String str) 