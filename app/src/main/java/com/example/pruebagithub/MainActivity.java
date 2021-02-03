package com.example.pruebagithub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etUsuario ;
    private EditText etClave ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Probando GitHub   Commit
        etUsuario=findViewById(R.id.etUsuario);
        etClave=findViewById(R.id.etClave);


    }
    public void entrar(View view){
        String cedula= etUsuario.getText().toString();
        String clave= etClave.getText().toString();
        LoguearClases(cedula,clave);

    }
    public void insertar(View view){
        String cedula= etUsuario.getText().toString();
        String clave= etClave.getText().toString();
        InsertarClases(cedula,clave);

    }
    private void LoguearClases(String cedula, String clave){
        DataAccessPersona data = new DataAccessPersona(this);
        Persona persona = new Persona();
         persona = data.getPersonaByID(cedula,clave);
        if (persona!=null){
              Toast.makeText(getBaseContext()," Sesión Iniciada ",Toast.LENGTH_LONG).show();
              Intent intent = new Intent();
        }else{
            Toast.makeText(getBaseContext()," Error !",Toast.LENGTH_LONG).show();
        }
    }
    private void InsertarClases(String cedula, String clave){
        DataAccessPersona data = new DataAccessPersona(this);
        Persona persona = new Persona(cedula,clave);
        long conteo = data.insert(persona);
        if (conteo>0){
            Toast.makeText(getBaseContext()," Persona Insertada ",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getBaseContext()," Error !",Toast.LENGTH_LONG).show();
        }
    }
}