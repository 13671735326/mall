package com.mall.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mall.dtos.AddressDTO;
import com.mall.entities.AddressEntity;
import com.mall.entities.UserEntity;
import com.mall.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public UserEntity createUser(UserEntity userEntity) {
		em.persist(userEntity);
		return userEntity;
	}
	
	public AddressEntity createAddress(AddressEntity addressEntity) {
		em.persist(addressEntity);
		return addressEntity;
		
	}

	public void deleteUser(Long id) {
		em.remove(em.find(UserEntity.class, id));
	}

	public UserEntity updateUser(UserEntity user) {
		UserEntity newUser = em.find(UserEntity.class, user.getId());
		newUser.setNickName(user.getNickName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setGender(user.getGender());
		em.merge(newUser);
		return newUser;
	}

	public List<UserEntity> getUsers() {

		return em.createQuery("from UserEntity", UserEntity.class).getResultList();

	}

	public UserEntity getUser(Long idUser) {

		return em.find(UserEntity.class, idUser);
	}

	@Override
	public List<AddressEntity> getUserAddresses(Long id) {

		UserEntity newUser = em.find(UserEntity.class, id);
		List<AddressEntity> addresses = newUser.getAddressEntityList();
		return addresses;
	}

}
