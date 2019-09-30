package com.harrisburg.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harrisburg.entity.ProductOrder;

public class OrderDAO extends JpaDAO<ProductOrder> implements GenericDAO<ProductOrder> {

	@Override
	public ProductOrder create(ProductOrder order) {
		order.setOrderDate(new Date());
		order.setStatus("processing");
		return super.create(order);
	}
	
	@Override
	public ProductOrder get(Object orderId) {
		return super.find(ProductOrder.class, orderId);
	}
	
	public ProductOrder get(Integer orderId, Integer customerId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("orderId", orderId);
		parameters.put("customerId", customerId);
		List<ProductOrder> ProductOrder = super.findWithNamedQuery("ProductOrder.findByIdAndCustomer", parameters);
		if(!ProductOrder.isEmpty()) {
			return ProductOrder.get(0);
		}
		return null;
	}

	@Override
	public void delete(Object orderId) {
		super.delete(ProductOrder.class, orderId);
	}

	@Override
	public List<ProductOrder> listAll() {
		return super.findWithNamedQuery("ProductOrder.findAll");
	}

	@Override
	public Long count() {
		return super.countWithNamedQuery("ProductOrder.countAll");
	}

	@Override
	public ProductOrder update(ProductOrder order) {
		return super.update(order);
	}

	public long countOrderDetailByProduct(int ProductId) {
		return super.countWithNamedQuery("OrderDetail.countByProduct", "ProductId", ProductId);
	}
	
	public long countByCustomer(int customerId) {
		return super.countWithNamedQuery("ProductOrder.countByCustomer", "customerId", customerId);
	}
	
	public List<ProductOrder> listByCustomer(Integer customerId){
		return super.findWithNamedQuery("ProductOrder.findByCustomer", "customerId", customerId);
	}
	
	public List<ProductOrder> listMostRecentSell(){
		return super.findWithNamedQuery("ProductOrder.findAll", 0, 3);
	}
	
}
