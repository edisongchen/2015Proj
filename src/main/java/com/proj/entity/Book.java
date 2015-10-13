package com.proj.entity;

public class Book {
	private String name;
	private Author author;
	public String getName() {
		return name;
	}
	public void setName(String pName) {
		name = pName;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author pAuthor) {
		author = pAuthor;
	}
	public Book() {
		super();
		System.out.println("generate book entity ......");
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + "]";
	}
	
}
