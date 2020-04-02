package com.nh.support.config;

import com.nh.support.client.rest.RestClient;
import com.nh.support.identity.AiaGroupEntityManager;
import com.nh.support.identity.AiaUserEntityManager;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.spring.boot.FlowableProperties;
import org.flowable.spring.boot.idm.FlowableIdmProperties;
import org.flowable.spring.boot.idm.IdmEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Classname FlowableIdmConfig
 * @Description TODO 人员关系配置类
 * @Date 2020/4/2 5:46 PM
 * @Created by nihui
 */
public class FlowableIdmConfig extends IdmEngineAutoConfiguration {

    @Autowired
    private RestClient restClient;

    public FlowableIdmConfig(FlowableProperties flowableProperties, FlowableIdmProperties idmProperties) {
        super(flowableProperties, idmProperties);
    }

    /**
     * 接管用户自定义用户配置
     * @param dataSource
     * @param platformTransactionManager
     * @return
     */
    @Autowired
    public SpringIdmEngineConfiguration idmEngineConfiguration(DataSource dataSource, PlatformTransactionManager platformTransactionManager) {
        SpringIdmEngineConfiguration configuration = super.idmEngineConfiguration(dataSource, platformTransactionManager);
        configuration.setGroupEntityManager(new AiaGroupEntityManager(restClient, configuration, configuration.getGroupDataManager()));
        configuration.setUserEntityManager(new AiaUserEntityManager(restClient, configuration, configuration.getUserDataManager()));
        return configuration;
    }
}
