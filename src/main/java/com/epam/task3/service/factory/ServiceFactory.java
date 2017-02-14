package com.epam.task3.service.factory;

import com.epam.task3.service.NewsService;
import com.epam.task3.service.impl.NewsServiceImpl;

/**
 * Class creates objects of Service layer classes
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final NewsService newsService = new NewsServiceImpl();

    private ServiceFactory(){}

    /**
     * Singleton implementation
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    public NewsService getNewsService() {
        return newsService;
    }
}
