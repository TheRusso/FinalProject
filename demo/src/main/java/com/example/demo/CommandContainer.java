package com.example.demo;

import com.example.demo.controllers.*;
import com.example.demo.controllers.open_view.*;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

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
        commands.put("cart_actions", new CartCommand());
        commands.put("cart", new OpenCartCommand());
        commands.put("order", new OpenOrderCommand());
        commands.put("make_order", new MakeOrderCommand());
        commands.put("update_quantity", new AjaxUpdateQuantityCommand());
        commands.put("user", new UserPanelCommand());
        commands.put("update_status", new ChangeStatusCommand());
        commands.put("admin", new OpenAdminOrdersCommand());
        commands.put("admin/orders", new OpenAdminOrdersCommand());
        commands.put("admin/users", new OpenAdminUsersCommand());
        commands.put("admin/edit_item", new OpenEditItemCommand());
        commands.put("update_role", new ChangeUserRoleCommand());
        commands.put("ban_user", new BanUserCommand());
        commands.put("admin/edit_item_command", new EditItemCommand());
        commands.put("admin/delete_item", new DeleteItemCommand());
        commands.put("admin/add_item", new OpenAddItemPageCommand());
        commands.put("admin/add_item_command", new AddItemCommand());
        commands.put("add_item_command", new AddItemCommand());
    }

    public static Command get(String commandName){
        if(commandName == null || !commands.containsKey(commandName)){
            log.info("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
