package com.proj.entity;

import org.apache.ibatis.type.Alias;

@Alias("price")
public class Price {
	private Double salePrice;
	private Double listPrice;
	private Double preSalePrice;
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double pSalePrice) {
		salePrice = pSalePrice;
	}
	public Double getListPrice() {
		return listPrice;
	}
	public void setListPrice(Double pListPrice) {
		listPrice = pListPrice;
	}
	public Double getPreSalePrice() {
		return preSalePrice;
	}
	public void setPreSalePrice(Double pPreSalePrice) {
		preSalePrice = pPreSalePrice;
	}
	public Price() {
		super();
		System.out.println("constructor getted .....................");
	}
	
	
}
