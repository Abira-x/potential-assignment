package com.orders.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        String message = "Hello, World!";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/greeting")
    public ResponseEntity<String> greetUser(@RequestBody String name) {
        String message = "Hello, " + name + "!";
        return ResponseEntity.ok(message);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody String name) {
        // Logic to update the user with the given ID and name
        String message = "User with ID " + id + " updated successfully";
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // Logic to delete the user with the given ID
        String message = "User with ID " + id + " deleted successfully";
        return ResponseEntity.ok(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }
}