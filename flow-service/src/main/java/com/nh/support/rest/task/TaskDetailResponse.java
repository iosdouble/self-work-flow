package com.nh.support.rest.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.flowable.task.api.DelegationState;

/**
 * @Classname TaskDetailResponse
 * @Description TODO
 * @Date 2020/4/2 6:26 PM
 * @Created by nihui
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskDetailResponse extends HistoricTaskResponse {
    private String delegationState;
    private boolean suspended;
    protected String ownerName;
    protected String assigneeName;
    private String formKey;

    public void setDelegationState(DelegationState delegationState) {
        this.delegationState = getDelegationStateString(delegationState);
    }

    private String getDelegationStateString(DelegationState state) {
        String result = null;
        if (state != null) {
            result = state.toString().toLowerCase();
        }
        return result;
    }
}
