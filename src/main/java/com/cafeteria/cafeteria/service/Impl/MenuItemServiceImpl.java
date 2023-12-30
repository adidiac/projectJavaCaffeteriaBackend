
package com.cafeteria.cafeteria.service.Impl;
import com.cafeteria.cafeteria.DbModels.MenuItem;
import com.cafeteria.cafeteria.repository.MenuItemRepository;
import com.cafeteria.cafeteria.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        try {
            System.out.println("Getting all menu items");
            System.out.println(menuItemRepository.findAll());
            return menuItemRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        MenuItem existingMenuItem = menuItemRepository.findById(id).orElse(null);
        if (existingMenuItem != null) {
            // Update the properties you want to allow modification
            existingMenuItem.setName(menuItem.getName());
            existingMenuItem.setDescription(menuItem.getDescription());
            existingMenuItem.setPrice(menuItem.getPrice());
            existingMenuItem.setCategory(menuItem.getCategory());

            return menuItemRepository.save(existingMenuItem);
        }
        return null; // or throw an exception if not found
    }

    @Override
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
