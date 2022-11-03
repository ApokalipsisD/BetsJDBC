package com.bets.controller.command.api;

import com.bets.controller.command.ApplicationCommand;

public interface Command {
    ResponseContext execute(RequestContext context);

    static Command of(String name) {
        return ApplicationCommand.getCommandByString(name);
    }

}
