package com.example.managorgan;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView text_display;

    EditText tongue_base_text;
    EditText vallecula_text;
    EditText gloss_epiglott_text;
    EditText tonsil_text;
    EditText epiglotiss_text;
    EditText aef_text;
    EditText false_vocal_cord_text;
    EditText true_vocal_cord_text;
    EditText pfs_text;
    EditText posterior_commissure_text;
    EditText post_pharyngeal_wall_text;
    EditText first_pass_text;
    EditText second_pass_text;
    EditText third_pass_text;
    EditText nasopharynx_text;
    EditText impression_text;
    EditText patient_id_text;
    EditText patient_name_text;
    EditText gender_text;
    EditText age_text;
    EditText visit_date_text;

    final Calendar visit_calendar = Calendar.getInstance();


    Button update_button;
    DatabaseReference firebase_db_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_display = findViewById(R.id.tongue_base_view);

        tongue_base_text = findViewById(R.id.tongue_base_text);
        vallecula_text = findViewById(R.id.vallecula_text);
        gloss_epiglott_text = findViewById(R.id.gloss_epiglott_text);
        tonsil_text = findViewById(R.id.tonsil_text);
        epiglotiss_text = findViewById(R.id.epiglottis_text);
        aef_text = findViewById(R.id.aef_text);
        false_vocal_cord_text = findViewById(R.id.false_vocal_cord_text);
        true_vocal_cord_text = findViewById(R.id.true_vocal_cord_text);
        pfs_text = findViewById(R.id.pfs_text);
        posterior_commissure_text = findViewById(R.id.posterior_commissure_text);
        post_pharyngeal_wall_text = findViewById(R.id.post_pharyngeal_wall_text);
        first_pass_text = findViewById(R.id.first_pass_text);
        second_pass_text = findViewById(R.id.second_pass_text);
        third_pass_text = findViewById(R.id.third_pass_text);
        nasopharynx_text = findViewById(R.id.nasopharynx_text);
        impression_text = findViewById(R.id.impression_text);
        patient_id_text = findViewById(R.id.patient_id_text);
        patient_name_text = findViewById(R.id.patient_name_text);
        gender_text = findViewById(R.id.gender_text);
        age_text = findViewById(R.id.age_text);
        visit_date_text = findViewById(R.id.visit_date_text);

        update_button = findViewById(R.id.upload_button);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                visit_calendar.set(Calendar.YEAR, year);
                visit_calendar.set(Calendar.MONTH, month);
                visit_calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                update_label();
            }
        };

        visit_date_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, visit_calendar.get(Calendar.YEAR), visit_calendar.get(Calendar.MONTH),
                        visit_calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        firebase_db_ref = FirebaseDatabase.getInstance().getReference();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.base_tongue)).setValue(tongue_base_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.epiglottis)).setValue(epiglotiss_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.aef)).setValue(aef_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.false_vocal_cord)).setValue(false_vocal_cord_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.true_vocal_cord)).setValue(true_vocal_cord_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.pfs)).setValue(pfs_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.posterior_commissure)).setValue(posterior_commissure_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.post_pharyngeal_wall)).setValue(post_pharyngeal_wall_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.first_pass)).setValue(first_pass_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.second_pass)).setValue(second_pass_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.third_pass)).setValue(third_pass_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.nasopharynx)).setValue(nasopharynx_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.nasopharynx)).setValue(nasopharynx_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.impression)).setValue(impression_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.gender)).setValue(gender_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.age)).setValue(age_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.visit_date)).setValue(visit_date_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.patient_name)).setValue(patient_name_text.getText().toString());
                firebase_db_ref.child(patient_id_text.getText().toString()).child(getString(R.string.visit_date)).setValue(visit_date_text.getText().toString());


                Toast.makeText(MainActivity.this, "sent data", Toast.LENGTH_LONG).show();

                firebase_db_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String data = dataSnapshot.child(patient_id_text.getText().toString()).getValue().toString();
                        text_display.setText(data);
                        Toast.makeText(MainActivity.this, "got data", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }


    private void update_label(){
        String format = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        visit_date_text.setText(sdf.format(visit_calendar.getTime()));
    }

}



