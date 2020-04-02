package com.nh.support.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nh.support.exception.ExceptionFactory;
import com.nh.support.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * @Classname BaseResource
 * @Description TODO
 * @Date 2020/4/2 6:08 PM
 * @Created by nihui
 */
public abstract class BaseResource {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected ExceptionFactory exceptionFactory;
    @Autowired
    protected ObjectMapper objectMapper;

    protected Pageable getPageable(Map<String, String> requestParams) {
        int page = -1;
        if (ObjectUtils.isNotEmpty(requestParams.get("pageNum"))) {
            page = ObjectUtils.convertToInteger(requestParams.get("pageNum"), 1);
        }
        int size = 10;
        if (ObjectUtils.isNotEmpty(requestParams.containsKey("pageSize"))) {
            size = ObjectUtils.convertToInteger(requestParams.get("pageSize"), 10);
        }

        if (page < 0) {
            return null;
        }

        Sort.Order order = null;
        if (ObjectUtils.isNotEmpty(requestParams.get("sortName"))) {
            String sortName = requestParams.get("sortName");
            String sortOrder = requestParams.get("sortOrder");
            if (ObjectUtils.isEmpty(sortOrder) || "desc".equals(sortOrder)) {
                order = new Sort.Order(Sort.Direction.DESC, sortName);
            } else {
                order = new Sort.Order(Sort.Direction.ASC, sortName);
            }
        }

        if (order == null) {
            return new PageRequest(page - 1, size);
        } else {
            return new PageRequest(page - 1, size, new Sort(order));
        }
    }

    @SuppressWarnings("rawtypes")
    protected PageResponse createPageResponse(Page page) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setData(page.getContent());
        pageResponse.setTotal(page.getTotalElements());
        return pageResponse;
    }
}
