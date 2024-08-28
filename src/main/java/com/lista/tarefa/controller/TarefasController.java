package com.lista.tarefa.controller;

import com.lista.tarefa.model.TarefaModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TarefasController {
    private static List<TarefaModel> listTarefas = new ArrayList<>();

    public TarefasController() {
        listTarefas = new ArrayList<>();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarTarefas(@RequestBody TarefaModel addTarefa) {
        if (addTarefa.getId() == 0) {
            addTarefa.setId(listTarefas.size() + 1);
        }
        boolean status = listTarefas.add(new TarefaModel(addTarefa));
        if (status) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    private static int findById(int id) {
        int size = 0;
        for (TarefaModel tarefa : listTarefas) {
            size++;
            System.out.println(tarefa.getId());
            if (tarefa.getId() == id) {
                return size - 1;
            }
        }
        return size;
    }

    @PostMapping("/concluirid{id}")
    public ResponseEntity<HttpStatus> concluirTarefa(@PathVariable int id) {
        listTarefas.remove(findById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deletar{id}")
    public ResponseEntity<HttpStatus> deletarTarefa(@PathVariable int id) {
        int index = findById(id);
        int sizeAtual = listTarefas.size();
        listTarefas.remove(index);
        int novoSize = listTarefas.size();
        if (sizeAtual == novoSize) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if (novoSize < sizeAtual) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/tarefas")
    public ResponseEntity<List> retonarTarefa() {
        if (listTarefas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listTarefas, HttpStatus.OK);
        }

    }
}



