package com.hongcheng.fruitmall.common.util.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghongcheng on 2018/04/20.
 */
public class TableBuilder<T> {
    private int fromRow;
    private int fromColumn;
    private List<ColumnBuilder> columns;
    private List<T> data;

    private TableBuilder() {
    }

    public static <V> TableBuilder<V> build(List<V> data) {
        TableBuilder<V> builder = new TableBuilder<>();
        builder.data = data;
        builder.columns = new ArrayList<>();
        return builder;
    }

    public TableBuilder<T> fromRow(int row) {
        this.fromRow = row;
        return this;
    }

    public TableBuilder<T> fromColumn(int column) {
        this.fromColumn = column;
        return this;
    }


    public TableBuilder<T> addColumn(ColumnBuilder column) {
        if (column.getColumnIndex() == null) {
            column.setIndex(this.getMaxColumnIndex() + 1);
        }
        columns.add(column);
        return this;
    }

    private int getMaxColumnIndex() {
        int max = -1;
        for (ColumnBuilder column : columns) {
            if (column.getColumnIndex() > max) {
                max = column.getColumnIndex();
            }
        }
        return max;
    }

    public int getFromRow() {
        return fromRow;
    }

    public int getFromColumn() {
        return fromColumn;
    }


    public List<ColumnBuilder> columns() {
        return columns;
    }


    public List<T> getData() {
        return data;
    }

}
