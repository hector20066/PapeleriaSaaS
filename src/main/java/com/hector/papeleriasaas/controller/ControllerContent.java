package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.utils.MessageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ControllerContent {

    protected final Color color1 = new Color(60, 73, 89);
    protected final Color color2 = new Color(42, 49, 64);
    protected boolean expandedMenu = true;
    protected Timer animation;

    public ControllerContent() {
        connectEvents();
    }

    public abstract void connectEvents();

    public void menu() {
        try {
            int initialWidth = getJpl_menu().getWidth();
            int finalWidth = expandedMenu ? 50 : 219;
            int passed = (finalWidth - initialWidth) / 14;

            if (animation != null && animation.isRunning()) {
                return;
            }

            animation = new Timer(15, null);
            animation.addActionListener(new ActionListener() {
                int currentWidth = initialWidth;

                @Override
                public void actionPerformed(ActionEvent e) {
                    currentWidth += passed;
                    boolean endAnimation = (passed > 0 && currentWidth >= finalWidth) || (passed < 0
                            && currentWidth <= finalWidth);

                    if (endAnimation) {
                        currentWidth = finalWidth;
                        animation.stop();
                        expandedMenu = !expandedMenu;

                        updateTexts(expandedMenu);
                    }

                    getJpl_menu().setPreferredSize(new Dimension(currentWidth, getJpl_menu().getHeight()));
                    getJpl_menu().revalidate();
                    getJpl_menu().repaint();
                }
            });

            animation.start();
        } catch (Exception e) {
            MessageUtils.errorMessage("No fue posible cambiar el estado del menú.", null);
        }
    }

    public abstract JPanel getJpl_menu();
    public abstract void updateTexts(boolean expandedMenu);
    public abstract void logout();

}
