package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ExternalAccountProvider;
import com.mycompany.myapp.domain.User;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, String> {
    
    @Query("select u from User u where u.activationKey = ?1")
    User getUserByActivationKey(String activationKey);
    
    @Query("select u from User u where u.activated = false and u.createdDate > ?1")
    List<User> findNotActivatedUsersByCreationDateBefore(DateTime dateTime);

    @Query("select u from User u inner join u.externalAccounts ea where ea.externalProvider = ?1 and ea.externalId = ?2")
    User getUserByExternalAccount(ExternalAccountProvider provider, String externalAccountId);
}
