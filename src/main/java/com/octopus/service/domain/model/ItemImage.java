package com.octopus.service.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;


@Entity
@Table(name = "item_images")
@JsonFilter("ITEM_IMAGE_FILTER")
public class ItemImage extends BaseModel {
	
	private static final long serialVersionUID = 4200002825211590096L;

	private Item item;
	
	private String imagePath;

	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "image_path")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
