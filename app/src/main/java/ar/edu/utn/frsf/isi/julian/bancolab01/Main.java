package ar.edu.utn.frsf.isi.julian.bancolab01;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import ar.edu.utn.frsf.isi.julian.bancolab01.modelo.Cliente;
import ar.edu.utn.frsf.isi.julian.bancolab01.modelo.Moneda;
import ar.edu.utn.frsf.isi.julian.bancolab01.modelo.PlazoFijo;

public class Main extends AppCompatActivity {

    private PlazoFijo pf;
    private Cliente cliente;

    private Button btnHacerPlazoFijo;
    private EditText edtMonto;
    private EditText edtMail;
    private EditText edtCuit;
    private RadioGroup optMoneda;
    private SeekBar seekDias;
    private Switch swAvisarVencimiento;
    private ToggleButton togAccion;
    private CheckBox chkAceptoTerminos;
    private TextView setPlazo;
    private TextView mostrarIntereses;
    private TextView tvMail;
    private TextView tvCuil;
    private TextView tvMonto;
    private TextView tvMensajes;
    private ToggleButton btnRenovar;
    private RadioButton optPeso;
    private RadioButton optDolar;
    private TextView tvPlazoCorrecto;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pf = new PlazoFijo(this.getResources().getStringArray(R.array.tasas));
        cliente = new Cliente();

        btnHacerPlazoFijo = findViewById(R.id.btnHacerPF);
        edtMonto = findViewById(R.id.edtMonto);
        edtMail = findViewById(R.id.edtMail);
        edtCuit = findViewById(R.id.edtCuit);
        optMoneda = findViewById(R.id.optMoneda);
        seekDias = findViewById(R.id.seekDias);
        swAvisarVencimiento = findViewById(R.id.swAvisarVencimiento);
        togAccion = findViewById(R.id.togAccion);
        chkAceptoTerminos = findViewById(R.id.chkAceptoTerminos);
        setPlazo = findViewById(R.id.tvDiasSeleccionados);
        mostrarIntereses = findViewById(R.id.tvIntereses);
        tvMail = findViewById(R.id.tvCorreo);
        tvCuil = findViewById(R.id.tvCuit);
        tvMonto = findViewById(R.id.tvMonto);
        tvMensajes = findViewById(R.id.edtMensajes);
        btnRenovar = (ToggleButton) findViewById(R.id.togAccion);
        optPeso = (RadioButton) findViewById(R.id.optPesos);
        optDolar= (RadioButton) findViewById(R.id.optDolar);
        tvPlazoCorrecto=(TextView) findViewById(R.id.tvPlazoCorrecto) ;

        btnHacerPlazoFijo.setEnabled(false);

        pf.setDias(10);
        seekDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setPlazo.setText(getResources().getString(R.string.resultado1)+ " " + (progress+10) + " " + getResources().getString(R.string.resultado2));
                pf.setDias(progress+10);
                if(edtMonto.getText().toString().isEmpty()){
                    mostrarIntereses.setText(getResources().getString(R.string.lblIntereses));
                }else {

                    pf.setMonto(Double.valueOf(edtMonto.getText().toString()));
                    mostrarIntereses.setText(pf.intereses().toString());
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        chkAceptoTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnHacerPlazoFijo.setEnabled(true);
                } else {
                    btnHacerPlazoFijo.setEnabled(false);
                    Toast toast1 = Toast.makeText(getApplicationContext(),getResources().getString(R.string.mensajeATyC), Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER);

                    toast1.show();
                }
            }
        });

        optMoneda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                String text = checkedRadioButton.getText().toString();

                if(text == optDolar.getText().toString() ){
                    pf.setMoneda(Moneda.DOLAR);
                }

                if(text == optPeso.getText().toString() ){
                    pf.setMoneda(Moneda.PESO);
                }
            }
        });

        btnHacerPlazoFijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean critico=false;
                cliente.setMail(edtMail.getText().toString());
                cliente.setCuil(edtCuit.getText().toString());
                if(edtMail.getText().toString().isEmpty()){
                    tvMail.setTextColor(Color.RED);
                    critico = true;
                }
                if(edtCuit.getText().toString().isEmpty()){
                    tvCuil.setTextColor(Color.RED);
                    critico = true;
                }
                if(edtMonto.getText().toString().isEmpty()){
                    tvMonto.setTextColor(Color.RED);
                    critico = true;
                }

                if(critico){
                    Toast.makeText(Main.this,getResources().getString(R.string.mensajeErrorPF), Toast.LENGTH_SHORT).show();
                    tvMensajes.setText("");
                    tvPlazoCorrecto.setText("");
                }else{
                    tvMonto.setTextColor(Color.GRAY);
                    tvCuil.setTextColor(Color.GRAY);
                    tvMail.setTextColor(Color.GRAY);
                    pf.setAvisarVencimiento(swAvisarVencimiento.isChecked());
                    pf.setRenovarAutomaticamente(togAccion.isChecked());
                    pf.setCliente(cliente);
                    tvPlazoCorrecto.setTextColor(Color.GREEN);
                    tvPlazoCorrecto.setText(getResources().getText(R.string.tvPlazoFijocorrecto));
                    tvMensajes.setTextColor(Color.GREEN);
                    tvMensajes.setText(pf.toString());

                }

            }
        });
    }



    
}
