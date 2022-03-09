package br.senai.sp.cotia.folhadepagamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

// colocar todos dentro do extra (no clique do botão) e puxar tudo d outra pagina já feito o calculo- by intent
public class MainActivity extends AppCompatActivity {
    private EditText salario, dependentes;
    private RadioButton tipoStandard, tipoBasico, tipoSuper, tipoMaster;
    private CheckBox recebeTransp, recebeAli, recebeRef;
    private Button botao;
    private double sLiquido, totalDescontos, baseCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salario = findViewById(R.id.txSalario);
        dependentes = findViewById(R.id.txDependentes);
        tipoStandard = (RadioButton) findViewById(R.id.tipostandard);
        tipoBasico = (RadioButton) findViewById(R.id.tipobasico);
        tipoSuper = (RadioButton) findViewById(R.id.tiposuper);
        tipoMaster = (RadioButton) findViewById(R.id.tipomaster);
        recebeTransp = findViewById(R.id.valeTransp);
        recebeAli = findViewById(R.id.valeAli);
        recebeRef = findViewById(R.id.valeRef);

    }

    public void irparasegunda(View view) {

        if (salario.getText().toString().isEmpty()) {
            salario.setError(getString(R.string.valida_salario)); //obrigando a preencher
            Toast.makeText(getBaseContext(), R.string.valida_salario, Toast.LENGTH_SHORT).show(); // mensagem de validacao
        } else if (dependentes.getText().toString().isEmpty()) {
            dependentes.setError(getString(R.string.valida_dependentes)); //obrigando a preencher
            Toast.makeText(getBaseContext(), R.string.valida_dependentes, Toast.LENGTH_SHORT).show();
        } else {

            Intent intecao = new Intent(this, Resultado.class);

            double valorSalario = Double.parseDouble(salario.getText().toString());

            double depen = Double.parseDouble(dependentes.getText().toString());

            //SB –INSS -(189,59  *  número  de  dependentes).
            baseCalc = valorSalario - inss() - 189.59 * depen;

            sLiquido = valorSalario - descontoPlano() - valeTransporte() - valeAlimentacao() - valeRef() - impRenda() - inss();

            totalDescontos = (descontoPlano() + valeTransporte() + valeAlimentacao() + valeRef() + impRenda() + inss()) * 100 / valorSalario;

            intecao.putExtra("salarioLiquido", sLiquido);
            intecao.putExtra("salarioBruto", valorSalario);
            intecao.putExtra("descontoPlanoSaude", descontoPlano());
            intecao.putExtra("descontoTransporte", valeTransporte());
            intecao.putExtra("descontoAlimentacao", valeAlimentacao());
            intecao.putExtra("descontoRefeicao", valeRef());
            intecao.putExtra("descontoImpostoRenda", impRenda());
            intecao.putExtra("descontoInss", inss());
            intecao.putExtra("totalDescontos", totalDescontos);
            //finish();

            startActivity(intecao);
        }

    }


    public double descontoPlano() {

        double descontoPlano;
        double valorSalario = Double.parseDouble(salario.getText().toString());
        double valorDependentes = Double.parseDouble(dependentes.getText().toString());

        if (valorSalario <= 3000) {
            if (tipoStandard.isChecked()) {
                descontoPlano = 60;

            } else if (tipoBasico.isChecked()) {
                descontoPlano = 80;

            } else if (tipoSuper.isChecked()) {
                descontoPlano = 95;

            } else {
                descontoPlano = 130;
            }

        } else {
            if (tipoStandard.isChecked()) {
                descontoPlano = 80;

            } else if (tipoBasico.isChecked()) {
                descontoPlano = 110;

            } else if (tipoSuper.isChecked()) {
                descontoPlano = 135;

            } else {
                descontoPlano = 180;
            }
        }
        return descontoPlano;

    }

    public double valeTransporte() {

        double valorSalario = Double.parseDouble(salario.getText().toString());

        if (recebeTransp.isChecked()) {

            return valorSalario * 0.06;
        }
        return 0;
    }

    public double valeAlimentacao() {

        double valorSalario = Double.parseDouble(salario.getText().toString());

        if (recebeAli.isChecked()) {

            if (valorSalario <= 3000) {
                return 15;
            } else if (valorSalario <= 5000) {
                return 25;
            } else {
                return 35;
            }
        } else {
            return 0;
        }
    }

    public double valeRef() {

        double valorSalario = Double.parseDouble(salario.getText().toString());

        if (recebeRef.isChecked()) {

            if (valorSalario <= 3000) {
                return 2.60 * 22;
            } else if (valorSalario <= 5000) {
                return 3.65 * 22;
            } else {
                return 6.50 * 22;
            }
        } else {
            return 0;
        }
    }

    public double impRenda() {

        if (baseCalc <= 1903.98) {
            return 0;
        } else if (baseCalc < 2826.65) {
            return (baseCalc * 0.075) - 142.80;
        } else if (baseCalc < 3751.05) {
            return (baseCalc * 0.15) - 354.80;
        } else if (baseCalc < 4664.68) {
            return (baseCalc * 0.225) - 636.13;
        } else {
            return (baseCalc * 0.275) - 869.36;
        }

    }

    public double inss() {
        double valorSalario = Double.parseDouble(salario.getText().toString());

        if (valorSalario <= 1212) {
            return (valorSalario * 0.075);
        } else if (valorSalario < 2427.35) {
            return (valorSalario * 0.09) - 18.18;
        } else if (valorSalario < 3641.03) {
            return (valorSalario * 0.12) - 91;
        } else if (valorSalario < 7087.22) {
            return (valorSalario * 0.14) - 163.82;
        } else {
            return 828.39;
        }

    }


}