package common.util;

public final class Constants
{

  private Constants()
  {

  }

  /*****************************************************************************
   * LISTS THE FORMATS FOR VALIDATIONS - STARTS
   ****************************************************************************/
  public static final String ALPHA_NUMERIC_FORMAT = "[a-z|A-Z|0-9|\\s]*";

  public static final String NUMERIC_FORMAT = "[0-9]*";

  public static final String EMAIL_VALIDATION_FORMAT = "^[A-Za-z0-9._]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{0,}\\S";

  public static final String MONETARY_DATA_FORMAT = "([0-9]){1,16}([.][0-9]{1,2}){0,1}";

  public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

  public static final String DATE_FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";

  public static final String YRN_FORMAT = "S[0-9]{2}[A-Za-z]{3}[0-9]{4,}";

  public static final String APP_NUMBER_FORMAT = "SAS[0-9]{1,}";

  public static final String POSTCODE_PREFIX_VALIDATION = "^(GIR 0AA|[A-PR-UWYZ]([0-9]{1,2}|([A-HK-Y][0-9]|[A-HK-Y][0-9]([0-9]|[ABEHMNPRV-Y]))|[0-9][A-HJKS-UW]))$";

  public static final String POSTCODE_SUFFIX_VALIDATION = "^([0-9][ABD-HJLNP-UW-Z]{2})$";

  /*****************************************************************************
   * LISTS THE FORMATS FOR UI VALIDATIONS - ENDS
   ****************************************************************************/

  /*****************************************************************************
   * LISTS CONSTANTS USED ACROSS USE CASES - COMMON - STARTS
   ****************************************************************************/

  public static final String TRIM_LEFT = "LEFT";

  public static final String TRIM_RIGHT = "RIGHT";

  public static final String TRIM_BOTH = "BOTH";

  public static final String BLANK_STRING = " ";

  public static final String EMPTY_STRING = "";

  public static final String DATE_BEFORE = "BEFORE";

  public static final String DATE_AFTER = "AFTER";

  public static final String DATE_SAME = "SAME";

  public static final String RESOURCE_CONFIG_FILES = "Configuration Property files";

  public static final int LESS_THAN = -1;

  public static final int EQUAL = 0;

  public static final int GREATER_THAN = 1;

  public static final String classStringType = "class java.lang.String";

  public static final char CHAR_Y_IN_CAPS = 'Y';

  public static final char CHAR_N_IN_CAPS = 'N';

  public static final String TEXT_YES = "Yes";

  public static final String TEXT_NO = "No";

  public static final char INVALID_CHAR = '#';

  public static final String COMMA = ",";

  public static final String AMPERSAND = "&";

  public static final String USER = "LOGIN_USER";
  
  public static final String USER_FORM = "userForm";
  
  public static final String USER_ACTIVITIES = "user_activities";

  public static final String ITEM_TYPES = "ItemTypes";

  public static final String CASH_FLOW_TYPES = "CashFlowTypes";

  public static final Long ORDINARY_USER = 10L;

  public static final String USER_ROLES = "user_roles";

  public static final String DEFAULT_USER_ROLES = "default_user_roles";
  
  public static final String USER_NOT_ASSOC_USER_ROLES = "user_not_assoc_user_roles";
  
  public static final String ROLE_ACTIVITIES = "role_activities";

  public static final String SEARCHED_USERS = "searched_users";

  public static final String SEARCHED_ROLES = "searched_roles";

  public static final String SEARCHED_ACTIVITIES = "searched_activities";

  public static final String OPERATION = "operation";

  public static final String SUBMIT_LABEL = "submit_label";

  public static final String SEARCHED_CORE_TEAMS = "searched_core_teams";
  
  public static final String SEARCHED_PLAYERS = "SEARCHED_PLAYERS";

  public static final String INVALID_SESSION = "invalidSession";

  public static final String USER_TEAM = "userTeamForm";

  public static final String TEAM_SHEDULE = "team_shedule";

  public static final String MATCH_DETAILS = "match_details";
  
  public static final String FIRST_TEAM_PLATER_MATCH_SCORES = "first_team_plater_match_scores";
  
  public static final String SECOND_TEAM_PLATER_MATCH_SCORES = "second_team_plater_match_scores";

  public static final String ACTIVE_ROLE = "Selected Activity is Active.";

  public static final String CONFIG_DATA = "configDataForm";

  public static final String CHANGE_PLAYER = "Substitute Player/Captain";

  public static final String UPDATE = "Update";

  public static final String SAVE = "Save";
  
  public static final String VIEW = "View";

  public static final String SELECTED_PLAYER = "Selected_Player";

  public static final String PLAYER_FORM = "playerForm";

  public static final String USER_TEAMS_SCORES = "USER_TEAMS_SCORES";
  
