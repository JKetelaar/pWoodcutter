package parachute.scripts.pwoodcutter.main;

import org.rev317.min.api.wrappers.Tile;

import parachute.scripts.api.Region;

public class Data {
	
	//Strings
	public static String Method = "";
	public static String Location = "";

	//Objects
	public static int idchosen[];
	public static int idwillow[] = {1308, 5553, 5551, 5552};
	public static int idoak[] = {1281};
	
	//Tiles
	public static Tile chosenTile;
	public static Tile TileDraynor = new Tile(3093, 3243);
	public static Tile TileVarrockWest = new Tile(3253, 3421);
	
	//Items
	public static int hatchetsid[] = {1352, 1350, 1354, 1356, 1358, 1360, 1362, 6740};
	
	//groundItems
	public static int nestids[] = {5070, 5071, 5072, 5073, 5074, 5075};
	
	//ChatInterfaces
	public static int chatboxid = 968;
	public static int chatboxfullid = 356;

	//Regions
	public static Region chosenRegion;
	public static Region chosenBankRegion;
	
	public static Region totalRegionDraynor = new Region(new Tile(3081, 3249), new Tile(3097, 3225));
	public static Region bankRegionDraynor = new Region(new Tile(3087, 3247), new Tile(3098, 3239));
	
	public static Region totalRegionVarrockWest = new Region(new Tile(3274, 3427), new Tile(3286, 3412));
	public static Region bankRegionVarrockWest = new Region(new Tile(3247, 3424), new Tile(3260, 3416));
}
