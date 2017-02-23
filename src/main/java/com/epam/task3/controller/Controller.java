package com.epam.task3.controller;

import com.epam.task3.controller.command.Command;
import com.epam.task3.service.ResourceManagerService;
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

    private final char paramDelimiter = ' ';

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

    public void initResource() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ResourceManagerService resourceManagerService = serviceFactory.getResourceManagerServiceImpl();
        try {
            resourceManagerService.init();
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    public void clearResource() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ResourceManagerService resourceManagerService = serviceFactory.getResourceManagerServiceImpl();
        try {
            resourceManagerService.destroy();
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
