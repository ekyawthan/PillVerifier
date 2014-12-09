package edu.ccny.cs.kyaw.pillverifier.adapters;

/**
 * Created by kyawthan on 12/6/14.
 */
public class ConstantHelp {

    public static final String API_KEY = "MQFLTZAIAI";
    public static final String PILL_BOX_BASE_URL = "http://pillbox.nlm.nih.gov/PHP/pillboxAPIService.php";
    // Image Base Url
    public static final String SmallImageBaseUrl = "http://pillbox.nlm.nih.gov/assets/small/";
    public static final String LargeImageBaseUrl = "http://pillbox.nlm.nih.gov/assets/large/";



    //FDA Spl
    public static final  String BLACK = "C48323";
    public static final  String BLUE = "C48333";
    public static final  String BROWN = "C48332";
    public static final  String GRAY = "C48324";
    public static final  String GREEN = "C48329";
    public static final  String ORANGE = "C48331";
    public static final  String PINK = "C48328";
    public static final  String PURPLE = "C48327";
    public static final  String RED = "C48326";
    public static final  String TURQUOISE = "C48334";
    public static final  String WHITE = "C48325";
    public static final  String YELLOW = "C48330";
    // shape
    public static final String BULLET = "C48335";
    public static final String CAPSULE = "C48336";
    public static final String CLOVER =	"C48337";
    public static final String DIAMOND = "C48338";
    public static final String DOUBLE_CIRCLE = "C48339";


    /*


DOUBLE CIRCLE	C48339
FREEFORM	C48340
GEAR	C48341
HEPTAGON (7 sided)	C48342
HEXAGON (6 sided)	C48343
OCTAGON (8 sided)	C48344
OVAL	C48345
PENTAGON (5 sided)	C48346
RECTANGLE	C48347
ROUND	C48348
SEMI-CIRCLE	C48349
SQUARE	C48350
TEAR	C48351
TRAPEZOID	C48352
TRIANGLE	C48353
     */


    //XML Parse helper

    public static final String KEY_PILL = "pill";
    public static final String KEY_RECORD_COUNT = "record_count";
    public static final String KEY_SPL = "SPL_ID";
    public static final String KEY_PRODUCT_CODE = "PRODUCT_CODE";
    public static final String KEY_NDC9 = "NDC9";
    public static final String KEY_SPLCOLOR = "SPLCOLOR";
    public static final String KEY_SPLIMPRINT = "SPLIMPRINT";
    public static final String KEY_SPLSHAPE = "SPLSHAPE";
    public static final String KEY_SPLSIZE = "SPLSIZE";
    public static final String KEY_SPLSCORE = "SPLSCORE";
    public static final String KEY_RXCUI = "RXCUI";
    public static final String KEY_RXTTY = "RXTTY";
    public static final String KEY_RXSTRING = "RXSTRING";
    public static final String KEY_INGREDIENTS = "INGREDIENTS";
    public static final String KEY_HAS_IMAGE = "HAS_IMAGE";
    public static final String KEY_IMAGE_ID = "image_id";
    public static final String KEY_SETID = "SETID";
    public static final String KEY_AUTHOR = "AUTHOR";


    //database table
    public static final String DRUG_INFO_TABLE = "drugInfo";
    public static final String PRIMIARY_KEY = "id";
    public static final String DB = "drug_db";
}
