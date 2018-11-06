package com.mmall.beans;

import lombok.*;

import java.util.Set;

/**
 * Created by RookieWangZhiWei on 2018/11/6.
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {


    private String subject;

    private String message;

    private Set<String> receivers;


}
