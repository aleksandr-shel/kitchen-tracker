package com.kitchentracker.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController implements ErrorController{
	
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/register")
    public String register()
    {
        return "register";
    }
	
	@RequestMapping("/login")
    public String login()
    {
        return "login";
    }
	
	@RequestMapping("/recipes")
	public String recipes() {
		return "recipes";
	}
	
	@RequestMapping("/inventory")
	public String inventory() {
		return "inventory";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	@RequestMapping("/userGuide")
	public String guide() {
		return"userGuide";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/recipes/{id}")
	public ModelAndView recipe(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("recipe");
		mv.addObject("id", id);
		return mv;
	}
	
	@RequestMapping("/recipes/edit-page/{id}")
	public ModelAndView recipeEdit(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("recipe-edit");
		mv.addObject("id", id);
		return mv;
	}
	
	@RequestMapping("/inventory/{id}")
	public ModelAndView item(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("inventory-item");
		mv.addObject("itemId", id);
		return mv;
	}
	
	@RequestMapping("/add-recipe")
	public String addRecipe() {
		return "add-recipe";
	}
	
	
	@RequestMapping("/error")
	public String error() {
		return "error";
	}
	
}
