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
    private static List<TarefaModel> tarefasConcluidas = new ArrayList<>();
    public TarefasController() {
        listTarefas = new ArrayList<>();
        tarefasConcluidas = new ArrayList<>();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarTarefas(@RequestBody TarefaModel addTarefa) {
        if(addTarefa.getId() == 0){
            addTarefa.setId(listTarefas.size() + 1);
        }
        boolean status = listTarefas.add(new TarefaModel(addTarefa));
        if (status) {
            return new ResponseEntity<>("Tarefa adicionada com sucesso!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Erro ao adicionar uma tarefa!", HttpStatus.CONFLICT);
        }
    }

    private static int findById(int id) {
        int size = 0;
        for (TarefaModel tarefa : listTarefas) {
            size++;
            if (tarefa.getId() == id) {
                return size - 1;
            }
        }
        return 0;
    }

    private static void concluir( int id){
        int size = findById(id);

        for(TarefaModel tarefa : listTarefas){

            if(tarefa.getId() == id){
                tarefa.setStatus(true);
                listTarefas.set(size, tarefa);
                break;
            }
        }

    }
    @PostMapping("/concluirid{id}")
    public ResponseEntity<String>concluirTarefa(@PathVariable int id){
        concluir(id);
        return new ResponseEntity<>("Tarefa concluida com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/deletar{id}")
    public ResponseEntity<String> deletarTarefa(@PathVariable int id) {
        int index = findById(id);
        int sizeAtual = listTarefas.size();
        listTarefas.remove(index);
        int novoSize = listTarefas.size() ;
        if(sizeAtual == novoSize) {
            return new ResponseEntity<>("Não foi possivel remover a tarefa!", HttpStatus.CONFLICT);
        } else if(novoSize < sizeAtual){
            return new ResponseEntity<>("Tarefa removida com sucesso!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Erro desconhecido", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/update")
    public static void atualizarTarefa(@RequestBody TarefaModel updateTarefa) {
        System.out.println("Teste");
        int index = findById(updateTarefa.getId());
        listTarefas.set(index, updateTarefa);
    }

    @GetMapping("/tarefas")
    public ResponseEntity<List> retonarTarefa() {
        if(listTarefas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(listTarefas, HttpStatus.OK);
        }

    }

    public static void main(String[] args) {
        if(listTarefas.isEmpty()){
            System.out.println("Estou vazio");
        }

    }
}



