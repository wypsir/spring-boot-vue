package com.yaping.common.support.datatables;

import com.yaping.common.util.Log;
import org.springframework.data.jpa.datatables.parameter.ColumnParameter;
import org.springframework.data.jpa.datatables.parameter.OrderParameter;
import org.springframework.data.jpa.datatables.parameter.SearchParameter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/16.
 */
public class DataTablesRequest {


    private Integer draw;

    private Integer start;

    private Integer length;



    public DataTablesRequest() {
        Log.info("DataTablesRequest init");
    }
}
