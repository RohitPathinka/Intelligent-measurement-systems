
public class Data {
		private String ID;
		private String location;
		private double latitude;
		private double longitude;
		
		public Data(String ID, String location, double latitude, double longitude) {
			this.ID =ID;
			this.location = location;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		
	}
