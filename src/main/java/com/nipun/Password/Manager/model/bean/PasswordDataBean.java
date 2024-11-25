package com.nipun.Password.Manager.model.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordDataBean {

    private String plainPassword;
    private String encryptedPassword;
    private String encodedPassword;
    private String remark;
    private String lastUpdatedTime;
    private String lastUpdatedUser;
    private String description;

}
