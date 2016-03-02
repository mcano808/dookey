package rdfwriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.util.Calendar;

import org.openrdf.OpenRDFException;
import org.openrdf.model.IRI;
import org.openrdf.model.Literal;
import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.Rio;
import org.openrdf.sail.memory.MemoryStore;

import info.aduna.iteration.Iterations;

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
			String headers = dataCsv.readLine();
			System.out.println("headers : " + headers);
			while((dataLine = dataCsv.readLine()) != null){
				//System.out.println("Data line: " + dataLine);
				AisVesselEvent event = new AisVesselEvent(dataLine);
				makeObservation(nameSpace, conn, f, event);				
				//mmsi, timestamp, lat, lon, cog, sog, heading, navstat, imo, name, callsign, type,a-d, draught, destination, eta
			}
			//Get everything from the local store.
			RepositoryResult<Statement> statements = conn.getStatements(null, null, null, true);
			//Change Results to model so can print out easier.
			Model model = Iterations.addAll(statements, new LinkedHashModel());
			//Print out to document
			try{
				writeDocument(model);
			}catch(FileNotFoundException e){
				System.out.println("Problem with writing file");
				e.printStackTrace();
			}
			//Print out model using turtle format.
			//Rio.write(model, System.out, RDFFormat.TURTLE);
			System.out.println("Done writing file");
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
	private static String makePath (){
		Calendar cal = Calendar.getInstance();
		String fileBase = "dataOut/";
		String filePath = null;
		Integer year;
		Integer month;
		String monthStr;
		Integer day_of_month;
		
		//Get a date set up so we can set the path based on YYYY/MMM/DD/filename.rdf
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		monthStr = new DateFormatSymbols().getShortMonths()[month];
		day_of_month = cal.get(Calendar.DAY_OF_MONTH);
		
		filePath = year + "/" + monthStr + "/" + day_of_month + "/";
		//create the file patch
		try {
			Files.createDirectories(Paths.get(fileBase + filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem making the file path");
			e.printStackTrace();
		}
		return fileBase + filePath;
	}
	// @param {Model} statements The RDF statements in a model that are to be written to file.
	private static void writeDocument (Model statements) throws FileNotFoundException{
		
		String fileName ="output.rdf";
		
		FileOutputStream out = new FileOutputStream(makePath() + fileName);
		org.openrdf.rio.RDFWriter writer = Rio.createWriter(RDFFormat.RDFXML, out);

		try{
			//Start the writer
			writer.startRDF();
			//Loop through and write each statement
			for (Statement st: statements){
				writer.handleStatement(st);
			}
			//Close writer
			writer.endRDF();
		}catch(RDFHandlerException e){
			e.printStackTrace();
		}
	}
	private static  void makeObservation ( String nameSpace, RepositoryConnection conn,ValueFactory f, AisVesselEvent event){
		//Make the Uri
		IRI subject = f.createIRI(nameSpace, event.getImo().toString());
		//TODO We should just add statements to a local model for use in the writer later instead
		//of writing to local storage and retrieving later.
		//Do the writing
		//TODO should always have a type and label with it (is type vessel and label should be short name)
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
	private static void writeLatitude(RepositoryConnection conn, IRI subject, Literal latitude){
		conn.add(subject, AISOnto.LAT, latitude);
	}
	private static void writeLongitude(RepositoryConnection conn, IRI subject, Literal longitude){
		conn.add(subject, AISOnto.LON, longitude);
	}
	private static void writeCOG(RepositoryConnection conn, IRI subject, Literal cog){
		conn.add(subject, AISOnto.COG, cog);
	}
	private static void writeSOG(RepositoryConnection conn, IRI subject, Literal sog){
		conn.add(subject, AISOnto.SOG, sog);
	}
	private static void writeHeading(RepositoryConnection conn, IRI subject, Literal heading){
		conn.add(subject, AISOnto.HEADING, heading);
	}
	private static void writeNavstat(RepositoryConnection conn, IRI subject, Literal navstat){
		conn.add(subject, AISOnto.NAVSTAT, navstat);
	}
	private static void writeIMO(RepositoryConnection conn, IRI subject, Literal imo){
		conn.add(subject, AISOnto.IMO, imo);
	}
	private static void writeName(RepositoryConnection conn, IRI subject, Literal name){
		conn.add(subject, AISOnto.NAME, name);
	}
	private static void writeCallsign(RepositoryConnection conn, IRI subject, Literal callsign){
		conn.add(subject, AISOnto.CALLSIGN, callsign);
	}
	private static void writeType(RepositoryConnection conn, IRI subject, Literal type){
		conn.add(subject, AISOnto.TYPE, type);
	}
	private static void writeA(RepositoryConnection conn, IRI subject, Literal a){
		conn.add(subject, AISOnto.A, a);
	}
	private static void writeB(RepositoryConnection conn, IRI subject, Literal b){
		conn.add(subject, AISOnto.B, b);
	}
	private static void writeC(RepositoryConnection conn, IRI subject, Literal c){
		conn.add(subject, AISOnto.C, c);
	}
	private static void writeD(RepositoryConnection conn, IRI subject, Literal d){
		conn.add(subject, AISOnto.D, d);
	}
	private static void writeDraught(RepositoryConnection conn, IRI subject, Literal draught){
		conn.add(subject, AISOnto.DRAUGHT, draught);
	}
	private static void writeDestination(RepositoryConnection conn, IRI subject, Literal destination){
		conn.add(subject, AISOnto.DESTINATION, destination);
	}
	private static void writeETA(RepositoryConnection conn, IRI subject, Literal eta){
		conn.add(subject, AISOnto.ETA, eta);
	}
}
