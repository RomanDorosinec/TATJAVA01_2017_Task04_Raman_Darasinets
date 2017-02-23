package com.epam.task3.service;

import com.epam.task3.service.exception.ServiceException;

/**
 *
 */
public interface ResourceManagerService {
    void init() throws ServiceException;

    void destroy() throws ServiceException;
}
