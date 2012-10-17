package br.inf.pucrio.hotel.dao;

import java.util.List;

import br.inf.pucrio.hotel.model.Bean;

public interface IGenericDAO<T extends Bean>
{
	public abstract T getById(Integer id);

	public abstract List<T> getAll();

	public abstract void add(T element);

	public abstract void delete(T element);

	public abstract void update(T element);

}
