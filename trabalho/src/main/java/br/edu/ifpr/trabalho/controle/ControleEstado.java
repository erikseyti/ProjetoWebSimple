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

import br.edu.ifpr.trabalho.entidade.Estado;
import br.edu.ifpr.trabalho.repository.RepositoryEstado;

@Controller
@RequestMapping("/estado")
public class ControleEstado {
	
	@Autowired
	private RepositoryEstado repositorio;
	private List<Estado> lista;

	
	@GetMapping(value = "/novo")
	public ModelAndView novo(Estado estado) {
		ModelAndView mv = new ModelAndView("/formEstado");
		return mv;
	}
	
	@GetMapping(value = "/alterar")
	public ModelAndView alterar(@RequestParam(name="index")String index,Estado estado) {
		ModelAndView mv = new ModelAndView("/formEstado");
		mv.addObject("estado", lista.get(Integer.parseInt(index)) );
		return mv;
	}

	@PostMapping(value = "/salvar")
	public ModelAndView salvar(@Valid Estado estado, BindingResult resultado, RedirectAttributes atributos) {
		if (resultado.hasErrors()) {
			return novo(estado);
		}
		repositorio.save(estado);
		ModelAndView mv = new ModelAndView("redirect:/estado/novo");
		atributos.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		return mv;
	}
	
	@GetMapping(value = "/lista")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/listaEstado");
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
