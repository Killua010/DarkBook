package br.com.darkbook.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
	public void executar(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
