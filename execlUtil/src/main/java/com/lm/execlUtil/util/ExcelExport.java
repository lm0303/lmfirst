package com.lm.execlUtil.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.lm.execlUtil.anatition.ExcelAnnotation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class ExcelExport {

	// 内部工具类
	public class Tools {
		private String titlename;
		private String sheetname;

		@SuppressWarnings("rawtypes")
		private ArrayList objectlist = null;

		public String getTitlename() {
			return titlename;
		}

		public void setTitlename(String titlename) {
			this.titlename = titlename;
		}

		public String getSheetname() {
			return sheetname;
		}

		public void setSheetname(String sheetname) {
			this.sheetname = sheetname;
		}

		@SuppressWarnings("rawtypes")
		public ArrayList getObjectlist() {
			return objectlist;
		}

		@SuppressWarnings("rawtypes")
		public void setObjectlist(ArrayList objectlist) {
			this.objectlist = objectlist;
		}

		public Tools() {
			super();
		}

		@SuppressWarnings("rawtypes")
		public Tools(String titlename, String sheetname, ArrayList objectlist) {
			super();
			this.titlename = titlename;
			this.sheetname = sheetname;
			this.objectlist = objectlist;
		}
	}

	/**
	 * 输出文件名
	 */
	private String filename;
	/**
	 * 表格每一行的高度，默认值为20
	 */
	private int height = 20;

	/**
	 * 存放sheet列表
	 */
	private ArrayList<Tools> toolslist = new ArrayList<Tools>();
	/**
	 * 浏览器描述串，不同的浏览器输出文件名时转码不一样
	 */
	private String agent;

	// 输出表格
	@SuppressWarnings("rawtypes")
	public void addSheet(String titlename, String sheetname, ArrayList objectlist) {
		Tools tools = new Tools();
		tools.setSheetname(sheetname);
		tools.setTitlename(titlename);
		tools.setObjectlist(objectlist);
		toolslist.add(tools);
	}

	// 输出表格
	public void print(HttpServletResponse response) {
		response.reset();// 清空输出流
		response.setContentType("application/ms-excel;charset=GB2312");
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + processFileName(filename) + ".xls");
		} catch (Exception e) {
			response.setHeader("Content-disposition", "attachment; filename=thesisScore.xls");
		}
		try {
			ServletOutputStream os = response.getOutputStream();
			if (null == os) {
				return;
			}
			// 创建工作簿
			Workbook workbook = new HSSFWorkbook();

			for (int i = 0; i < toolslist.size(); i++) {
				Tools tools = toolslist.get(i);
				publiccode(tools, workbook);
			}
			// 输出
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	public void exportExcel(String filepath,ArrayList<Tools> toolslist) {
		try {
			if (filepath == null || toolslist == null) {
				throw new Exception("传入数据错误");
			}
			//创建工作簿
			Workbook workbook = new HSSFWorkbook();
			//创建文件
			FileOutputStream fileOutputStream = new FileOutputStream(filepath);
			for (int i = 0; i < toolslist.size(); i++) {
				Tools tools = toolslist.get(i);
				publiccode(tools,workbook);
			}
			//输出
			workbook.write(fileOutputStream);	
			fileOutputStream.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	// 多次执行的代码提取
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void publiccode(Tools tools, Workbook workbook) {
		try {
			Class tClass = tools.getObjectlist().get(0).getClass();
			Field[] fields = tClass.getDeclaredFields();
			// 标题集合
			ArrayList<String> titlename = new ArrayList<String>();
			// 列宽集合
			Map<String, Integer> widthMap = new HashMap<String, Integer>();
			// getter方法集合
			ArrayList<Method> methodobj = new ArrayList<Method>();
			// 保留小数位数
			int tofixed = 0;
			// 每列对齐方式
			ArrayList<String> alginlist = new ArrayList<String>();
			// 日期保留格式
			String toDate = null;
			// 每列对应cellstyle
			ArrayList<CellStyle> stylelist = new ArrayList<CellStyle>();
			for (Field field : fields) {
				// 解析注解
				ExcelAnnotation excelAnnotion = field.getAnnotation(ExcelAnnotation.class);
				if (excelAnnotion != null) {
					// 将被标记的注解添加到标题集合中
					String exportname = excelAnnotion.exportName();
					if (exportname.equals("")) {
						exportname = field.getName();
					}
					titlename.add(exportname);
					int width = excelAnnotion.width();
					widthMap.put(exportname, width);

					tofixed = excelAnnotion.toFixed();
					toDate = excelAnnotion.toDate();
					alginlist.add(excelAnnotion.align());

					// 构造getter方法
					String fieldname = field.getName();
					// 方法名
					String getMethodname = "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
					Method getMethod = tClass.getMethod(getMethodname, new Class[] {});
					methodobj.add(getMethod);
				}
			}
			String sheetname = tools.getSheetname();
			String title = tools.getTitlename();
			Sheet sheet = workbook.createSheet(sheetname);

			HSSFCellStyle titlestyle = (HSSFCellStyle) workbook.createCellStyle();
			// 标题样式
			Row titlerow = sheet.createRow(0);
			titlerow.setHeightInPoints(height + 10); // 比每条记录的行高+10
			Cell titlecell = titlerow.createCell(0);
			// 合并单元格
			CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, titlename.size() - 1);
			sheet.addMergedRegion(rangeAddress);
			// 标题样式
			titlestyle = setTitleStyle(workbook, true, rangeAddress, sheet);
			titlecell.setCellStyle(titlestyle);
			titlecell.setCellValue(title);
			// 初始化样式集合
			stylelist = setStyle(alginlist, workbook, stylelist);
			// 表头行
			Row rowHead = sheet.createRow(1);
			// 表头样式
			HSSFFont font1 = (HSSFFont) workbook.createFont();
			// font1.setFontHeight((short)250);
			HSSFCellStyle headstyle = publicStyle(workbook, font1, true);
			rowHead.setHeightInPoints(height);
			for (int n = 0; n < titlename.size(); n++) {
				Cell cellHead = rowHead.createCell(n);
				cellHead.setCellStyle(headstyle);

				// 设置列宽
				if (widthMap.containsKey(titlename.get(n))) {
					sheet.setColumnWidth(n, 256 * (int) widthMap.get(titlename.get(n)));
					HSSFRichTextString textString = new HSSFRichTextString(titlename.get(n));
					cellHead.setCellValue(textString);
				}
			}
			// 内容行
			for (int k = 1; k < tools.getObjectlist().size() + 1; k++) {
				Row rowbody = sheet.createRow(k + 1);
				rowbody.setHeightInPoints(height);

				Object t1 = tools.getObjectlist().get(k - 1);

				for (int j = 0; j < methodobj.size(); j++) {
					Cell cellbody = rowbody.createCell(j);
					Method getMethod = methodobj.get(j);

					// 获取每个单元格的值
					Object value = getMethod.invoke(t1, new Object[] {});

					// 内容格式
					getValue(value, toDate, cellbody, tofixed);
					// 设置样式
					cellbody.setCellStyle(stylelist.get(j));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 设置标题格式
	private static HSSFCellStyle setTitleStyle(Workbook workbook, boolean bold, CellRangeAddress region, Sheet sheet) {
		HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
		HSSFFont font = (HSSFFont) workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeight((short) 300);
		font.setColor(HSSFFont.COLOR_NORMAL);
		if (bold) {
			// 标题需要加粗
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // font.setBold(bold);
		}
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
														// style.setAlignment
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		// 边框细线
		RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook); // setBorderBottom(BorderStyle.THIN,region,
																						// sheet);
		RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region, sheet, workbook); // setBorderLeft(BorderStyle.THIN,region,
																						// sheet)
		RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		return style;

	}

	// 公共样式
	private static HSSFCellStyle publicStyle(Workbook workbook, HSSFFont font, boolean bold) {
		HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
		font.setFontName("宋体");
		font.setColor(HSSFFont.COLOR_NORMAL);
		if (bold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // font.setBold(bold);
		}
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
														// HorizontalAlignment.CENTER
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
																	// VerticalAlignment.CENTER

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); // BorderStyle.THIN
		return style;

	}

	// 格式化内容
	private static void getValue(Object value, String toDate, Cell cellbody, int tofixed) {

		String textValue = "";
		if (value == null) {
			cellbody.setCellValue(textValue);
		} else if (value.getClass() == Double.class || value.getClass() == Float.class) {
			double double1 = 0;
			if (value.getClass() == Float.class) {
				float temp = (float) value;
				double1 = temp;
			} else {
				double1 = (double) value;
			}
			// double double2 = double1.doubleValue();
			BigDecimal bg = new BigDecimal(double1);
			// setScale(int newScale, RoundingMode roundingMode) 返回
			// BigDecimal，其标度为指定值，其非标度值通过此 BigDecimal
			// 的非标度值乘以或除以十的适当次幂来确定，以维护其总值。
			// ROUND_HALF_UP 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。

			double doublevalue = bg.setScale(tofixed, BigDecimal.ROUND_HALF_UP).doubleValue();
			cellbody.setCellValue(doublevalue);
		} else if (value.getClass() == Integer.class) {
			// 整形数字
			cellbody.setCellValue((Integer) value);
		} else if (value.getClass() == Byte.class) {
			cellbody.setCellValue((Byte) value);
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
			cellbody.setCellValue(textValue);
		} else {
			// 设置单元格为文本格式
			cellbody.setCellValue((String) value);
		}
	}

	// 得到样式集合
	private static ArrayList<CellStyle> setStyle(ArrayList<String> alginlist, Workbook workbook,
			ArrayList<CellStyle> stylelist) {
		for (int i = 0; i < alginlist.size(); i++) {
			HSSFCellStyle textstyle = (HSSFCellStyle) workbook.createCellStyle();
			HSSFFont font2 = (HSSFFont) workbook.createFont();
			if (alginlist.get(i).equals("center")) {
				textstyle = publicStyle(workbook, font2, false);
				textstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// HorizontalAlignment.CENTER
				stylelist.add(textstyle);
			} else if (alginlist.get(i).equals("left")) {
				textstyle = publicStyle(workbook, font2, false);
				textstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				stylelist.add(textstyle);
			} else {
				textstyle = publicStyle(workbook, font2, false);
				textstyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				stylelist.add(textstyle);
			}
		}
		return stylelist;
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

	// 测试方法
	public static void main(String[] args) {
		// ArrayList<Student> list1 = new ArrayList<Student>();
		// ArrayList<Student> list2 = new ArrayList<Student>();
		// ArrayList<Student> list3 = new ArrayList<Student>();
		// for (int i = 0; i < 100; i++) {
		// //list1.add(new Student("lm", i, "0316401"+i, 37.375+i, new Date()));
		// //list2.add(new Student("lm", i, "0316402"+i, 37.375+i, new Date()));
		// //list3.add(new Student("lm", i, "0316403"+i, 37.375+i, new Date()));
		// }
		// String filepath3 = "d:\\学生3.xls";
		// String filepath4 = "d:\\学生4.xls";
		// ExcelExport excelUtils3 = new ExcelExport();
		// ExcelExport.Tools tools1 = new ExcelExport().new Tools("学生表",
		// "0316401", list1);
		// ExcelExport.Tools tools2 = new ExcelExport().new Tools("学生表",
		// "0316402", list2);
		// ExcelExport.Tools tools3 = new ExcelExport().new Tools("学生表",
		// "0316403", list3);
		// ArrayList<Tools> toolslist = new ArrayList<Tools>();
		// toolslist.add(tools1);
		// toolslist.add(tools2);
		// toolslist.add(tools3);
		// excelUtils3.exportExcel(filepath3, tools1);
		// excelUtils3.exportExcel(filepath4, toolslist);
	}

	/**
	 * 通过Excel返回错误数据
	 */
	public void errorMsg(String msg, HttpServletResponse response) {
		response.reset();// 清空输出流
		response.setContentType("application/ms-excel;charset=GB2312");
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + processFileName(filename) + ".xls");
		} catch (Exception e) {
			response.setHeader("Content-disposition", "attachment; filename=" + "error.xls");
		}
		try {
			ServletOutputStream os = response.getOutputStream();
			if (null == os) {
				return;
			}
			// 表格组装
			HSSFWorkbook wb = new HSSFWorkbook();
			// 表头
			HSSFSheet sheet = wb.createSheet("错误");
			// 初始化风格
			HSSFFont font = wb.createFont(); // 创建字体
			font.setFontName("宋体");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体
			font.setFontHeightInPoints((short) 14); // 大小
			HSSFCellStyle style = wb.createCellStyle();
			style.setWrapText(true);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
			style.setFont(font);

			HSSFFont font1 = wb.createFont(); // 创建字体
			font1.setFontName("宋体");
			font1.setFontHeightInPoints((short) 10); // 大小

			wb.setSheetName(0, "错误");
			sheet.setPrintGridlines(true);// 打印时显示网格线
			// 设置单元格宽度
			sheet.setColumnWidth(0, 5300);

			// 第0行 表头
			HSSFCell cell;
			HSSFRow r0 = sheet.createRow(0);
			cell = r0.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(msg);

			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAgent() {
		return agent;
	}

	public ExcelExport setAgent(String agent) {
		this.agent = agent;
		return this;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
