package com.nh.support;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nh.support.rest.task.HistoricTaskResponse;
import com.nh.support.rest.task.TaskDetailResponse;
import com.nh.support.utils.ObjectUtils;
import com.nh.support.veriable.*;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @Classname RestResponseFactory
 * @Description TODO Rest 返回结果工厂
 * @Date 2020/4/2 6:09 PM
 * @Created by nihui
 */
@Component
public class RestResponseFactory {

    private final ObjectMapper objectMapper;
    private final IdentityService identityService;

    private List<RestVariableConverter> variableConverters = new ArrayList<>();

    @Autowired
    public RestResponseFactory(ObjectMapper objectMapper, IdentityService identityService) {
        initializeVariableConverters();
        this.objectMapper = objectMapper;
        this.identityService = identityService;
    }

    private void initializeVariableConverters() {
        variableConverters.add(new StringRestVariableConverter());
        variableConverters.add(new IntegerRestVariableConverter());
        variableConverters.add(new LongRestVariableConverter());
        variableConverters.add(new ShortRestVariableConverter());
        variableConverters.add(new DoubleRestVariableConverter());
        variableConverters.add(new BooleanRestVariableConverter());
        variableConverters.add(new DateRestVariableConverter());
        variableConverters.add(new ListRestVariableConverter());

    }

    public TaskDetailResponse createTaskDetailResponse(HistoricTaskInstance historicTaskInstance, Task taskInstance, String formKey) {
        TaskDetailResponse result = new TaskDetailResponse();
        createHistoricTaskResponse(result, historicTaskInstance);
        result.setAssigneeName(getUserName(historicTaskInstance.getAssignee()));
        result.setOwnerName(getUserName(historicTaskInstance.getOwner()));
        if (taskInstance != null) {
            result.setDelegationState(taskInstance.getDelegationState());
            result.setSuspended(taskInstance.isSuspended());
        }
        result.setFormKey(formKey);
        return result;
    }


    private void createHistoricTaskResponse(HistoricTaskResponse result, HistoricTaskInstance taskInstance) {
        result.setId(taskInstance.getId());
        result.setName(taskInstance.getName());
        result.setOwner(taskInstance.getOwner());
        result.setTaskDefinitionKey(taskInstance.getTaskDefinitionKey());
        result.setAssignee(taskInstance.getAssignee());
        result.setDescription(taskInstance.getDescription());
        result.setCategory(taskInstance.getCategory());
        result.setDueDate(taskInstance.getDueDate());
        result.setFormKey(taskInstance.getFormKey());
        result.setParentTaskId(taskInstance.getParentTaskId());
        result.setPriority(taskInstance.getPriority());
        result.setProcessDefinitionId(taskInstance.getProcessDefinitionId());
        result.setTenantId(taskInstance.getTenantId());
        result.setProcessInstanceId(taskInstance.getProcessInstanceId());
        result.setDurationInMillis(taskInstance.getDurationInMillis());
        result.setStartTime(taskInstance.getStartTime());
        result.setEndTime(taskInstance.getEndTime());
        result.setClaimTime(taskInstance.getClaimTime());
        result.setWorkTimeInMillis(taskInstance.getWorkTimeInMillis());
    }

    private String getUserName(String userId) {
        if (ObjectUtils.isEmpty(userId)) {
            return null;
        }
        User user = identityService.createUserQuery().userId(userId).singleResult();
        if (user != null) {
            return user.getFirstName();
        }
        return null;
    }

    private String getGroupName(String groupId) {
        if (ObjectUtils.isEmpty(groupId)) {
            return null;
        }
        Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
        if (group != null) {
            return group.getName();
        }
        return null;
    }

}
