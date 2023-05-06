package org.example.client;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 读取多个Excel文件
        List<File> files = new ArrayList<>();
        files.add(new File("/Users/wanghuimin/IdeaProjects/test/src/main/resources/data/test.xlsx"));
        files.add(new File("/Users/wanghuimin/IdeaProjects/test/src/main/resources/data/test1.xlsx"));
//        files.add(new File("excel2.xlsx"));
//        files.add(new File("excel3.xlsx"));

        // 定义需要的维度列表
        List<String> dimensions = new ArrayList<>();
        dimensions.add("Day");
        dimensions.add("Revenue (Pub Currency)");
//        dimensions.add("维度3");

        // 定义数据字典
        ArrayList<ArrayList<Object>> dataDict = new ArrayList<>();

        // 遍历多个Excel文件，将数据存入数据字典中
        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            // 获取表头行
            Row headerRow = sheet.getRow(0);

            // 获取表头列名
            Map<Integer, String> columnNames = new HashMap<>();
            for (Cell cell : headerRow) {
                int columnIndex = cell.getColumnIndex();
                String columnName = cell.getStringCellValue();
                columnNames.put(columnIndex, columnName);
            }

            // 遍历数据行，将数据存入数据字典中
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row dataRow = sheet.getRow(i);

                // 获取维度值
                List<Object> dimensionValues = new ArrayList<>();
                for (String dimension : dimensions) {
                    Cell dimensionCell = dataRow.getCell(columnNames.entrySet().stream().filter(entry -> entry.getValue().equals(dimension)).findFirst().get().getKey());
                    dimensionValues.add(covertToType(dimensionCell));
                }


                // 存入数据字典
                ArrayList<Object> list = new ArrayList<>();
                for (Object dimensionValue : dimensionValues) {
                    list.add(dimensionValue);
                }
                dataDict.add(list);
            }

            workbook.close();
            fis.close();
        }

        // 创建新的Excel文件
        Workbook newWorkbook = new XSSFWorkbook();
        Sheet newSheet = newWorkbook.createSheet("合并数据");

        // 写入表头行
        Row headerRow = newSheet.createRow(0);
        int columnIndex = 0;
        for (String dimension : dimensions) {
            Cell dimensionCell = headerRow.createCell(columnIndex++);
            dimensionCell.setCellValue(dimension);
        }

        // 写入数据行
        int rowIndex = 1;
        for (ArrayList<Object> list : dataDict) {
            Row dataRow = newSheet.createRow(rowIndex++);
            columnIndex = 0;
            for (Object dimensionValue : list) {
                Cell dimensionValueCell = dataRow.createCell(columnIndex++);
                if (dimensionValue instanceof Integer) {
                    dimensionValueCell.setCellValue((Integer) dimensionValue);
                } else if (dimensionValue instanceof String) {
                    dimensionValueCell.setCellValue((String) dimensionValue);
                } else if (dimensionValue instanceof Double) {
                    dimensionValueCell.setCellValue((Double) dimensionValue);
                } else if (dimensionValue instanceof Date) {
                    dimensionValueCell.setCellValue(simpleDateFormat.format((Date) dimensionValue));
                }
            }
        }


        // 设置单元格样式
        XSSFCellStyle dataCellStyle = (XSSFCellStyle) newWorkbook.createCellStyle();
        dataCellStyle.setDataFormat(newWorkbook.createDataFormat().getFormat("yyyy-MM-dd"));


        // 保存新的Excel文件
        FileOutputStream fos = new FileOutputStream("merged.xlsx");
        newWorkbook.write(fos);
        newWorkbook.close();
        fos.close();
    }


    public static Object covertToType(Cell cell) {

        switch (cell.getCellType()) {

            case STRING:
                return cell.getRichStringCellValue().getString();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }


            case BOOLEAN:
                return cell.getBooleanCellValue();

            case FORMULA:
                return cell.getCellFormula();

            default:
                return "";
        }
    }


}