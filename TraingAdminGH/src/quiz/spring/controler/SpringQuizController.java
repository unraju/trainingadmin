package quiz.spring.controler;


/**
 * @author raju
 */
//@Controller
//@SessionAttributes
public class SpringQuizController {
/*	@Autowired(required = true)
	private QuizService quizService;
	//ModelAndView mav = new ModelAndView();

	//@RequestMapping(value = "/preManageQuiz")
	public ModelAndView preAddQuiz() {

		mav.setViewName("quiz/ui/managequiz");
		mav.addObject("quizlist", quizService.fetchQuizDetails());
		mav.addObject("quizTO", new QuizTO());
		return mav;
		
		 * return new ModelAndView("quiz/ui/managequiz", "quizTO", new
		 * QuizTO());
		 

	}

	@RequestMapping(value = "/saveQuiz")
	public ModelAndView addQuiz(@ModelAttribute("quizTO") @Valid QuizTO quizTO, BeanPropertyBindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		
		String actions = (String) request.getParameter("actions");
		if(actions != null && actions.equalsIgnoreCase("Cancel"))
		{
			mav.setViewName("quiz/ui/managequiz");
			mav.addObject("quizlist", quizService.fetchQuizDetails());
			return mav;
		}
		
		if (result.hasErrors()) {
			mav.setViewName("quiz/ui/managequiz");
			mav.addObject("quizlist", quizService.fetchQuizDetails());
			mav.addObject("quizTO", quizTO);
			return mav;
		}
		quizService.addQuiz(quizTO);
		
		mav.addObject("quizlist", quizService.fetchQuizDetails());
		return mav;

	}
	@RequestMapping(value = "/preUpdateQuiz")
	public ModelAndView updateQuiz(HttpServletRequest request, HttpServletResponse response) {
		String quidId = (String) request.getParameter("id");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("quiz/ui/managequiz");
		mav.addObject("quizlist", quizService.fetchQuizDetails());
		mav.addObject("quizTO", quizService.fetchQuiz(Long.parseLong(quidId)).getQuizTO());
		return mav;

	}
	@RequestMapping(value = "/updateQuiz")
	public String updateQuiz(@ModelAttribute("quizTO") @Valid QuizTO quizTO, BeanPropertyBindingResult result) {

		if (result.hasErrors()) {
			return "addmodule";
		}
		quizService.updateQuiz(quizTO);
		return "redirect:/module.dsc";

	}

	@RequestMapping(value = "/deleteQuiz")
	public String deleteQuiz(@ModelAttribute("quizTO") @Valid QuizTO quizTO, BeanPropertyBindingResult result) {

		if (result.hasErrors()) {
			return "addmodule";
		}
		quizService.deleteQuiz(quizTO);
		return "redirect:/module.dsc";

	}*/

}
