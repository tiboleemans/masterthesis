
public class Background {

	private BackgroundElement[] bgElmnts;
	private final double multiplier = 0.85;
	
	public Background(BackgroundElement[] list){
		bgElmnts = list;
	}
	
	public Background(){
		BackgroundElement[] bgElements = new BackgroundElement[15];

		bgElements[0] = new BackgroundElement("fork", 2);
		bgElements[1] = new BackgroundElement("knife", 2);
		bgElements[2] = new BackgroundElement("spoon", 2);
		bgElements[3] = new BackgroundElement("smallKnife", 2);
		
		bgElements[4] = new BackgroundElement("plate", 2);
		bgElements[5] = new BackgroundElement("bowl", 2);
		bgElements[6] = new BackgroundElement("pastaPlate", 2);
		bgElements[7] = new BackgroundElement("soupPlate", 2);
		bgElements[8] = new BackgroundElement("saucer", 2);
		
		bgElements[9] = new BackgroundElement("waterGlass", 2);
		bgElements[10] = new BackgroundElement("wineGlass", 2);
		bgElements[11] = new BackgroundElement("teaCup", 2);
		
		bgElements[12] = new BackgroundElement("food_utensil", 1);
		bgElements[13] = new BackgroundElement("food_container", 1);
		bgElements[14] = new BackgroundElement("drink_container", 1);
		
		this.bgElmnts = bgElements;
	}
	
	public double getPenaltyElement(String element){
		
		for(int i = 0; i < bgElmnts.length; i++){
			if(element.contains(bgElmnts[i].getName())){
				element = bgElmnts[i].getName();
				break;
			}
		}
		
		BackgroundElement bgElement = findBgElement(element);
		if(bgElement != null){
			return Math.pow(multiplier, bgElement.getLevel());
		}
		return 1;
	}
	
	public BackgroundElement findBgElement(String element){
		for(int i = 0; i < bgElmnts.length; i++){
			if(element.contains(bgElmnts[i].getName()))
				return bgElmnts[i];
		}
		return null;
	}
	
}
