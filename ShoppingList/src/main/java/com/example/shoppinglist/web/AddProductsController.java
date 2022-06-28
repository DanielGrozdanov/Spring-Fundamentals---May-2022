package com.example.shoppinglist.web;


import com.example.shoppinglist.model.entity.dtos.ProductAddDTO;
import com.example.shoppinglist.services.ProductService;
import com.example.shoppinglist.session.CurrentLoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AddProductsController {

    private ProductService productService;
    private CurrentLoggedUser loggedUser;

    public AddProductsController(ProductService productService, CurrentLoggedUser loggedUser) {
        this.productService = productService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/product/add")
    public String products() {
        if (loggedUser.getId() == null){
            return "redirect:/login";
        }
        return "product-add";
    }

    @ModelAttribute("productAlreadyExists")
    public boolean productAlreadyExists(){
        return false;
    }

    @PostMapping("/product/add")
    public String addProduct(@Valid ProductAddDTO productAddDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productAddDTO",productAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddDTO",bindingResult);
            return "redirect:/product/add";
        }
        if(!productService.addProduct(productAddDTO)){
            redirectAttributes.addFlashAttribute("productAddDTO",productAddDTO);
            redirectAttributes.addFlashAttribute("productAlreadyExists",true);
            return "redirect:/product/add";
        }
        return "redirect:/home";
    }

    @ModelAttribute("productAddDTO")
    public ProductAddDTO initProduct(){
        return new ProductAddDTO();
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id){
        productService.buyById(id);
        return "redirect:/";
    }
    @GetMapping("/buy/all")
    public String buyAllProduct(){
        productService.buyAll();
        return "redirect:/";
    }
}
