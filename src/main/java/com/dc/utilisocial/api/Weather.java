package com.dc.utilisocial.api;


public class Weather {
	private double temperature;
	private String icon;
	private String summary;

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Weather [temperature=" + temperature + ", icon=" + icon + ", summary=" + summary + "]";
	}
	
	
}
