package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {

	Connection con;
	String sql;
	PreparedStatement pst;
	
	public void salvar(Aluno a) throws SQLException {
		
		try {
			con = db.getConexao();
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?,?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date dataSql = Date.valueOf( a.getDt_nasc() );
			pst.setDate(3, dataSql);
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			pst.close();
			db.closeConexao();
		}
	}
	
	public List<Aluno> listar() {
		List<Aluno> alunos = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT * FROM alunos";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSexo(rs.getString("sexo"));
				aluno.setDt_nasc(rs.getDate("dt_nasc").toLocalDate());
				alunos.add(aluno);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar alunos: " + e.getMessage());
		}
		return alunos;
	}
	
	
	public void apagar(int id) {
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "DELETE FROM alunos WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar aluno: " + e.getMessage());
		}
	}
	
	
	public void alterar(Aluno a) {
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "UPDATE alunos SET nome = ?, sexo = ?, dt_nasc = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, a.getNome());
			stmt.setString(2, a.getSexo());
			stmt.setDate(3, java.sql.Date.valueOf(a.getDt_nasc()));
			stmt.setInt(4, a.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao alterar aluno: " + e.getMessage());
		}
	}
	
}
