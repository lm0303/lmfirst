package com.lm.execlUtil.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lm.execlUtil.anatition.ExcelAnnotation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;



public class ExcelUtils<T> {

	/**
	 * 从excel导入,返回一个list集合
	 * 
	 * @author 解伟(2011/4/20)
	 * @param file
	 *            导入的excel文件
	 * @param pattern
	 * @return
	 */
	Class<T> clazz;

	public ExcelUtils(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> importExcel(File file, StringBuffer result, String... pattern) {
		Collection<T> dist = new ArrayList<T>();
		int iRow = 0; // 当前处理的行
		String titleString = ""; // 当前处理的字段名
		try {
			/**
			 * 类反射得到调用方法
			 */
			// 得到目标目标类的所有的字段列表
			Field filed[] = clazz.getDeclaredFields();
			// 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
			@SuppressWarnings("rawtypes")
			Map fieldmap = new HashMap();
			// 循环读取所有字段
			for (int i = 0; i < filed.length; i++) {
				Field f = filed[i];
				// 得到单个字段上的Annotation
				ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
				// 如果标识了Annotationd的话
				if (exa != null) {
					// 构造设置了Annotation的字段的Setter方法
					String fieldname = f.getName();
					String setMethodName = "set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
					// 构造调用的method，
					Method setMethod = clazz.getMethod(setMethodName, new Class[] { f.getType() });
					// 将这个method以Annotaion的名字为key来存入。
					fieldmap.put(exa.exportName(), setMethod);
				}
			}
			/**
			 * excel的解析开始
			 */
			// 将传入的File构造为FileInputStream;
			FileInputStream in = new FileInputStream(file);
			// // 得到工作表
			HSSFWorkbook book = new HSSFWorkbook(in);
			// Workbook book = new XSSFWorkbook(in);

			// // 得到第一页
			Sheet sheet = book.getSheetAt(0);
			// // 得到第一面的所有行
			Iterator<Row> row = sheet.rowIterator();

			/**
			 * 标题解析
			 */
			// 得到第一行，也就是标题行
			Row title = row.next();

			// 得到第一行的所有列
			Iterator<Cell> cellTitle = title.cellIterator();
			// 将标题的文字内容放入到一个map中。
			@SuppressWarnings("rawtypes")
			Map titlemap = new HashMap();
			// 从标题第一列开始
			int i = 0;
			// 循环标题所有的列
			while (cellTitle.hasNext()) {
				Cell cell = cellTitle.next();
				String value = cell.getStringCellValue();
				titlemap.put(i, value);
				i = i + 1;
			}
			/**
			 * 解析内容行
			 */
			// 用来格式化日期的DateFormat
			SimpleDateFormat sf;

			if (pattern.length < 1) {
				sf = new SimpleDateFormat("yyyy-MM-dd");
			} else
				sf = new SimpleDateFormat(pattern[0]);

			while (row.hasNext()) {
				// 标题下的第一行
				Row rown = row.next();
				// System.out.println(rown.getRowNum());
				iRow = rown.getRowNum();
				// 行的所有列
				Iterator<Cell> cellbody = rown.cellIterator();
				// 得到传入类的实例
				T tObject = clazz.newInstance();
				// 遍历一行的列
				while (cellbody.hasNext()) {
					Cell cell = cellbody.next();
					// 这里得到此列的对应的标题
					titleString = (String) titlemap.get(cell.getColumnIndex());
					// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值
					if (fieldmap.containsKey(titleString)) {
						Method setMethod = (Method) fieldmap.get(titleString);
						// 得到setter方法的参数
						Type[] ts = setMethod.getGenericParameterTypes();
						// 只要一个参数
						String xclass = ts[0].toString();
						// 判断参数类型
						if (xclass.equals("class java.lang.String")) {
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
									|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
								DecimalFormat df = new DecimalFormat("0");
								setMethod.invoke(tObject, String.valueOf(df.format(cell.getNumericCellValue())));
								// setMethod.invoke(tObject, String.valueOf(cell.getNumericCellValue()));
							} else {
								setMethod.invoke(tObject, cell.getStringCellValue());
							}
						} else if (xclass.equals("class java.util.Date")) {
							setMethod.invoke(tObject, sf.parse(cell.getStringCellValue()));
						} else if (xclass.equals("class java.lang.Boolean")) {
							Boolean boolname = true;
							if (cell.getStringCellValue().equals("否")) {
								boolname = false;
							}
							setMethod.invoke(tObject, boolname);
						} else if (xclass.equals("class java.lang.Integer")) {
							setMethod.invoke(tObject, new Integer(cell.getStringCellValue()));
						} else if (xclass.equals("int")) {
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
									|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
								setMethod.invoke(tObject, new Integer((int) cell.getNumericCellValue()));
							} else {
								if (cell.getStringCellValue() != null && !"".equals(cell.getStringCellValue())) {
									setMethod.invoke(tObject, new Integer(cell.getStringCellValue()));
								} else {
									setMethod.invoke(tObject, new Integer(0));
								}
							}
						} else if (xclass.equals("byte")) {
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
									|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
								setMethod.invoke(tObject, new Byte((byte) cell.getNumericCellValue()));
							} else {
								if (cell.getStringCellValue() != null && !"".equals(cell.getStringCellValue())) {
									setMethod.invoke(tObject, new Byte(cell.getStringCellValue()));
								}
							}
						} else if (xclass.equals("class java.lang.Long")) {
							setMethod.invoke(tObject, new Long(cell.getStringCellValue()));
						} else if (xclass.equals("float")) {
							if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
									|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
								setMethod.invoke(tObject, new Float(cell.getNumericCellValue()));
							} else {
								if (cell.getStringCellValue() != null && !"".equals(cell.getStringCellValue())) {
									setMethod.invoke(tObject, new Float(cell.getStringCellValue()));
								} else {
									setMethod.invoke(tObject, new Float(0));
								}
							}
						}
					}
					// 下一列
				}
				dist.add(tObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
			iRow++;
			if (null != result) {
				result.append("第" + iRow + "行 【" + titleString + "】字段 解析出错！");
			}
			return null;
		}
		return dist;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> importExcel2(File file, StringBuffer result, String... pattern) {
		Collection<T> dist = new ArrayList<T>();
		int iRow = 0; // 当前处理的行
		String titleString = ""; // 当前处理的字段名
		try {
			/**
			 * 类反射得到调用方法
			 */
			// 得到目标目标类的所有的字段列表
			Field filed[] = clazz.getDeclaredFields();
			// 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
			@SuppressWarnings("rawtypes")
			Map fieldmap = new HashMap();
			// 循环读取所有字段
			for (int i = 0; i < filed.length; i++) {
				Field f = filed[i];
				// 得到单个字段上的Annotation
				ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
				// 如果标识了Annotationd的话
				if (exa != null) {
					// 构造设置了Annotation的字段的Setter方法
					String fieldname = f.getName();
					String setMethodName = "set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
					// 构造调用的method，
					Method setMethod = clazz.getMethod(setMethodName, new Class[] { f.getType() });
					// 将这个method以Annotaion的名字为key来存入。
					fieldmap.put(exa.exportName(), setMethod);
				}
			}
			/**
			 * excel的解析开始
			 */
			// 将传入的File构造为FileInputStream;
			FileInputStream in = new FileInputStream(file);
			// // 得到工作表
			HSSFWorkbook book = new HSSFWorkbook(in);
			// // 得到第一页
			HSSFSheet sheet = book.getSheetAt(0);
			// // 得到第一面的所有行
			Iterator<Row> row = sheet.rowIterator();

			/**
			 * 标题解析
			 */
			// 得到第一行，也就是标题行
			Row title = row.next();
			// 得到第一行的所有列
			Iterator<Cell> cellTitle = title.cellIterator();
			// 将标题的文字内容放入到一个map中。
			@SuppressWarnings("rawtypes")
			Map titlemap = new HashMap();
			// 从标题第一列开始
			int i = 0;
			// 循环标题所有的列
			while (cellTitle.hasNext()) {
				Cell cell = cellTitle.next();
				String value = cell.getStringCellValue();
				titlemap.put(i, value);
				i = i + 1;
			}
			/**
			 * 解析内容行
			 */
			// 用来格式化日期的DateFormat
			SimpleDateFormat sf;

			if (pattern.length < 1) {
				sf = new SimpleDateFormat("yyyy-MM-dd");
			} else
				sf = new SimpleDateFormat(pattern[0]);

			while (row.hasNext()) {
				// 标题下的第一行
				Row rown = row.next();
				// System.out.println(rown.getRowNum());
				iRow = rown.getRowNum();
				// 行的所有列
				Iterator<Cell> cellbody = rown.cellIterator();
				// 得到传入类的实例
				T tObject = clazz.newInstance();
				// 遍历一行的列
				while (cellbody.hasNext()) {
					Cell cell = cellbody.next();
					// 这里得到此列的对应的标题
					titleString = (String) titlemap.get(cell.getColumnIndex());
					// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值
					if (fieldmap.containsKey(titleString)) {
						try {
							Method setMethod = (Method) fieldmap.get(titleString);
							// 得到setter方法的参数
							Type[] ts = setMethod.getGenericParameterTypes();
							// 只要一个参数
							String xclass = ts[0].toString();
							// 判断参数类型
							if (xclass.equals("class java.lang.String")) {
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
										|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
									setMethod.invoke(tObject, String.valueOf(cell.getNumericCellValue()));
								} else {
									setMethod.invoke(tObject, cell.getStringCellValue());
								}
							} else if (xclass.equals("class java.util.Date")) {
								setMethod.invoke(tObject, sf.parse(cell.getStringCellValue()));
							} else if (xclass.equals("class java.lang.Boolean")) {
								Boolean boolname = true;
								if (cell.getStringCellValue().equals("否")) {
									boolname = false;
								}
								setMethod.invoke(tObject, boolname);
							} else if (xclass.equals("class java.lang.Integer")) {
								setMethod.invoke(tObject, new Integer(cell.getStringCellValue()));
							} else if (xclass.equals("int")) {
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
										|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
									setMethod.invoke(tObject, new Integer((int) cell.getNumericCellValue()));
								} else {
									if (cell.getStringCellValue() != null && !"".equals(cell.getStringCellValue())) {
										setMethod.invoke(tObject, new Integer(cell.getStringCellValue()));
									} else {
										setMethod.invoke(tObject, new Integer(0));
									}
								}
							} else if (xclass.equals("class java.lang.Byte")) {
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
										|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
									setMethod.invoke(tObject, new Byte((byte) cell.getNumericCellValue()));
								} else {
									if (cell.getStringCellValue() != null && !"".equals(cell.getStringCellValue())) {
										setMethod.invoke(tObject, new Byte(cell.getStringCellValue()));
									}
								}
							} else if (xclass.equals("class java.lang.Long")) {
								setMethod.invoke(tObject, new Long(cell.getStringCellValue()));
							} else if (xclass.equals("float")) {
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
										|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
									setMethod.invoke(tObject, new Float(cell.getNumericCellValue()));
								} else {
									if (cell.getStringCellValue() != null && !"".equals(cell.getStringCellValue())) {
										setMethod.invoke(tObject, new Float(cell.getStringCellValue()));
									} else {
										setMethod.invoke(tObject, new Float(0));
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							result.append("第" + (iRow + 1) + "行 【" + titleString + "】字段 解析出错！\r\n");
						}
					}
					// 下一列
				}
				dist.add(tObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			iRow++;
			if (null != result) {
				result.append("第" + iRow + "行 【" + titleString + "】字段 解析出错！\r\n");
			}
			return null;
		}
		if (result.length() > 1) {
			return null;
		} else {
			return dist;
		}
	}

	// 格式化日期
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 导出到excel中去,
	 * 
	 * @author 解伟(2011/4/20)
	 * @param title
	 *            excel的工作表名
	 * @param dateset
	 *            导出的数据集合
	 * @param out
	 *            输出流
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public void exportExcel(String title, List<T> dataset, OutputStream out) {
		// 声明一个工作薄

		try {
			//XSSFWorkbook
			HSSFWorkbook workbook = new HSSFWorkbook();
			//HSSFWorkbook workbook = new XSSFWorkbook();
			// 首先检查数据看是否是正确的
			if (dataset == null || dataset.size() == 0 || title == null || out == null) {
				throw new Exception("传入的数据不对！");
			}
			// 取得实际泛型类
			T ts = (T) dataset.get(0);
			@SuppressWarnings("rawtypes")
			Class tCls = ts.getClass();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth(15);
			// 生成一个样式
			HSSFCellStyle style = workbook.createCellStyle();
			// 设置标题样式
			style = this.setHeadStyle(workbook, style);

			// 得到所有字段

			Field filed[] = ts.getClass().getDeclaredFields();
			// 标题
			List<String> exportfieldtile = new ArrayList<String>();
			// 导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();
			// 遍历整个filed
			for (int i = 0; i < filed.length; i++) {
				Field f = filed[i];
				ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
				// 如果设置了annottion
				if (exa != null) {
					String exprot = exa.exportName();
					// 添加到标题
					exportfieldtile.add(exprot);
					// 添加到需要导出的字段的方法
					String fieldname = f.getName();
					String getMethodName = "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);

					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});

					methodObj.add(getMethod);
				}
			}
			// 产生表格标题行
			HSSFRow row = sheet.createRow(0);
			for (int i = 0; i < exportfieldtile.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(exportfieldtile.get(i));
				cell.setCellValue(text);
			}

			int index = 0;

			// 循环整个list
			for (int j = 0; j < dataset.size(); j++) {
				// 从第二行开始写，第一行是标题
				T t = (T) dataset.get(j);
				row = sheet.createRow(index + 1);
				for (int k = 0; k < methodObj.size(); k++) {
					HSSFCell cell = row.createCell(k);
					Method getMethod = methodObj.get(k);
					Object value = getMethod.invoke(t, new Object[] {});

					String textValue = getValue(value);
					cell.setCellValue(textValue);
				}
				index++;
			}
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getValue(Object value) {
		String textValue = "";
		if (value == null)
			return textValue;

		if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			textValue = "是";
			if (!bValue) {
				textValue = "否";
			}
		} else if (value instanceof Date) {
			Date date = (Date) value;

			textValue = sdf.format(date);
		} else
			textValue = value.toString();

		return textValue;
	}

	/**
	 * 初始化导出的excel样式
	 */
	public static HSSFCellStyle setHeadStyle(HSSFWorkbook workbook, HSSFCellStyle style) {

		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样样式
		style.setFont(font);
		return style;

	}

	public static HSSFCellStyle setbodyStyle(HSSFWorkbook workbook, HSSFCellStyle style2) {
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样样式
		style2.setFont(font2);
		return style2;
	}

	/*
	 * 判断单元格是否为空
	 */
	/*
	 * public static boolean isRowEmpty(Row row) { for (int i =
	 * row.getFirstCellNum(); i < row.getLastCellNum(); i++) { Cell cell =
	 * row.getCell(i); if (cell != null && cell.getCellType() !=
	 * Cell.CELL_TYPE_BLANK) { if (String.valueOf(cell.getNumericCellValue()) !=
	 * null && !"".equals(String.valueOf(cell.getNumericCellValue()))) { return
	 * false; } return true; } } return true; }
	 */

}
