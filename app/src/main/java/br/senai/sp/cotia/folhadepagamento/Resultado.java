package br.senai.sp.cotia.folhadepagamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    private double salarioBruto, salarioLiquido, vt, va, vr, inss, impRend, descontoPlanoSaude, totalDesc;
    TextView txSalarioLiquido;
    TextView txSalarioBruto;
    TextView txDescontoPlano;
    TextView txDescontoTransporte;
    TextView txDescontoAlimentacao;
    TextView txDescontoRefeicao;
    TextView txDescontoImpRenda;
    TextView txDescontoInss;
    TextView txTotalDescontos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        txSalarioLiquido = findViewById(R.id.salarioLiquido);
        txSalarioBruto = findViewById(R.id.salarioBruto);
        txDescontoPlano = findViewById(R.id.planoSaude);
        txDescontoTransporte = findViewById(R.id.transporte);
        txDescontoAlimentacao = findViewById(R.id.alimentacao);
        txDescontoRefeicao = findViewById(R.id.refeicao);
        txDescontoImpRenda = findViewById(R.id.impostoRenda);
        txDescontoInss = findViewById(R.id.inss);
        txTotalDescontos = findViewById(R.id.totalDescontos);

        //pegando os valores passados via 'intecao'

        salarioLiquido = getIntent().getDoubleExtra("salarioLiquido", 0);
        salarioBruto = getIntent().getDoubleExtra("salarioBruto", 0);
        descontoPlanoSaude = getIntent().getDoubleExtra("descontoPlanoSaude", 0);
        vt = getIntent().getDoubleExtra("descontoTransporte", 0);
        va = getIntent().getDoubleExtra("descontoAlimentacao", 0);
        vr = getIntent().getDoubleExtra("descontoRefeicao", 0);
        impRend = getIntent().getDoubleExtra("descontoImpostoRenda", 0);
        inss = getIntent().getDoubleExtra("descontoInss", 0);
        totalDesc = getIntent().getDoubleExtra("totalDescontos", 0);

        txSalarioLiquido.setText("R$ " + String.format("%.2f", salarioLiquido));
        txSalarioBruto.setText("R$ " + String.format("%.2f", salarioBruto));
        txDescontoPlano.setText("R$ " + String.format("%.2f", descontoPlanoSaude));
        txDescontoTransporte.setText("R$ " + String.format("%.2f", vt));
        txDescontoAlimentacao.setText("R$ " + String.format("%.2f", va));
        txDescontoRefeicao.setText("R$ " + String.format("%.2f", vr));
        txDescontoImpRenda.setText("R$ " + String.format("%.2f", impRend));
        txDescontoInss.setText("R$ " + String.format("%.2f", inss));
        txTotalDescontos.setText(String.format("%.2f", totalDesc) + "%");

    }
}