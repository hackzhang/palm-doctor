package com.doctor.tool;
    /** 
     *  
     * <p>application name:{ϵͳ��ȫ������}</p> 
     * <p>application describing:{�����ַ�������sqlע�롢xss��վ���ֶ�ȥ��}</p> 
     * <p>Copyright��Copyright  </p> 
     * <p>company:neusoft</p> 
     * <p>time:{ʱ�䣬��2007.11.16}</p> 
     * @author {pilove310} 
     * @version {v1.0} 
     */  
    public final class SecTool  
    {  
    /** 
     *  
     * {ȥ���ַ����е�sqlע���ֶ�} 
     *  
     * @param str ����һ��String 
     * @return  ����һ��������sqlע���ֶε�String 
     * @see: {�� �ķ���} 
     * @author:{pilove310} 
     */  
        public static String filterSQLInjection(String str)  
        {  
            String flt ="''|'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,|drop";  
            String filter[] = flt.split("|");  
            for(int i=0;i<filter.length ; i++)  
            {  
              str.replace(filter[i], "");  
            }  
            return str;  
        }  
        /** 
         *  
         * {ȥ���ַ����е�xss��վ�ֶ�} 
         *  
         * @param str ����һ��String 
         * @return ����һ��������XSS��վ�ֶε�String 
         * @see: {���յķ���} 
         * @author:{pilove310} 
         */  
        public static String filterXSS(String str)  
        {  
            String flt="<|>|script|&|%23|/n|/0|<scirpt|<scirpt>";  
            String filter[] = flt.split("|");  
             
              
           //-------˵��������ѭ�������Ƿ�ֹʹ��ƴ�ӽ��У�ʵ�ֿ�վ���봫��-------  
              
            for(int i=0;i<filter.length ; i++)  
            {  
              str.replace(filter[i], "");  
              System.out.println(filter[i]+"...");  
               
            }  
    //        for(int i=0;i<filter.length ; i++)  
    //        {  
    //          str.replace(filter[i], "");  
    //        }  
            return str;  
        }  
    }  