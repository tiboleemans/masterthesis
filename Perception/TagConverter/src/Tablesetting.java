import java.util.ArrayList;

public class Tablesetting {

	private Tag[] tags;

	public Tablesetting(Tag[] tags) {
		this.tags = tags;
	}

	/**
	 * Constructor used to convert raw duplicate tags to the unique average tag
	 * with corresponding id.
	 * 
	 * @param rawTags
	 *            rawTags to be converted to unique tags.
	 */
	public Tablesetting(ArrayList<Tag> rawTags) {
		this.tags = convertRawTags(rawTags);
		initiateTags(tags);
	}

	/**
	 * The average of all the coordinates of the raw tags with same identifiers
	 * are computed.
	 * 
	 * @param rawTags
	 */
	private Tag[] convertRawTags(ArrayList<Tag> rawTags) {
		Tag[] avTagsArray = new Tag[18];

		for (int i = 0; i < rawTags.size(); i++) {
			if (avTagsArray[rawTags.get(i).getId()] == null)
				avTagsArray[rawTags.get(i).getId()] = rawTags.get(i);
			else {
				Tag average = getAverage(avTagsArray[rawTags.get(i).getId()],
						rawTags.get(i));
				avTagsArray[rawTags.get(i).getId()] = average;
			}

		}
		ArrayList<Tag> avTagsList = new ArrayList<Tag>();
		for (int i = 0; i < avTagsArray.length; i++) {
			if (avTagsArray[i] != null)
				avTagsList.add(avTagsArray[i]);
		}
		Tag[] tags = new Tag[avTagsList.size()];

		for (int i = 0; i < avTagsList.size(); i++) {
			tags[i] = avTagsList.get(i);
			tags[i].initiateTag();
		}
		return tags;
	}

	/**
	 * Method to compute the average of the coordinates between two tags.
	 * 
	 * @param tag
	 * @param tag2
	 * @return Returns one tag with the same identifier and average of the
	 *         coordinates.
	 */
	private Tag getAverage(Tag tag, Tag tag2) {
		if (tag.getId() == tag2.getId())
			return new Tag(tag.getId(),
					(tag.getOldPos().getxCoordinaat() + tag2.getOldPos()
							.getxCoordinaat()) / 2, (tag.getOldPos()
							.getyCoordinaat() + tag2.getOldPos()
							.getyCoordinaat()) / 2, (tag.getOldPos()
							.getzCoordinaat() + tag2.getOldPos()
							.getzCoordinaat()) / 2);
		else
			return null;
	}

	public void createPositions() {
		Tag[] sortedByXCo = sortByXCo();
		createXPositions(sortedByXCo);
		Tag[] sortedByYCo = sortByYCo();
		createYPositions(sortedByYCo);
		
		controlPositions();

	}

	private void controlPositions() {
		for(int i = 0; i < tags.length; i++){
			for(int j = i + 1; j < tags.length; j++){
				if(samePos(tags[i], tags[j])){
					if(isInZoneOfX(tags[i], tags[j]) & isInZoneOfY(tags[i], tags[j])){
						if(standsOn(tags[i], tags[j])){
							tags[i].getNewPos().setzCoordinaat(tags[i].getNewPos().getzCoordinaat() + 1);
							break;
						}
						else if(standsOn(tags[j], tags[i])){
							tags[j].getNewPos().setzCoordinaat(tags[j].getNewPos().getzCoordinaat() + 1);
							break;
						}
						else if(getXDiff(tags[i], tags[j]) < getYDiff(tags[i], tags[j])){
							if(tags[i].getOldPos().getxCoordinaat() < tags[j].getOldPos().getxCoordinaat())
								augNewPosFrom("x", tags[j]);
							else
								augNewPosFrom("x", tags[i]);
						}
						else
							if(tags[i].getOldPos().getyCoordinaat() < tags[j].getOldPos().getyCoordinaat())
								augNewPosFrom("y", tags[j]);
							else 
								augNewPosFrom("y", tags[i]);
					}
					else if(isInZoneOfX(tags[i], tags[j])){
						if(tags[i].getOldPos().getyCoordinaat() < tags[j].getOldPos().getyCoordinaat())
							augNewPosFrom("y", tags[j]);
						else 
							augNewPosFrom("y", tags[i]);
					}
					else if(isInZoneOfY(tags[i], tags[j])){
						if(tags[i].getOldPos().getxCoordinaat() < tags[j].getOldPos().getxCoordinaat())
							augNewPosFrom("x", tags[j]);
						else{
							augNewPosFrom("x", tags[i]);
						}
					}
					else{
						augNewPosFrom("x", tags[j]);
						augNewPosFrom("x", tags[i]);
					}
					controlPositions();
				}
			}
		}
	}
	
	private void augNewPosFrom(String co, Tag t){
		if(co.equalsIgnoreCase("x")){
			for(int i = 0; i < tags.length; i++){
				if(tags[i].getNewPos().getxCoordinaat() > t.getNewPos().getxCoordinaat())
					tags[i].getNewPos().setxCoordinaat(tags[i].getNewPos().getxCoordinaat() + 1);
			}
		}
		t.getNewPos().setxCoordinaat(t.getNewPos().getxCoordinaat() + 1);
	}

	private boolean samePos(Tag tag, Tag tag2) {
		if(tag.getNewPos().getxCoordinaat() == tag2.getNewPos().getxCoordinaat() & tag.getNewPos().getyCoordinaat() == tag2.getNewPos().getyCoordinaat() & tag.getNewPos().getzCoordinaat() == tag2.getNewPos().getzCoordinaat())
			return true;
		return false;
	}
	
