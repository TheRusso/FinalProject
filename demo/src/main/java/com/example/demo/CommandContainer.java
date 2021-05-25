package com.example.demo;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class CommandContainer {
    private static final Logger log = Logger.getLogger(CommandContainer.class.getName());

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("enter", new LoginCommand());
        commands.put("login", new OpenLoginPageCommand());
        commands.put("logout", new LogoutCommand());

        commands.put("media", new OpenMediaCommand());
        commands.put("contacts", new OpenContactsPageCommand());
        commands.put("shop", new OpenShopPageCommand());
        commands.put("about_us", new OpenAboutUsPage());
        commands.put("press_list", new OpenPressPageCommand());
        commands.put("register", new OpenRegisterPage());
        commands.put("insert_user", new RegisterUserCommand());
        commands.put("change_lang", new ChangeLangCommand());
        commands.put("send_message_contacts", new SendMessageCommand());
        commands.put("item", new OpenItemCommand());
    }

    public static Command get(String commandName){
        if(commandName == null || !commands.containsKey(commandName)){
            log.severe("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
