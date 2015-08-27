public class NewPosition {

	private int xCoordinaat;
	private int yCoordinaat;
	private int zCoordinaat;

	public NewPosition() {
	}

	public NewPosition(int xCoordinaat, int yCoordinaat, int zCoordinaat) {
		this.xCoordinaat = xCoordinaat;
		this.yCoordinaat = yCoordinaat;
		this.zCoordinaat = zCoordinaat;
	}

	public int getxCoordinaat() {
		return xCoordinaat;
	}

	public void setxCoordinaat(int xCoordinaat) {
		this.xCoordinaat = xCoordinaat;
	}

	public int getyCoordinaat() {
		return yCoordinaat;
	}

	public void setyCoordinaat(int yCoordinaat) {
		this.yCoordinaat = yCoordinaat;
	}

	public int getzCoordinaat() {
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
