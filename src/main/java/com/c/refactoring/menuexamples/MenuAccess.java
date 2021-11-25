package com.c.refactoring.menuexamples;

import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(List<MenuItem> menuItems, Role[] roles) {
        if (roles == null) return;

        for (MenuItem menuItem : menuItems) {
            for (Role role : roles) {
                if (isReadAccess(menuItem, role)) {
                    assignAccess(menuItem, Constants.READ);
                } else if (isWriteAccess(menuItem, role)) {
                    assignAccess(menuItem, Constants.WRITE);
                }
            }
        }
    }

    private boolean isReadAccess(MenuItem menuItem, Role role) {
        return role.getName().equals(menuItem.getReadAccessRole())
                && !Constants.WRITE.equals(menuItem.getAccess());
    }

    private boolean isWriteAccess(MenuItem menuItem, Role role) {
        return role.getName().equals(menuItem.getWriteAccessRole());
    }

    private void assignAccess(MenuItem menuItem, String type) {
        menuItem.setAccess(type);
        menuItem.setVisible(true);
    }

}
