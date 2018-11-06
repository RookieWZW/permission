package com.mmall.dto;

import com.google.common.collect.Lists;
import com.mmall.model.SysDept;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/11/5.
 */
@Data
@ToString
public class DeptLevelDto extends SysDept {


    private List<DeptLevelDto> deptList = Lists.newArrayList();


    public static DeptLevelDto adapt(SysDept dept) {
        DeptLevelDto dto = new DeptLevelDto();

        BeanUtils.copyProperties(dept, dto);

        return dto;
    }
}
