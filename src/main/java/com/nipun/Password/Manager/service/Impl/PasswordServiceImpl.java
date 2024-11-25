package com.nipun.Password.Manager.service.Impl;

import com.nipun.Password.Manager.model.bean.PasswordDataBean;
import com.nipun.Password.Manager.model.bean.Requestbean;
import com.nipun.Password.Manager.model.bean.ResponseBean;
import com.nipun.Password.Manager.model.entity.Password;
import com.nipun.Password.Manager.repository.PasswordRepository;
import com.nipun.Password.Manager.service.CommonService;
import com.nipun.Password.Manager.service.PasswordService;
import com.nipun.Password.Manager.utils.DataVarList;
import com.nipun.Password.Manager.utils.LoggingUtils;
import com.nipun.Password.Manager.utils.MessageVarList;
import com.nipun.Password.Manager.utils.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nipun.Password.Manager.utils.KeyUtils.generateKey;

@Service
@Slf4j
@AllArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final CommonService commonService;
    private final ModelMapper modelMapper;
    private final PasswordRepository passwordRepository;

    @Override
    @Transactional
    public ResponseBean addPassword(Requestbean requestbean, ResponseBean responseBean) throws Exception {
        String responseCode = ResponseCode.RSP_ERROR;
        String responseMasg = Strings.EMPTY;

        try {
            PasswordDataBean passwordDataBean = modelMapper.map(requestbean.getRequestBody(), PasswordDataBean.class);

            SecretKey secretKey = generateKey(128);
            StringBuffer plainPassword = new StringBuffer(passwordDataBean.getPlainPassword());
            String encodedPassword = commonService.encodedPassword(String.valueOf(plainPassword), secretKey);
            System.out.println(encodedPassword);

            //add new password
            Password password = Password.builder()
                    .encodedPassword(encodedPassword)
                    .secretKey(commonService.saveSecretKey(secretKey))
                    .description(passwordDataBean.getDescription())
                    .lastUpdatedTime(commonService.getSysDate())
                    .systemUser(null)
                    .build();

            try {

                passwordRepository.saveAndFlush(password);
                responseCode = ResponseCode.RSP_SUCCESS;
                responseMasg = MessageVarList.PASSWORD_ADD_SUCC;

            } catch (Exception ex) {
                LoggingUtils.logError(log, ex);
                responseMasg = MessageVarList.PASSWORD_ADD_ERROR;
            }

            plainPassword.setLength(0);
            plainPassword = null;

        } catch (Exception ex) {
            LoggingUtils.logError(log, ex);
            throw ex;
        } finally {
            responseBean.setResponseCode(responseCode);
            responseBean.setResponseMasg(responseMasg);
            responseBean.setContent(null);
        }

        return responseBean;
    }

    @Override
    @Transactional
    public ResponseBean retrievePassword(String passwordId, ResponseBean responseBean) throws Exception {
        String responseCode = ResponseCode.RSP_ERROR;
        String responseMasg = Strings.EMPTY;
        PasswordDataBean passwordDataBean = new PasswordDataBean();

        try {
            Optional<Password> optionalPassword = passwordRepository.findById(Integer.parseInt(passwordId));
            if (optionalPassword.isEmpty()) {
                throw new RuntimeException(MessageVarList.INVALID_PASSWORD_ID);
            } else {
                Password password = optionalPassword.get();
                SecretKey secretKey = commonService.loadSecretKey(password.getSecretKey());
                String plainPassword = commonService.decodedPassword(password.getEncodedPassword(), secretKey);

                passwordDataBean = PasswordDataBean.builder()
                        .plainPassword(plainPassword)
                        .encodedPassword(password.getEncodedPassword())
                        .description(password.getDescription())
                        .lastUpdatedTime(ObjectUtils.isEmpty(password.getLastUpdatedTime()) ? DataVarList.DEFAULT_STRING : password.getLastUpdatedTime().toString())
                        .build();
            }

        } catch (Exception ex) {
            LoggingUtils.logError(log, ex);
            throw ex;
        } finally {
            responseBean.setResponseCode(responseCode);
            responseBean.setResponseMasg(responseMasg);
            responseBean.setContent(passwordDataBean);
        }

        return responseBean;
    }

    @Override
    @Transactional
    public ResponseBean retrievePasswordList(ResponseBean responseBean) throws Exception {
        String responseCode = ResponseCode.RSP_ERROR;
        String responseMasg = Strings.EMPTY;
        List<Object> passwordDataBeanList = new ArrayList<>();

        try {
            List<Password> passwordList = passwordRepository.findAll();

            passwordDataBeanList = passwordList.stream()
                    .map(p -> {
                        try {
                            SecretKey secretKey = commonService.loadSecretKey(p.getSecretKey());
                            String plainPassword = commonService.decodedPassword(p.getEncodedPassword(), secretKey);
                            PasswordDataBean passwordDataBean = PasswordDataBean.builder()
                                    .plainPassword(plainPassword)
                                    .encodedPassword(p.getEncodedPassword())
                                    .description(p.getDescription())
                                    .lastUpdatedTime(ObjectUtils.isEmpty(p.getLastUpdatedTime()) ? DataVarList.DEFAULT_STRING : p.getLastUpdatedTime().toString())
                                    .build();

                            return passwordDataBean;
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toList());

        } catch (Exception ex) {
            LoggingUtils.logError(log, ex);
            throw ex;
        } finally {
            responseBean.setResponseCode(responseCode);
            responseBean.setResponseMasg(responseMasg);
            responseBean.setContentList(passwordDataBeanList);
        }

        return responseBean;
    }

    @Override
    @Transactional
    public ResponseBean updatePassword(String passwordId, Requestbean requestbean, ResponseBean responseBean) throws Exception {
        String responseCode = ResponseCode.RSP_ERROR;
        String responseMasg = Strings.EMPTY;

        try {

        } catch (Exception ex) {
            LoggingUtils.logError(log, ex);
            throw ex;
        } finally {
            responseBean.setResponseCode(responseCode);
            responseBean.setResponseMasg(responseMasg);
            responseBean.setContent(null);
        }

        return responseBean;
    }

    @Override
    @Transactional
    public ResponseBean deletePassword(String passwordId, Requestbean requestbean, ResponseBean responseBean) throws Exception {
        String responseCode = ResponseCode.RSP_ERROR;
        String responseMasg = Strings.EMPTY;

        try {

        } catch (Exception ex) {
            LoggingUtils.logError(log, ex);
            throw ex;
        } finally {
            responseBean.setResponseCode(responseCode);
            responseBean.setResponseMasg(responseMasg);
            responseBean.setContent(null);
        }

        return responseBean;
    }
}
