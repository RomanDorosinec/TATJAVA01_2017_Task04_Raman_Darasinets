package com.epam.task3.service.factory;

import com.epam.task3.service.NewsService;
import com.epam.task3.service.ResourceManagerService;
import com.epam.task3.service.impl.NewsServiceImpl;
import com.epam.task3.service.impl.ResourceManagerServiceImpl;

/**
 * Class creates objects of Service layer classes
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final NewsService newsService = new NewsServiceImpl();
    private final ResourceManagerService resourceManagerServiceImpl = new ResourceManagerServiceImpl();

    /**
     * Singleton implementation
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public ResourceManagerService getResourceManagerServiceImpl() {
        return resourceManagerServiceImpl;
    }
}
