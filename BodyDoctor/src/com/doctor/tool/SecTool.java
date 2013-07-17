package com.doctor.tool;
    /** 
     *  
     * <p>application name:{系统安全工具类}</p> 
     * <p>application describing:{处理字符串，把sql注入、xss跨站等字段去除}</p> 
     * <p>Copyright：Copyright  </p> 
     * <p>company:neusoft</p> 
     * <p>time:{时间，如2007.11.16}</p> 
     * @author {pilove310} 
     * @version {v1.0} 
     */  
    public final class SecTool  
    {  
    /** 
     *  
     * {去除字符串中的sql注入字段} 
     *  
     * @param str 传入一个String 
     * @return  返回一个不包含sql注入字段的String 
     * @see: {参 的方法} 
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
         * {去除字符串中的xss跨站字段} 
         *  
         * @param str 传入一个String 
         * @return 返回一个不包含XSS跨站字段的String 
         * @see: {参照的方法} 
         * @author:{pilove310} 
         */  
        public static String filterXSS(String str)  
        {  
            String flt="<|>|script|&|%23|/n|/0|<scirpt|<scirpt>";  
            String filter[] = flt.split("|");  
             
              
           //-------说明：两次循环处理是防止使用拼接进行，实现跨站代码传入-------  
              
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