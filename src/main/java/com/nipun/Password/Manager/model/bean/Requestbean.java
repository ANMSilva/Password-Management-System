package com.nipun.Password.Manager.model.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Requestbean {

    private String userName;
    private Object requestBody;
    private String token;

}
