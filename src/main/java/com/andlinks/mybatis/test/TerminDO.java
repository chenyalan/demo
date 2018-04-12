package com.andlinks.mybatis.test;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by 陈亚兰 on 2018/1/4.
 */
@Setter
@Getter
public class TerminDO implements Serializable {
    private static final long serialVersionUID = -8978129870608298332L;
    private String terminal;
    private String province;
    private Integer num;
}
