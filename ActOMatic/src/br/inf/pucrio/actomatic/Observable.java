package br.inf.pucrio.actomatic;

import android.app.Activity;

public interface Observable<T extends Observer<?>>
{
	public void addObserver(T observer);

	public boolean deleteObserver(T observer);

	public void notifyObservers(Activity activity);
}
