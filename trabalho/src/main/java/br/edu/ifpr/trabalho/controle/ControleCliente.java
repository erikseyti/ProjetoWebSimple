package br.edu.ifpr.trabalho.controle;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.trabalho.entidade.Cliente;
import br.edu.ifpr.trabalho.repository.RepositoryCliente;

@Controller
@RequestMapping("/cliente")
public class ControleCliente {
	
	@Autowired
	private RepositoryCliente repositorio;
	private List<Cliente> lista;

	
	@GetMapping(value = "/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("/formCliente");
		return mv;
	}
	
	@GetMapping(value = "/alterar")
	public ModelAndView alterar(@RequestParam(name="index")String index,Cliente Cliente) {
		ModelAndView mv = new ModelAndView("/formCliente");
		mv.addObject("cliente", lista.get(Integer.parseInt(index)) );
		return mv;
	}

	@PostMapping(value = "/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult resultado, RedirectAttributes atributos) {
		if (resultado.hasErrors()) {
			return novo(cliente);
		}
		repositorio.save(cliente);
		ModelAndView mv = new ModelAndView("redirect:/cliente/novo");
		atributos.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		return mv;
	}
	
	@GetMapping(value = "/lista")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/listaCliente");
		lista = repositorio.findAll();
		mv.addObject("lista", lista);
		return mv;
	}
	
	@GetMapping(value = "/excluir")
	public ModelAndView excluir(@RequestParam(name="index")String index) {
		repositorio.delete( lista.get(Integer.parseInt(index)) );
		return listar();
	}
}
