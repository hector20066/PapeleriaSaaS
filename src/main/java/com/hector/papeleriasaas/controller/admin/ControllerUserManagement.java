package com.hector.papeleriasaas.controller.admin;

import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.panels.admin.AdminMenu;
import com.hector.papeleriasaas.view.content.panels.admin.RegisterUsersView;
import com.hector.papeleriasaas.view.content.panels.admin.UserManagementView;

public class ControllerUserManagement {

    private final UserManagementView userManagementView;
    private final RegisterUsersView registerUsersView;

    public ControllerUserManagement(UserManagementView userManagementView, RegisterUsersView registerUsersView) {
        this.userManagementView = userManagementView;
        this.registerUsersView = registerUsersView;

        connectEvents();
    }

    private void connectEvents() {
        userManagementView.setControllerRegisterUser(e -> NavigatorUtils.showPanels(AdminMenu.getJpl_content(), registerUsersView));
    }

}
