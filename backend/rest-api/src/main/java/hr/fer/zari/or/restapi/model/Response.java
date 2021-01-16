package hr.fer.zari.or.restapi.model;

import java.awt.Image;

public class Response<T> {
	private T breed;
	private Image picture;
	public Response(T breed, Image picture) {
		super();
		this.breed = breed;
		this.picture = picture;
	}
	public T getBreed() {
		return breed;
	}
	public void setBreed(T breed) {
		this.breed = breed;
	}
	
	public Image getPicture() {
		return picture;
	}
	public void setPicture(Image picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "Response [breed=" + breed + "]";
	}
	
	
}
