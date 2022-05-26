package com.ranjith.webapp.model;

public class Groups {
	
	private Integer id;
	private String name;
	public Groups(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Groups() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
