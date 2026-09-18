package com.ravikishan.jpaRelationships.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class StudentRepository {

    @PersistenceContext 
    EntityManager entityManager;
    
}
