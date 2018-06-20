package beans;

public final class EnderecoPaginas {

	public EnderecoPaginas() {
		throw new UnsupportedOperationException("Esta classe não deve ser instanciada!");
	}

	private static final String REDIRECT_TRUE = "?faces-redirect=true";

	public static final String PAGINA_PRINCIPAL = "/paginas/publico/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_SESSION_TIMEOUT = "/paginas/publico/sessionTimeout.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_ERROR_CODE_500 = "/paginas/publico/500.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_CADASTRO_CLIENTE = "/paginas/publico/cadastroCliente.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_CARRINHO= "/paginas/protegido/carrinho.xhtml" + REDIRECT_TRUE;

}
