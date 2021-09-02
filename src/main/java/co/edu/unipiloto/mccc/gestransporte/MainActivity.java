package co.edu.unipiloto.mccc.gestransporte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public abstract class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    EditText user,pass;
    Button btnEntrar, btnRegistrar;
    datoUsuario dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText) findViewById(R.id.User);
        pass=(EditText) findViewById(R.id.Pass);
        btnEntrar=(Button) findViewById(R.id.btnEntrar);
        btnRegistrar=(Button) findViewById(R.id.btnRegistrar);
        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this,"ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dato.login(u,p)==1){
                    Usuario ux =dato.getUsuario(u,p);
                    Toast.makeText(this, "Datos correctos", Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(MainActivity.this, Inicio.class);
                    i2.putExtra("Id", ux.getId());
                    startActivity(i2);
                    finish();
                }

                break;
            case R.id.btnRegistrar:
                Intent i = new Intent(MainActivity.this, Registrar.class);
                startActivity(i);
                break;
        }

    }
}