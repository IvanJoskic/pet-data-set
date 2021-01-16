package hr.fer.zari.or.restapi.model;

import java.awt.Image;

import hr.fer.zari.or.restapi.entity.Breed;

public class ResponseModel {
	private Integer status;
	private String message;
	private Response response;
	
	
	public ResponseModel(Integer status, String message, Breed breed, Image picture) {
		super();
		this.status = status;
		this.message = message;
		this.response = new Response<Breed>(breed, picture);
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "ResponseModel [status=" + status + ", message=" + message + ", response=" + response + "]";
	}

	
	
	
}
