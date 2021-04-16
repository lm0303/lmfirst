package com.lm.execlUtil.util;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lm.execlUtil.anatition.PdfAnnotation;

/**
 * 生成pdf
 * 
 */
public class PdfExport {
	Document document = new Document();// 建立一个Document对象

	private static Font headfont;// 设置字体大小
	private static Font keyfont;// 设置字体大小
	private static Font textfont;// 设置字体大小
	/**
	 * 浏览器描述串，不同的浏览器输出文件名时转码不一样
	 */
	private String agent;

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	static {
		// 中文格式
		BaseFont bfChinese;
		try {
			// 设置中文显示
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			headfont = new Font(bfChinese, 16);// 设置字体大小
			headfont.setStyle("宋体");
			keyfont = new Font(bfChinese, 14);// 设置字体大小
			keyfont.setStyle("宋体");
			textfont = new Font(bfChinese, 12, Font.NORMAL);// 设置字体大小
			textfont.setStyle("宋体");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文成文件
	 * 
	 * @param fileName
	 *            待生成的文件名
	 */
	public PdfExport(String fileName,HttpServletResponse response) {
		document.setPageSize(PageSize.A4);// 设置页面大小
		response.reset();// 清空输出流
		response.setContentType("application/ms-excel;charset=GB2312");
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + processFileName(fileName) + ".pdf");
		} catch (Exception e) {
			response.setHeader("Content-disposition", "attachment; filename=thesisScore.pdf");
		}
		try {
			ServletOutputStream os = response.getOutputStream();
			PdfWriter.getInstance(document, os);
			document.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PdfExport() {

	}

	public void initFile(File file) {
		document.setPageSize(PageSize.A4);// 设置页面大小
		try {
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  
	 */

	public static int setAlign(String align) {
		if (align.equals("center")) {
			return Element.ALIGN_CENTER;
		} else if (align.equals("left")) {
			return Element.ALIGN_LEFT;
		} else {
			return Element.ALIGN_RIGHT;
		}
	}

	// 格式化内容
	private static String getValue(Object value, String toDate, int tofixed) {

		String textValue = "";
		if (value == null || (value == null && value.getClass() == Integer.class)) {
			return textValue;
		} else if (value.getClass() == String.class) {
			return value.toString();
		} else if (value.getClass() == Double.class || value.getClass() == Float.class) {
			Double double1 = (Double) value;
			double double2 = double1.doubleValue();
			BigDecimal bg = new BigDecimal(double2);
			// setScale(int newScale, RoundingMode roundingMode) 返回
			// BigDecimal，其标度为指定值，其非标度值通过此 BigDecimal
			// 的非标度值乘以或除以十的适当次幂来确定，以维护其总值。
			// ROUND_HALF_UP 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。

			double doublevalue = bg.setScale(tofixed, BigDecimal.ROUND_HALF_UP).doubleValue();
			return String.valueOf(doublevalue);
		} else if (value.getClass() == Integer.class) {
			return value.toString();
		} else if (value.getClass() == Byte.class) {
			return value.toString();
		} else if (value.getClass() == Date.class) {
			Date date = (Date) value;
			// 完整
			SimpleDateFormat whole = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 简略
			SimpleDateFormat brief = new SimpleDateFormat("yyyy-MM-dd");
			if (toDate.equals("whole")) {
				textValue = whole.format(date);
			} else {
				textValue = brief.format(date);
			}
			return textValue;
		} else {
			// 设置单元格为文本格式
		}
		return "";
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @param align
	 *            对齐方式
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, String align) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(setAlign(align));
		cell.setMinimumHeight(30);// 设置表格行高
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @param align
	 *            对齐方式
	 * @param colspan
	 *            占多少列
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align, int colspan) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * 
	 * @param value
	 *            值
	 * @param font
	 *            字体
	 * @param align
	 *            对齐方式
	 * @param colspan
	 *            占多少列
	 * @param boderFlag
	 *            是否有有边框
	 * @return 添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align, int colspan, boolean boderFlag) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		cell.setPadding(3.0f);
		cell.setMinimumHeight(20);// 设置表格行高
		if (!boderFlag) {
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(8.0f);
		}
		return cell;
	}

	/**
	 * 创建一个表格对象
	 * 
	 * @param colNumber
	 *            表格的列数
	 * @return 生成的表格对象
	 */
	public PdfPTable createTable(int colNumber, List<Integer> widthlist) {
		PdfPTable table = new PdfPTable(colNumber);
		int maxWidth = 520;
		try {
			int[] widhts = new int[widthlist.size()];
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
			for (int i = 0; i < widthlist.size(); i++) {
				widhts[i] = widthlist.get(i);
			}
			table.setWidths(widhts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	public PdfPTable createTable(float[] widths) {
		PdfPTable table = new PdfPTable(widths);
		int maxWidth = 520;
		try {
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	public PdfPTable createBlankTable() {
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(0);
		table.addCell(createCell("", keyfont));
		table.setSpacingAfter(20.0f);
		table.setSpacingBefore(20.0f);
		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void generatePDF(String title, List<T> list) {

		try {

			Class classType = list.get(0).getClass();
			Field[] fields = classType.getDeclaredFields();
			// 列数
			int colNum = 0;
			// 标题集合
			ArrayList<String> titlename = new ArrayList<String>();
			// 列宽集合
			Map<String, Integer> widthMap = new HashMap<String, Integer>();
			ArrayList<Integer> widthList = new ArrayList<>();
			// getter方法集合
			ArrayList<Method> methodobj = new ArrayList<Method>();
			// 保留小数位数
			int tofixed = 0;
			// 每列对齐方式
			ArrayList<String> alginlist = new ArrayList<String>();
			// 日期保留格式
			String toDate = null;

			for (Field field : fields) {
				// 解析注解
				PdfAnnotation PdfAnnotation = field.getAnnotation(PdfAnnotation.class);
				if (PdfAnnotation != null) {
					// 将被标记的注解添加到标题集合中
					String exportname = PdfAnnotation.exportName();
					if (exportname.equals("")) {
						exportname = field.getName();
					}
					titlename.add(exportname);

					int width = PdfAnnotation.width();
					widthMap.put(exportname, width);
					widthList.add(width);

					tofixed = PdfAnnotation.toFixed();
					toDate = PdfAnnotation.toDate();
					alginlist.add(PdfAnnotation.align());

					// 构造getter方法
					String fieldname = field.getName();
					// 方法名
					String getMethodname = "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
					Method getMethod = classType.getMethod(getMethodname, new Class[] {});
					methodobj.add(getMethod);
				}
			}
			colNum = titlename.size();
			// 创建一个表格
			PdfPTable table = createTable(colNum, widthList);

			// 设置标题
			table.addCell(createCell(title, headfont, Element.ALIGN_CENTER, colNum, true));

			// 设置表头
			for (int i = 0; i < colNum; i++) {
				table.addCell(createCell(titlename.get(i), keyfont, alginlist.get(i)));
			}

			for (int k = 0; k < list.size(); k++) {

				T t = list.get(k);
				for (int j = 0; j < methodobj.size(); j++) {
					Method getMethod = methodobj.get(j);

					// 获取每个单元格的值
					Object value = getMethod.invoke(t, new Object[] {});
					// 格式化
					String val = getValue(value, toDate, tofixed);
					table.addCell(createCell(val, textfont, alginlist.get(j)));
				}

			}

			// 将表格添加到文档中
			document.add(table);

			// 关闭流
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 解决设置名称时的乱码
	 * 
	 * @param agent
	 *            浏览器 agent串
	 * @param fileNames
	 *            需要输出的名字
	 * @return
	 */
	public String processFileName(String fileNames) {
		String codedfilename = null;
		try {
			if (null == agent) {
				codedfilename = new String(fileNames.getBytes("gb2312"), "iso-8859-1");
			} else if (-1 != agent.indexOf("MSIE") || -1 != agent.indexOf("Trident") || -1 != agent.indexOf("Edge")) {// ie
				codedfilename = java.net.URLEncoder.encode(fileNames, "UTF8");
			} else if (-1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
				codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
			} else {
				codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codedfilename;
	}

	public static void main(String[] args) {
		
	}
}