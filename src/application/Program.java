package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;


public class Program {

	public static void main(String[] args) {
		try {
			int opcao = 0;
			Scanner console = new Scanner(System.in);
			AlunoJDBC acao = new AlunoJDBC();
			
			do {
				System.out.println("####### Menu #######"
									+ "\n1 - Cadastrar"
									+ "\n2 - Listar"
									+ "\n3 - Alterar"
									+ "\n4 - Excluir"
									+ "\n5 - Sair");
				System.out.println("\n\tOpção:");
				opcao = Integer.parseInt(console.nextLine());
				
				if (opcao == 1) {
					
					Aluno a = new Aluno();
					System.out.println("\n ### Cadastrar Aluno ### \n\r");
					
					System.out.print("Nome: ");
					a.setNome(console.nextLine());
					
					System.out.print("Sexo: ");
					a.setSexo(console.nextLine());
				
					System.out.print("Data de Nascimento (dd/mm/aaaa): ");
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					a.setDt_nasc(LocalDate.parse(console.nextLine(), formato));
					
					acao.salvar(a);
					console.nextLine();
					System.out.println("\n\n\n\n");
				} else if (opcao == 2) {
					
					System.out.println("\n ### Listar Alunos ### \n\r");
					List<Aluno> alunos = acao.listar();
					for (Aluno aluno : alunos) {
						System.out.println(aluno);
					}
					console.nextLine();
				} else if (opcao == 3) {
					
					System.out.println("\n ### Alterar Aluno ### \n\r");
					
					System.out.print("ID do Aluno: ");
					int id = Integer.parseInt(console.nextLine());
					
					Aluno a = new Aluno();
					a.setId(id);
					
					System.out.print("Novo Nome: ");
					a.setNome(console.nextLine());
					
					System.out.print("Novo Sexo: ");
					a.setSexo(console.nextLine());
				
					System.out.print("Nova Data de Nascimento (dd/mm/aaaa): ");
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					a.setDt_nasc(LocalDate.parse(console.nextLine(), formato));
					
					acao.alterar(a);
					console.nextLine();
				} else if (opcao == 4) {
				
					System.out.println("\n ### Excluir Aluno ### \n\r");
					
					System.out.print("ID do Aluno: ");
					int id = Integer.parseInt(console.nextLine());
					
					acao.apagar(id);
					console.nextLine();
				}
			} while(opcao != 5);
			
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}
	
}
