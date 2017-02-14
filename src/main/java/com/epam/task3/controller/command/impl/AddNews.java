package com.epam.task3.controller.command.impl;

import com.epam.task3.controller.command.Command;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class AddNews which implements the interface methods of Command
 */
public class AddNews implements Command {
    private static final String NEWS_ADD = "News add";
    private static final String ERROR_ADDED = "Error added news!";

    private static final String INCORRECT_NUMBER_PARAM = "Entered incorrect number of parameters!";

    private static final Logger logger = LogManager.getLogger();

    /**
     * Add news of file
     *
     * @param request request of user
     * @return message to user, if news added or doesn't add
     */
    @Override
    public String execute(String request) {
        String response;
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            NewsService newsService = serviceFactory.getNewsService();
            String[] paramsOfNews = getParamOfNews(request);
            newsService.addNews(paramsOfNews[0], paramsOfNews[1], paramsOfNews[2]);
            response = NEWS_ADD;
        } catch (IllegalArgumentException e1) {
            response = ERROR_ADDED;
            logger.error(e1);
        } catch (ServiceException e) {
            response = ERROR_ADDED;
            logger.error(e);
        }
        return response;
    }

    private String[] getParamOfNews(String request) {
        request = request.substring(request.indexOf(' ') + 1, request.length());
        String[] paramNews = request.split(",");
        if (paramNews.length < 3 || paramNews.length > 3) {
            throw new IllegalArgumentException(INCORRECT_NUMBER_PARAM);
        }
        return paramNews;
    }
}
