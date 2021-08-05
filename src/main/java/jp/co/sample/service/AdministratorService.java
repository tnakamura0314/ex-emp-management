package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービスクラス.
 * 
 * @author nakamuratomoya
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * 管理者情報を登録するメソッド.<br>
	 * administratorRepositoryのinsert()メソッドを呼ぶ処理
	 * 
	 * @param administrator　
	 */
	public void insert(Administrator administrator) {
		
		administratorRepository.insert(administrator);
		
	}

}
