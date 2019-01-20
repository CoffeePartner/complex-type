package coffeepartner.complextype.example;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import coffeepartner.complextype.example.common.AbstractRvActivity;
import coffeepartner.complextype.example.hierarchy.HierarchyActivity;
import coffeepartner.complextype.example.multiple.MultipleActivity;
import coffeepartner.complextype.example.simple.SimpleActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btn_simple).setOnClickListener(l ->
      AbstractRvActivity.jumpTo(((TextView) l).getText().toString(), MainActivity.this, SimpleActivity.class));
    findViewById(R.id.btn_hierarchy).setOnClickListener(l ->
      AbstractRvActivity.jumpTo(((TextView) l).getText().toString(), MainActivity.this, HierarchyActivity.class));
    findViewById(R.id.btn_multiple).setOnClickListener(l ->
      AbstractRvActivity.jumpTo(((TextView) l).getText().toString(), MainActivity.this, MultipleActivity.class));
  }
}
