package br.inf.pucrio.actomatic.action;

import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.Command;

public abstract class ActionCommand<T extends Action> extends Command<T>
{
	public ActionCommand(T argument)
	{
		super( argument );
	}
}
