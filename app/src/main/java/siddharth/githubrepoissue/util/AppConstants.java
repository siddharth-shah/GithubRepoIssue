package siddharth.githubrepoissue.util;

/**
 * Created by siddharth on 13/5/17.
 */

public class AppConstants {

    public static final String BASE_URL = "https://api.github.com/";


    public static final int NETWORK_CONNECTION_TIMEOUT = 30; // 30 sec
    public static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 MB
    public static final int CACHE_MAX_AGE = 2; // 2 min
    public static final int CACHE_MAX_STALE = 7; // 7 day


    public static final int API_RETRY_COUNT = 3;


}
