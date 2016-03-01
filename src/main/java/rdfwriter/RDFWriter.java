package rdfwriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.openrdf.OpenRDFException;
import org.openrdf.model.IRI;
import org.openrdf.model.Literal;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;

public class RDFWriter {

	public static void main(String[] args) throws OpenRDFException {
		
		BufferedReader dataCsv = null;
		String nameSpace = "http://inovex.com/ontology/aishub";
		//Create repo
		
		Repository repo = new SailRepository(new MemoryStore());
		
		//Initilize Repo
		try{
			repo.initialize();
		}catch (RepositoryException e){
			e.printStackTrace();
		}
		
		ValueFactory f = repo.getValueFactory();

		//Repo connection
		RepositoryConnection conn = null;
		try{
			conn = repo.getConnection();
		}catch(RepositoryException e){
			e.printStackTrace();
		}
		
		
		try{
			String dataLine;
			dataCsv = new BufferedReader(new FileReader("data.csv"));
			while((dataLine = dataCsv.readLine()) != null){
				System.out.println("Data line: " + dataLine);
				String splitOn = ",";
				String[] data = dataLine.split(splitOn);
				AisVesselEvent event = new AisVesselEvent(dataLine);
				makeObservation(nameSpace, conn, f, event);				
				//mmsi, timestamp, lat, lon, cog, sog, heading, navstat, imo, name, callsign, type,a-d, draught, destination, eta
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(dataCsv != null){
					dataCsv.close();
				}
			}catch(IOException dataException){
				dataException.printStackTrace();
			}
			conn.close();
		}
		//For the RDF object to write based on ontology.  Using hard coded values for now.
		//TODO We should really just read in the ontology and write dynamically.
		
		
	}
	
	private static void makeObservation ( String nameSpace, RepositoryConnection conn,ValueFactory f, AisVesselEvent event){
		//Make the Uri
		IRI subject = f.createIRI(nameSpace, event.getImo().toString());
		
		//Do the writing
		writeMMSI(conn, subject, event.getMmsi());
		writeTimeStamp(conn, subject, event.getTime());
		writeLatitude(conn, subject, event.getLatitude());
		writeLongitude(conn, subject, event.getLongitude());
		writeCOG(conn, subject, event.getCog());
		writeSOG(conn, subject, event.getSog());
		writeHeading(conn, subject, event.getHeading());
		writeNavstat(conn, subject, event.getNavstat());
		writeIMO(conn, subject, event.getImo());
		writeName(conn, subject, event.getName());
		writeCallsign(conn, subject, event.getCallsign());
		writeType(conn, subject, event.getType());
		writeA(conn, subject, event.getA());
		writeB(conn, subject, event.getB());
		writeC(conn, subject, event.getC());
		writeD(conn, subject, event.getD());
		writeDraught(conn, subject, event.getDraught());
		writeDestination(conn, subject, event.getDestination());
		writeETA(conn,  subject, event.getEta());
		
		
	}
	private static void writeMMSI (RepositoryConnection conn, IRI subject, Literal mmsi){
		conn.add(subject, AISOnto.MMSI, mmsi);
	}
	private static void writeTimeStamp(RepositoryConnection conn, IRI subject, Literal timestamp){
		conn.add(subject, AISOnto.TIME, timestamp);
	}
	private static void writeLatitude(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeLongitude(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeCOG(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeSOG(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeHeading(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeNavstat(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeIMO(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeName(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeCallsign(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeType(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeA(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeB(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeC(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeD(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeDraught(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeDestination(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
	private static void writeETA(RepositoryConnection conn, IRI subject, Literal timestamp){
		
	}
}
