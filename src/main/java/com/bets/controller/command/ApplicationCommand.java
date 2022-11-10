package com.bets.controller.command;

import com.bets.controller.command.api.Command;
import com.bets.controller.command.impl.AddTeamCommand;
import com.bets.controller.command.impl.ChangePasswordCommand;
import com.bets.controller.command.impl.CreateMatchCommand;
import com.bets.controller.command.impl.DefaultCommand;
import com.bets.controller.command.impl.DeleteAccountCommand;
import com.bets.controller.command.impl.DeleteMatchCommand;
import com.bets.controller.command.impl.EditProfileCommand;
import com.bets.controller.command.impl.LoginCommand;
import com.bets.controller.command.impl.LogoutCommand;
import com.bets.controller.command.impl.MakeBetCommand;
import com.bets.controller.command.impl.showPage.ShowErrorPageCommand;
import com.bets.controller.command.impl.showPage.ShowMatchCommand;
import com.bets.controller.command.impl.SignUpCommand;
import com.bets.controller.command.impl.showPage.ShowEditProfileCommand;
import com.bets.controller.command.impl.showPage.ShowLoginPageCommand;
import com.bets.controller.command.impl.showPage.ShowMainPageCommand;
import com.bets.controller.command.impl.showPage.ShowMatchesPageCommand;
import com.bets.controller.command.impl.showPage.ShowMyBetsPageCommand;
import com.bets.controller.command.impl.showPage.ShowPasswordPageCommand;
import com.bets.controller.command.impl.showPage.ShowProfilePageCommand;
import com.bets.controller.command.impl.showPage.ShowSignUpPageCommand;
import com.bets.dao.model.Role;

import java.util.Arrays;
import java.util.List;

public enum ApplicationCommand {
    DEFAULT(DefaultCommand.getInstance()),
    SHOW_MAIN(ShowMainPageCommand.getInstance()),
    SHOW_LOGIN(ShowLoginPageCommand.getInstance(), Role.UNAUTHORISED),
    SHOW_SIGN_UP(ShowSignUpPageCommand.getInstance(), Role.UNAUTHORISED),
    SIGN_UP_COMMAND(SignUpCommand.getInstance(), Role.UNAUTHORISED),
    LOGIN(LoginCommand.getInstance(), Role.UNAUTHORISED),
    LOGOUT(LogoutCommand.getInstance(), Role.USER, Role.ADMIN),
    SHOW_PROFILE_PAGE(ShowProfilePageCommand.getInstance(), Role.USER, Role.ADMIN),
    SHOW_EDIT_PROFILE(ShowEditProfileCommand.getInstance(), Role.USER, Role.ADMIN),
    EDIT_PROFILE(EditProfileCommand.getInstance(), Role.USER, Role.ADMIN),
    DELETE_ACCOUNT(DeleteAccountCommand.getInstance(), Role.USER, Role.ADMIN),
    SHOW_PASSWORD_PAGE(ShowPasswordPageCommand.getInstance(), Role.USER, Role.ADMIN),
    CHANGE_PASSWORD(ChangePasswordCommand.getInstance(), Role.USER, Role.ADMIN),
    SHOW_MATCHES(ShowMatchesPageCommand.getInstance(), Role.USER, Role.ADMIN),
    MATCH(ShowMatchCommand.getInstance(), Role.USER, Role.ADMIN),
    MAKE_BET(MakeBetCommand.getInstance(), Role.USER, Role.ADMIN),
    SHOW_MY_BETS(ShowMyBetsPageCommand.getInstance(), Role.USER, Role.ADMIN),
    CREATE_MATCH(CreateMatchCommand.getInstance(), Role.ADMIN),
    ADD_TEAM(AddTeamCommand.getInstance(), Role.ADMIN),
    SHOW_ERROR_PAGE(ShowErrorPageCommand.getInstance()),
    DELETE_MATCH(DeleteMatchCommand.getInstance(), Role.ADMIN);



    private final Command command;
    private final List<Role> allowRoles;

    ApplicationCommand(Command command, Role... roles) {
        this.command = command;
        this.allowRoles = roles != null && roles.length > 0 ? Arrays.asList(roles) : Role.valuesAsList();
    }

    public static Command getCommandByString(String name) {
        return Arrays.stream(ApplicationCommand.values())
                .filter(command -> command.toString().equalsIgnoreCase(name))
                .map(command -> command.command)
                .findFirst()
                .orElse(DefaultCommand.getInstance());
    }

    public Command getCommand() {
        return command;
    }

    public List<Role> getAllowRoles() {
        return allowRoles;
    }

    public static ApplicationCommand getCommands(String commandName) {
        for (ApplicationCommand command : values()) {
            if (command.name().equalsIgnoreCase(commandName)) {
                return command;
            }
        }
        return DEFAULT;
    }
}
