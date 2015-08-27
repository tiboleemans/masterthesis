import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ConvertTags {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		/**
		 * Tags are read from text file. Multiple new tag instances are created.
		 */
		ArrayList<Tag> tags = new ArrayList<Tag>();

		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"/home/thibault/catkin_ws/tags.txt"));
			String str;
			str = in.readLine();
			while ((str = in.readLine()) != null) {
				String[] input = str.split(";");
				Tag t = Tag.class.getConstructor(String.class, String.class,
						String.class, String.class).newInstance(input[1],
						input[2], input[3], input[4]);
				tags.add(t);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}

		/**
		 * Tags are treated so that a tablesetting can be constructed.
		 */
		Tablesetting ts = new Tablesetting(tags);
		ts.createPositions();
		// ts.calibrate();

		/**
		 * Tags are fetched from tablesetting to be printed.
		 */
		Tag[] tags2 = ts.getTags();
		for (int i = 0; i < tags2.length; i++) {
			System.out.println(tags2[i].toString());
		}
	}
}
