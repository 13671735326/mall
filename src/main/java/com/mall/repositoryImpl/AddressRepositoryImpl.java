package com.mall.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mall.entities.AddressEntity;
import com.mall.repositories.AddressRepository;

@Repository
@Transactional
public class AddressRepositoryImpl implements AddressRepository {

	@PersistenceContext
	EntityManager em;

	public void addAddress(AddressEntity address) {
		em.persist(address);
	}

	public void removeAddress(long idAddress) {
		em.remove(em.find(AddressEntity.class, idAddress));
	}

	public void updateAddress(AddressEntity address) {
		AddressEntity newAddress = em.find(AddressEntity.class, address.getId());
		newAddress.setAddress(address.getAddress());
		newAddress.setTelephone(address.getTelephone());
		em.merge(address);
	}

	public List<AddressEntity> getAddresses() {

		return em.createQuery("from AddressEntity", AddressEntity.class).getResultList();

	}

	public AddressEntity getAddress(long idAddress) {
		return em.find(AddressEntity.class, idAddress);
	}
}
