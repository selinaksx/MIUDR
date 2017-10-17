package id.sch.smktelkom_mlg.selinakusmiawati.miudr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;

    MiudrAdapter adapter;
    List<Miudr> miudrs = new ArrayList<>();

    long initialCount;

    int modifyPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Main", "onCreate");

        recyclerView = (RecyclerView) findViewById(R.id.main_list);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        recyclerView.setLayoutManager(gridLayoutManager);

        initialCount = Miudr.count(Miudr.class);

        if (savedInstanceState != null)
            modifyPos = savedInstanceState.getInt("modify");

        if (initialCount >=0 ) {
            miudrs = Miudr.listAll(Miudr.class);

            adapter = new MiudrAdapter(MainActivity.this, miudrs);
            recyclerView.setAdapter(adapter);

            if(miudrs.isEmpty())
                Snackbar.make(recyclerView, "No Activity Added", Snackbar.LENGTH_LONG).show();
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_add_24dp);
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, Color.WHITE);
            DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);

            fab.setImageDrawable(drawable);
        }

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                final int position = viewHolder.getAdapterPosition();
                final Miudr miudr = miudrs.get(viewHolder.getAdapterPosition());
                miudrs.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(position);

                miudr.delete();
                initialCount -= 1;

                Snackbar.make(recyclerView, "Activity Deleted", Snackbar.LENGTH_SHORT)
                        .setAction("UNDO", new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {

                                miudr.save();
                                miudrs.add(position, miudr);
                                adapter.notifyItemInserted(position);
                                initialCount += 1;
                            }
                        })
                        .show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        adapter.SetOnItemClickListener(new MiudrAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position){
                Log.d("Main", "click");

                Intent i = new Intent(MainActivity.this, AddActivity.class);
                i.putExtra("isEditing", true);
                i.putExtra("nrp", miudrs.get(position).nrp);
                i.putExtra("instructor", miudrs.get(position).instructor);
                i.putExtra("branch", miudrs.get(position).branch);
                i.putExtra("area", miudrs.get(position).area);
                i.putExtra("codejob", miudrs.get(position).codejob);
                i.putExtra("category", miudrs.get(position).category);
                i.putExtra("codecategory", miudrs.get(position).codecategory);
                i.putExtra("activity", miudrs.get(position).activity);
                i.putExtra("start", miudrs.get(position).activity);
                i.putExtra("end", miudrs.get(position).end);
                i.putExtra("desc", miudrs.get(position).description);

                modifyPos = position;

                startActivity(i);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("modify", modifyPos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        modifyPos = savedInstanceState.getInt("modify");
    }

    @Override
    protected void onResume() {
        super.onResume();

        final long newCount = Miudr.count(Miudr.class);

        if (newCount > initialCount) {
            Log.d("Main", "Adding new activity");

            Miudr miudr = Miudr.last(Miudr.class);

            miudrs.add(miudr);
            adapter.notifyItemInserted((int) newCount);

            initialCount = newCount;
        }

        if (modifyPos != -1) {
            miudrs.set(modifyPos, Miudr.listAll(Miudr.class).get(modifyPos));
            adapter.notifyItemChanged(modifyPos);
        }
    }
}
