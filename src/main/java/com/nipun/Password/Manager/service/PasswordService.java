package com.nipun.Password.Manager.service;

import com.nipun.Password.Manager.model.bean.Requestbean;
import com.nipun.Password.Manager.model.bean.ResponseBean;
import org.springframework.transaction.annotation.Transactional;

public interface PasswordService {

    ResponseBean addPassword(Requestbean requestbean, ResponseBean responseBean) throws Exception;

    ResponseBean retrievePassword(String passwordId, ResponseBean responseBean) throws Exception;

    ResponseBean retrievePasswordList(Requestbean requestbean, ResponseBean responseBean) throws Exception;

    ResponseBean updatePassword(String passwordId, Requestbean requestbean, ResponseBean responseBean) throws Exception;

    ResponseBean deletePassword(String passwordId, Requestbean requestbean, ResponseBean responseBean) throws Exception;

}
