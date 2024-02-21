package com.rk.xededitor;

import androidx.documentfile.provider.DocumentFile;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rk.xededitor.rkUtils;
import com.rk.xededitor.ui.themes.*;
import android.os.*;
import android.net.*;
import android.content.*;
import android.provider.*;
import android.widget.*;
import android.app.*;
import android.view.*;
import android.widget.ExpandableListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.*;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.rk.xededitor.databinding.ActivityMainBinding;
import io.github.rosemoe.sora.widget.CodeEditor;
import com.rk.xededitor.ui.home.HomeFragment;
import io.github.rosemoe.sora.*;
import io.github.rosemoe.sora.widget.schemes.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private AppBarConfiguration mAppBarConfiguration;
  private ActivityMainBinding binding;
  private static final int REQUEST_CODE_PICK_FOLDER = 123;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setSupportActionBar(binding.appBarMain.toolbar);

    CodeEditor editor = HomeFragment.binding.editor;
    if (rkUtils.isDarkMode(this)) {
      Xed_dark.applyTheme(this, editor);
    } else {
      Xed.applyTheme(this, editor);
    }

    DrawerLayout drawer = binding.drawerLayout;
    NavigationView navigationView = binding.navView;
    mAppBarConfiguration =
        new AppBarConfiguration.Builder(R.id.nav_home).setOpenableLayout(drawer).build();

    NavController navController =
        Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
    NavigationUI.setupWithNavController(navigationView, navController);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == REQUEST_CODE_PICK_FOLDER && resultCode == Activity.RESULT_OK) {
      Uri treeUri = data != null ? data.getData() : null;
      // action was successful
      rkUtils.setVisibility(findViewById(R.id.open_folder), false);
      

      TreeNode root = TreeNode.root();
      TreeNode parent = new TreeNode("MyParentNode");
      TreeNode child0 = new TreeNode("ChildNode0");
      TreeNode child1 = new TreeNode("ChildNode1");
      parent.addChildren(child0, child1);
      root.addChild(parent);
      AndroidTreeView tView = new AndroidTreeView(this, root);
      LinearLayout l = findViewById(R.id.drawbar);
      l.addView(tView.getView());

      if (treeUri != null) {
        // Get the root folder
        DocumentFile rootFolder = DocumentFile.fromTreeUri(this, treeUri);

        // Loop over files and folders
        if (rootFolder != null && rootFolder.isDirectory()) {
          for (DocumentFile file : rootFolder.listFiles()) {
            if (file.isDirectory()) {
              // It's a folder
              String folderName = file.getName();

              // Do something with the folder
            } else {
              // It's a file
              String fileName = file.getName();
              // Do something with the file
            }
          }
        }
      }
    }
  }

  public void run(View view) {
    rkUtils.ni(this);
  }

  public void open_folder(View view) {
    // Launch SAF intent to pick a folder
    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
    startActivityForResult(intent, REQUEST_CODE_PICK_FOLDER);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    final int id = item.getItemId();

    if (id == R.id.action_save) {
      rkUtils.ni(this);
      return true;
    } else if (id == R.id.action_settings) {
      Intent intent = new Intent(this, Settings.class);
      startActivity(intent);
      return true;
    } else if (id == R.id.action_terminal) {
      rkUtils.ni(this);
      return true;
    } else if (id == R.id.action_plugin) {
      rkUtils.ni(this);
      return true;
    } else {
      return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onSupportNavigateUp() {
    NavController navController =
        Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    return NavigationUI.navigateUp(navController, mAppBarConfiguration)
        || super.onSupportNavigateUp();
  }

  public void toast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
}
