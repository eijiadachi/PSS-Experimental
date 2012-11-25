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
		map.put( ++idCounter, t );
	}

	public void delete(T t)
	{
		map.remove( t );
	}

	public void update(T t)
	{
		Integer id = t.getId();

		map.put( id, t );
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

}
