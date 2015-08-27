public class OldPosition {

	private double xCoordinaat;
	private double yCoordinaat;
	private double zCoordinaat;

	public OldPosition(double xCoordinaat, double yCoordinaat,
			double zCoordinaat) {
		this.xCoordinaat = xCoordinaat;
		this.yCoordinaat = yCoordinaat;
		this.zCoordinaat = zCoordinaat;
	}

	public double getxCoordinaat() {
		return xCoordinaat;
	}

	public void setxCoordinaat(int xCoordinaat) {
		this.xCoordinaat = xCoordinaat;
	}

	public double getyCoordinaat() {
		return yCoordinaat;
	}

	public void setyCoordinaat(int yCoordinaat) {
		this.yCoordinaat = yCoordinaat;
	}

	public double getzCoordinaat() {
		return zCoordinaat;
	}

	public void setzCoordinaat(int zCoordinaat) {
		this.zCoordinaat = zCoordinaat;
	}

	public String toString() {
		return "position(" + xCoordinaat + "," + yCoordinaat + ","
				+ zCoordinaat + ")";
	}

}
