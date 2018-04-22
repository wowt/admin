package com.hongcheng.fruitmall.common.util.excel;

/**
 * Created by wanghongcheng on 2018/04/20.
 */
public class CellBuilder {
    private int row;
    private int column;
    private Object value;

    private int stretchToRows;
    private int stretchToColumns;

    public CellBuilder(int row, int column, Object value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }


    public CellBuilder stretchTo(int row, int column) {
        this.stretchToRows = row;
        this.stretchToColumns = column;
        return this;
    }

    public CellBuilder stretchBy(int rows, int columns) {
        this.stretchToRows = row + rows;
        this.stretchToColumns = column + columns;
        return this;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }

    public int getStretchToRows() {
        return stretchToRows;
    }

    public int getStretchToColumns() {
        return stretchToColumns;
    }
}
