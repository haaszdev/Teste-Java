import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));

        funcionarios.removeIf(func -> func.getNome().equals("João"));

        System.out.println("=== FUNCIONÁRIOS ===");
        System.out.println(String.format("%-10s | %-12s | %-12s | %-15s", "Nome", "Nascimento", "Salário", "Função"));
        System.out.println("-----------------------------------------------------");
        funcionarios.forEach(System.out::println);
        System.out.println();

        funcionarios.forEach(func -> {
            BigDecimal novoSalario = func.getSalario().multiply(new BigDecimal("1.10"));
            func.setSalario(novoSalario);
        });

        System.out.println("=== FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO ===");
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\n" + funcao + ":");
            System.out.println(String.format("%-10s | %-12s | %-12s", "Nome", "Nascimento", "Salário"));
            System.out.println("---------------------------------------");
            lista.forEach(func -> System.out.println(String.format("%-10s | %-12s | %-12s",
                    func.getNome(), func.getDataNascimentoFormatada(), func.getSalarioFormatado())));
        });
        System.out.println();

        System.out.println("=== ANIVERSARIANTES DOS MESES 10 E 12 ===");
        funcionarios.stream()
                .filter(func -> {
                    int mes = func.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(func -> System.out.println(func.getNome() + " - " + func.getDataNascimentoFormatada()));
        System.out.println();

        System.out.println("=== FUNCIONÁRIO COM MAIOR IDADE ===");
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Pessoa::getDataNascimento))
                .orElse(null);

        if (maisVelho != null) {
            System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + maisVelho.getIdade() + " anos");
        }
        System.out.println();

        System.out.println("=== FUNCIONÁRIOS EM ORDEM ALFABÉTICA ===");
        funcionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(func -> System.out.println(func.getNome() + " - " + func.getFuncao()));
        System.out.println();

        System.out.println("=== TOTAL DOS SALÁRIOS ===");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String totalFormatado = String.format("%,.2f", totalSalarios).replace(",", "X").replace(".", ",").replace("X", ".");
        System.out.println("Total: R$ " + totalFormatado);
        System.out.println();

        System.out.println("=== SALÁRIOS MÍNIMOS ===");
        System.out.println(String.format("%-10s | %-8s", "Nome", "Salários Mín."));
        System.out.println("-----------------------");
        funcionarios.forEach(func ->
                System.out.println(String.format("%-10s | %-8s", func.getNome(), func.getSalariosMinimos())));
    }
}