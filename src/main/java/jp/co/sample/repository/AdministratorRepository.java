package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ
 * 
 * @author nakamuratomoya
 *
 */
@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Administratorオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {

		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));

		return administrator;

	};

	/**
	 * 渡した管理者情報を保存する
	 * 
	 * @param administrator 管理者情報
	 * @return 追加された管理者情報
	 */
	public void insert(Administrator administrator) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		String insertSql = "INSERT INTO administrators (name, mail_addres, password) VALUES (:name, :mailAddress, :password);";

		template.update(insertSql, param);

	}

	/**
	 * メールアドレスとパスワードから1件の管理者情報を取得する メールアドレスが一意のため
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return １件の管理者情報を返す
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {

		String sql = "SELECT id, name, mail_address, password FROM administrators WHERE mail_address LIKE :mailAddress AND password LIKE :password ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + mailAddress + "%")
				.addValue("password", "%" + password + "%");
		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);

		if (administrator == null) {
			return null;
		}
		return administrator;

	}

}
