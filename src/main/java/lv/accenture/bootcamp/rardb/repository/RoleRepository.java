package lv.accenture.bootcamp.rardb.repository;


import lv.accenture.bootcamp.rardb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    lv.accenture.bootcamp.rardb.model.Role findByRole(String role);

}