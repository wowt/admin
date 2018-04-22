package com.hongcheng.fruitmall.common.util.excel;

import java.util.function.Function;

import com.hongcheng.fruitmall.common.util.excel.converter.Converter;

/**
 * Created by wanghongcheng on 2017/04/20.
 */
public class ColumnBuilder {
    private Integer columnIndex;
    private String fieldName;
    private String name;
    private Converter converter;
    private Function function;

    private ColumnBuilder() {
    }

    /**
     * Create a ColumnBuilder with specified name, the name will be written at 1st line
     *
     * @param name columnName
     * @return
     */
    public static ColumnBuilder build(String name) {
        ColumnBuilder columnBuilder = new ColumnBuilder();
        columnBuilder.name = name;
        return columnBuilder;
    }

    /**
     * By default, columns will be ordered by the order they are added to SheetBuilder, so it is always unnecessary to call this method.
     * However, if you want to organize the order of columns personally, you can use this method to specify a column number.
     *
     * @param columnIndex
     * @return
     * @see SheetBuilder
     */
    public ColumnBuilder setIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
        return this;
    }

    /**
     * ExcelBuilder will get the value of this field to fill in excel on this column.
     * When you want to change the value into specified format, you can add a Converter.
     *
     * @param fieldName the value of this field will be written on this column
     * @return
     * @see ExcelBuilder
     * @see Converter
     */
    public ColumnBuilder setField(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    /**
     * Sometimes the value of this field could be unfriendly to user when directly written into excel.
     * Add a Converter to converter the value into specified format.
     *
     * @param converter value converter
     * @return
     * @see Converter
     */
    public ColumnBuilder setConverter(Converter<?, Object> converter) {
        this.converter = converter;
        return this;
    }

    public String getName() {
        return name;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Converter getConverter() {
        return converter;
    }

    public Function getFunction() {
        return function;
    }

    public ColumnBuilder setFunction(Function<?, Object> function) {
        this.function = function;
        return this;
    }
}
