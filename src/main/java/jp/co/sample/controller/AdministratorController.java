package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
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

	/**
	 * InsertAdministratorFormオブジェクトを自動でModelオブジェクトに格納するメソッド.
	 * 
	 * @return
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * administrator/insert.htmlにフォワードするメソッド.
	 * 
	 * @return administrator/insert.htmlを返す
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}

	/**
	 * 管理者情報を登録するinsertメソッド.
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

}
