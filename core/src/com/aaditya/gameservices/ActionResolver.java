package com.aaditya.gameservices;

public interface ActionResolver {
    public boolean getSignedInGPGS();

    public void loginGPGS();

    public void submitScoreGPGS(long score);

    public void unlockAchievementGPGS(String achievementId);

    public void getLeaderboardGPGS();

    public void getAchievementsGPGS();

    public void showLeaderboardsGPGS();

    public void shareScoreGPGS();
}
