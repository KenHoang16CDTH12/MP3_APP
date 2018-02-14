package it.hueic.kenhoang.mp3_app.Common;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.Window;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import it.hueic.kenhoang.mp3_app.Model.Song;

/**
 * Created by kenhoang on 06/02/2018.
 */

public class Common {
    public static final String ARKHIP_FONT = "Arkhip_font.ttf";
    public static final String NABILA_FONT = "NABILA.TTF";
    //Set TypeFace
    public static Typeface setTypeFaceFonts(Activity activity, String typeFont) {
        Typeface face = Typeface.createFromAsset(activity.getAssets(), "fonts/" + typeFont);
        return face;
    }
    public static final String[] arrImageFake =
            {"http://img2.wikia.nocookie.net/__cb20131222090145/glee/images/0/0a/Radioactive-Imagine-Dragons.jpg",
            "http://1.bp.blogspot.com/-bI6ouYCApmQ/Un208VrhWtI/AAAAAAAAGkg/YbktSGpFcy8/s1600/Westlife-7.jpg",
            "https://d2s36jztkuk7aw.cloudfront.net/sites/default/files/styles/media_responsive_widest/public/tile/image/Beatles-%20Image%201.jpg?itok=ilH8EnYd",
            "https://www.bridgestonearena.com/assets/img/Maroon5_spot-7756c1efa2.jpg",
            "https://i.ytimg.com/vi/wJnBTPUQS5A/hqdefault.jpg?sqp=-oaymwEXCNACELwBSFryq4qpAwkIARUAAIhCGAE=&rs=AOn4CLDXtZScJSS7lbEqxEX79sdqZ8rSog"};

    //Song Manager
    //Offline Song
    //In Download Folder
    public static final String MEDIA_PATH = new String("/sdcard/Download/");
    public static ArrayList<Song> songslist = getPlayList();
    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     */
    public static ArrayList<Song> getPlayList() {
        ArrayList<Song> songs = new ArrayList<>();
        File home = new File(MEDIA_PATH);
        File[] listOfFile = home.listFiles(new FileExtensionFilter());
        if (listOfFile != null && listOfFile.length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                Song model = new Song();
                model.setId("");
                model.setName(file.getName().substring(0, (file.getName().length() - 4)));
                model.setAuthor("");
                model.setUrl(file.getPath());
                //Adding each song list to SongList
                songs.add(model);
            }
        } else {
            System.out.println("Ko co gi");
        }
        //return song list array
        return songs;
    }
    /**
     * Class to filter files which are having .mp3 extension
     * */
    public static class FileExtensionFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
    //
}
