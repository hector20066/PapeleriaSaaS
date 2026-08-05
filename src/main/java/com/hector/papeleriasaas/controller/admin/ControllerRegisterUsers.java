package com.hector.papeleriasaas.controller.admin;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.model.dto.user.DTOUserCreate;
import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.UserService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.view.content.panels.admin.RegisterUsersView;

import javax.swing.*;
import java.util.Objects;

public class ControllerRegisterUsers {

    private final RegisterUsersView registerUsersView;
    private final Subject subject;

    private final UserService userService;

    public ControllerRegisterUsers(RegisterUsersView registerUsersView, Subject subject, UserService userService) {
        this.registerUsersView = registerUsersView;
        this.subject = subject;

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
            String roleStr = (String) registerUsersView.getCbx_role().getSelectedItem();
            Integer companyId = null;

            if (idStr.isBlank() || name.isBlank() || user.isBlank() || passwd.isBlank() || Objects.requireNonNull(roleStr).isBlank()) {
                throw new EmptyFieldException("");
            }

            if (!roleStr.equals("Root")) {
                if (companyIdStr.isBlank()) {
                    throw new InvalidInputException("El necesario el id de la empresa o papelería");
                }

                companyId = ParserUtils.parseInteger(companyIdStr, "Id Empresa");
            }

            Roles role = convertRole(roleStr);
            Integer id = ParserUtils.parseInteger(idStr, "Cédula");

            prepareUI();
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

    private void prepareUI() {
        registerUsersView.getBtn_register().setText("Registrando...");
        registerUsersView.getBtn_register().setEnabled(false);
    }

    private void doInBackground(DTOUserCreate dto) {
        BackgroundExecutor.execute(() -> {
            try {
                int result = userService.add(dto.getIdentification(), dto.getName(), dto.getUser(), dto.getPasswd(), dto.getRole(), dto.getStatus(), dto.getCompanyId());
                SwingUtilities.invokeLater(() -> messageSuccess(result));
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("Ha ocurrido un error al registrar la información.\nError: " + e.getMessage(), registerUsersView);
                    resetUI();
                });
            }
        });
    }

    private void messageSuccess(int result) {
        String message = result == 0
                ? "Ha ocurrido un error en el registro. ID obtenido '0'"
                : "Se ha registrado el nuevo usuario.";

        MessageUtils.informationMessage(message, registerUsersView);
        subject.notifyObservers();
        registerUsersView.clearUI();
        resetUI();
    }

    private void resetUI() {
        registerUsersView.getBtn_register().setText("Registrar");
        registerUsersView.getBtn_register().setEnabled(true);
    }

}
