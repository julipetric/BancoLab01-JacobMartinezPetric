package ar.edu.utn.frsf.isi.julian.bancolab01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.edu.utn.frsf.isi.julian.bancolab01.modelo.Cliente;
import ar.edu.utn.frsf.isi.julian.bancolab01.modelo.PlazoFijo;

public class Main extends AppCompatActivity {

    private PlazoFijo pf;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pf = new PlazoFijo(this.getResources().getStringArray(R.array.tasas));
        cliente = new Cliente();
    }
}
