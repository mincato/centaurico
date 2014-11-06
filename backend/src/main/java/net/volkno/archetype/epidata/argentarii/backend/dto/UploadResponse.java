package net.volkno.archetype.epidata.argentarii.backend.dto;

import net.volkno.archetype.epidata.argentarii.backend.model.BackEndObject;

public class UploadResponse extends BackEndObject {

	private static final long serialVersionUID = -1775231529638642829L;

	private String status;
	private String url;
	
	public UploadResponse() {
	}
	
	public UploadResponse(String status, String url) {
		this.status = status;
		this.url = url;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
}
