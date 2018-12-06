package br.com.darkbook.command;

import br.com.darkbook.fachada.Fachada;
import br.com.darkbook.fachada.IFachada;

public abstract class AbstractCommand implements ICommand {
	protected IFachada fachada = new Fachada();

}
