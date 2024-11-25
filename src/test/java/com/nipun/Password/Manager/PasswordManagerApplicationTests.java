package com.nipun.Password.Manager;

import com.nipun.Password.Manager.model.bean.PasswordDataBean;
import com.nipun.Password.Manager.model.bean.Requestbean;
import com.nipun.Password.Manager.model.bean.ResponseBean;
import com.nipun.Password.Manager.service.PasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class PasswordManagerApplicationTests {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testAddPassword() throws Exception {

        PasswordDataBean passwordDataBean = PasswordDataBean.builder()
                .plainPassword("testPassword2")
                .description("This is test description2")
                .remark("test remark2")
                .build();

        Requestbean requestbean = Requestbean.builder()
                .requestBody(passwordDataBean)
                .build();

        ResponseBean responseBean = new ResponseBean();

        log.info("############################################################## adding password process started ###################################################################");
        responseBean = passwordService.addPassword(requestbean, responseBean);

        log.info(responseBean.getResponseCode());
        log.info(responseBean.getResponseMasg());

        log.info("############################################################## adding password process completed ##################################################################");

    }

    @Test
    void testRetrievePassword() throws Exception {
        ResponseBean responseBean = new ResponseBean();

        log.info("############################################################## retrieving password process started ###################################################################");
        responseBean = passwordService.retrievePassword("1", responseBean);


        log.info(responseBean.getResponseCode());
        log.info(responseBean.getResponseMasg());
        PasswordDataBean passwordDataBean = modelMapper.map(responseBean.getContent(), PasswordDataBean.class);

        log.info("Plain password : " + passwordDataBean.getPlainPassword());
        log.info("Encoded password : " + passwordDataBean.getEncodedPassword());
        log.info("Description : " + passwordDataBean.getDescription());
        log.info("Last updated time : " + passwordDataBean.getLastUpdatedTime());

        log.info("############################################################## retrieving password process completed ##################################################################");

    }

    @Test
    void testRetrievePasswordList() throws Exception {
        ResponseBean responseBean = new ResponseBean();

        log.info("############################################################## retrieving password list process started ###################################################################");
        responseBean = passwordService.retrievePasswordList(responseBean);


        log.info(responseBean.getResponseCode());
        log.info(responseBean.getResponseMasg());

        List<PasswordDataBean> passwordDataBeanList = responseBean.getContentList().stream()
                .map(p -> modelMapper.map(p, PasswordDataBean.class))
                .toList();

        passwordDataBeanList.forEach(passwordDataBean -> {
            log.info("Plain password : " + passwordDataBean.getPlainPassword());
            log.info("Encoded password : " + passwordDataBean.getEncodedPassword());
            log.info("Description : " + passwordDataBean.getDescription());
            log.info("Last updated time : " + passwordDataBean.getLastUpdatedTime());
        });

        log.info("############################################################## retrieving password list process completed ##################################################################");

    }
}
