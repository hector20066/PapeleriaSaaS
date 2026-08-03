package com.hector.papeleriasaas.controller.admin;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.model.dto.user.DTOUserCreate;
import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.service.interfaces.UserService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.view.content.panels.admin.RegisterUsersView;

public class ControllerRegisterUsers {

    private final RegisterUsersView registerUsersView;

    private final UserService userService;

    public ControllerRegisterUsers(RegisterUsersView registerUsersView, UserService userService) {
        this.registerUsersView = registerUsersView;

        this.userService = userService;

        connectEvents();
    }

    private void connectEvents() {
        registerUsersView.setControllerRegister(e -> register());
    }

    private void register() {
        try {
            String idStr = registerUsersView.getTxt_userId().getText().trim();
            String name = registerUsersView.getTxt_personName().getText().trim();
            String companyIdStr = registerUsersView.getTxt_companyId().getText().trim();
            String user = registerUsersView.getTxt_username().getText().trim();
            String passwd = new String(registerUsersView.getJpld_passwd().getPassword());
            String roleStr = registerUsersView.getCbx_role().toString();

            if (idStr.isBlank() || name.isBlank() || user.isBlank() || passwd.isBlank()) {
                throw new EmptyFieldException("");
            }

            if (!roleStr.equals("Root")) {
                if (companyIdStr.isBlank()) {
                    throw new InvalidInputException("El necesario el id de la empresa o papelería");
                }
            }

            Roles role = convertRole(roleStr);
            Integer id = ParserUtils.parseInteger(idStr, "Cédula");
            Integer companyId = ParserUtils.parseInteger(companyIdStr, "Id Empresa");

            doInBackground(dtoUserCreate(id, name, companyId, user, passwd, role));
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(e.getMessage(), registerUsersView);
        }
    }

    private DTOUserCreate dtoUserCreate(Integer id, String name, Integer companyId, String user, String passwd, Roles role) {
        return new DTOUserCreate.Builder()
                .identification(id)
                .name(name)
                .user(user)
                .passwd(passwd)
                .role(role)
                .status(Status.ACTIVE)
                .companyId(companyId)
                .build();
    }

    private Roles convertRole(String role) {
        return switch (role) {
            case "Root" -> Roles.ROOT;
            case "Administrador" -> Roles.ADMIN;
            case "Empleado" -> Roles.EMPLOYEE;
            default -> Roles.OTHER;
        };
    }

    private void doInBackground(DTOUserCreate dto) {
        BackgroundExecutor.execute(() -> {
            try {
                userService.add(dto.getIdentification(), dto.getName(), dto.getUser(), dto.getPasswd(), dto.getRole(), dto.getStatus());
            } catch (DAOException e) {

            }
        });
    }

}
