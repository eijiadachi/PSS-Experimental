package br.inf.pucrio.hotel.dao;

import br.inf.pucrio.hotel.model.Room;
import br.inf.pucrio.hotel.model.Room.RoomType;

public class RoomDAO extends TransientDAOImpl<Room>
{
	private static Integer counter = 0;

	public RoomDAO()
	{
		this.addRoom( RoomType.STANDARD, 2 );
		this.addRoom( RoomType.WOODLAND, 3 );
		this.addRoom( RoomType.VALLEY, 3 );
		this.addRoom( RoomType.SUITE, 2 );
	}

	@Override
	public void add(Room room)
	{
		room.setId( ++counter );
		super.add( room );
	}

	private void addRoom(RoomType type, Integer quantity)
	{
		for (int i = 0; i < quantity; i++)
		{
			this.add( new Room( type ) );
		}
	}
}
