package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.controller.TaskController;
import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		try {
			controller.save(todo);
			Assert.fail("Não deveria chegar aqui ");
		} catch (ValidationException e) {
			Assert.assertEquals("Preencha a descrição da tarefa", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarSemDataDePreenchimento() {
		Task todo = new Task();
		todo.setTask("descricao");
		try {
			controller.save(todo);
			Assert.fail("Não deveria chegar aqui");
		} catch (ValidationException e) {
			Assert.assertEquals("Preencha a data de vencimento", e.getMessage());
		}
	}
	
	
	@Test
	public void naoDeveSalvarComDataPassada() {
		LocalDate date  = LocalDate.of(2010, 01, 01);
		Task todo  = new Task();
		todo.setTask("descricao");
		todo.setDueDate(date);
		try {
			controller.save(todo);
			Assert.fail("Não deveria chegar aqui");
		} catch (ValidationException e) {
			Assert.assertEquals("A data de vencimento não deve ser no passado", e.getMessage());
		}
		
	}
	
	
	@Test
	public void deveSalvarComSucesso() throws ValidationException {
		Task todo = new Task();
		todo.setTask("descricao");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		Mockito.verify(taskRepo).save(todo);
		
	}

}
