package com.ccit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseNews
{

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception
	{
		  //step1:将目标网页读成一个字符串
		  String alldata=readStringFromUrl("http://roll.mil.news.sina.com.cn/col/zgjq/index_2.shtml");
		  //step2:截取中间内容部分
		  String start="</div>\\s+<div class=\"fixList\">\\s+<ul class=\"linkNews\">\\s+";
		  String end="</ul>\\s+</div>\\s+<style>\\s+.page span [{]margin-right:5px[}]\\s+</style>";
		  String content=cutContent(alldata,start, end);
		  //System.out.println(content);
		  //step3:找URL和标题
           UrlBox allurl=getAllURL(content);
           for(int i=0;i<allurl.size();i++)
           {
        	   //System.out.println(allurl.get());
        	   //将url读成字符串，
        	   String allcontent=readStringFromUrl(allurl.get());
        	   //解析出标题，解析出内容
        	   String title=getTitle(allcontent);
        	   System.out.println(title);
        	   
        	   String contentStart="<div\\s+class=\"blkContainerSblkCon\"\\s+id=\"artibody\">\\s+";
        	   //String contentEnd="<div\\s+style=\"clear:both;height:0;visibility:hiddden;overflow:hidden;\">\\s*</div>\\s*<style type=\"text/css\">\\s*";
        	   String contentEnd="<style type=\"text/css\">\\s*[.]otherContent_01 p [{]line-height[:]23px[;] margin[:]0px[;][}]\\s*</style>\\s*";
        	   String contents=cutContent(allcontent, contentStart, contentEnd);
        	   System.out.println("-----------------------------------");
        	   System.out.println(contents);
        	   System.out.println("****************************");
        	   
        	   //存入文件中
           }
		  //Step4:写入一个文件
           
           

	}
	/**
	 * 得到title
	 * @param content
	 * @return
	 */
	public static String getTitle(String content)
	{
		String result="";
		String reg="(<h1 id=\"artibodyTitle\">)(.*?)(</h1>)";
		Matcher mat=Pattern.compile(reg).matcher(content);
	    if(mat.find())
	    {
	    	result=mat.group(2);
	    }
		return result;
	}
	
	/**
	 * 解析的url封装入一个box中
	 * @param content
	 * @return
	 */
	public static UrlBox getAllURL(String content)
	{
		  UrlBox ub=new UrlBox();
		  String re="(<li><a href=\"(http://.*?[.]html)\" target=\"_blank\">)(.*?)(</a><span class=\"time\">[(]11月10日 \\d{2}[:]\\d{2}[)]</span></li>)";	  
		  Pattern lire=Pattern.compile(re);
		  Matcher lim=lire.matcher(content);
		  int findindex=0;
		  while(lim.find(findindex))
		  {
			  //System.out.println(lim.start()+"\t"+lim.end());
			 // System.out.println(lim.group(3));
			//  System.out.println(lim.group(2));
			//  System.out.println("-----------------");
			  ub.add(lim.group(2));
			  findindex=lim.end();
		  }	
	return ub;	
	}
	
	/**
	 * 截取我们要的内容
	 * @param allData
	 * @param start
	 * @param end
	 * @return
	 */
	public static String cutContent(String allData,String start,String end)
	{
	    int startindex=0;
	    int endindex=0;
		Pattern startp=Pattern.compile(start);
		Pattern endp=Pattern.compile(end);
		
		Matcher startm=startp.matcher(allData);
		Matcher endm=endp.matcher(allData);
	
		if(startm.find())
		{
		   startindex=startm.end();	
			
		}
		if(endm.find())
		{
			endindex=endm.start();
		}
		/*int startindex=allData.indexOf(start)+start.length();
		int endindex=allData.indexOf(end)+1;
		*/
		System.out.println(startindex+"\t\t"+endindex);
		if(startindex>0&&endindex>0&&startindex<endindex)
		{
		return allData.substring(startindex, endindex);
		}else
		{
			return "";
		}
	}

	
    /**
     * 将url读成一个字符串
     * @param url
     * @return
     */
	public static  String readStringFromUrl(String url)throws Exception
	{
		URL turl=new URL(url);
		URLConnection con=turl.openConnection();
	    InputStream in=con.getInputStream();
	    BufferedReader br=new BufferedReader(new InputStreamReader(in));
	    StringBuilder sb=new StringBuilder();
	    String tem=null;
	    while(null!=(tem=br.readLine()))
	    {
	    	sb.append(tem);
	    }
	    
		return sb.toString();
	}
	
	
	
}
