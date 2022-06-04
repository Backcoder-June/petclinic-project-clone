package petclinicclone.owner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnerRepository extends Repository<Owner, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    List<PetType> findPetTypes();                    // petType 을 왜 find 하지? 필요해?



    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT owner FROM Owner owner left join owner.pets WHERE owner.lastName LIKE : lastName% ")
    Page<Owner> findByLastName(@Param("lastName") String lastName, Pageable pageable);



    @Transactional(readOnly = true)
    @Query("SELECET owner FROM Owner owner left join fetch owner.pets WHERE owner.id =: id ")
    Owner findById(@Param("id") Integer id);




    void save(Owner owner);                 // 이거 dataType Owner 아니여도 돼?



    @Transactional(readOnly = true)
    @Query("SELECT owner FROM Owner owner")
    Page<Owner> findAll(Pageable pageable);




}
