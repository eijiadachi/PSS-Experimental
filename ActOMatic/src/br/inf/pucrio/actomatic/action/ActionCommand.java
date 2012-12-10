package br.inf.pucrio.actomatic.action;

import android.app.Activity;
import br.inf.pucrio.actomatic.Observer;
import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.CommandArgument;
import br.inf.pucrio.actomatic.model.Entity;

public abstract class ActionCommand<T extends Action> extends Entity implements
		Observer<CommandArgument>
{
	private final T argument;

	public ActionCommand(T argument)
	{
		this.argument = argument;
	}

	public abstract void update(Activity activity);

	public T getArgument()
	{
		return argument;
	}

}
