package com.example.hibernatetest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hibernatetest.entity.UserEntity;
import com.example.hibernatetest.exceptions.TransactionFailureException;
import com.example.hibernatetest.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	/* Test 1 — no exception thrown, everything committed
	 * Outcome :  Happy case
	*/
	
	@Transactional
	public void executeTransactionFirstCase() {
		
		for(int i=1;i<=5;i++) {
			executeChildTransactionFirstCase(i);
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishabh");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void executeChildTransactionFirstCase(int i){
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishi");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	/*
	 * Test 2 — exception thrown in an inner transaction,
	 *  no handling in outer transaction
	 *  
	 *  Outcome: Everything got rollbacked
	 */
	
	
	@Transactional
	public void executeTransactionSecondCase() {
		for(int i=1;i<=5;i++) {
			executeChildTransactionSecondCase(i);
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishabh");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void executeChildTransactionSecondCase(int i){
		if(i==3) {
			throw new TransactionFailureException("Data");
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishi");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	/*
	 * Test 3— exception thrown in an inner transaction, 
	 * noRollbackFor=TransactionFailureException
	 *  in outer transaction
	 *  
	 *  Outcome :  exception throw on 3 item, 
	 *  last 2 will persist in this scenario
	 */

	@Transactional(noRollbackFor=TransactionFailureException.class)
	public void executeTransactionThirdCase() {
		for(int i=1;i<=5;i++) {
			executeChildTransactionThirdCase(i);
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishabh");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void executeChildTransactionThirdCase(int i){
		if(i==3) {
			throw new TransactionFailureException("Data");
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishi");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	/*
	 * Test 4— exception thrown in an inner transaction,
	 *  try/catch block in outer transaction
	 * 
	 */
	
	@Transactional
	public void executeTransactionFourthCase() {
		try {
			for(int i=1;i<=5;i++) {
				executeChildTransactionFourthCase(i);
			}
			UserEntity user =  new UserEntity();
			user.setFirstName("Rishabh");
			user.setLastName("Tiwari");
			userRepo.save(user);
		}catch(TransactionFailureException e) {
			e.printStackTrace();
		}
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void executeChildTransactionFourthCase(int i){
		if(i==3) {
			throw new TransactionFailureException("Data");
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishi");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	
	/*
	 * Test 5 — exception (ObjectOptimisticLockingFailureException)
	 *  thrown in an inner transaction,
	 *  no handling in outer transaction
	 *  
	 *  Outcome: Everything got rollbacked
	 */
	
	
	@Transactional
	public void executeTransactionFifthCase() {
		for(int i=1;i<=5;i++) {
			executeChildTransactionFifthCase(i);
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishabh");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void executeChildTransactionFifthCase(int i){
		if(i==3) {
			throw new ObjectOptimisticLockingFailureException(UserEntity.class,i);
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishi");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	
	
	/*
	 * Test 6— exception thrown in an inner transaction, 
	 * noRollbackFor=TransactionFailureException
	 *  in outer transaction
	 *  
	 *  Outcome :  exception throw on 3 item, 
	 *  last 2 will persist in this scenario
	 */

	@Transactional(noRollbackFor=ObjectOptimisticLockingFailureException.class)
	public void executeTransactionSixthCase() {
		for(int i=1;i<=5;i++) {
			executeChildTransactionSixthCase(i);
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishabh");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void executeChildTransactionSixthCase(int i){
		if(i==3) {
			throw new ObjectOptimisticLockingFailureException(UserEntity.class,"Data");
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishi");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
	
	
	
	/*
	 * Test 7—
	 * 
	 * Trying to update one item in outer transaction
	 * 
	 * exception thrown in an inner transaction, 
	 * noRollbackFor=TransactionFailureException
	 * in outer transaction
	 *  
	 * Outcome :  exception throw on 3 item, 
	 * last 2 will persist in this scenario
	 */

	@Transactional(noRollbackFor = TransactionFailureException.class)
	public void executeTransactionSeventhCase() {
		
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishabh");
		user.setLastName("Tiwari");
		UserEntity userEntity = userRepo.save(user);
		userEntity.setIsNew("Y");
		userRepo.save(userEntity);
		
		for(int i=1;i<=5;i++) {
			executeChildTransactionSeventhCase(i);
		}
		
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void executeChildTransactionSeventhCase(int i){
		if(i==3) {
			throw new TransactionFailureException("Data");
		}
		UserEntity user =  new UserEntity();
		user.setFirstName("Rishi");
		user.setLastName("Tiwari");
		userRepo.save(user);
	}
}
