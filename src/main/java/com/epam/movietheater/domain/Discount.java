package com.epam.movietheater.domain;

public class Discount {
	
	/**
	 * 
	 */
	private byte totalDiscount;
	
	/**
	 * 
	 */
	private DiscountType dicountType;
	
	/**
	 * 
	 */
	private Integer counter;
	
	/**
	 * 
	 */
	private boolean booking;
	
	public Discount() {}
	
	public Discount(byte totalPrice, DiscountType discountType, Integer counter, boolean booking) {
		this.totalDiscount = totalPrice;
		this.dicountType = discountType;
		this.counter = counter;
		this.booking = booking;
	}
	
	public byte getTotalDiscount() {
		return totalDiscount;
	}
	
	public void setTotalDiscount(byte totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public boolean isBooking() {
		return booking;
	}

	public void setBooking(boolean booking) {
		this.booking = booking;
	}

	public DiscountType getDicountType() {
		return dicountType;
	}

	public void setDicountType(DiscountType dicountType) {
		this.dicountType = dicountType;
	}	
}
