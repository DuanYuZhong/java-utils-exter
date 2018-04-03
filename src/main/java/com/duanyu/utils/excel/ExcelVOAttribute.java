package com.duanyu.utils.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target( { java.lang.annotation.ElementType.FIELD })  
public @interface ExcelVOAttribute {  
  
    /** 
     * 导出到Excel中的名字. 扩展属性时 由extensionHeader指定名字
     */  
    String name() default "";

    String column() default "";

    /** 
     * 支持扩展属性 column -> sort  eg : 1 -> A
     */  
    int sort() default 0;
    
    /**
     * 是否为扩展属性
     */
    boolean extension() default false;
    
    /** 
     * 提示信息 
     */  
    String prompt() default "";
  
    /** 
     * 设置只能选择不能输入的列内容. 
     */  
    String[] combo() default {};
  
    /** 
     * 是否导出数据,应对需求:有时我们需要导出一份模板,这是标题需要但内容需要用户手工填写. 
     */  
    boolean isExport() default true;
  
    /** 
     * 日期导出格式
     */
    String dateFormat() default "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 数值转String时所保留小树点数
     */
    int decimalLength() default 0;
}