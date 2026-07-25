package sn.isi.serviceclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.serviceclient.entity.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
