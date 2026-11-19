package com.example.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Rating {
	private Double rate;
	private long count;
}

@Getter
@Setter
@Data
public class FakeStoreProductDownstream {
	private Long id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
}

