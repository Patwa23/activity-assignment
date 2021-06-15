package com.tacx.assignment.auth;

public class Endpoint {

    public static final String API_V1_ACTIVITY = "/api/v1";

    //Upload CSV file
    public static final String UPLOAD_POST = "/activity/upload";

    //GET
    public static final String ACTIVITY_GET = "/activity/{activityId}";
    public static final String ACTIVITIES_GET = "/activities";

    //DELETE
    public static final String ACTIVITY_DELETE = "/activity/{activityId}";

}
