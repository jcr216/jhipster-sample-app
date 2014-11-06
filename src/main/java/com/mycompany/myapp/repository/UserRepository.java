package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ExternalAccountProvider;
import com.mycompany.myapp.domain.User;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Spring Data MongoDB repository for the User entity.
 */
public interface UserRepository extends MongoRepository<User, String> {
    
    @Query("{activationKey: ?0}")
    User getUserByActivationKey(String activationKey);
    
    @Query("{activation_key: 'false', createdDate: {$gt: ?0}}")
    List<User> findNotActivatedUsersByCreationDateBefore(DateTime dateTime);

    @Query("{externalAccounts: { $in: [ {externalProvider: ?0, externalId: ?1} ]}}")
    User getUserByExternalAccount(ExternalAccountProvider provider, String externalAccountId);
}
