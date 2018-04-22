package com.hongcheng.fruitmall.common.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hongcheng.fruitmall.common.util.excel.converter.Converter;

/**
 * Created by wanghongcheng on 2018/04/20.
 */
public class ExcelBuilder {
    public enum TYPE {XLS, XLSX}

    private Workbook workbook;
    private List<SheetBuilder> sheetBuilders;

    private ExcelBuilder() {
    }

    public static ExcelBuilder build(TYPE version) {
        ExcelBuilder builder = new ExcelBuilder();
        builder.sheetBuilders = new ArrayList<>();
        switch (version) {
            case XLS:
                builder.workbook = new HSSFWorkbook();
                break;
            case XLSX:
                builder.workbook = new XSSFWorkbook();
                break;
            default:
                builder.workbook = new XSSFWorkbook();
                break;
        }
        return builder;
    }

    public ExcelBuilder addSheet(SheetBuilder sheet) {
        sheetBuilders.add(sheet);
        return this;
    }

    public void writeTo(OutputStream out) throws IOException, NoSuchFieldException {
        if (sheetBuilders != null && !sheetBuilders.isEmpty()) {
            for (int i = 0; i < sheetBuilders.size(); i++) {
                SheetBuilder sheetBuilder = sheetBuilders.get(i);
                String sheetName = sheetBuilder.getName();
                Sheet sheet;
                if (sheetName != null && sheetName.length() > 0) {
                    sheet = workbook.getSheet(sheetName);
                    if (sheet == null) {
                        sheet = workbook.createSheet(sheetName);
                    }
                } else {
                    sheet = workbook.getSheetAt(i);
                    if (sheet == null) {
                        sheet = workbook.createSheet();
                    }
                }
                this.buildSheet(sheet, sheetBuilder);
            }
        }

        workbook.write(out);
        workbook.close();
    }

    private void buildSheet(Sheet sheet, SheetBuilder builder) throws IOException, NoSuchFieldException {
        List<CellBuilder> cells = builder.getCells();
        if (!cells.isEmpty()) {
            this.buildFreeCells(sheet, cells);
        }

        List<TableBuilder> tables = builder.getTables();
        if (!tables.isEmpty()) {
            for (TableBuilder table : tables) {
                this.buildTable(sheet, table);
            }
        }
    }

    private void buildTable(Sheet sheet, TableBuilder builder) throws IOException, NoSuchFieldException {
        List<ColumnBuilder> columns = builder.columns();
        List data = builder.getData();

        if (null == columns || null == data || columns.isEmpty() || data.isEmpty()) {
            return;
        }
        Row header = sheet.createRow(builder.getFromRow());
        for (ColumnBuilder column : columns) {
            Cell cell = createCell(header, column.getColumnIndex() + builder.getFromColumn(), isEmpty(column.getName()) ? column.getFieldName() : column.getName());
            if (cell != null) {
                CellStyle style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cell.setCellStyle(style);
            }
        }

        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(builder.getFromRow() + i + 1);
            Object rowData = data.get(i);
            for (ColumnBuilder column : columns) {
                Object value;
                if (column.getFunction() != null) {
                    value = column.getFunction().apply(rowData);
                } else {
                    value = getValue(column.getFieldName(), rowData, column.getConverter());
                }
                createCell(row, column.getColumnIndex() + builder.getFromColumn(), value);
            }
        }
    }

    private void buildFreeCells(Sheet sheet, List<CellBuilder> cellBuilders) {
        for (CellBuilder builder : cellBuilders) {
            Row row = sheet.getRow(builder.getRow());
            if (null == row) {
                row = sheet.createRow(builder.getRow());
            }
            if (builder.getStretchToRows() > 0 || builder.getStretchToColumns() > 0) {
                CellRangeAddress address = new CellRangeAddress(builder.getRow(), builder.getStretchToRows(), builder.getColumn(), builder.getStretchToColumns());
                sheet.addMergedRegion(address);
            }
            createCell(row, builder.getColumn(), builder.getValue());
        }
    }

    private Cell createCell(Row row, int index, Object value) {
        if (null != value) {
            Cell cell = row.getCell(index);
            if (null == cell) {
                cell = row.createCell(index);
            }
            setCellValue(cell, value);
            return cell;
        }
        return null;
    }

    private void setCellValue(Cell cell, Object value) {
        if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else {
            cell.setCellValue(value.toString());
        }
    }

    private Object getValue(String fieldName, Object object, Converter converter) throws NoSuchFieldException {
        Object value;
        if (object instanceof Map) {
            value = ((Map) object).get(fieldName);
        } else {
            Field field = getAccessField(object.getClass(), fieldName);
            try {
                field.setAccessible(true);
                value = field.get(object);
            } catch (Exception e) {
                value = null;
            }
        }


        if (null != value) {
            Object rawValue;

            if (null != converter) {
                try {
                    rawValue = converter.convert(value);
                } catch (Exception e) {
                    rawValue = value;
                }
            } else {
                rawValue = value;
            }
            return rawValue;
        }
        return null;
    }

    private boolean isEmpty(String s) {
        return null == s || s.trim().length() == 0;
    }

    private Field getAccessField(Class clazz, String fieldName) throws NoSuchFieldException {
        Field field = null;
        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                field = superClass.getDeclaredField(fieldName);
                return field;
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
                if (superClass.getSuperclass() == Object.class) {
                    throw e;
                }
            }
        }
        return field;
    }
}
