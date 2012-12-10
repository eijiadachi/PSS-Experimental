package br.inf.pucrio.actomatic.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.inf.pucrio.actomatic.model.Entity;

public class TransientDAO<T extends Entity> implements IAbstractDAO<T>
{
	private final Map<Integer, T> map;

	private Integer idCounter;

	protected TransientDAO()
	{
		map = new TreeMap<Integer, T>();
		idCounter = 0;
	}

	public void add(T t)
	{
		++idCounter;
		t.setId( idCounter );

		this.update( t );
	}

	public boolean delete(Integer id)
	{
		T result = map.remove( id );
		return result != null;
	}

	public void update(T t)
	{
		map.put( idCounter, t );
	}

	public List<T> listAll()
	{
		Collection<T> values = map.values();

		List<T> result = new ArrayList<T>( values );

		return result;
	}

	public T getById(Integer id)
	{
		T result = map.get( id );
		return result;
	}

	public void addAll(List<T> list)
	{
		for (T t : list)
		{
			this.add( t );
		}
	}

}
