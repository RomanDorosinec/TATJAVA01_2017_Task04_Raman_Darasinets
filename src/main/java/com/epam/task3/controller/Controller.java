package com.epam.task3.controller;

import com.epam.task3.controller.command.Command;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class which to dispatch a number of commands
 */
public final class Controller {

    private static final Logger logger = LogManager.getLogger();

    private final CommandProvider provider = new CommandProvider();

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final NewsService newsService = serviceFactory.getNewsService();

    private final char paramDelimiter = ' ';

    public void init() {
        try {
            newsService.init();
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    /**
     * Method gets request as argument and calls definite command to execute current request
     *
     * @param request line of parameters of request to execute
     */
    public String executeTask(String request) {
        String commandName;
        Command executionCommand;

        commandName = request.substring(0, request.indexOf(paramDelimiter));
        executionCommand = provider.getCommand(commandName);

        String response;

        response = executionCommand.execute(request);

        return response;
    }

    public void destroy() {
        newsService.destroy();
    }
}
