package com.nh.support.rest.task.resource;

import com.nh.support.resource.BaseResource;
import com.nh.support.rest.task.TaskDetailResponse;
import io.swagger.annotations.Api;
import org.flowable.engine.common.api.query.QueryProperty;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.service.impl.HistoricTaskInstanceQueryProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname TaskResource
 * @Description TODO
 * @Date 2020/4/2 6:06 PM
 * @Created by nihui
 */
@Api(value = "任务接口")
@RestController
public class TaskResource extends BaseTaskResource {

    private static Map<String, QueryProperty> allowedSortProperties = new HashMap<>();

    static {
        allowedSortProperties.put("deleteReason", HistoricTaskInstanceQueryProperty.DELETE_REASON);
        allowedSortProperties.put("duration", HistoricTaskInstanceQueryProperty.DURATION);
        allowedSortProperties.put("endTime", HistoricTaskInstanceQueryProperty.END);
        allowedSortProperties.put("executionId", HistoricTaskInstanceQueryProperty.EXECUTION_ID);
        allowedSortProperties.put("taskInstanceId", HistoricTaskInstanceQueryProperty.HISTORIC_TASK_INSTANCE_ID);
        allowedSortProperties.put("processDefinitionId", HistoricTaskInstanceQueryProperty.PROCESS_DEFINITION_ID);
        allowedSortProperties.put("processInstanceId", HistoricTaskInstanceQueryProperty.PROCESS_INSTANCE_ID);
        allowedSortProperties.put("assignee", HistoricTaskInstanceQueryProperty.TASK_ASSIGNEE);
        allowedSortProperties.put("taskDefinitionKey", HistoricTaskInstanceQueryProperty.TASK_DEFINITION_KEY);
        allowedSortProperties.put("description", HistoricTaskInstanceQueryProperty.TASK_DESCRIPTION);
        allowedSortProperties.put("dueDate", HistoricTaskInstanceQueryProperty.TASK_DUE_DATE);
        allowedSortProperties.put("name", HistoricTaskInstanceQueryProperty.TASK_NAME);
        allowedSortProperties.put("owner", HistoricTaskInstanceQueryProperty.TASK_OWNER);
        allowedSortProperties.put("priority", HistoricTaskInstanceQueryProperty.TASK_PRIORITY);
        allowedSortProperties.put("tenantId", HistoricTaskInstanceQueryProperty.TENANT_ID_);
        allowedSortProperties.put("startTime", HistoricTaskInstanceQueryProperty.START);
    }

    @GetMapping(value = "/tasks/{taskId}", name = "根据ID任务查询")
    public TaskDetailResponse getTaskById(@PathVariable("taskId") String taskId) {
        HistoricTaskInstance historicTaskInstance = getHistoricTaskFromRequest(taskId);

        Task task = null;
        if (historicTaskInstance.getEndTime() == null) {
            task = getTaskFromRequest(taskId);
        }

        String formKey = formService.getTaskFormKey(historicTaskInstance.getProcessDefinitionId(), historicTaskInstance.getTaskDefinitionKey());

        return restResponseFactory.createTaskDetailResponse(historicTaskInstance, task, formKey);
    }
}
