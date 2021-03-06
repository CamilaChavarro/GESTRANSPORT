package co.edu.unipiloto.mccc.gestransporte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity implements View.OnClickListener {
    EditText us, pas, nom, ap;
    Button reg, can;
    datoUsuario dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);
        us=(EditText) findViewById(R.id.RegUser);
        pas=(EditText) findViewById(R.id.RegPass);
        nom=(EditText) findViewById(R.id.RegNombre);
        ap=(EditText) findViewById(R.id.RegApellido);
        reg=(Button) findViewById(R.id.btnRegistrar);
        can=(Button) findViewById(R.id.btnRegCancelar);
        reg.setOnClickListener(this);
        can.setOnClickListener(this);
        dato=new datoUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegRegistrar:
                Usuario u = new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellido(ap.getText().toString());
                if(!u.isNull()){
                    Toast.makeText(this,"ERROR: Campos Vacios", Toast.LENGTH_LONG).show();
                }else if (dato.insertUsuario(u)){
                    Toast.makeText(this,"Registro Exitoso!!!", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent (Registrar.this,MainActivity.class);
                    startActivity(i2);
                    finish();
                }else {
                    Toast.makeText(this,"Ususario ya registrado!!!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnRegCancelar:
                Intent i = new Intent(Registrar.this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
