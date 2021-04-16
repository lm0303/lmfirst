package com.lm.execlUtil.anatition;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.FIELD)  
public @interface PdfAnnotation {
	    // excel导出时标题显示的名字，如果没有设置Annotation属性，将不会被导出和导入  
	    public String exportName();  
	    int width() default 20;			//列宽（字符数）,不超过255个字符
		int toFixed() default 2;		//保留两位小数
		String align() default "center";	 //水平对齐方式		
		String toDate() default ""; //日期格式
}
