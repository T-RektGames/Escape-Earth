package com.aaditya.escapeearth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;


import com.aaditya.escapeearth.EEGame;
import com.aaditya.gameservices.ActionResolver;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.PixmapIO.PNG;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class AndroidLauncher extends AndroidApplication implements GameHelperListener, ActionResolver {

    private GameHelper gameHelper;
    private int counter;
    File picFile;
    //private GoogleApiClient mGoogleApiClient;

    public AndroidLauncher() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
        gameHelper.enableDebugLog(false);
        gameHelper.setup(this);

        initialize(new EEGame(this), cfg);

    }

    public void onSignInFailed() {
        // TODO Auto-generated method stub

    }

    public void onSignInSucceeded() {
        // TODO Auto-generated method stub

    }

    public boolean getSignedInGPGS() {
        // TODO Auto-generated method stub
        return gameHelper.isSignedIn();
    }

    public void loginGPGS() {
        // TODO Auto-generated method stub
        try {
            runOnUiThread(new Runnable(){
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (final Exception ex) {
        }
        //gameHelper.getApiClient().si

    }

    public void unlockAchievementGPGS(String achievementId) {
        // TODO Auto-generated method stub
        // gameHelper.getApiClient().unlockAchievement(achievementId);
        if (gameHelper.isSignedIn()) {
            Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
        }
    }

    public void getLeaderboardGPGS() {
        // TODO Auto-generated method stub

    }

    public void getAchievementsGPGS() {
        // TODO Auto-generated method stub
        if (gameHelper.isSignedIn()) {
            startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 101);
        }
        else if (!gameHelper.isConnecting()) {
            loginGPGS();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        gameHelper.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameHelper.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    public void submitScoreGPGS(long score) {
        // TODO Auto-generated method stub\
        if (gameHelper.isSignedIn()) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(), "CgkIm7vkra8UEAIQBg", score);
            //shareScreenShot();
/*
            saveScreenshot();
			String filePath = Gdx.files.getLocalStoragePath() + "stoneIMG" + counter + ".png";
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_STREAM, bitmap);
			shareIntent.setType("image/png");
			startActivity(Intent.createChooser(shareIntent, "send to"));
			*/


        }

    }

    public void showLeaderboardsGPGS() {
        if (gameHelper.isSignedIn()) {

            //startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkIm7vkra8UEAIQBg"),
              //      9002);
            startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(gameHelper.getApiClient()), 9002);

        } else if (!gameHelper.isConnecting()) {
            loginGPGS();
        }

    }

    @Override
    public void shareScoreGPGS() {
        shareScreenShot();
    }

	/*
    private Bitmap takeSS(){
		View rootView = findViewById(android.R.id.content).getRootView();
		rootView.setDrawingCacheEnabled(true);
		return rootView.getDrawingCache();
	}
	*/





    public void shareScreenShot() {
        try {
            File imagePath = new File(this.getFilesDir(), "screenshots");
            File newFile = new File(imagePath, "screenshot.png");
            Uri uri = FileProvider.getUriForFile(this, "com.our.cool.game.fileprovider", newFile);
            FileHandle fileHandle = Gdx.files.getFileHandle(newFile.getAbsolutePath(), Files.FileType.Absolute);
            saveScreenshot(fileHandle);
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Escape Earth");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "I challenge you to beat my score! Download Escape Earth from the Play Store at https://play.google.com/store/apps/details?id=com.aaditya.escapeearth&hl=en now!" );
            sendIntent.setType("image/png");
            sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            this.startActivity(Intent.createChooser(sendIntent, "Share Via"));
        } catch (Exception e) {
            Toast.makeText(this,
                    "Unable to take screenshot at this time.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static FileHandle saveScreenshot(FileHandle fh) throws Exception {
        Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        writePNG(fh, pixmap);
        pixmap.dispose();
        return fh;
    }

    public static void writePNG(FileHandle fh, Pixmap pixmap) {
        PNG writer = new PNG((int)(pixmap.getWidth() * pixmap.getHeight() * 1.5f)); // Guess at deflated size.
        try {
            writer.setFlipY(true);
            writer.write(fh, pixmap);
        } catch (IOException e) {
            e.printStackTrace();
           // Toast.makeText(this.getBaseConte,
             //       "Unable to take screenshot at this time.",
               //     Toast.LENGTH_SHORT).show();
        } finally {
            writer.dispose();
        }
    }
}
