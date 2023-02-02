package com.testvoicecom.managerclient.repository;

import com.testvoicecom.managerclient.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Query("select m from Manager m join fetch m.clients where m.id =:id")
    Manager getWithClients(Integer id);

    @Modifying
    @Query(value = "UPDATE manager SET alternate_id = null WHERE alternate_id =:id", nativeQuery = true)
    void deleteAlternateId(Integer id);
/**
 * замена на текущего заместителя для всех записей у которых удвляемый менеджер был заместителем
 */
//    @Modifying
//    @Query("update Manager m set m.alternate=:alternateManager where m.alternate.id=:id")
//    void updateAlternate(Integer id, Manager alternateManager);
}
