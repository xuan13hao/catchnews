package com.ccit;

public class UrlBox
{
	String [] box=new String[10];
	int index=0;
	int max=10;
	int getindex=0;
	
	public UrlBox()
	{
		max=box.length;
	}
	
    public void add(String url)
    {
        if(index>=max)
        {
        	String [] tem=new String[max+10];
        	max=tem.length;
        	System.arraycopy(box, 0,tem, 0,box.length);
        	box=tem;
        }
    	box[index++]=url;
    }
    public String get()
    {
    	
    	return getindex<max?box[getindex++]:null;
    }
    
    public static void main(String[] args)
	{
    	UrlBox ub=new UrlBox();
	  	for(int i=0;i<100;i++)
	  	{
	  		ub.add("test"+i);
	  	}
	  	
	  	for(int j=0;j<105;j++)
	  	{
	  		  String re=ub.get();
	  		  if(null!=re&&!"".equals(re))
	  		  {
	  			  System.out.println(re);
	  		  }else
	  		  {
	  			  break;
	  		  }
	  	}
	  	
	}
    public int size()
    {
    	return index;
    }
}
