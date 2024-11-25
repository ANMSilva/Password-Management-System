package com.nipun.Password.Manager.service.Impl;

import com.nipun.Password.Manager.service.CommonService;
import com.nipun.Password.Manager.utils.DataVarList;
import com.nipun.Password.Manager.utils.LoggingUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    /**
     * The @PersistenceContext annotation in Java is part of the Jakarta Persistence API (JPA),
     * used to inject an instance of the EntityManager into a class.
     * This EntityManager is responsible for interacting with the persistence context,
     * which manages the lifecycle of entity objects and database interactions.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * plain text + secret key = Cipher -> Cipher text
     * */
    @Override
    public String encodedPassword(String plainPassword, SecretKey secretKey) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(DataVarList.ALGORITHM_ASE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainPassword.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            LoggingUtils.logError(log, e);
            throw e;
        }
    }

    @Override
    public String decodedPassword(String encryptedPassword, SecretKey secretKey) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(DataVarList.ALGORITHM_ASE);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);

            return new String(cipher.doFinal(decodedBytes));
        } catch (Exception e) {
            LoggingUtils.logError(log, e);
            throw e;
        }
    }

    @Override
    public String saveSecretKey(SecretKey secretKey) throws Exception {
        try {
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            LoggingUtils.logError(log, e);
            throw e;
        }
    }

    @Override
    public SecretKey loadSecretKey(String keyString) throws Exception {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(keyString);
            return new SecretKeySpec(decodedKey, 0, decodedKey.length, DataVarList.ALGORITHM_ASE);
        } catch (Exception e) {
            LoggingUtils.logError(log, e);
            throw e;
        }
    }

    @Override
    public Date getSysDate() throws Exception {
        Date sysDateTime = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat(DataVarList.yyyyMMddHHmmss);
        Query query = entityManager.createNativeQuery(DataVarList.SQL_SYSTEM_TIME);
        String stime = (String) query.getSingleResult();

        if (StringUtils.isNotEmpty(stime)) {
            sysDateTime = dateFormat.parse(stime);
        } else {
            sysDateTime = new Date();
        }

        return sysDateTime;
    }
}
