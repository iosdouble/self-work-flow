package com.nh.support.identity;

import com.nh.support.client.rest.RestClient;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityManagerImpl;
import org.flowable.idm.engine.impl.persistence.entity.data.UserDataManager;

/**
 * @Classname AiaUserEntityManager
 * @Description TODO
 * @Date 2020/4/2 5:51 PM
 * @Created by nihui
 */
public class AiaUserEntityManager extends UserEntityManagerImpl {
    private RestClient restClient;



    public AiaUserEntityManager(RestClient restClient, IdmEngineConfiguration idmEngineConfiguration, UserDataManager userDataManager) {
        super(idmEngineConfiguration, userDataManager);
        this.restClient = restClient;
    }
}
