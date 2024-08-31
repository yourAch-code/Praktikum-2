package com.example.praktikum2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.time.LocalDate;
import java.time.Period;


public class MainActivity extends AppCompatActivity {
    public static int HitungUmur(LocalDate dob)
    {
        LocalDate skrg = LocalDate.now();
        if ((dob != null) && (skrg != null))
        {
           return Period.between(dob, skrg) .getYears();
        }
        else
        {
            return 0;
        }
    }
    public static int HitungUmurBln (LocalDate dob)
    {
        LocalDate skrg = LocalDate.now();

        if ((dob != null) && (skrg != null)) {
            return Period.between(dob, skrg).getMonths();
        }
        else
        {
            return 0;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSub;
        EditText etBB, etTB, etTL;

        TextView status_ideal, hasil_umur, t_ku;

        etBB = (EditText) findViewById(R.id.editTextNumberDecimal);
        etTB = (EditText) findViewById(R.id.editTextNumber);
        etTL = (EditText) findViewById(R.id.editTextDate);
        status_ideal = (TextView) findViewById(R.id.textViewHasil);
        hasil_umur = (TextView) findViewById(R.id.textViewUmur);
        t_ku = (TextView) findViewById(R.id.textViewKategori);

        btnSub = (Button) findViewById(R.id.btnsubmit);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter =
ArrayAdapter.createFromResource(this,
                R.array.umur,
        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double TB, BB, hasil;
                int umur_asli;
                String hasil_ideal, tglLahir, KU, htg;

                TB = Double.parseDouble(etTB.getText().toString());
                BB = Double.parseDouble(etBB.getText().toString());
                KU = spinner.getSelectedItem().toString();

                tglLahir = etTL.getText().toString();

                LocalDate dob = LocalDate.parse(tglLahir);

                if (HitungUmur(dob)>=1){
                    umur_asli = HitungUmur(dob);
                    htg = "tahun";
                    hasil = (HitungUmur(dob)*2)+8;
                    if(BB>=hasil){
                        hasil_ideal = "Gizi Baik";
                    }else{
                        hasil_ideal = "Gizi Buruk";
                    }
                }
                else if (HitungUmur(dob)>=7){
                    umur_asli = HitungUmur(dob);
                    htg = "tahun";
                    hasil = ((HitungUmur(dob)*7)-5)/2;
                    if(BB>=hasil){
                        hasil_ideal = "Gizi Baik";
                    }else{
                        hasil_ideal = "Gizi Buruk";
                    }
                }
                else
                {
                    umur_asli = HitungUmurBln(dob);
                    htg = "bulan";
                    hasil = (HitungUmurBln(dob)+9)/2;
                    if(BB>=hasil){
                        hasil_ideal = "Gizi Baik";
                    }else{
                        hasil_ideal = "Gizi Buruk";
                    }
                }

                status_ideal.setText("Hasil : " + hasil_ideal);
                hasil_umur.setText("Umur : " + umur_asli + " " + htg);
                t_ku.setText("Kategori : " + KU);

            }
        });
    }
}
