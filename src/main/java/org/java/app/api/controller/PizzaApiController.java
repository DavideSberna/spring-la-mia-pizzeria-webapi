package org.java.app.api.controller;

import java.util.List;


import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Discount;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.CategoryService;
import org.java.app.db.serv.DiscountService;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1.0/pizza")
public class PizzaApiController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping
	public ResponseEntity<List<Pizza>> getAllPizzas(@RequestParam(value = "q", required = false) String name){
	
		List<Pizza> pizzas;
		
		if (name != null && !name.isEmpty()) {
	        
	        pizzas = pizzaService.findByTitle(name);
	    } else {
	       
	        pizzas = pizzaService.findAll();
	    }
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizzaId(@PathVariable int id){
	
		 Pizza pizza = pizzaService.findById(id);
		 
		 if(pizza == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza){
	
		  pizzaService.save(pizza);
		  
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pizza> updatePizza(@PathVariable int id, @RequestBody Pizza pizza){
	
		  
		 Pizza p = pizzaService.findById(id);
		 p.setNome(pizza.getNome());
		 p.setDescrizione(pizza.getDescrizione());
		 p.setImage(pizza.getImage());
		 p.setPrezzo(pizza.getPrezzo());
		 p.setVoto(pizza.getVoto());
		
		 pizzaService.save(p);
		 
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletePizza(@PathVariable int id) {
	    Pizza pizza = pizzaService.findById(id);

	    if (pizza == null) {
	        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	    }

	    List<Discount> discounts = pizza.getSpecialOffers();

	    if (!discounts.isEmpty()) {
	        for (Discount discount : discounts) {
	            discountService.deleteById(discount.getId());
	        }
	    }
	    
	    List<Category> categories = pizza.getCategorie();

	    if (!categories.isEmpty()) {
	        for (Category category : categories) {
	            categoryService.deleteById(category.getId());
	        }
	    }
	    
	    

	    pizzaService.deleteById(id);

	    return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
