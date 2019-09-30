package com.harrisburg.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.harrisburg.entity.Product;

public class ShoppingCart {
	
	Map<Product, Integer> cart = new HashMap<>();
	
	public void addItem(Product Product) {
		if(cart.containsKey(Product)) {
			Integer quantity = cart.get(Product) + 1;
			cart.put(Product, quantity);
		}else {
			cart.put(Product, 1);
		}
	}
	
	public int getTotalQuantity() {
		int total = 0;
		Iterator<Product> iterator = cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Product next = iterator.next();
			int quantity = cart.get(next);
			total += quantity;
		}
		
		return total;
	}
	
	public float getTotalAmount() {
		float total = 0.0f;
		Iterator<Product> iterator = cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Product Product = iterator.next();
			int quantity = cart.get(Product);
			double subTotal = quantity * Product.getPrice();
			total += subTotal;
		}
		
		return total;
	}
	
	public void updateCart(int[] ProductIds, int[] quantities) {
		for(int i=0; i<ProductIds.length; i++) {
			Product key = new Product(ProductIds[i]);
			Integer value = quantities[i];
			cart.put(key, value);
		}
	}
	
	public Map<Product, Integer> getItems(){
		return this.cart;
	}
	
	public void removeItem(Product Product) {
		cart.remove(Product);
	}
	
	public void clear() {
		cart.clear();
	}
	
	public int getTotalItems() {
		return cart.size();
	}
}
