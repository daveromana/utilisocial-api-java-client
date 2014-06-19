package com.dc.utilisocial.api;

import java.util.LinkedHashSet;
import java.util.Set;

public class Audit implements Comparable<Audit> {
	private String userName;
	private String message;
	private double latitude;
	private double longitude;
	private String phoneNumber;
	private AuditType type;
	private String imageUrl = "";
	private Weather weather;
	private String date;
	private Set<Group> groups = new LinkedHashSet<>();
	private String userProfileImageUrl = "";
	private Long id;
	private String tweetId;
	private String keyword;
	private LocationType locationType;
	private Set<Sms> smsMessages;
	private State state;
    private String userDetails;

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public AuditType getType() {
		return type;
	}

	public void setType(AuditType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}

	public void setUserProfileImageUrl(String userProfileImageUrl) {
		this.userProfileImageUrl = userProfileImageUrl;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Sms> getSmsMessages() {
		return smsMessages;
	}

	public void setSmsMessages(Set<Sms> smsMessages) {
		this.smsMessages = smsMessages;
	}
	
    public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
    public int compareTo(Audit audit) {
        if (getId() < audit.getId()) {
            return -1;

        } else if (getId() == audit.getId()) {
            return 0;

        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Audit{" +
                "userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type=" + type +
                ", imageUrl='" + imageUrl + '\'' +
                ", weather=" + weather +
                ", date='" + date + '\'' +
                ", groups=" + groups +
                ", userProfileImageUrl='" + userProfileImageUrl + '\'' +
                ", id=" + id +
                ", tweetId='" + tweetId + '\'' +
                ", keyword='" + keyword + '\'' +
                ", locationType=" + locationType +
                ", smsMessages=" + smsMessages +
                ", state=" + state +
                ", userDetails='" + userDetails + '\'' +
                '}';
    }
}
