package com.aaditya.escapeearth.desktop;

import com.aaditya.gameservices.ActionResolver;

public class ActionResolverDesktop implements ActionResolver {	
		boolean signedInStateGPGS = false;

		@Override
		public boolean getSignedInGPGS() {
			return signedInStateGPGS;
		}

		@Override
		public void loginGPGS() {
			System.out.println("loginGPGS");
			signedInStateGPGS = true;
		}

		@Override
		public void submitScoreGPGS(long score) {
			System.out.println("submitScoreGPGS " + score);
		}

		@Override
		public void unlockAchievementGPGS(String achievementId) {
			System.out.println("unlockAchievement " + achievementId);
		}

		@Override
		public void getLeaderboardGPGS() {
			System.out.println("getLeaderboardGPGS");
		}

		@Override
		public void getAchievementsGPGS() {
			System.out.println("getAchievementsGPGS");
		}

		@Override
		public void showLeaderboardsGPGS() {
			// TODO Auto-generated method stub
			
		}

	@Override
	public void shareScoreGPGS() {

	}
}

