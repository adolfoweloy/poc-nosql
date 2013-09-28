package br.com.benchmark.sampler.dgrid;

import br.com.benchmark.dbcontext.api.DBContext;
import br.com.benchmark.dbcontext.impl.DatagridContext;
import br.com.benchmark.domain.User;
import br.com.benchmark.jmeter.SimpleSampleResult;
import br.com.benchmark.jmeter.TemplateSampler;
import br.com.benchmark.sampler.DatagridIDList;

/**
 * The Class SamplerDatagridGET.
 * @author astronauta
 */
public class SamplerDatagridGET extends TemplateSampler {

	/**
	 * Instantiates a new sampler datagrid get.
	 */
	public SamplerDatagridGET() {
		super("datagrid GET");
	}

	/* (non-Javadoc)
	 * @see br.com.benchmark.jmeter.TemplateSampler#execute()
	 */
	@Override
	protected SimpleSampleResult execute() {
		DBContext context = DatagridContext.getInstance();
		if (DatagridIDList.getInstance().getRandomIdSize() == 0) {
			return new SimpleSampleResult("400", "empty data entry storage", true);			
		}
		Integer tokenId = DatagridIDList.getInstance().getRandomId();

		User usuario = context.get(tokenId.toString(), User.class);
		if (usuario == null) {
			return new SimpleSampleResult("400", "value = null", true);
		}

		String[] result = usuario.getNome().split(":");

		if (result.length != 2) {
			return new SimpleSampleResult("400", "nome invalido ", true);
		}

		if (result[0].equals(DBContext.CONTENT) && result[1].equals(tokenId.toString())) {
			return new SimpleSampleResult("200", "sucesso", false);
		} else {
			return new SimpleSampleResult("400", "conteudo recuperado eh diferente do conteudo gravado", false);
		}
	}

}
