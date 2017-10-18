package com.yaping.common.support.datatables;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */
@Data
public class DataTablesResponse<T> {
    private Integer draw;

    private Long recordsTotal;

    private Long recordsFiltered;

    private List<T> data;

    private String error;
}
