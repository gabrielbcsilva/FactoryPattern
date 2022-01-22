package modelo;

public class Usuario {
 private Integer id;
 private String nome;
 private String cpf;
 
 
public Usuario(String nome, String cpf) {
	this.nome = nome;
	this.cpf = cpf;
}
public Usuario(Integer id,String nome, String cpf) {
	this.id = id;
	this.nome = nome;
	this.cpf = cpf;
}


public String getNome() {
	return nome;
}


public String getCpf() {
	return cpf;
}


public void setId(Integer id) {
	this.id = id;
}

@Override
public String toString() {
	return String.format("Retorno: %d, %s, %s ",this.id,this.nome, this.cpf);
}


 
}
