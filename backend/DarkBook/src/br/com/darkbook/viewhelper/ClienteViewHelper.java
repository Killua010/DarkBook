package br.com.darkbook.viewhelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.darkbook.dominio.CartaoCredito;
import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.dominio.Endereco;
import br.com.darkbook.dominio.EnderecoEntrega;
import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.util.JSONUtil;
import br.com.darkbook.util.Resultado;
import br.com.darkbook.viewhelper.helper.IHelper;
import br.com.darkbook.viewhelper.helper.cliente.AlterarCliente;
import br.com.darkbook.viewhelper.helper.cliente.ConsultarCliente;
import br.com.darkbook.viewhelper.helper.cliente.SalvarCliente;

public class ClienteViewHelper implements IViewHelper {
	
	private Map<String, IHelper> helperView;
	
	private IHelper helper;
	
	public ClienteViewHelper() {
		helperView = new HashMap<String, IHelper>();
		helperView.put("CONSULTAR", new ConsultarCliente());
		helperView.put("SALVAR", new SalvarCliente());
		helperView.put("ALTERAR", new AlterarCliente());
	}

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		JSONObject clienteJson = null;
		String linha;
		

		try {
			clienteJson = new JSONObject(JSONUtil.ParseString(request.getReader()));
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		
		helper = helperView.get(request.getParameter("operacao"));
    	
		return helper.getEntidade(clienteJson);
	}

	@Override
	public void setEntidade(Resultado resultado, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
    	
		helper.setEntidade(resultado, response);

	}

}
