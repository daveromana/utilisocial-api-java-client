package com.dc.utilisocial.api;

import java.util.Set;

public class Group {
	private String name;
	private Region region;
	private String phone;
	private Set<IvrTemplate> ivrTemplates;
    private Set<Region> regions;

	public Group() {
	}

	public Group(String name) {
		this.name = name;
	}

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<IvrTemplate> getIvrTemplates() {
		return ivrTemplates;
	}

	public void setIvrTemplates(Set<IvrTemplate> ivrTemplates) {
		this.ivrTemplates = ivrTemplates;
	}

	public IvrTemplate getActiveIvrTemplate() {
		for (IvrTemplate template : this.ivrTemplates) {
			if (template.isActive())
				return template;
		}

		throw new IllegalStateException("Group must always have one active template");
	}

	@Override
	public String toString() {
		return "Group [name=" + name + ", region=" + region + ", phone=" + phone + "]";
	}

}
