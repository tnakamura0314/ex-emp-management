package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を検索や更新に使用する.
 * 
 * @author nakamuratomoya
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	
	/**
	 * 従業員一覧を取得.
	 * 
	 * @param model requestスコープ
	 * @return　従業員一覧
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		
		List<Employee> employeeList = employeeService.showList();
		
		model.addAttribute("employeeList", employeeList);
		
		return "/employee/list";
		
	}
	
	/**
	 * 従業員詳細情報を取得.
	 * 
	 * @param id　従業員ID
	 * @param model　requestスコープ
	 * @return 従業員詳細情報
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {

		int id2 = Integer.parseInt(id);
		
		Employee employee = employeeService.showDetail(id2);
		
		model.addAttribute("employee", employee);
		
		return "/employee/detail";
		
	}
	
	

}
