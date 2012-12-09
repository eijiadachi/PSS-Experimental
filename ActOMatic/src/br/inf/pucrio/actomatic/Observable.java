package br.inf.pucrio.actomatic;

import android.app.Activity;
import br.inf.pucrio.actomatic.model.Command;

public interface Observable<T extends Command<?>>
{
	public void addObserver(T observer);

	public boolean deleteObserver(T observer);

	public void notifyObservers(Activity activity);
}
