package com.ccit;

public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String str="<li><a href=\"http://mil.news.sina.com.cn/2011-11-10/1028673065.html\" target=\"_blank\">ý����ڿ�����˵����������й���ը��ս��</a><span class=\"time\">(11��10�� 10:28)</span></li>";
		String re="<li><a href=.*+ target=\"_blank\">.*+</a><span class=\"time\">.*+</span></li>";
        System.out.println(str.matches(re));
	}

}
