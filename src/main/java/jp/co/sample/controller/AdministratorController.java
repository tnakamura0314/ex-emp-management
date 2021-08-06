package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者登録画面を表示する処理を記述するコントローラー.
 * 
 * @author nakamuratomoya
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * 管理者登録画面にフォワードするメソッド.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form 管理者情報登録時に使用するフォーム
	 * @return ログイン画面にリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {

		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);

		administratorService.insert(administrator);

		return "redirect:/";
	}

	/**
	 * ログイン画面にフォワードするメソッド.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "/administrator/login";
	}

	/**
	 * ログイン後のページにユーザ名を表示するメソッド.
	 * 
	 * @param form ログイン時に使用するフォーム
	 * @param model requestスコープ
	 * @return 従業員情報一覧ページ
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {

		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

		model.addAttribute("administrator", administrator);
		
		if(administrator == null) {
			model.addAttribute("administrator", "メールアドレスまたはパスワードが不正です。");
			return "administrator/login";
		}
		
		session.setAttribute("administratorName", administrator);
		
		return "forward:/employee/showList";
	
	}

}
