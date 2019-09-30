package com.harrisburg.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harrisburg.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	
	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}
	
	@Override
	public Review get(Object id) {
		return super.find(Review.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Review.class, id);
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.findAll");
	}

	@Override
	public Long count() {
		return super.countWithNamedQuery("Review.countAll");
	}
	
	public long countByCustomer(int customerId) {
		return super.countWithNamedQuery("Review.countByCustomer", "customerId", customerId);
	}
	
	public Review findByCustomer(int customerId, int bookId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("customerId", customerId);
		parameters.put("bookId", bookId);
		
		List<Review> result = super.findWithNamedQuery("Review.findByCustomer", parameters);
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	public List<Review> listMostRecentReview(){
		return super.findWithNamedQuery("Review.findAll", 0, 3);
	}

}
