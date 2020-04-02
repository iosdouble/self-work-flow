package com.nh.support.cmd;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.common.impl.interceptor.Command;
import org.flowable.engine.common.impl.interceptor.CommandContext;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.repository.Model;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname UpdateModelKeyCmd
 * @Description TODO 批量修改模型Key
 * @Date 2020/4/2 6:00 PM
 * @Created by nihui
 */
public class UpdateModelKeyCmd implements Command<Void>, Serializable {
    private String modelId;
    private String modelKey;

    public UpdateModelKeyCmd(String modelId, String modelKey) {
        this.modelId = modelId;
        this.modelKey = modelKey;
    }

    @Override
    public Void execute(CommandContext commandContext) {
        RepositoryService repositoryService = CommandContextUtil.getProcessEngineConfiguration(commandContext).getRepositoryService();
        Model model = repositoryService.getModel(modelId);
        if (model == null) {
            return null;
        }

        List<Model> models = repositoryService.createModelQuery().modelKey(model.getKey()).list();
        for (Model updateModel : models) {
            if (!updateModel.getId().equals(modelId)) {
                updateModel.setKey(modelKey);
                repositoryService.saveModel(updateModel);
            }
        }

        return null;
    }
}
