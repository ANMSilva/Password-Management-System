package com.nipun.Password.Manager.service;

import javax.crypto.SecretKey;
import java.util.Date;

public interface CommonService {

    String encodedPassword(String plainPassword, SecretKey secretKey) throws Exception;

    String decodedPassword(String encryptedPassword, SecretKey secretKey) throws Exception;

    String saveSecretKey(SecretKey secretKey) throws Exception;

    SecretKey loadSecretKey(String keyString) throws Exception;

    Date getSysDate() throws Exception;

}
