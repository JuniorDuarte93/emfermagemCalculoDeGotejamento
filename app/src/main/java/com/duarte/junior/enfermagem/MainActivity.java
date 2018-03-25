package com.duarte.junior.enfermagem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btnCalcular;
    TextView txtVolume, txtTempo, txtResultado;
    CheckBox ckMa, ckMi, ckHoras, ckMinutos;


    DecimalFormat df = new DecimalFormat("0.##");
    String resultadoaux;
    double resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalcular = findViewById(R.id.btnCalcular);

        txtVolume = findViewById(R.id.txtVolume);
        txtTempo = findViewById(R.id.txtTempo);
        txtResultado = findViewById(R.id.txtResultado);

        ckMa = findViewById(R.id.ckMa);
        ckMi = findViewById(R.id.ckMi);
        ckHoras = findViewById(R.id.ckHoras);
        ckMinutos = findViewById(R.id.ckMinutos);

        ckHoras.setChecked(true);
        ckMa.setChecked(true);
    }

    public void ckSelecionadoTipoGota (View view) {

        // Verificar qual caixa de seleção foi clicada
        switch (view.getId ()) {

            case R.id.ckMa:
                if (ckMa.isChecked()) {
                    ckMi.setChecked(false);
                }
                break;

            case R.id.ckMi:
                if (ckMi.isChecked()) {
                    ckMa.setChecked(false);
                }
                break;
        }
    }

    public void ckSelecionadoTipoTempo (View view) {

        // Verificar qual caixa de seleção foi clicada
        switch (view.getId ()) {

            case R.id.ckHoras:
                if (ckHoras.isChecked()) {
                    ckMinutos.setChecked(false);
                }
                break;

            case R.id.ckMinutos:
                if (ckMinutos.isChecked()) {
                    ckHoras.setChecked(false);
                }
                break;
        }
    }


    public void calcular (View view) {
        if(verificaTxt()) {
            if (ckMa.isChecked()) {

                resultado = macrogota(Double.parseDouble(txtVolume.getText().toString()), Double.parseDouble(txtTempo.getText().toString()));
                resultadoaux = df.format(Math.round(resultado));

                txtResultado.setText(String.valueOf(resultadoaux) + " gts/min");

            } else {

                resultado = microgota(Double.parseDouble(txtVolume.getText().toString()), Double.parseDouble(txtTempo.getText().toString()));

                resultadoaux = df.format(Math.round(resultado));

                txtResultado.setText(String.valueOf(resultadoaux) + " gts/min");
            }
        }
    }

    public double convertHr(double minutos){
        double resultado;
        resultado = minutos / 60;
        return  resultado;
    }

    public double macrogota (double volume, double tempo){
        double resultado = 0;
        double tempoaux;
        if(ckHoras.isChecked()){
            resultado = volume / (tempo * 3);
        }else{
            tempoaux = convertHr(tempo);
            resultado = volume / (tempoaux * 3);
        }

        return resultado;
    }


    public double microgota (double volume, double tempo){
        double resultado;
        double tempoaux;

        if(ckHoras.isChecked()){
            resultado = volume / tempo;
        }else{
            tempoaux = convertHr(tempo);
            resultado = volume / tempoaux;
        }
        return resultado;
    }

    public boolean verificaTxt(){
        if (txtVolume.getText().toString().trim().isEmpty()){
            Toast.makeText(MainActivity.this, "Preencha o campo VOLUME", Toast.LENGTH_SHORT).show();
            txtVolume.requestFocus();
            return false;
        } else if(txtTempo.getText().toString().trim().isEmpty()){
            Toast.makeText(MainActivity.this, "Preencha o campo TEMPO", Toast.LENGTH_SHORT).show();
            txtTempo.requestFocus();
            return false;
        }
        return true;
    }
}
