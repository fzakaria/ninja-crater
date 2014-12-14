/**
 * Copyright (C) 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package services;

import static com.google.common.base.Preconditions.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
import com.google.inject.persist.Transactional;
import models.User;
import ninja.jpa.UnitOfWork;
import com.google.inject.Inject;
import com.google.inject.Provider;
import requests.UserRegisterRequest;

public class UserService {

    private final Provider<EntityManager> entityManagerProvider;

    /***
     * The Guice-Persist docs mention that if your service is a @Singleton
     * you should always be using Provider<T>
     * @param entityManagerProvider
     */
    @Inject
    public UserService(Provider<EntityManager> entityManagerProvider){
        this.entityManagerProvider = entityManagerProvider;
    }

    @Transactional
    public User create(UserRegisterRequest request) {
        EntityManager entityManager = entityManagerProvider.get();
        User newUser = new User();
        newUser.username = request.username;
        newUser.email = request.username;
        newUser.password = BCrypt.hashpw(request.password, BCrypt.gensalt());
        newUser.firstName = request.firstName;
        newUser.lastName = request.lastName;
        entityManager.persist(newUser);
        return newUser;
    }

    /***
     * Returns either a User object that is found or throws Exception
     * @return User the user object being found
     * @throws javax.persistence.NoResultException if there is no result
     * @throws javax.persistence.NonUniqueResultException if more than one result
     * @throws javax.persistence.QueryTimeoutException if the query execution exceeds
     * is rolled back
     */
    @UnitOfWork
    public User findByUsername(String username) {
        checkNotNull(username);
        EntityManager entityManager = entityManagerProvider.get();
        TypedQuery<User> q = entityManager.createQuery("FROM User u WHERE u.username = :username", User.class);
        q.setParameter("username", username);
        return q.getSingleResult();
    }


}
