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

import br.edu.ifpr.trabalho.entidade.Cidade;
import br.edu.ifpr.trabalho.repository.RepositoryCidade;

@Controller
@RequestMapping("/cidade")
public class ControleCidade {
	
	@Autowired
	private RepositoryCidade repositorio;
	private List<Cidade> lista;

	
	@GetMapping(value = "/novo")
	public ModelAndView novo(Cidade cidade) {
		ModelAndView mv = new ModelAndView("/formCidade");
		return mv;
	}
	
	@GetMapping(value = "/alterar")
	public ModelAndView alterar(@RequestParam(name="index")String index,Cidade Cidade) {
		ModelAndView mv = new ModelAndView("/formCidade");
		mv.addObject("cidade", lista.get(Integer.parseInt(index)) );
		return mv;
	}

	@PostMapping(value = "/salvar")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult resultado, RedirectAttributes atributos) {
		if (resultado.hasErrors()) {
			return novo(cidade);
		}
		repositorio.save(cidade);
		ModelAndView mv = new ModelAndView("redirect:/cidade/novo");
		atributos.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		return mv;
	}
	
	@GetMapping(value = "/lista")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/listaCidade");
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
