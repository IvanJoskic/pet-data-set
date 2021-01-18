package hr.fer.zari.or.restapi.model;

import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.databind.JsonNode;

import hr.fer.zari.or.restapi.entity.Breed;

public class ResponseModelSimple {
	private Integer status;
	private String message;
	private JsonNode response;
	
	
	public ResponseModelSimple(Integer status, String message, JsonNode json) {
		super();
		this.status = status;
		this.message = message;
		this.response = json;
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
	
	

	public JsonNode getResponse() {
		return response;
	}

	public void setResponse(JsonNode response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "ResponseModel [status=" + status + ", message=" + message + ", response=" + response + "]";
	}
	
}
