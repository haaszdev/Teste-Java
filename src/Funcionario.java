import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getSalarioFormatado() {
        return String.format("%,.2f", salario).replace(",", "X").replace(".", ",").replace("X", ".");
    }

    public String getSalariosMinimos() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        BigDecimal quantidade = salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
        return String.format("%.2f", quantidade).replace(".", ",");
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-12s | %-12s | %-15s",
                getNome(),
                getDataNascimentoFormatada(),
                getSalarioFormatado(),
                funcao);
    }
}