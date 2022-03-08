package br.senai.sp.cotia.folhadepagamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Resultado extends AppCompatActivity {

    //declarar as variaveis
    private double salarioBruto, salarioLiquido, vt, va, vr, inss, impRend, descPlano;


    //SB –INSS -(189,59  *  número  de  dependentes).

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);



        //pegando os valores passados via 'intecao'

        salarioBruto = getIntent().getDoubleExtra("salario",0);
        vt = getIntent().getDoubleExtra("vt", 0);
      //  imc = getIntent().getDoubleExtra("imc", 0);
       // genero = getIntent().getStringExtra("genero");
    }
}