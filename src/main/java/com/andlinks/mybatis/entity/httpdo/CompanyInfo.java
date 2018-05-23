package com.andlinks.mybatis.entity.httpdo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by 陈亚兰 on 2018/5/23.
 */
@Setter
@Getter
public class CompanyInfo {
    @SerializedName(value = "companyId")
    private String id;
    @SerializedName(value = "companyName")
    private String name;
    private String title;
    @SerializedName(value = "timestamp")
    private String Timestamp;
    private String url;
    private String content;
}
