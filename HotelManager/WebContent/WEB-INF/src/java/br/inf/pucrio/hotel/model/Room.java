package br.inf.pucrio.hotel.model;

public class Room extends Bean
{
	public enum RoomType
	{
		STANDARD
		{
			@Override
			public String getCategory()
			{
				return "Standard";
			}

			@Override
			public Integer getMaximumCapacity()
			{
				return 2;
			}

			@Override
			public Float getPrice()
			{
				return 268.00f;
			}
		},
		WOODLAND
		{
			@Override
			public String getCategory()
			{
				return "Vista Bosque";
			}

			@Override
			public Integer getMaximumCapacity()
			{
				return 4;
			}

			@Override
			public Float getPrice()
			{
				return 315.00f;
			}
		},
		VALLEY
		{
			@Override
			public String getCategory()
			{
				return "Vista Vale";
			}

			@Override
			public Integer getMaximumCapacity()
			{
				return 4;
			}

			@Override
			public Float getPrice()
			{
				return 353.00f;
			}
		},
		SUITE
		{
			@Override
			public String getCategory()
			{
				return "Suite";
			}

			@Override
			public Integer getMaximumCapacity()
			{
				return 2;
			}

			@Override
			public Float getPrice()
			{
				return 498.00f;
			}
		};
		public abstract String getCategory();

		public abstract Integer getMaximumCapacity();

		public abstract Float getPrice();
	}

	private final RoomType type;

	public Room(RoomType type)
	{
		this.type = type;
	}

	public String getCategory()
	{
		return type.getCategory();
	}

	public Integer getMaximumCapacity()
	{
		return type.getMaximumCapacity();
	}

	public Float getPrice()
	{
		return type.getPrice();
	}

	@Override
	public String toString()
	{
		String toString = String.format( "%s - %s", getId(), getCategory() );
		return toString;
	}
}
