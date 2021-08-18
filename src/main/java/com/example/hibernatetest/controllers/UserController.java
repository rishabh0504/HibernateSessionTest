package com.example.hibernatetest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernatetest.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("execute-first-case")
	public void executeTransactionFirstCase() {
		userService.executeTransactionFirstCase();
	}
	
	@GetMapping("execute-second-case")
	public void executeTransactionSecondCase() {
		userService.executeTransactionSecondCase();
	}
	
	@GetMapping("execute-third-case")
	public void executeTransactionThirdCase() {
		userService.executeTransactionThirdCase();
	}
	
	@GetMapping("execute-fourth-case")
	public void executeTransactionFourthCase() {
		userService.executeTransactionFourthCase();
	}
	
	@GetMapping("execute-fifth-case")
	public void executeTransactionFifthCase() {
		userService.executeTransactionFifthCase();
	}
	
	
	@GetMapping("execute-sixth-case")
	public void executeTransactionSixthCase() {
		userService.executeTransactionSixthCase();
	}
	
	@GetMapping("execute-seventh-case")
	public void executeTransactionSeventhCase() {
		userService.executeTransactionSeventhCase();
	}
}
