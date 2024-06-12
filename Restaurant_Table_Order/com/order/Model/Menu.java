package com.order.Model;

import java.util.ArrayList;

public class Menu {
	private String menuItem;
	private int count;
	private int price;
	private int sum;
	
	public Menu(String menuItem, int count, int price) {
		this.menuItem=menuItem;
		this.count=count;
		this.price=price;
		this.sum=price*count;
	}

	public String getMenuItem() {
		return menuItem;
	}
	
	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
	
}