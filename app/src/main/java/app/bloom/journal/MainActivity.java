package app.bloom.journal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;

/**
 * Bloom opens your Bloom page in a Chrome Custom Tab. Custom Tabs share Chrome's
 * sign-in, so your Claude login, sync, AI quotes and Google Calendar all work,
 * and Google sign-in is allowed (it is blocked inside plain WebViews).
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = Uri.parse(getString(R.string.bloom_url));
        try {
            CustomTabColorSchemeParams colors = new CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(Color.parseColor("#2d4f86"))
                    .setNavigationBarColor(Color.parseColor("#2d4f86"))
                    .build();
            CustomTabsIntent tab = new CustomTabsIntent.Builder()
                    .setDefaultColorSchemeParams(colors)
                    .setShowTitle(true)
                    .setUrlBarHidingEnabled(true)
                    .build();
            tab.launchUrl(this, uri);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
        finish();
    }
}
