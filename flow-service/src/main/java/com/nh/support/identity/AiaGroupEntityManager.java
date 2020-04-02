package com.nh.support.identity;

import com.nh.support.client.rest.RestClient;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityManagerImpl;
import org.flowable.idm.engine.impl.persistence.entity.data.GroupDataManager;

/**
 * @Classname AiaGroupEntityManager
 * @Description TODO
 * @Date 2020/4/2 5:51 PM
 * @Created by nihui
 */
public class AiaGroupEntityManager extends GroupEntityManagerImpl {

    private RestClient restClient;
    public AiaGroupEntityManager(IdmEngineConfiguration idmEngineConfiguration, GroupDataManager groupDataManager) {
        super(idmEngineConfiguration, groupDataManager);
    }

    public AiaGroupEntityManager(RestClient restClient, IdmEngineConfiguration idmEngineConfiguration, GroupDataManager groupDataManager) {
        super(idmEngineConfiguration, groupDataManager);
        this.restClient = restClient;
    }
}
