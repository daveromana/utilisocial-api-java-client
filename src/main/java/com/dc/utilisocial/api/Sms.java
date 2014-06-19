package com.dc.utilisocial.api;

public class Sms {
	private Long id;
	private Long auditId;
	private String message;
	private String fromNumber;
	private boolean response;
	private String date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFromNumber() {
		return fromNumber;
	}

	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", auditId=" + auditId +
                ", message='" + message + '\'' +
                ", fromNumber='" + fromNumber + '\'' +
                ", response=" + response +
                ", date='" + date + '\'' +
                '}';
    }
}
