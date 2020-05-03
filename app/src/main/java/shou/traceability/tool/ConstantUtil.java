package shou.traceability.tool;

public class ConstantUtil {
    //接口url
    public static final String BASE_URL="http://192.168.8.130:8081/demo/";
    public static final String USER_LOG_IN="sign/in";
    public static final String USER_SIGN_UP="sign/up";
    public static final String  USER_TOKEN="sign/token";
    public static final String QUERY_FAC="factrace/search";
    public static final String QUERY_ORI="oritrace/search";
    public static final String QUERY_PUB="pubtrace/search";
    //Error_Code
    public static final int HTTP_ERROR=999999;
    public static final int TOKEN_NEGATIVE=100002;
    public static final int LOGIN_PASSWORDERROR=100005;
    public static final int LOGIN_DONOT_EXIST=500001;
    public static final int SIGNUP_ALEXIST=500000;

    //溯源类型
    public static final int INDEX_ORI=60000;
    public static final int INDEX_PUB=60001;
    public static final int INDEX_FAC=60002;
}
