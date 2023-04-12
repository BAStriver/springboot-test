package org.bas.repository;

import org.bas.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query(nativeQuery = true, value = "select * from db_account where username = :text or email = :text")
    Account getAccountByUsernameOrEmail(String text);

}
