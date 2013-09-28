package br.com.benchmark.dbcontext.ext;

import java.util.Arrays;

import me.prettyprint.cassandra.model.CqlQuery;
import me.prettyprint.cassandra.serializers.LongSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.ThriftKsDef;
import me.prettyprint.cassandra.service.template.ColumnFamilyResult;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ColumnFamilyUpdater;
import me.prettyprint.cassandra.service.template.ThriftColumnFamilyTemplate;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.ddl.ColumnFamilyDefinition;
import me.prettyprint.hector.api.ddl.ComparatorType;
import me.prettyprint.hector.api.ddl.KeyspaceDefinition;
import me.prettyprint.hector.api.factory.HFactory;

/**
 * The Class CassandraManager.
 * 
 * Created to manage access to Cassandra.
 * @author rafael.camilo
 */
public class CassandraManager {

	/** The Constant COLUMN_NAME. */
	private static final String COLUMN_NAME = "value";
	
	/** The Constant COLUMN_FAMILY_NAME. */
	private static final String COLUMN_FAMILY_NAME = "ColumnFamilyName";
	
	/** The Constant SPACE_NAME. */
	private static final String SPACE_NAME = "jbossSpace";
	
	/** The Constant CLUSTER_NAME. */
	private static final String CLUSTER_NAME = "clusterCassandraJBoss";
	
	/** The Constant IP. */
	private static final String IP = "localhost:9160";

	/** The cluster. */
	private Cluster cluster;
	
	/** The keyspace. */
	private Keyspace keyspace;
	
	/** The template. */
	private ColumnFamilyTemplate<String, String> template;

	/**
	 * Inits the.
	 */
	public void init() {
		cluster = HFactory.getOrCreateCluster(CLUSTER_NAME, IP);
		KeyspaceDefinition keyspaceDefinition = cluster
				.describeKeyspace(SPACE_NAME);
		if (keyspaceDefinition == null) {
			createSchema();
		}

		keyspace = HFactory.createKeyspace(SPACE_NAME, cluster);
		template = new ThriftColumnFamilyTemplate<String, String>(keyspace,
				COLUMN_FAMILY_NAME, StringSerializer.get(),
				StringSerializer.get());

	}

	/**
	 * Drop column family.
	 *
	 * @return true, if successful
	 */
	public boolean dropColumnFamily() {
		try {
			CqlQuery<String,String,Long> cqlQuery = new CqlQuery<String,String,Long>(
					keyspace, 
					StringSerializer.get(), 
					StringSerializer.get(), 
					LongSerializer.get());
			
			cqlQuery.setQuery("truncate " + COLUMN_FAMILY_NAME);
			cqlQuery.execute();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * Creates the schema.
	 */
	private void createSchema() {
		ColumnFamilyDefinition cfDef = HFactory.createColumnFamilyDefinition(
				SPACE_NAME, COLUMN_FAMILY_NAME, ComparatorType.BYTESTYPE);
		KeyspaceDefinition newKeyspace = HFactory.createKeyspaceDefinition(
				SPACE_NAME, ThriftKsDef.DEF_STRATEGY_CLASS, 1,
				Arrays.asList(cfDef));
		cluster.addKeyspace(newKeyspace, true);
	}

	/**
	 * Read.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String read(String key) {
		try {
			ColumnFamilyResult<String, String> res = template.queryColumns(key);
			return res.getString(COLUMN_NAME);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Update.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void update(String key, String value) {
		try {
			ColumnFamilyUpdater<String, String> updater = template.createUpdater(key);
			updater.setString(COLUMN_NAME, value);
			template.update(updater);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Delete.
	 *
	 * @param key the key
	 */
	public void delete(String key) {
		try {
			template.deleteColumn(key, COLUMN_NAME);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
