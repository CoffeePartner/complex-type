package coffeepartner.complextype.example.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import coffeepartner.complextype.androidx.ComplexAdapter;
import coffeepartner.complextype.example.R;

/**
 * Created by dieyi on 2019/1/20.
 */
public abstract class AbstractRvActivity extends AppCompatActivity {

  static final String EXTRA_TITLE = "extra:title";

  public static void jumpTo(String title, Activity context, Class<? extends AbstractRvActivity> clazz) {
    context.startActivity(new Intent(context, clazz).putExtra(EXTRA_TITLE, title));
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rv);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_TITLE));
    RecyclerView rv = findViewById(R.id.rv);
    rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    rv.setAdapter(createAdapter());
    DividerItemDecoration decoration = new DividerItemDecoration(this, LinearLayout.VERTICAL);
    decoration.setDrawable(getResources().getDrawable(R.drawable.divider_h));
    rv.addItemDecoration(decoration);
  }

  public abstract ComplexAdapter createAdapter();

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
