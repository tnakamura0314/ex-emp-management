package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報一覧を全件検索するメソッド.
 * 
 * @author nakamuratomoya
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件所得する.
	 * 
	 * @return　全件の従業員情報
	 */
	public List<Employee> showList(){
		
		return employeeRepository.findAll();
	
	}
	
}
