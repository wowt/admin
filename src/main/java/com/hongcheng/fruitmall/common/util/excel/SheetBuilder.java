package com.hongcheng.fruitmall.common.util.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghongcheng on 2018/04/20.
 */
public class SheetBuilder {
    private String name;
    private List<CellBuilder> cells;
    private List<TableBuilder> tables;


    private SheetBuilder() {
    }

    public static SheetBuilder build(String name) {
        SheetBuilder builder = new SheetBuilder();
        builder.name = name;
        builder.tables = new ArrayList<>();
        builder.cells = new ArrayList<>();
        return builder;
    }

    public SheetBuilder addTable(TableBuilder tableBuilder) {
        this.tables.add(tableBuilder);
        return this;
    }

    public SheetBuilder addCell(CellBuilder cellBuilder) {
        this.cells.add(cellBuilder);
        return this;
    }

    public String getName() {
        return name;
    }

    public List<CellBuilder> getCells() {
        return cells;
    }

    public List<TableBuilder> getTables() {
        return tables;
    }
}
