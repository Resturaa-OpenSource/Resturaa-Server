package com.auri.model;
public class ApiResponse {
 
	private String time;
    private String status;
    private String message;
    private String path;
    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
 
    public String getStatus() {
        return status;
    }
 
    public String getMessage() {
        return message;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}