package com.example.shoppinglist.services;


import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.entity.dtos.ProductAddDTO;
import com.example.shoppinglist.model.entity.enums.CategoryEnum;
import com.example.shoppinglist.model.entity.views.ProductViewModel;
import com.example.shoppinglist.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }
    public boolean addProduct(ProductAddDTO productAddDTO){
        Optional<Product> byName = productRepository.findByName(productAddDTO.getName());
        if (byName.isPresent()){
            return false;
        }
        Product product = modelMapper.map(productAddDTO,Product.class);
        product.setCategory(categoryService.getCategory(productAddDTO.getCategory()));
        this.productRepository.save(product);
        return true;
    }

    public List<ProductViewModel> getAllProductsByCategory(CategoryEnum category){
        Category cat = this.categoryService.getCategory(category);
        List<Product> allProductsByCategory = this.productRepository.findAllByCategory(cat);
       return allProductsByCategory.stream().map(product -> modelMapper.map(product,ProductViewModel.class)).collect(Collectors.toList());
    }

    public Double getAllProductsByPriceSum() {
        List<Product> all = this.productRepository.findAll();
       return all.stream().map(Product::getPrice).mapToDouble(BigDecimal::doubleValue).sum();
    }

    public void buyById(Long id) {
        this.productRepository.deleteById(id);
    }

    public void buyAll() {
        this.productRepository.deleteAll();
    }
}
