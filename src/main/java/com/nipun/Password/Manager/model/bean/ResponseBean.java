package com.nipun.Password.Manager.model.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBean {

    private String responseCode;
    private String responseMasg;
    private Object content;
    private List<Object> contentList;

}
