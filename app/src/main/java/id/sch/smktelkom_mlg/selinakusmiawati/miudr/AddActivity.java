package id.sch.smktelkom_mlg.selinakusmiawati.miudr;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    AutoCompleteTextView atvInstructor, atvBranch, atvArea, atvCodejob, atvCategory, atvCodecategory, atvActivity;
    EditText etNRP, etStart, etEnd, etDesc;
    String inst[]={"Indra Yuliatma","Achmad Roza'i","Charles","Syamsuni"};
    String branch[]={"BLP"};
    String area[]={"TTA Balikpapan"};
    String codejob[]={"100","200","300","305","430","460"};
    String category[]={"Absent","Development","JA Instructional","JA Non Instructional"};
    String codecategory[]={"ABSENT","DEV","JI","JNI"};
    String activity[] = {"Absent","Formal Development","Formal Teaching","Non Formal Teaching","Other Activity","Travel"};
    private int mYear, mMonth, mDay;
    Toolbar toolbar;
    FloatingActionButton fab;
    String nrp1, inst1, branch1, area1, codejob1, category1, codecategory1, activity1, start1, end1, desc1;
    Boolean editingAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etNRP = (EditText) findViewById(R.id.editTextNRP);
        atvInstructor = (AutoCompleteTextView) findViewById(R.id.editTextInst);
        atvBranch = (AutoCompleteTextView) findViewById(R.id.editTextBranch);
        atvArea = (AutoCompleteTextView) findViewById(R.id.editTextArea);
        atvCodejob = (AutoCompleteTextView) findViewById(R.id.editTextCodeJob);
        atvCategory = (AutoCompleteTextView) findViewById(R.id.editTextCategory);
        atvCodecategory = (AutoCompleteTextView) findViewById(R.id.editTextCodeCat);
        atvActivity = (AutoCompleteTextView) findViewById(R.id.editTextActivity);
        etStart = (EditText) findViewById(R.id.editTextStart);
        etEnd = (EditText) findViewById(R.id.editTextEnd);
        etDesc = (EditText) findViewById(R.id.editTextDesc);
        toolbar = (Toolbar) findViewById(R.id.addnote_toolbar);
        fab = (FloatingActionButton) findViewById(R.id.add_fab);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_clear_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editingAct = getIntent().getBooleanExtra("isEditing", false);
        if (editingAct) {
            nrp1 = getIntent().getStringExtra("nrp");
            inst1 = getIntent().getStringExtra("instructor");
            branch1 = getIntent().getStringExtra("branch");
            area1 = getIntent().getStringExtra("area");
            codejob1 = getIntent().getStringExtra("codejob");
            category1 = getIntent().getStringExtra("category");
            codecategory1 = getIntent().getStringExtra("codecategory");
            activity1 = getIntent().getStringExtra("activity");
            start1 = getIntent().getStringExtra("start");
            end1 = getIntent().getStringExtra("end");
            desc1 = getIntent().getStringExtra("desc");

            etNRP.setText(nrp1);
            atvInstructor.setText(inst1);
            atvBranch.setText(branch1);
            atvArea.setText(area1);
            atvCodejob.setText(codejob1);
            atvCategory.setText(category1);
            atvCodecategory.setText(codecategory1);
            atvActivity.setText(activity1);
            etStart.setText(start1);
            etEnd.setText(end1);
            etDesc.setText(desc1);
        }

        etStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                        updateDisplay();
                    }

                    private void updateDisplay() {
                        etStart.setText(
                                new StringBuilder()
                                .append(mDay).append("/")
                                .append(mMonth + 1).append("/")
                                .append(mYear));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Start Date");
                mDatePicker.show();
            }
        });

        etEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                        updateDisplay();
                    }

                    private void updateDisplay() {
                        etEnd.setText(
                                new StringBuilder()
                                        .append(mDay).append("/")
                                        .append(mMonth + 1).append("/")
                                        .append(mYear));
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("End Date");
                mDatePicker.show();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, inst);
        atvInstructor.setAdapter(adapter);
        atvInstructor.setThreshold(1);

        ArrayAdapter adapterbr = new ArrayAdapter(this,android.R.layout.simple_list_item_1, branch);
        atvBranch.setAdapter(adapterbr);
        atvBranch.setThreshold(1);

        ArrayAdapter adapterar = new ArrayAdapter(this,android.R.layout.simple_list_item_1, area);
        atvArea.setAdapter(adapterar);
        atvArea.setThreshold(1);

        ArrayAdapter adaptercod = new ArrayAdapter(this,android.R.layout.simple_list_item_1, codejob);
        atvCodejob.setAdapter(adaptercod);
        atvCodejob.setThreshold(1);

        ArrayAdapter adaptercat = new ArrayAdapter(this,android.R.layout.simple_list_item_1, category);
        atvCategory.setAdapter(adaptercat);
        atvCategory.setThreshold(1);

        ArrayAdapter adaptercocat = new ArrayAdapter(this,android.R.layout.simple_list_item_1, codecategory);
        atvInstructor.setAdapter(adaptercocat);
        atvInstructor.setThreshold(1);

        ArrayAdapter adapteract = new ArrayAdapter(this,android.R.layout.simple_list_item_1, activity);
        atvActivity.setAdapter(adapteract);
        atvActivity.setThreshold(1);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNRP = etNRP.getText().toString();
                String newInst = atvInstructor.getText().toString();
                String newBranch = atvBranch.getText().toString();
                String newArea = atvArea.getText().toString();
                String newCodeJob = atvCodejob.getText().toString();
                String newCategory = atvCategory.getText().toString();
                String newCodeCategory = atvCategory.getText().toString();
                String newActivity = atvActivity.getText().toString();
                String newStart = etStart.getText().toString();
                String newEnd = etEnd.getText().toString();
                String newDesc = etDesc.getText().toString();

                if (!editingAct) {
                    Log.d("Act", "saving");
                    Miudr miudr = new Miudr(newNRP, newInst, newBranch, newArea, newCodeJob, newCategory, newCodeCategory, newActivity, newStart, newEnd, newDesc);
                    miudr.save();
                } else {
                    Log.d("Act", "updating");

                    List<Miudr> miudrs = Miudr.find(Miudr.class, "nrp = ?", nrp1);
                    if (miudrs.size() > 0 ) {

                        Miudr miudr = miudrs.get(0);
                        Log.d("got act", "act: " + miudr.nrp);
                        miudr.nrp = newNRP;
                        miudr.instructor = newInst;
                        miudr.branch = newBranch;
                        miudr.area = newArea;
                        miudr.codejob = newCodeJob;
                        miudr.category = newCategory;
                        miudr.codecategory = newCodeCategory;
                        miudr.activity = newActivity;
                        miudr.start = newStart;
                        miudr.end = newEnd;
                        miudr.description = newDesc;

                        miudr.save();
                    }
                }
                finish();
            }


        });
    }
}
