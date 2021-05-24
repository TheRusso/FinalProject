package com.example.demo;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class CommandContainer {
    private static final Logger log = Logger.getLogger(CommandContainer.class.getName());

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterUserCommand());
        commands.put("catalog_page", new CatalogCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("send_message_contacts", new SendMessageCommand());
        commands.put("change_lang", new ChangeLangCommand());

        commands.put("enter", new OpenLoginPageCommand());
        commands.put("register_page", new OpenRegisterPage());
        commands.put("contacts_page", new OpenContactsPageCommand());
        commands.put("shop_page", new OpenShopPageCommand());
        commands.put("media_page", new ShowMediaCommand());
        commands.put("aboutUs_page", new OpenAboutUsPage());
        commands.put("press_page", new OpenPressPageCommand());

    }

    public static Command get(String commandName){
        if(commandName == null || !commands.containsKey(commandName)){
            log.severe("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
