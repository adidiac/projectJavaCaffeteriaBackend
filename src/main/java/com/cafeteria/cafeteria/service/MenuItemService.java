package com.cafeteria.cafeteria.service;
import java.util.List;

import com.cafeteria.cafeteria.DbModels.MenuItem;

public interface MenuItemService {

    List<MenuItem> getAllMenuItems();

    MenuItem getMenuItemById(Long id);

    MenuItem createMenuItem(MenuItem menuItem);

    MenuItem updateMenuItem(Long id, MenuItem menuItem);

    void deleteMenuItem(Long id);

    MenuItem getMenuItemByName(String name);
}
