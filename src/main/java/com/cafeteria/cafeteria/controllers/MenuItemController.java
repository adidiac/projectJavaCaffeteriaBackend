
package com.cafeteria.cafeteria.controllers;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.DbModels.MenuItem;
import com.cafeteria.cafeteria.service.MenuItemService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@Tag(name = "Menu Items", description = "Menu Items API for creating, getting, updating and deleting menu items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok().body(menuItemService.getAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return ResponseEntity.ok().body(menuItemService.getMenuItemById(id));
    }

    @PostMapping
    @ValidateTokenAdmin
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(menuItemService.createMenuItem(menuItem));
    }

    @PutMapping("/{id}")
    @ValidateTokenAdmin
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(menuItemService.updateMenuItem(id, menuItem));
    }

    @DeleteMapping("/{id}")
    @ValidateTokenAdmin
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.ok().build();
    }

}
