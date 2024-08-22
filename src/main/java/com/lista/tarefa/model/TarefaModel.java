package com.lista.tarefa.model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TarefaModel {
	private int id;
	private String titulo;
	private String descricao;
	private boolean status;
	private String data;
	private Date dataAtual;


	public TarefaModel() {
		this.data = getData();
	}



	public TarefaModel(TarefaModel tarefa) {
		this.id = tarefa.getId();
		this.titulo = tarefa.getTitulo();
		this.descricao = tarefa.getDescricao();
		this.status = tarefa.getStatus();
		this.data = tarefa.getData();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getData() {
		SimpleDateFormat dateFormated = new SimpleDateFormat("dd/MM/YYY");
		Date dataAtual = new Date();
		String dataSTR = dateFormated.format(dataAtual);
		return dataSTR;
	}

	@Override
	public String toString() {
		return "TarefaModel{" +
				"id=" + id +
				", titulo='" + titulo + '\'' +
				", descricao='" + descricao + '\'' +
				", status=" + status +
				", data='" + data + '\'' +
				", dataAtual=" + dataAtual +
				'}';
	}
}
