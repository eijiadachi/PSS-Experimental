package br.inf.pucrio.actomatic.action;

import br.inf.pucrio.actomatic.MainActivity;
import br.inf.pucrio.actomatic.Observer;
import br.inf.pucrio.actomatic.model.Action;
import br.inf.pucrio.actomatic.model.Command;
import br.inf.pucrio.actomatic.model.CommandArgument;

public abstract class ActionCommand<T extends Action> extends Command<T> implements
		Observer<CommandArgument>
{
	public ActionCommand(T argument)
	{
		super( argument );
	}

	public abstract void update(MainActivity activity);

}
