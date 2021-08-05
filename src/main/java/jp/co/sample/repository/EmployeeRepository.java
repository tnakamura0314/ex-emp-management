package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリ.
 * 
 * @author nakamuratomoya
 *
 */
@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	/**
	 * Employeeオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		
		return employee;
		
	};
	
	
	/**
	 * 従業員一覧情報を入社日順（降順）で取得する.<br>
	 * また、従業員が存在しない場合にはサイズ０件の従業員一覧を返す
	 * 
	 * @return　従業員一覧情報
	 */
	public List<Employee> findAll(){
		
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees ORDER BY hire_date DESC;";
		
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		
		if (employeeList.size() == 0) {
			return null;
		}
		
		return employeeList;
		
	}
	
	/**
	 * 主キーから従業員情報を取得する.<br>
	 * 従業員が存在しない場合は、Springが自動的に例外を発生させます
	 * 
	 * @param id ID
	 * @return　主キー対応の従業員情報
	 */
	public Employee load(Integer id) {
		
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees WHERE id=:id ;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
	}
	
	
	/**
	 * 従業員の情報を更新する.<br>
	 * また、IDカラムを除いた全てのカラムを更新できるSQL文
	 * 
	 * @param employee　従業員ドメイン
	 * @return　主キーと一致した従業員情報を更新
	 */
	public void update(Employee employee) {
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		
		String sql = "UPDATE employees SET name=:name, image=:image, gender=:gender, hire_date=:hireDate, mail_address=:mailAddress, zip_code=:zipCode, address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, dependents_count=:dependentsCount WHERE id=:id;";
		
		template.update(sql, param);
	}

}
