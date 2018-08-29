package ar.edu.utn.frsf.isi.julian.bancolab01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

import ar.edu.utn.frsf.isi.julian.bancolab01.modelo.Cliente;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pf = new PlazoFijo(this.getResources().getStringArray(R.array.tasas));
        cliente = new Cliente();

        btnHacerPlazoFijo = (Button) findViewById(R.id.btnHacerPF);
        edtMonto = (EditText) findViewById(R.id.edtMonto);
        edtMail = (EditText) findViewById(R.id.edtMail);
        edtCuit = (EditText) findViewById(R.id.edtCuit);
        optMoneda = (RadioGroup) findViewById(R.id.optMoneda);
        seekDias = (SeekBar) findViewById(R.id.seekDias);
        swAvisarVencimiento = (Switch) findViewById(R.id.swAvisarVencimiento);
        togAccion = (ToggleButton) findViewById(R.id.togAccion);
        chkAceptoTerminos = (CheckBox) findViewById(R.id.chkAceptoTerminos);

        btnHacerPlazoFijo.setEnabled(false);
    }
}
