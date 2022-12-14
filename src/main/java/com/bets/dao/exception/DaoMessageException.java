package com.bets.dao.exception;

public interface DaoMessageException {
    String SAVE_USER_EXCEPTION = "Save user was failed";
    String UPDATE_USER_EXCEPTION = "Update user was failed";
    String UPDATE_USER_BALANCE_EXCEPTION = "Update user balance was failed";
    String DELETE_USER_EXCEPTION = "Delete user was failed";
    String FIND_USER_BY_ID_EXCEPTION = "Find user by id was failed";
    String FIND_USER_BY_LOGIN_EXCEPTION = "Find user by login was failed";
    String FIND_ALL_USERS_EXCEPTION = "Find all users was failed";
    String CHECK_IF_LOGIN_IF_FREE_EXCEPTION = "Check login is free was failed";


    String SAVE_MATCH_EXCEPTION = "Save match was failed";
    String UPDATE_MATCH_EXCEPTION = "Update match was failed";
    String UPDATE_MATCH_STATUS_EXCEPTION = "Update match status was failed";
    String DELETE_MATCH_EXCEPTION = "Delete match was failed";
    String FIND_MATCH_BY_ID_EXCEPTION = "Find match by id was failed";
    String FIND_ALL_MATCHES_EXCEPTION = "Find all matches was failed";

    String SAVE_BET_EXCEPTION = "Save bet was failed";
    String UPDATE_BET_EXCEPTION = "Update bet was failed";
    String DELETE_BET_EXCEPTION = "Delete bet was failed";
    String FIND_BET_BY_ID_EXCEPTION = "Find bet by id was failed";
    String FIND_ALL_BETS_EXCEPTION = "Find all bets was failed";

    String SAVE_TEAM_EXCEPTION = "Save team was failed";
    String UPDATE_TEAM_EXCEPTION = "Update team was failed";
    String DELETE_TEAM_EXCEPTION = "Delete team was failed";
    String FIND_TEAM_BY_ID_EXCEPTION = "Find team by id was failed";
    String FIND_ALL_TEAMS_EXCEPTION = "Find all teams was failed";
}
