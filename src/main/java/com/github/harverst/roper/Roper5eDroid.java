package com.github.harverst.roper;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.github.harverst.roper.view.CharacterListActivity;
import com.github.harverst.roper.view.CreditsActivity;

public class Roper5eDroid extends Activity
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }
  public void openCharacterListActivity(View v)
  {
    Intent characterListIntent = new Intent(this, CharacterListActivity.class);
    startActivity(characterListIntent);
  }
  public void openCreditsActivity(View v)
  {
    Intent creditsIntent = new Intent(this, CreditsActivity.class);
    startActivity(creditsIntent);
  }
}
