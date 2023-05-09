package com.testvoicecom.managerclient.repository;

import com.testvoicecom.managerclient.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    //получение всех клиентов
    @Query("SELECT c FROM Client c JOIN FETCH c.manager as m left join fetch m.alternate where c.enabled = true")
    List<Client> getAll();
}
