package com.mmall.beans;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/11/5.
 */
@Data
@ToString
@Builder
public class PageResult<T> {


    private List<T> data = Lists.newArrayList();

    private int total = 0;


}
