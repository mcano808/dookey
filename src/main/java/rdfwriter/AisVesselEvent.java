package rdfwriter;

import org.openrdf.model.Literal;
import org.openrdf.model.impl.SimpleValueFactory;
import org.openrdf.model.vocabulary.XMLSchema;

public class AisVesselEvent {
	
	private static SimpleValueFactory factory = SimpleValueFactory.getInstance();
	
	private Literal mmsi;
	private Literal time;
	private Literal longitude;
	private Literal latitude;
	private Literal cog;
	private Literal sog;
	private Literal heading;
	private Literal navstat;
	private Literal pac;
	private Literal rot;
	private Literal imo;
	private Literal name;
	private Literal callsign;
	private Literal type;
	private Literal device;
	private Literal a;
	private Literal b;
	private Literal c;
	private Literal d;
	private Literal draught;
	private Literal destination;
	private Literal eta;
	
	//@param {String} data The csv string in AISHub format to be used in making this vessel.
	public AisVesselEvent (String dataLine){
		//make sure string is not null
		if(dataLine != null){
			String splitOn = ",";
			String[] data = dataLine.split(splitOn);
			System.out.println("observation count: " + data.length);
			//If length = 20 we have a destination that had a city,Country value.
			if(data.length == 20){
				//set destination equal to 17 + 18 
				destination = factory.createLiteral((data[16] + ", " + data[17]), XMLSchema.STRING);
				eta = factory.createLiteral(data [18], XMLSchema.STRING);

			}else{
				//set destination regular.
				destination = factory.createLiteral(data[16], XMLSchema.STRING);
				eta = factory.createLiteral(data[17], XMLSchema.STRING);
			}
			//Set all the other attributes
			mmsi = factory.createLiteral(data[0], XMLSchema.STRING);
			time = factory.createLiteral(data[1], XMLSchema.STRING);
			latitude = factory.createLiteral(data[2], XMLSchema.STRING);
			longitude = factory.createLiteral(data[3], XMLSchema.STRING);
			cog = factory.createLiteral(data[4], XMLSchema.STRING);
			sog = factory.createLiteral(data[5], XMLSchema.STRING);
			heading = factory.createLiteral(data[6], XMLSchema.STRING);
			imo = factory.createLiteral(data[7], XMLSchema.STRING);
			name = factory.createLiteral(data[8], XMLSchema.STRING);
			callsign = factory.createLiteral(data[9], XMLSchema.STRING);
			type = factory.createLiteral(data[10], XMLSchema.STRING);
			a = factory.createLiteral(data[11], XMLSchema.STRING);
			b = factory.createLiteral(data[12], XMLSchema.STRING);
			c = factory.createLiteral(data[13], XMLSchema.STRING);
			d = factory.createLiteral(data[14], XMLSchema.STRING);
			draught = factory.createLiteral(data[15], XMLSchema.STRING);

		}
			
	}

	/**
	 * @return the factory
	 */
	public static SimpleValueFactory getFactory() {
		return factory;
	}

	/**
	 * @return the mmsi
	 */
	public Literal getMmsi() {
		return mmsi;
	}

	/**
	 * @return the time
	 */
	public Literal getTime() {
		return time;
	}

	/**
	 * @return the longitude
	 */
	public Literal getLongitude() {
		return longitude;
	}

	/**
	 * @return the latitude
	 */
	public Literal getLatitude() {
		return latitude;
	}

	/**
	 * @return the cog
	 */
	public Literal getCog() {
		return cog;
	}

	/**
	 * @return the sog
	 */
	public Literal getSog() {
		return sog;
	}

	/**
	 * @return the heading
	 */
	public Literal getHeading() {
		return heading;
	}

	/**
	 * @return the navstat
	 */
	public Literal getNavstat() {
		return navstat;
	}

	/**
	 * @return the pac
	 */
	public Literal getPac() {
		return pac;
	}

	/**
	 * @return the rot
	 */
	public Literal getRot() {
		return rot;
	}

	/**
	 * @return the imo
	 */
	public Literal getImo() {
		return imo;
	}

	/**
	 * @return the name
	 */
	public Literal getName() {
		return name;
	}

	/**
	 * @return the callsign
	 */
	public Literal getCallsign() {
		return callsign;
	}

	/**
	 * @return the type
	 */
	public Literal getType() {
		return type;
	}

	/**
	 * @return the device
	 */
	public Literal getDevice() {
		return device;
	}

	/**
	 * @return the a
	 */
	public Literal getA() {
		return a;
	}

	/**
	 * @return the b
	 */
	public Literal getB() {
		return b;
	}

	/**
	 * @return the c
	 */
	public Literal getC() {
		return c;
	}

	/**
	 * @return the d
	 */
	public Literal getD() {
		return d;
	}

	/**
	 * @return the draught
	 */
	public Literal getDraught() {
		return draught;
	}

	/**
	 * @return the destination
	 */
	public Literal getDestination() {
		return destination;
	}

	/**
	 * @return the eta
	 */
	public Literal getEta() {
		return eta;
	}

}
