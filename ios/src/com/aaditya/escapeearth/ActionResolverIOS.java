package com.aaditya.escapeearth;

import com.aaditya.gameservices.ActionResolver;

/**
 * Created by AadityaPatwari on 2/12/15.
 */
public class ActionResolverIOS implements ActionResolver {
    @Override
    public boolean getSignedInGPGS() {
        return false;
    }

    @Override
    public void loginGPGS() {

    }

    @Override
    public void submitScoreGPGS(long score) {

    }

    @Override
    public void unlockAchievementGPGS(String achievementId) {

    }

    @Override
    public void getLeaderboardGPGS() {

    }

    @Override
    public void getAchievementsGPGS() {

    }

    @Override
    public void showLeaderboardsGPGS() {

    }

    @Override
    public void shareScoreGPGS() {

    }
}
