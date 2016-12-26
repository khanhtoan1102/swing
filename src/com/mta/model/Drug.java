package com.mta.model;

import java.util.Date;

public class Drug {
	private String id;
	private String name;
	private String producer;
	private double price;
	private Date mfg;
	private Date exp;
	private String batchNumber;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getMfg() {
		return mfg;
	}
	public void setMfg(Date mfg) {
		this.mfg = mfg;
	}
	public Date getExp() {
		return exp;
	}
	public void setExp(Date exp) {
		this.exp = exp;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public Drug() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Drug(String id, String name, String producer, double price, Date mfg, Date exp, String batchNumber) {
		super();
		this.id = id;
		this.name = name;
		this.producer = producer;
		this.price = price;
		this.mfg = mfg;
		this.exp = exp;
		this.batchNumber = batchNumber;
	}
	
}
