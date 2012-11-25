package br.inf.pucrio.actomatic.dao;

import java.util.List;

import br.inf.pucrio.actomatic.model.Entity;

public interface IAbstractDAO<T extends Entity>
{
	public void add(T t);

	public void delete(T t);

	public void update(T t);

	public List<T> listAll();

	public T getById(Integer id);
}
