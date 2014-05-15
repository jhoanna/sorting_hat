import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SortingHat {
	public static void sort(Path name, Path source) {
		Path hogwarts = Paths.get(Constant.TARGET_DIR);
		Path house1 = Paths.get(Constant.TARGET_DIR + Constant.HOUSE1);
		Path house2 = Paths.get(Constant.TARGET_DIR + Constant.HOUSE2);
		Path house3 = Paths.get(Constant.TARGET_DIR + Constant.HOUSE3);
		Path house4 = Paths.get(Constant.TARGET_DIR + Constant.HOUSE4);
		Path[] houses = {house1, house2, house3, house4};
		
		String[] strengths = {Constant.HOUSE1_STRENGTH, Constant.HOUSE2_STRENGTH, Constant.HOUSE3_STRENGTH, Constant.HOUSE4_STRENGTH};
		
		String filename = name.toString();
		
		try {
			if (Files.notExists(hogwarts, LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectory(hogwarts);
			}
			
			prepareHouses(houses);
			
			for (int i = 0; i < strengths.length; i++) {
				if (filename.contains(strengths[i])) {
					copy(source, houses[i].resolve(name));
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void prepareHouses(Path[] houses) throws IOException {
		for (Path house: houses) {
			if (Files.notExists(house, LinkOption.NOFOLLOW_LINKS) && !Files.exists(house, LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectory(house);
			}
			
		}
	}

	public static void copy(Path source, Path target) throws IOException {
		Files.copy(source, target, REPLACE_EXISTING);
	}
}
