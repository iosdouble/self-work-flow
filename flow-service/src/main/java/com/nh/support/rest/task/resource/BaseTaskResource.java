package com.nh.support.rest.task.resource;

import com.nh.support.RestResponseFactory;
import com.nh.support.constant.ErrorConstant;
import com.nh.support.resource.BaseResource;
import org.flowable.engine.FormService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname BaseTaskResource
 * @Description TODO
 * @Date 2020/4/2 6:06 PM
 * @Created by nihui
 */
public class BaseTaskResource extends BaseResource {
    @Autowired
    protected RestResponseFactory restResponseFactory;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected FormService formService;

    Task getTaskFromRequest(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            exceptionFactory.throwObjectNotFound(ErrorConstant.TASK_NOT_FOUND, taskId);
        }
        return task;
    }

    HistoricTaskInstance getHistoricTaskFromRequest(String taskId) {
        HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        if (task == null) {
            exceptionFactory.throwObjectNotFound(ErrorConstant.TASK_NOT_FOUND, taskId);
        }
        return task;
    }
}
