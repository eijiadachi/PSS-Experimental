package br.inf.pucrio.hotel.dao;

import java.util.ArrayList;
import java.util.List;

import br.inf.pucrio.hotel.model.Bean;

public abstract class TransientDAOImpl<T extends Bean> implements IGenericDAO<T>
{

	private static Integer counter;

	private final List<T> repository;

	public TransientDAOImpl()
	{
		this.repository = new ArrayList<T>();
		counter = this.initCounter();
	}

	@Override
	public void add(T element)
	{
		element.setId( ++counter );
		this.getRepository().add( element );
	}

	@Override
	public void delete(T element)
	{
		this.getRepository().remove( element );
	}

	@Override
	public List<T> getAll()
	{
		return getRepository();
	}

	@Override
	public T getById(Integer id)
	{
		if (id != null)
		{
			for (T element : getRepository())
			{
				if (id.equals( element.getId() ))
				{
					return element;
				}
			}
		}

		return null;
	}

	public List<T> getRepository()
	{
		return repository;
	}

	public abstract Integer initCounter();

	@Override
	public void update(T element)
	{
		throw new IllegalStateException( "Not yet implemented" );
	}

}
