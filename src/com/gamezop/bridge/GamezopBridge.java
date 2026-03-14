package com.gamezop.bridge;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

@DesignerComponent(version = 1, category = ComponentCategory.EXTENSION,
    description = "Gamezop AndroidBridge", nonVisible = true)
@SimpleObject(external = true)
public class GamezopBridge extends AndroidNonvisibleComponent {

    public GamezopBridge(ComponentContainer container) {
        super(container.$form());
    }

    @SimpleFunction(description = "Attach AndroidBridge to CustomWebView. Call in Screen.Initialize.")
    public void AttachBridge(WebView wv) {
        wv.addJavascriptInterface(this, "AndroidBridge");
    }

    @JavascriptInterface
    public void getGameEvents(final String data) {
        form.runOnUiThread(new Runnable() {
            public void run() {
                GameEventReceived(data);
            }
        });
    }

    @SimpleEvent(description = "Fired when Gamezop sends a game event")
    public void GameEventReceived(String data) {
        EventDispatcher.dispatchEvent(this, "GameEventReceived", data);
    }
}
```

Click **Commit new file** (green button)

---

### Step 3 — Create File 2

Click **Add file → Create new file** → type:
```
.github/workflows/build.yml
