package hr.fer.zari.or.restapi.model;

import org.springframework.hateoas.EntityModel;

import hr.fer.zari.or.restapi.entity.Breed;

public class ResponseModel {
	private Integer status;
	private String message;
	private EntityModel<Response<Breed>> response;
	
	
	public ResponseModel(Integer status, String message, EntityModel<Response<Breed>> response) {
		super();
		this.status = status;
		this.message = message;
		this.response = response;
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
	
	

	public EntityModel<Response<Breed>> getResponse() {
		return response;
	}

	public void setResponse(EntityModel<Response<Breed>> response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "ResponseModel [status=" + status + ", message=" + message + ", response=" + response + "]";
	}
	
}
