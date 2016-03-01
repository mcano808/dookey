package rdfwriter;

import org.openrdf.model.IRI;
import org.openrdf.model.Namespace;
import org.openrdf.model.impl.SimpleNamespace;
import org.openrdf.model.impl.SimpleValueFactory;
//mmsi, timestamp, lat, lon, cog, sog, heading, navstat, imo, name, callsign, type,a-d, draught, destination, eta
public final class AISOnto {
	private static final String NAMESPACE = "http://www.inovex.com/sp/aishub/ontology/";
	public static final String PREFIX = "ais";
	public static final Namespace NS = new SimpleNamespace(PREFIX, NAMESPACE);

	static SimpleValueFactory fac = SimpleValueFactory.getInstance();
	 
	public static IRI MMSI = fac.createIRI(NAMESPACE, "MMSI");
	public static IRI TIME = fac.createIRI(NAMESPACE, "time");
	public static IRI LAT = fac.createIRI(NAMESPACE, "latitude");
	public static IRI LON = fac.createIRI(NAMESPACE, "longitude");
	public static IRI COG = fac.createIRI(NAMESPACE, "cog");
	public static IRI SOG = fac.createIRI(NAMESPACE, "sog");
	public static IRI HEADING = fac.createIRI(NAMESPACE, "heading");
	public static IRI NAVSTAT = fac.createIRI(NAMESPACE, "navstat");
	public static IRI IMO = fac.createIRI(NAMESPACE, "imo");
	public static IRI NAME = fac.createIRI(NAMESPACE, "name");
	public static IRI CALLSIGN = fac.createIRI(NAMESPACE, "callsign");
	public static IRI TYPE = fac.createIRI(NAMESPACE, "type");
	public static IRI A = fac.createIRI(NAMESPACE, "a");
	public static IRI B = fac.createIRI(NAMESPACE, "b");
	public static IRI C = fac.createIRI(NAMESPACE, "c");
	public static IRI D = fac.createIRI(NAMESPACE, "d");
	public static IRI DRAUGHT = fac.createIRI(NAMESPACE, "draught");
	public static IRI DESTINATION = fac.createIRI(NAMESPACE, "destination");
	public static IRI ETA = fac.createIRI(NAMESPACE, "eta");
}
