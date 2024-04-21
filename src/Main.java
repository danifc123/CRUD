import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<String[]> studentsData = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Importar Arquivo");
            System.out.println("2. Listar Dados");
            System.out.println("3. Inserir Registro");
            System.out.println("4. Alterar Registro");
            System.out.println("5. Excluir Registro");
            System.out.println("6. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (choice) {
                case 1:
                    importFile(scanner);
                    break;
                case 2:
                    displayData();
                    break;
                case 3:
                    insertRecord(scanner);
                    break;
                case 4:
                    updateRecord(scanner);
                    break;
                case 5:
                    deleteRecord(scanner);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void importFile(Scanner scanner) {
        System.out.println("Digite o caminho do arquivo CSV:");
        String filePath = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                studentsData.add(parts);
            }
            System.out.println("Arquivo importado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao importar arquivo: " + e.getMessage());
        }
    }

    private void displayData() {
        if (studentsData.isEmpty()) {
            System.out.println("Não há dados para exibir.");
            return;
        }

        for (String[] student : studentsData) {
            for (String info : student) {
                System.out.print(info + "\t");
            }
            System.out.println();
        }
    }

    private void insertRecord(Scanner scanner) {
        System.out.println("Digite os dados do novo registro (separados por vírgula):");
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        studentsData.add(parts);
        System.out.println("Registro inserido com sucesso!");
    }

    private void updateRecord(Scanner scanner) {
        System.out.println("Digite o índice do registro que deseja alterar:");
        int index = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (index >= 0 && index < studentsData.size()) {
            System.out.println("Digite os novos dados do registro (separados por vírgula):");
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            studentsData.set(index, parts);
            System.out.println("Registro alterado com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private void deleteRecord(Scanner scanner) {
        System.out.println("Digite o índice do registro que deseja excluir:");
        int index = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (index >= 0 && index < studentsData.size()) {
            studentsData.remove(index);
            System.out.println("Registro excluído com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }
}
