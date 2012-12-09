package br.inf.pucrio.actomatic.model;

public class Region extends Event
{
	private Double latitude;

	private Double longitude;

	private Double radius;

	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	public void setRadius(Double radius)
	{
		this.radius = radius;
	}

	public Double getLatitude()
	{
		return latitude;
	}

	public Double getLongitude()
	{
		return longitude;
	}

	public Double getRadius()
	{
		return radius;
	}

	@Override
	public String getObjectType()
	{
		return "Region";
	}
}
