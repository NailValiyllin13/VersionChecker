package versionchecker;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.cordova.LOG;
import org.jsoup.Jsoup;
import java.io.IOException;

import ru.kaska.BuildConfig;

public class VersionChecker extends CordovaPlugin
{
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        String google = "";
        String rustore= "";
        boolean isupdate = false;
        try {
            google = Jsoup.connect(args.getString(0))
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .get().getElementsByClass(args.getString(1)).select("span").first().ownText();
            rustore = Jsoup.connect(args.getString(3))
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .get().select("p[itemprop=" + args.getString(3) +"]").first().ownText();
            String versionName = BuildConfig.VERSION_NAME;
            Log.d("google", google);
            Log.d("rustore", rustore);
            if (google == rustore && versionName != google) {
                isupdate=true;
            } else {
                isupdate =false;
            }
            callbackContext.success();
            return isupdate;
        } catch (IOException | JSONException e) {
            LOG.d("ErrorVersion", String.valueOf(e));
        }
        return isupdate;
    }
}