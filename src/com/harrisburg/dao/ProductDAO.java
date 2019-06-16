package com.harrisburg.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.harrisburg.entity.Product;

public class ProductDAO extends JpaDAO<Product> implements GenericDAO<Product> {

	public ProductDAO() {
	}

	@Override
	public Product create(Product Product) {
		Product.setLastUpdateTime(new Date());
		return super.create(Product);
	}

	@Override
	public Product update(Product Product) {
		Product.setLastUpdateTime(new Date());
		return super.update(Product);
	}

	@Override
	public Product get(Object ProductId) {
		return super.find(Product.class, ProductId);
	}

	@Override
	public void delete(Object ProductId) {
		super.delete(Product.class, ProductId);
	}

	@Override
	public List<Product> listAll() {
		return super.findWithNamedQuery("Product.findAll");
	}

	public Product findByTitle(String title) {
		List<Product> result = super.findWithNamedQuery("Product.findByTitle", "title", title);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public List<Product> listByCategory(int categoryId) {
		return super.findWithNamedQuery("Product.findByCategory", "catId", categoryId);
	}

	public List<Product> search(String keyword) {
		return super.findWithNamedQuery("Product.search", "keyword", keyword);
	}

	public List<Product> listNewProduct() {
		return super.findWithNamedQuery("Product.listNew", 0, 4);
	}

	@Override
	public Long count() {
		return super.countWithNamedQuery("Product.countAll");
	}

	public long countByCategory(int categoryId) {

		return super.countWithNamedQuery("Product.countByCategory", "catId", categoryId);
	}

	public List<Product> listBestSellingProduct() {
		return super.findWithNamedQuery("OrderDetail.bestSelling", 0, 4);
	}

	public List<Product> mostFavorateProduct() {
		List<Product> mostFavorateProduct = new ArrayList<>();
		List<Object[]> result = super.findWithNamedQueryObject("Review.mostFavoriteProducts", 0, 4);

		if (!result.isEmpty()) {
			for (Object[] element : result) {
				Product Product = (Product) element[0];
				mostFavorateProduct.add(Product);
			}
		}

		return mostFavorateProduct;
	}
}
