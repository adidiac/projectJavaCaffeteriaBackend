
package com.cafeteria.cafeteria.controllers;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.DbModels.MenuItem;
import com.cafeteria.cafeteria.service.MenuItemService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @PostMapping
    @ValidateTokenAdmin
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem, @RequestHeader("Authorization") String token) {
        return menuItemService.createMenuItem(menuItem);
    }

    @PutMapping("/{id}")
    @ValidateTokenAdmin
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem, @RequestHeader("Authorization") String token) {
        return menuItemService.updateMenuItem(id, menuItem);
    }

    @DeleteMapping("/{id}")
    @ValidateTokenAdmin
    public void deleteMenuItem(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        menuItemService.deleteMenuItem(id);
    }

}
