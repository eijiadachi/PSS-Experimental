package br.inf.pucrio.actomatic.model;

public class RegionEvent extends Event
{

	private Double latitude;

	private Double longitude;

	private Double radius;

	public Double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	public Double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	public Double getRadius()
	{
		return radius;
	}

	public void setRadius(Double radius)
	{
		this.radius = radius;
	}

	@Override
	public String getObjectType()
	{
		return "RegionEvent";
	}

}
