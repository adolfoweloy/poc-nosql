package br.com.benchmark.sampler.cassd;

import br.com.benchmark.dbcontext.api.DBContext;
import br.com.benchmark.dbcontext.impl.CassandraContext;
import br.com.benchmark.domain.User;
import br.com.benchmark.jmeter.SimpleSampleResult;
import br.com.benchmark.jmeter.TemplateSampler;
import br.com.benchmark.sampler.CassandraIDList;

/**
 * The Class SamplerCassandraGET.
 * @author astronauta
 */
public class SamplerCassandraGET extends TemplateSampler {
	
	/**
	 * Instantiates a new sampler cassandra get.
	 */
	public SamplerCassandraGET() {
		super("cassandra GET");
	}
	
	/* (non-Javadoc)
	 * @see br.com.benchmark.jmeter.TemplateSampler#execute()
	 */
	@Override
	protected SimpleSampleResult execute() {
		CassandraContext context = CassandraContext.getInstance();
		if (CassandraIDList.getInstance().getRandomIdSize() == 0) {
			return new SimpleSampleResult("400", "empty data entry storage", true);			
		}
		Integer tokenId = CassandraIDList.getInstance().getRandomId();

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