	private boolean standsOn(Tag tag, Tag tag2){
		if(tag.getOldPos().getzCoordinaat() < tag2.getOldPos().getzCoordinaat())
			return true;
		return false;
	}

	private void createXPositions(Tag[] sortedByXCo) {
		sortedByXCo[0].getNewPos().setxCoordinaat(0);
		for (int i = 1; i < sortedByXCo.length; i++) {
			if (isInZoneOfX(sortedByXCo[i], sortedByXCo[i - 1]))
				sortedByXCo[i].getNewPos().setxCoordinaat(
						sortedByXCo[i - 1].getNewPos().getxCoordinaat());
			else
				sortedByXCo[i].getNewPos().setxCoordinaat(
						sortedByXCo[i - 1].getNewPos().getxCoordinaat() + 1);
		}

	}

	private void createYPositions(Tag[] sortedByYCo) {
		sortedByYCo[0].getNewPos().setyCoordinaat(0);
		for (int i = 1; i < sortedByYCo.length; i++) {
			if (isInZoneOfY(sortedByYCo[i], sortedByYCo[i - 1]))
				sortedByYCo[i].getNewPos().setyCoordinaat(
						sortedByYCo[i - 1].getNewPos().getyCoordinaat());
			else
				sortedByYCo[i].getNewPos().setyCoordinaat(
						sortedByYCo[i - 1].getNewPos().getyCoordinaat() + 1);
		}

	}

	private boolean isInZoneOfX(Tag tag1, Tag tag2) {
		double minimumTag1 = tag1.getOldPos().getxCoordinaat()
				- tag1.getDistLeftRight();
		double maximumTag1 = tag1.getOldPos().getxCoordinaat()
				+ tag1.getDistLeftRight();

		double minimumTag2 = tag2.getOldPos().getxCoordinaat()
				- tag2.getDistLeftRight();
		double maximumTag2 = tag2.getOldPos().getxCoordinaat()
				+ tag2.getDistLeftRight();

		if ((tag1.getOldPos().getxCoordinaat() < maximumTag2 && tag1
				.getOldPos().getxCoordinaat() > minimumTag2)
				|| (tag2.getOldPos().getxCoordinaat() < maximumTag1 && tag2
						.getOldPos().getxCoordinaat() > minimumTag1))
			return true;

		return false;
	}

	private boolean isInZoneOfY(Tag tag1, Tag tag2) {
		double minimumTag1 = tag1.getOldPos().getyCoordinaat()
				- tag1.getDistDownUp();
		double maximumTag1 = tag1.getOldPos().getyCoordinaat()
				+ tag1.getDistDownUp();

		double minimumTag2 = tag2.getOldPos().getyCoordinaat()
				- tag2.getDistDownUp();
		double maximumTag2 = tag2.getOldPos().getyCoordinaat()
				+ tag2.getDistDownUp();

		if ((tag1.getOldPos().getyCoordinaat() < maximumTag2 && tag1
				.getOldPos().getyCoordinaat() > minimumTag2)
				|| (tag2.getOldPos().getyCoordinaat() < maximumTag1 && tag2
						.getOldPos().getyCoordinaat() > minimumTag1))
			return true;

		return false;
	}

	public Tag[] sortByXCo() {
		Tag[] result = this.tags;
		for (int i = 0; i < result.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < result.length; j++) {
				if (result[j].getOldPos().getxCoordinaat() < result[minIndex]
						.getOldPos().getxCoordinaat())
					minIndex = j;
			}
			Tag temp = result[i];
			result[i] = result[minIndex];
			result[minIndex] = temp;
		}
		return result;
	}

	public Tag[] sortByYCo() {
		Tag[] result = this.tags;
		for (int i = 0; i < result.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < result.length; j++) {
				if (result[j].getOldPos().getyCoordinaat() < result[minIndex]
						.getOldPos().getyCoordinaat())
					minIndex = j;
			}
			Tag temp = result[i];
			result[i] = result[minIndex];
			result[minIndex] = temp;
		}
		return result;
	}

	public Tag[] getTags() {
		return this.tags;
	}

	public void initiateTags(Tag[] tags) {
		for (int i = 0; i < tags.length; i++) {
			tags[i].initiateTag();
		}
	}

	private void setTags(Tag[] tags) {
		this.tags = tags;
	}
	
	public double getXDiff(Tag t1, Tag t2){
		return Math.abs(tags[0].getOldPos().getxCoordinaat() - tags[1].getOldPos().getxCoordinaat());
	}
	
	public double getYDiff(Tag t1, Tag t2){
		return Math.abs(tags[0].getOldPos().getyCoordinaat() - tags[1].getOldPos().getyCoordinaat());
	}

	public void calibrate() {
		if(tags.length == 2){
			double xDiff = Math.abs(tags[0].getOldPos().getxCoordinaat() - tags[1].getOldPos().getxCoordinaat());
			System.out.println("X difference: " + xDiff);
			double yDiff = Math.abs(tags[0].getOldPos().getyCoordinaat() - tags[1].getOldPos().getyCoordinaat());
			System.out.println("Y difference: " + yDiff);
			double zDiff = Math.abs(tags[0].getOldPos().getzCoordinaat() - tags[1].getOldPos().getzCoordinaat());
			System.out.println("Z difference: " + zDiff);
		}
			
	}
}
