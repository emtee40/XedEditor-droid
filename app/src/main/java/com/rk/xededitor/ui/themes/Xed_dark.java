package com.rk.xededitor.ui.themes;

import android.app.Activity;
import android.widget.ImageButton;
import com.rk.xededitor.*;
import android.content.Context;
import android.graphics.Color;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import io.github.rosemoe.sora.widget.CodeEditor;
import android.content.Context;
import android.graphics.Color;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.schemes.*;

public class Xed_dark {
  public static void applyTheme(Activity ctx,CodeEditor editor) {
  	editor.setColorScheme(new SchemeDarcula());
     ImageButton run = (ImageButton)ctx.findViewById(R.id.run);
    run.setImageResource(R.drawable.run_white);
  }
}
