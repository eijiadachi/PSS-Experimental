package br.inf.pucrio.hotel.dao;

import java.util.ArrayList;
import java.util.List;

import br.inf.pucrio.hotel.model.Bean;

public class TransientDAOImpl<T extends Bean> implements IGenericDAO<T>
{

	public TransientDAOImpl()
	{
		this.repository = new ArrayList<T>();
	}

	private final List<T> repository;

	@Override
	public T getById(Integer id)
	{
		for (T element : getRepository())
		{
			if (id.equals( element.getId() ))
			{
				return element;
			}
		}

		return null;
	}

	@Override
	public List<T> getAll()
	{
		return getRepository();
	}

	@Override
	public void delete(T element)
	{
		this.getRepository().remove( element );
	}

	@Override
	public void update(T element)
	{
		throw new IllegalStateException( "Not yet implemented" );
	}

	@Override
	public void add(T element)
	{
		this.getRepository().add( element );
	}

	public List<T> getRepository()
	{
		return repository;
	}

}