  public static final String LIVE_MATCH_SHEDULES = "LIVE_MATCH_SHEDULES";
  
  public static final String CRIC_RULES = "CRIC_RULES";
  
  public static final String SERIES_BEST_PLAYERS = "SERIES_BEST_PLAYERS";
  
  public static final String SCT_NEWS = "SCT_NEWS";
  
  public static final String SCTNEWS_FORM = "sctNewsForm";
  
  public static final String SCTNEWS_ADD_SCTION = "SCTNEWS_ADD_SCTION";

  public static final String SUBSTITUTIONS_HISTORY = "SUBSTITUTIONS_HISTORY";

  public static final String SCORE_REPORTS = "SCORE_REPORTS";

  public static final String CHN_CAPT_IN_TEAM = "Changed Captain with in Team";

  public static final String SUB_CAPT_OUT_SIDE = "Substituted Captain from outside";

  public static final String SUB_PLAYER = "Substituted Player";
  
  public static final String SUB_PLAYER_AS_CAPTAIN = "Substituted Player As Captain";

  public static final String CHN_INACT_PLY = "Free Substitution";
  
  public static final String CHN_INACT_PLY_AS_CAPTAIN = "Free Substitution and Captain Change";

  public static String ERROR_MESSAGE = "Error Message";
  
  public static String ERROR_MESSAGE_2 = "Error Message2";

  public static String VALIDATION_MESSAGE = "validate_message";

  public static String USER_NOT_SIGNED_IN = "userNotSignedIn";

  public static String SKILL_BM = "Batsman";

  public static String SKILL_BL = "Bowler";

  public static String SKILL_WK = "WicketKeeper";

  public static String CORE_TEAM = "core_team";
  
  public static String CORE_TEAM_FORM = "coreTeamForm";

  public static String DISABLED = "disabled";

  public static String DATE_TEME_FRM = "dd-MMM-yyyy HH:mm";

  public static String DATE_FRM_DD_MMM_YYYY = "dd-MMM-yyyy";

  public static String FORWARD_ACTION = "FORWARD_ACTION";

  public static Object GENERATE_SCORE_REPORT_URL = "generateScoreReport.do";

  public static String GENDER_MALE = "male";
  
  public static String GENDER_FEMALE = "female";

  public static String PLAYER_MATCH_SCORE_FORMS = "PLAYER_MATCH_SCORE_FORMS";

  public static final String DATE_FRM_DD_MM_YYYY = "dd/MM/yyyy";

  public static final String TIME_FRM_HH_MM = "HH:mm";

  public static final String DATE_TEME_AM_FRM = "dd-MMM-yyyy hh:mm aaa";
  
  public static final String DATE_TEME_AM__PM_SHORT = "dd-MMM hh:mm aaa";

  public static final String COUNTRIES = "COUNTRIES";
  
  public static final String SERIESES = "SERIESES";
  
  public static final String SERIESES_TYPES = "SERIESES_TYPES";
  
  public static final String SUN_SIGNS="SUN_SIGNS";
  
  public static final String ADD_BTN_VALUE = "Add";

  public static final String SEARCH_BTN_VALUE = "Search";

  public static final String DELETE_BTN_VALUE = "Delete";
  
  public static final String UPDATE_BTN_VALUE = "Update";
  
  public static final String CANCEL_BTN_VALUE = "Cancel";
  
  public static final String SAVE_BTN_VALUE = "Save";
  
  public static final String CANCEL_BTN_NEED = "CANCEL_BTN_NEED";

  public static final String BEST_BATSMENS = "BEST_MATSMENS";

  public static final String BEST_BOWLERS = "BEST_BOWLERS";
  
  public static final String BEST_PLAYERS = "BEST_PLAYERS";

  public static final String UPDATE_SCT_NEWS_URL ="manageSCTNews.do";
  
  public static final String SEND_MAIL_BTN ="Send Score Mail";
  
  public static final String PUBLISH_REPORTS ="Publish Report";
  
  public static final String PUBLISH_NEWS ="Publish News";
  
  public static final String BEAT_PLAYER_TYPE = "BEAT_PLAYER_TYPE";

  public static final String CHANGE_PWD_FORM = "changePwdForm";

  public static final String SERIES_ID = "seriedId";

  public static final String SERIES_DETAILS = "SERIES_DETAILS";

  public static final String COMMON_CACHE = "COMMON_CACHE";
  
  public static final String EXPENDITURE_CACHE = "EXPENDITURE_CACHE";

  public static final String BACK_REF = "BACK_REF";

  public static final String CORETEAM_ID = "CORETEAM_ID";

  public static final String PLAYER_NAME = "PLAYER_NAME";
  
  
  
  
}
