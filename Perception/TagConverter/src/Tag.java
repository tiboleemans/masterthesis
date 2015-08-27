public class Tag {

	private int id;
	private double distLeftRight;
	private double distDownUp;
	private NewPosition newPos;
	private OldPosition oldPos;
	private String name;

	public Tag(String id, String xCoordinaat, String yCoordinaat,
			String zCoordinaat) {
		this.id = Integer.parseInt(id);
		this.oldPos = new OldPosition(Double.parseDouble(xCoordinaat),
				Double.parseDouble(yCoordinaat),
				Double.parseDouble(zCoordinaat));
		this.newPos = new NewPosition();
		initiateTag();
	}

	public Tag(int id, double xCoordinaat, double yCoordinaat,
			double zCoordinaat) {
		this.id = id;
		this.oldPos = new OldPosition(xCoordinaat, yCoordinaat, zCoordinaat);
		this.newPos = new NewPosition();
		initiateTag();
	}

	public Tag(int id, double xCoordinaat, double yCoordinaat,
			double zCoordinaat, double distLeftRight, double distDownUp) {
		this.id = id;
		this.oldPos = new OldPosition(xCoordinaat, yCoordinaat, zCoordinaat);
		this.distLeftRight = distLeftRight;
		this.distDownUp = distDownUp;
		this.newPos = new NewPosition();
	}

	public void initiateTag() {
		switch (getId()) {
		case 0: // fork
			this.distLeftRight = 0.01;
			this.distDownUp = 0.1;
			this.name = "fork";
			break;
		case 1: // knife
			this.distLeftRight = 0.01;
			this.distDownUp = 0.1;
			this.name = "knife";
			break;
		case 2: // spoon
			this.distLeftRight = 0.01;
			this.distDownUp = 0.1;
			this.name = "spoon";
			break;
		case 3: // small knife
			this.distLeftRight = 0.1;
			this.distDownUp = 0.01;
			this.name = "smallKnife";
			break;		
		case 4: // plate
			this.distLeftRight = 0.11;
			this.distDownUp = 0.11;
			this.name = "plate";
			break;
		case 5: //bowl
			this.distLeftRight = 0.07;
			this.distDownUp = 0.07;
			this.name = "bowl";
			break;
		case 6: //saucer
			this.distLeftRight = 0.08;
			this.distDownUp = 0.08;
			this.name = "saucer";
			break;
		case 7: //pastaPlate
			this.distLeftRight = 0.1;
			this.distDownUp = 0.1;
			this.name = "pastaPlate";
			break;
		case 8: //soupPlate
			this.distLeftRight = 0.11;
			this.distDownUp = 0.11;
			this.name = "soupPlate";
			break;
		case 9: // waterGlass
			this.distLeftRight = 0.05;
			this.distDownUp = 0.05;
			this.name = "waterGlass";
			break;
		case 10: //wineGlass
			this.distLeftRight = 0.03;
			this.distDownUp = 0.03;
			this.name = "wineGlass";
			break;
		case 11: //teaCup
			this.distLeftRight = 0.05;
			this.distDownUp = 0.05;
			this.name = "teaCup";
			break;
		case 13: //fork2
			this.distLeftRight = 0.01;
			this.distDownUp = 0.1;
			this.name = "fork2";
			break;
		case 14: //knife2
			this.distLeftRight = 0.01;
			this.distDownUp = 0.1;
			this.name = "knife2";
			break;

		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OldPosition getOldPos() {
		return this.oldPos;
	}

	public NewPosition getNewPos() {
		return this.newPos;
	}

	public double getDistLeftRight() {
		return distLeftRight;
	}

	public double getDistDownUp() {
		return distDownUp;
	}
	
	public String getName(){
		return this.name;
	}

	public String toString() {
		return "position(" + newPos.getxCoordinaat() + ", "
				+ newPos.getyCoordinaat() + ", " + newPos.getzCoordinaat()
				+ ", " + getName() + ").";
	}
}
