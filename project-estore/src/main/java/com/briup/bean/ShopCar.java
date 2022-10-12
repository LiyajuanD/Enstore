package com.briup.bean;



public class ShopCar {
	private Integer id;
	private int num;
	private int bookId;
	private int customerId;

	private Book book;
	private Customer customer;

	public ShopCar() {
	}
	public ShopCar(int num, int bookId, int customerId) {
		this.num = num;
		this.bookId = bookId;
		this.customerId = customerId;
	}



	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "ShopCar{" +
				"id=" + id +
				", num=" + num +
				", bookId=" + bookId +
				", customerId=" + customerId +
				", book=" + book +
				", customer=" + customer +
				'}';
	}
}
