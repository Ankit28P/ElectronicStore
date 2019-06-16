package com.harrisburg.entity;

import static javax.persistence.GenerationType.IDENTITY;

//Generated Feb 25, 2019 6:49:50 PM by Hibernate Tools 5.2.11.Final

import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "product", catalog = "Productstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@NamedQueries({
	@NamedQuery(name="Product.findAll", query="select b from Product b"),
	@NamedQuery(name="Product.findByTitle", query="select b from Product b where b.title = :title"),
	@NamedQuery(name="Product.countAll", query="select COUNT(*) from Product b"),
	@NamedQuery(name="Product.countByCategory", query="select COUNT(*) from Product b JOIN"
			+ " Category c ON b.category.categoryId = c.categoryId AND c.categoryId =:catId"),
	@NamedQuery(name="Product.findByCategory", query="select b from Product b JOIN "
			+ "Category c ON b.category.categoryId = c.categoryId AND c.categoryId = :catId"),
	@NamedQuery(name="Product.listNew", query="select b from Product b ORDER BY b.publishDate DESC"),
	@NamedQuery(name="Product.search", query="select b from Product b where b.title LIKE '%' || :keyword || '%'"
			+ "OR b.author Like '%' || :keyword || '%'"
			+ "OR b.description Like '%' || :keyword || '%'")
})
public class Product implements java.io.Serializable {

	private Integer productId;
	private Category category;
	private String productName;
	private String owner;
	private String description;
	private byte[] image;
	private String base64Image;
	private float price;
	private Date publishDate;
	private Date lastUpdateTime;

	public Product() {
	}

	public Product(Integer productId) {
		super();
		this.productId = productId;
	}

	public Product(Category category, String productName, String owner, String description, byte[] image,
			float price, Date publishDate, Date lastUpdateTime) {
		this.category = category;
		this.productName = productName;
		this.owner = owner;
		this.description = description;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdateTime = lastUpdateTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "product_id", unique = true, nullable = false)
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "title", unique = true, nullable = false, length = 128)
	public String getProductName() {
		return this.productName;
	}

	public void setTitle(String productName) {
		this.productName = productName;
	}

	@Column(name = "owner", nullable = false, length = 64)
	public String getAuthor() {
		return this.owner;
	}

	public void setAuthor(String author) {
		this.owner = author;
	}

	@Column(name = "description", nullable = false, length = 16777215)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "image", nullable = false)
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publish_date", nullable = false, length = 10)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_time", nullable = false, length = 19)
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Transient
	public String getBase64Image() {
		this.base64Image = Base64.getEncoder().encodeToString(image);
		return this.base64Image;
	}

	@Transient
	public void setBase64Image(String base64) {
		this.base64Image = base64;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

}
