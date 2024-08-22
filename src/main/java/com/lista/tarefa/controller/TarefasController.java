package com.lista.tarefa.controller;

import com.lista.tarefa.model.TarefaModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TarefasController {
    public TarefasController() {
        listTarefas = new ArrayList<>();
    }

    private static List<TarefaModel> listTarefas = new ArrayList<>();

    @PostMapping("/cadastrar")
    public String cadastrarTarefas(@RequestBody TarefaModel addTarefa) {
        listTarefas.add(new TarefaModel(addTarefa));
        System.out.println(listTarefas);
        return "Tarefa adicionada com sucesso!";

    }

    private static TarefaModel findById(int id) {
        for (TarefaModel tarefa : listTarefas) {
            if (tarefa.getId() == id) {
                return tarefa;
            }
        }
        return null;
    }

    public void atualizarTarefa(@RequestBody int id, TarefaModel updateTarefa) {
        TarefaModel tarefa = findById(id);
        System.out.println(tarefa);

    }

    @GetMapping("/tarefas")
    public ResponseEntity<List> retonarTarefa() {
        return new ResponseEntity<>(listTarefas, HttpStatus.OK);
    }

    public static void main(String[] args) {
        TarefaModel tarefa1 = new TarefaModel();
        tarefa1.setId(1);
        tarefa1.setTitulo("Tarefa 1");
        tarefa1.setDescricao("Tarefa 1");
        tarefa1.setStatus(false);
        TarefaModel tarefa2 = new TarefaModel();
        tarefa2.setId(2);
        tarefa2.setTitulo("Tarefa 2");
        tarefa2.setDescricao("Tarefa 1");
        tarefa2.setStatus(false);
        TarefaModel tarefa3 = new TarefaModel();
        tarefa3.setId(3);
        tarefa3.setTitulo("Tarefa 2");
        tarefa3.setDescricao("Tarefa 1");
        tarefa3.setStatus(false);
        listTarefas.add(new TarefaModel(tarefa1));
        listTarefas.add(new TarefaModel(tarefa2));
        listTarefas.add(new TarefaModel(tarefa3));
        TarefaModel tarefa = findById(3);
        System.out.println(tarefa);
    }

}



